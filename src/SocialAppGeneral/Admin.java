package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppServer.Generator;

/**
 * Created by mosta on 27-Nov-16.
 */
public class Admin extends LoggedUser implements FilesPath{
    public void convertIntoPermnantUser(String Email) {

        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
        String ID = Generator.GenerateUnigueId(USERS);
        FilesManager.CreateFolder(USERS + ID);
   FilesManager.CreateFile(USERS+ID+"\\"+INFO);
        //     FilesManager.OpenToWrite(USERS + ID+"\\",INFO+".txt");
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



       // if (FilesManager.FileIsExist(USERS + NAMES + Generator.GenerateID())) {

            // /FilesManager.OpenToReade(UNREIGESTERDUSERS+ID+".txt");
           // if(FilesManager.ReadLine(UNREIGESTERDUSERS+ID+".txt","\"email\":\""+Email+"\",")==true){
             //   Command EMAILUSED = new Command();
               // connection.startConnection();
      //      }else{
                // FilesManager.OpenToWrite(UNREIGESTERDUSERS+"\\"+Id+".txt");
                //FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
      //          FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+ID+".txt",Name);
      //  }
    }
}
