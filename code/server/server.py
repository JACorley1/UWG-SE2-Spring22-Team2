import zmq # type: ignore
import time, json
from credentials_manager.base import CredentialsManager
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
    
    def handleRequest(self, request: MutableMapping[str, Any]) -> MutableMapping[str, Any]:
        response: MutableMapping[str, Any]
        if ("requestType" not in request) :
            response = {"successCode": -1, "errorMessage": "Malformed Request, missing Request Type"}
        if (request["requestType"] == "getSystemNames") :
            response = self._getSystemNames()
        if (request["requestType"] == "login") :
            response = self._login(request["username"], request["password"])
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


