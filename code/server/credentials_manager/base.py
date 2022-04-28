from hashlib import new
from json import JSONEncoder
import json
from msilib.schema import Class
import random


from pip import List

''' Manages the set of system credentials for a single user.

 @author CS3212
 @version Spring 2022
'''
class UserManager:
    ''' Create a new User manager with one admin user
    
     @precondition none
     @postcondition no systems exist
    '''
    def __init__(self):
        keys = ["infinity"]
        adminUser = User("infinity","gauntlet")
        self.currentUser = adminUser
        self._allLogins = [adminUser]

    def addUser(self, username: str, password: str) -> bool:
        if (self.userExists(username) is False):
            newUser = User(username, password)
            self._allLogins.append(newUser)
            return True
        else :
            return False
        
    ''' checks if a username password combo exists in the _allLogins dictionary
    
     @precondition none
     @postcondition none
    '''    
    def userExists(self, username: str) -> bool:
        for user in self._allLogins:
            if user.username == username :
                return True
        return False
        
    
    ''' Retrieves a list of the Users within the system
     
     @precondition none
     @postcondition none
     
     @return list of the Users in the system
    '''
    def getUsers(self):
        return self._allLogins
    
    def getUser(self, username):
        for user in self._allLogins:
            if (user.username.lower() == username.lower()):
                return user
    
    def deleteImage(self,imageId : int) :
        usersSharedWith = self.currentUser._deleteImage(imageId)
        if (usersSharedWith != None) :
            for user in usersSharedWith:
                user._deleteSharedImage(self.currentUser, imageId)



class Image:
    
    def __init__(self, name, imageBytes, imageVisibility):
        self.name = name
        self.imageBytes = imageBytes
        self.imageId = random.randint(0,1000)
        self.imageVisibility = imageVisibility
        self.isSharedWith = []
  
class ImageEncoder (JSONEncoder) :

    def default(self, object):
        if isinstance(object, Image):
            return object.__dict__
        
        else: 
            return json.JSONEncoder.default(self,object)

class User:
    def __init__(self, username, password):
        self.username = username
        self.password = password
        self.images = []
        self.sharedImages = dict()

    def addImage(self, newImage) :
        self.images.append(newImage)

    def addsharedImage(self, imageId, username) :
        if(self.sharedImages.get(username) == None):
            self.sharedImages.update({username: [imageId]})
        else:
            self.sharedImages.get(username).append(imageId)

    def _deleteImage(self, imageId) :
        if (self.hasImage(imageId)) :
            image = self.getImage(imageId)
            sharedwithUsers = image.isSharedWith
            self.images.remove(image)
            return sharedwithUsers
        
        return None
    
    def _deleteSharedImage(self, username, imageId) -> bool :
        if (self.sharedImages.get(username) != None) :
            self.sharedImages.get(username).remove(imageId)
            return True
        
        return False

    def hasImage(self,imageId : int) -> bool :
        for image in self.images:
            if (image.imageId == imageId) :
                return True
        return False
    
    def getImage(self, imageId : int):
        for image in self.images:
            if (int(image.imageId) == imageId) :
                return image
        return None


class UserEncoder (JSONEncoder) :

    def default(self, object):
        if isinstance(object, User):
            return object.__dict__
        
        else: 
            return json.JSONEncoder.default(self,object)