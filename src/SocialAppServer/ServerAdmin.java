package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Admin;
import SocialAppGeneral.RegisterInfo;
import SocialAppServer.Generator;
import SocialAppServer.ServerLoggedUser;

import java.util.ArrayList;

/**
 * Created by mosta on 12-Dec-16.
 */
public class ServerAdmin extends ServerLoggedUser implements FilesPath , Admin {
    public ServerAdmin(String id) {
        super(id);
    }
    public static Boolean adminChecker(String ID){
        return FilesManager.searcher(USERS+ADMINS,ID);
    }
    public static boolean adminCheck(String Email){
        if(FilesManager.FileIsExist(USERS+ADMINS)){
            //wait admin approval to add another admin
            return false;
        }else{
            return true;
        }
    }

    public  static void  convertIntoPermnantUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
        String ID = Generator.GenerateUnigueId(USERS);
        FilesManager.CreateFolder(USERS + ID);
        FilesManager.CreateFile(USERS+ID+"\\"+INFO);
        FilesManager.AddLineWithoutAppend(USERS + ID+"\\"+INFO,re.getUserInfo().convertToJsonString());
        if(FilesManager.FileIsExist(USERS+EMAILS)){
            FilesManager.AddLine(USERS+EMAILS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().convertToJsonString()+"ID=["+ID+"]");
        }else{
            FilesManager.CreateFolder(USERS+EMAILS);
            FilesManager.AddLine(USERS+EMAILS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().convertToJsonString()+"ID=["+ID+"]");
        }
        if(FilesManager.FileIsExist(USERS+NAMES)){
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"ID=["+ID+"]");
        }else{
            FilesManager.CreateFolder(USERS+NAMES);
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"ID=["+ID+"]");
        }

        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
    }
    //Check if needing modification
    public void convertIntoBannedUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
       // String ID = Generator.GenerateUnigueId(BLOCKEDUSERS);
        FilesManager.AddLine(BLOCKEDUSERS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().getEMAIL());
        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
    }
   public ArrayList<String> fetchRequests(){
       ArrayList<String> strings = new ArrayList<>();
       strings = FilesManager.ReadArrayList(UNREIGESTERDUSERS+AllUSERS);
       return strings;
   }
    @Override
    public void approve(String Email) {
        convertIntoPermnantUser(Email);
    }

    @Override
    public void reject(String Email) {
        convertIntoBannedUser(Email);
    }

    @Override
    public void approveAsAdmin(String Email) {
        convertIntoPermnantUser(Email);
        String Line=FilesManager.FileSearcherForID(USERS+EMAILS+ Generator.GenerateID(Email)+".txt",Email);
        Line=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        FilesManager.AddLine(USERS+ADMINS,Line);
    }
}
