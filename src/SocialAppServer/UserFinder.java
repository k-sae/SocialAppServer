package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Command;
import SocialAppGeneral.RegisterInfo;
import SocialAppServer.Generator;
import SocialAppServer.HalfDuplexConnection;

import java.util.Objects;

/**
 * Created by mosta on 30-Nov-16.
 */
public class UserFinder implements FilesPath {
public String findBy(String Token, String SearchedObject){
String Id="0";
    if(Objects.equals(Token, "email")){
        String Line=FilesManager.FileSearcher(USERS+EMAILS+ Generator.GenerateID(SearchedObject)+".txt",SearchedObject);
Id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
return Id;
    }
    if(Objects.equals(Token, "name")){
        //array list of names
        String Line=FilesManager.FileSearcher(USERS+NAMES+ Generator.GenerateID(SearchedObject)+".txt",SearchedObject);
        Id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        return Id;
    }
    return null;
}
public void Userfound(String email, String pass, HalfDuplexConnection connection){
    Command command = new Command();
    command.setKeyWord(RegisterInfo.KEYWORD);
    String id="-1";
    if(FilesManager.StringFinder(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email)&&FilesManager.StringFinder(USERS+EMAILS+ Generator.GenerateID(email)+".txt",pass)){
        String Line=FilesManager.FileSearcher(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email);
        id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        command.setSharableObject(id);
        connection.sendCommand(command);
    }
    else{
        command.setSharableObject(id);
        connection.sendCommand(command);
    }

}
}
