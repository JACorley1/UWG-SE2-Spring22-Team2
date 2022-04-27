from hashlib import new
from json import JSONEncoder
import json
from msilib.schema import Class
import random
import typing

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
            if user.username == username :
                return user


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
        self.sharedImages = []

    def addImage(self, newImage) :
        self.images.append(newImage)

    def addsharedImage(self, imageId) :
        self.sharedImages.append(imageId)

    def _deleteImage(self, imageId) -> bool :
        imageId = int(imageId)
        for image in self.images:
            if image.imageId == imageId :
                self.images.remove(image)
                return True
        
        return False
    
    def _deleteSharedImage(self, imageId) -> bool :
        imageId = int(imageId)
        for image in self.sharedImages:
            if image.imageId == imageId :
                self.images.remove(image)
                return True
        
        return False


class UserEncoder (JSONEncoder) :

    def default(self, object):
        if isinstance(object, User):
            return object.__dict__
        
        else: 
            return json.JSONEncoder.default(self,object)