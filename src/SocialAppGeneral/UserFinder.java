package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;

/**
 * Created by mosta on 30-Nov-16.
 */
public class UserFinder implements FilesPath {
public String UserFinder(String Token,String SearchedObject){
String Id="0";
    if(Token=="email"){
        String Line=FilesManager.FileSearcher(USERS+EMAILS+ Generator.GenerateID(SearchedObject),SearchedObject);
Id=Line.substring('[',']');
return Id;
    }
    if(Token=="name"){
        //array list of names
        String Line=FilesManager.FileSearcher(USERS+NAMES+ Generator.GenerateID(SearchedObject),SearchedObject);
        Id=Line.substring('[',']');
        return Id;
    }
    return null;
}
}
