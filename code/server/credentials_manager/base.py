from msilib.schema import Class
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
        values = [{"Username": "infinity", "Password": "gauntlet"}]
        self._allCredentials = dict( zip(keys,values) )

    def addSystem(self, username: str, password: str) -> bool:
        if (self.systemExists(username, password) is False):
            self._allCredentials.update({username: {"Username": username, "Password": password}})
            return True
        else :
            return False
        
    ''' checks if a usernam password combo exists in the allCredentials dictionary
    
     @precondition none
     @postcondition none
    '''    
    def systemExists(self, username: str, password: str) -> bool:
         
        if(username in self._allCredentials) :
            return ((username,{"Username": username, "Password": password}) in self._allCredentials.items())  
        
        else :
            return False
        
    
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
    
    def __init__(self, name, imageBytes ):
        self.name = name
        self.imageBytes = imageBytes
