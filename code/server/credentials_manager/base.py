from json import JSONEncoder
import json
from msilib.schema import Class
import random 
import typing

''' Manages the set of system credentials for a single user.

 @author CS3212
 @version Spring 2022
'''
class CredentialsManager:
    ''' Create a new credential manager with no systems
    
     @precondition none
     @postcondition no systems exist
    '''
    def __init__(self):
        keys = ["infinity"]
        values = [{"Username": "infinity", "Password": "gauntlet", "userImages": []}]
        self._allCredentials = dict( zip(keys,values) )

    def addSystem(self, username: str, password: str) -> bool:
        if (self.systemExists(username, password) is False):
            self._allCredentials.update({username: {"Username": username, "Password": password, "userImages": []}})
            return True
        else :
            return False
        
    ''' checks if a usernam password combo exists in the allCredentials dictionary
    
     @precondition none
     @postcondition none
    '''    
    def systemExists(self, username: str, password: str) -> bool:
        
        if(username in self._allCredentials) :
            systemPassword =  self._allCredentials[username].get("Password")
            return systemPassword == password  
        
        else :
            return False
        
    def _addUserImage(self, imageName: str, imageBytes:str, username: str, password: str):
        if (self.systemExists(username, password) is False):
            return False
        else :
            if (imageName == None or imageBytes == None) :
                return False
            else :
                newImage = Image(imageName, imageBytes)
                self._allCredentials[username].get("userImages").append(newImage)
        return True
    ''' Retrieves a list of the names for all systems with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all systems with credentials in the password manager
    '''
    def getSystemNames(self) -> dict:
      
        return self._allCredentials
    
    ''' Return the password for a specified system
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition none
     
     @param systemName name of the system
     
     @return password of the system if getSystemNames().contains(systemName)
               null                   if !getSystemNames().contains(systemName)
    '''
    def getSystemPassword(self, systemName: str) -> str:
        raise NotImplementedError()


class Image:
    
    def __init__(self, name, imageBytes):
        self.name = name
        self.imageBytes = imageBytes
        self.imageId = random.randint(0,1000)

class User:
    def __init__(self, username, password, userImages):
        self.username = username
        self.password = password
        self.userImages = []
    
    def _addImage(self, imageName: str, imageBytes:str):
        if (imageName == None or imageBytes == None) :
            return False
        else :
            newImage = Image(imageName, imageBytes)
            self.userImages.append(newImage)
        return True
    
    def _getImages(self):
        encodedImages = []
        for img in self.userImages :
           encodedImages.append(ImageEncoder().encode(img)) 
        return encodedImages
        
    def _deleteImages(self, imageId):
        imageToBeRemoved = None
        imageId = int(imageId)
        for image in self.userImages:
            if image.imageId == imageId :
                imageToBeRemoved = image
                break

        if imageToBeRemoved != None :
            self.userImages.remove(imageToBeRemoved)
            response = True
        else:
            response = False

        return response    
class ImageEncoder (JSONEncoder) :

    def default(self, object):
        if isinstance(object, Image):
            return object.__dict__
        
        else: 
            return json.JSONEncoder.default(self,object)