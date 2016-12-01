package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;

import javax.swing.text.StyledEditorKit;

/**
 * Created by mosta on 30-Nov-16.
 */
public class UserFinder implements FilesPath {
public String UserFind(String Token,String SearchedObject){
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
public Boolean Userfound(String email){

}

}
