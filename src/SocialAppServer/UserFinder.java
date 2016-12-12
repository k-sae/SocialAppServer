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
    ArrayList<String> a=new ArrayList();
    ArrayList<String> b=new ArrayList();
    ArrayList<String> finalsearch=new ArrayList();
    a=FilesManager.ReadIntoArrayList(USERS+EMAILS+Generator.GenerateID(email)+".txt");
    b=FilesManager.ReadIntoArrayList(USERS+NAMES+Generator.GenerateID(email)+".txt");
    finalsearch.addAll(a);
    finalsearch.addAll(b);
return finalsearch;
}
}
