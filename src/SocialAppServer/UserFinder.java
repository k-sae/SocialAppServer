package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Command;
import SocialAppGeneral.RegisterInfo;

import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by mosta on 30-Nov-16.
 */
class UserFinder implements FilesPath {
    //TODO
    //REwork Finder
    public String findBy(String Token, String SearchedObject){
String Id="0";
    if(Objects.equals(Token, "email")){
        String Line=FilesManager.FileSearcher(USERS+EMAILS+ Generator.GenerateID(SearchedObject)+".txt",SearchedObject);
        if (Line != null) {
            Id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        }
        return Id;
    }
    if(Objects.equals(Token, "name")){
        //array list of names
        String Line=FilesManager.FileSearcher(USERS+NAMES+ Generator.GenerateID(SearchedObject)+".txt",SearchedObject);
        if (Line != null) {
            Id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        }
        return Id;
    }
    return null;
}
public static String  validate(String email, String pass){
    Command command = new Command();
    command.setKeyWord(RegisterInfo.KEYWORD);
    String id="-1";
    if(FilesManager.LoginValidatior(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email,pass)){
        String Line=FilesManager.FileSearcherForID(USERS+EMAILS+ Generator.GenerateID(email)+".txt",email);
        if (Line != null) {
            id=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        }

    }
    return id;
}
//takes email or name
public ArrayList<String> Search(String email){
    ArrayList<String> a=new ArrayList<String>();
    ArrayList<String> b=new ArrayList<String>();
    ArrayList<String> finalsearch=new ArrayList<String>();
    a=FilesManager.ReadIntoArrayList(USERS+EMAILS+Generator.GenerateID(email)+".txt");
    b=FilesManager.ReadIntoArrayList(USERS+NAMES+Generator.GenerateID(email)+".txt");
    finalsearch.addAll(a);
    finalsearch.addAll(b);
return finalsearch;
}
}
