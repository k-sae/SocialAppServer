package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;
import SocialAppServer.ServerLoggedUser;

/**
 * Created by mosta on 27-Nov-16.
 */
public class Admin extends ServerLoggedUser implements FilesPath{
    public Admin(String id) {
        super(id);
    }

    public void convertIntoPermnantUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
        String ID = Generator.GenerateUnigueId(USERS);
        FilesManager.CreateFolder(USERS + ID);
   FilesManager.CreateFile(USERS+ID+"\\"+INFO+".txt");
        FilesManager.AddLineWithoutAppend(USERS + ID+"\\"+INFO+".txt",re.getUserInfo().convertToJsonString());
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
    public void convertIntoBannedUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
        String ID = Generator.GenerateUnigueId(BLOCKEDUSERS);
        FilesManager.AddLine(BLOCKEDUSERS+EMAILS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().getEMAIL());
        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
    }
}
