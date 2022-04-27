from asyncio.windows_events import NULL
from urllib import response
import zmq # type: ignore
import time, json
from credentials_manager.base import UserManager, ImageEncoder
from credentials_manager.base import Image
from typing import MutableMapping, Any

''' Handles server requests and returns appropriately formatted responses

 @author CS3212
 @version Spring 2022
'''
class _RequestHandler:
    _userManager: UserManager
    ''' Creates a new RequestHandler with the provided CredentialsManager
     
     @precondition credentialsManager != None && isinstance(credentialsManager, CredentialsManager)
     @postcondition RequestHandler has appropriate CredentialsManager to server requests
    '''
    def __init__(self, credentialsManager: UserManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, UserManager)):
            raise Exception("Must provide a subtype of CredentialsManager")
        self._userManager = credentialsManager
        self.images = []
    
    ''' Returns a response for the getSystemNames request
     Format: comma separated list of all system names
     
     @precondition none
     @postcondition none
     
     @return response string using appropriate format (see description for details)
    '''
    def _getSystemNames(self) -> MutableMapping[str, Any]:
        systemNames = self._userManager.getUsers()
        response = {"successCode": 1, "names": systemNames}
        return response
        
    ''' Returns a response for the getSystemNames request
     Format: comma separated list of all system names
     
     @precondition none
     @postcondition none
     
     @return response string using appropriate format (see description for details)
    '''
    def _login(self, usernameInput: str, passwordInput:str) -> MutableMapping[str, Any]:       
        if(self._userManager.userExists(usernameInput) is True) :
            if(self._userManager.getUser(usernameInput).password == passwordInput):
                self._userManager.currentUser = self._userManager.getUser(usernameInput)
                response = {"successCode": 1}
            else :
                response = {"successCode": -1}
            
        return response
    
    def _createAccount(self, usernameInput: str, passwordInput:str) -> MutableMapping[str, Any]:       
        
        if(self._userManager.userExists(usernameInput) is True) :
            response = {"successCode": -1}
        else :
            self._userManager.addUser(usernameInput, passwordInput)
            response = {"successCode": 1}
            
        return response
    
    def _addImage(self, imageName: str, imageBytes:str, imageVisibility:str) -> MutableMapping[str,Any]:
        if (imageName == None or imageBytes == None) :
            response = {"successCode":-1}
        else :
            newImage = Image(imageName, imageBytes, imageVisibility)
            self._userManager.currentUser.addImage(newImage)
            response = {"successCode": 1}
        return response
    
    def _getPublicImages(self) -> MutableMapping[str,Any]:
        encodedImages = []
        for user in self._userManager._allLogins :
            for img in user.images:
                if (img.imageVisibility == "Public"):
                    encodedImages.append(ImageEncoder().encode(img)) 
            response = {"successCode": 1, "images": json.dumps(encodedImages)}
        return response
    
    def _getMyImages(self) -> MutableMapping[str,Any]:
        encodedImages = []
        for img in self._userManager.currentUser.images :
           encodedImages.append(ImageEncoder().encode(img)) 
        response = {"successCode": 1, "images": json.dumps(encodedImages)}
        return response

    def _getMySharedImages(self) -> MutableMapping[str,Any]:
        currentUser = self._userManager.currentUser
        encodedImages = []
        for username in currentUser.sharedImages.keys() :
            user = self._userManager.getUser(username)
            for imageId in currentUser.sharedImages.get(username):
                img = user.getImage(imageId)
                print(imageId)
                encodedImages.append(ImageEncoder().encode(img)) 
                
        response = {"successCode": 1, "images": json.dumps(encodedImages)}
        return response

    def _deleteImages(self, imageId) -> MutableMapping[str,Any]:
        if self._userManager.currentUser.hasImage(imageId) :
            self._userManager.deleteImage(imageId)
            response = {"successCode": 1}
        else:
            response = {"successCode": -1}

        return response

    def _shareImage(self, imageId, username) -> MutableMapping[str,Any]:
        self._userManager.getUser(username).addsharedImage(imageId, self._userManager.currentUser.name)
        for img in self._userManager.currentUser.images:
            if (img.imageId == imageId):
                img.isSharedWith.append(username)

        
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
        elif (request["requestType"] == "getPublicImages") :
            response = self._getPublicImages()
        elif (request["requestType"] == "getMyImages") :
            response = self._getMyImages()
        elif (request["requestType"] == "getMySharedImages") :
            response = self._getMySharedImages()
        elif (request["requestType"] == "deleteImage",request["imageId"]) :
            response = self._deleteImages(request["imageId"])
        elif (request["requestType"] == "shareImage",):
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
    def run(self, credentialsManager: UserManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, UserManager)):
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
    sv.run(UserManager())


if (__name__ == "__main__"):
    main()
########################################################################


