from asyncio.windows_events import NULL
from urllib import response
import zmq # type: ignore
import time, json
from credentials_manager.base import CredentialsManager, ImageEncoder
from credentials_manager.base import Image , User , UserEncoder
from typing import MutableMapping, Any

''' Handles server requests and returns appropriately formatted responses

 @author CS3212
 @version Spring 2022
'''
class _RequestHandler:
    _credentialsManager: CredentialsManager
    ''' Creates a new RequestHandler with the provided CredentialsManager
     
     @precondition credentialsManager != None && isinstance(credentialsManager, CredentialsManager)
     @postcondition RequestHandler has appropriate CredentialsManager to server requests
    '''
    def __init__(self, credentialsManager: CredentialsManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, CredentialsManager)):
            raise Exception("Must provide a subtype of CredentialsManager")
        self._credentialsManager = credentialsManager
        self.serverImages = []
        self.serverUsers = []
    
    ''' Returns a response for the getSystemNames request
     Format: comma separated list of all system names
     
     @precondition none
     @postcondition none
     
     @return response string using appropriate format (see description for details)
    '''
    def _getSystemNames(self) -> MutableMapping[str, Any]:
        systemNames = self._credentialsManager.getSystemNames()
        response = {"successCode": 1, "names": systemNames}
        return response
        
    ''' Returns a response for the getSystemNames request
     Format: comma separated list of all system names
     
     @precondition none
     @postcondition none
     
     @return response string using appropriate format (see description for details)
    '''
    def _login(self, usernameInput: str, passwordInput:str) -> MutableMapping[str, Any]:       
        if(self._credentialsManager.systemExists(usernameInput, passwordInput) is True) :
            response = {"successCode": 1}
        else :
            response = {"successCode": -1}
            
        return response
    
    def _createAccount(self, usernameInput: str, passwordInput:str) -> MutableMapping[str, Any]:       
        
        if(self._credentialsManager.systemExists(usernameInput, passwordInput) is True) :
            response = {"successCode": -1}
        else :
            self._credentialsManager.addSystem(usernameInput, passwordInput)
            response = {"successCode": 1}
            
        return response
    
    def _addImage(self, imageName: str, imageBytes:str, imageVisibility:str) -> MutableMapping[str,Any]:
        if (imageName == None or imageBytes == None) :
            response = {"successCode":-1}
        else :
            newImage = Image(imageName, imageBytes, imageVisibility)
            self.serverImages.append(newImage)
            response = {"successCode": 1}
        return response
    
    def _getImages(self) -> MutableMapping[str,Any]:
        encodedImages = []
        for img in self.serverImages :
           encodedImages.append(ImageEncoder().encode(img)) 
        response = {"successCode": 1, "images": json.dumps(encodedImages)}
        return response

    def _deleteImages(self, imageId) -> MutableMapping[str,Any]:
        imageToBeRemoved = None
        imageId = int(imageId)
        for image in self.serverImages:
            if image.imageId == imageId :
                imageToBeRemoved = image
                break

        if imageToBeRemoved != None :
            self.serverImages.remove(imageToBeRemoved)
            response = {"successCode": 1}
        else:
            response = {"successCode": -1}

        return response

    def _shareImages(self, imageId, username) -> MutableMapping[str,Any]:
        imageToBeShared = None
        imageId = int(imageId)
        for image in self.serverImages:
            if image.imageId == imageId :
                imageToBeShared = image
                break

        if imageToBeShared != None :
            imageToBeShared
            response = {"successCode": 1}
        else:
            response = {"successCode": -1}

        return response

        
    def handleRequest(self, request: MutableMapping[str, Any]) -> MutableMapping[str, Any]:
        response: MutableMapping[str, Any]
        if ("requestType" not in request) :
            response = {"successCode": -1, "errorMessage": "Malformed Request, missing Request Type"}
        elif (request["requestType"] == "getSystemNames") :
            response = self._getSystemNames()
        elif (request["requestType"] == "login") :
            response = self._login(request["username"], request["password"])
        elif (request["requestType"] == "createAccount") :
            response = self._createAccount(request["username"], request["password"])
        elif (request["requestType"] == "addImage"):
            response = self._addImage(request["imageName"], request["imageBytes"], request["imageVisibility"])
        elif (request["requestType"] == "getImages") :
            response = self._getImages()
        elif (request["requestType"] == "deleteImage",request["imageId"]) :
            response = self._deleteImages(request["imageId"])
        elif(request["requestType"] == "shareImage"):
            response = self._shareImage(request["imageId"], request["username"])
        else :
            errorMessage = "Unsupported Request Type ({requestType})".format(requestType = request['requestType'])
            response = {"successCode": -1, "errorMessage": errorMessage}
        return response

''' Handles server communication
 
 @author CS 3212
 @version Spring 2022
'''
class Server:
    ''' Launches server main loop using the provided CredentialManager
     
     @precondition credentialsManager != None && isinstance(credentialsManager, CredentialsManager)
     @postcondition none
    '''
    def run(self, credentialsManager: CredentialsManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, CredentialsManager)):
            raise Exception("Must provide a subtype of CredentialsManager")
        
        requestHandler = _RequestHandler(credentialsManager)
        context = zmq.Context()
        socket = context.socket(zmq.REP)
        socket.bind("tcp://127.0.0.1:5555")
        
        while True:
            #  Wait for next request from client
            print("waiting for message...")
            jsonRequest = socket.recv_string()
            request = json.loads(jsonRequest)
            jsonResponse: str

            if (request["requestType"] != "addImage"):
                print("Received request: %s" % request)
            
            if (request == "exit"):
                print("exit request works")
                return
            else :
                response = requestHandler.handleRequest(request)
                jsonResponse = json.dumps(response)
            
            #  Do some 'work'
            time.sleep(1)
        
            #  Send reply back to client
            socket.send_string(jsonResponse)
def main():
    sv = Server()
    sv.run(CredentialsManager())


if (__name__ == "__main__"):
    main()
########################################################################


