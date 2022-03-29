import typing

''' Manages the set of system credentials for a single user.

 @author CS3212
 @version Spring 2022
'''
class CredentialsManager:
    global allCredentials
    ''' Create a new credential manager with no systems
    
     @precondition none
     @postcondition no systems exist
    '''
    def __init__(self):
        global allCredentials
        keys = ["Admin"]
        values = [{"Username": "inifinity", "Password": "gauntlet"}]
        allCredentials = dict( zip(keys,values) )

    def addSystem(self, username: str, password: str) -> bool:
        global allCredentials
        allCredentials.update({username: {"Username": username, "Password": password}})
        return True
    
    ''' Retrieves a list of the names for all systems with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all systems with credentials in the password manager
    '''
    def getSystemNames(self) -> dict:
        global allCredentials
        return allCredentials
    
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

    
    
