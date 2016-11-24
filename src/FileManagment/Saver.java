package FileManagment;

import SocialAppGeneral.*;
import SocialAppServer.Generator;
import SocialAppServer.HalfDuplexConnection;
import SocialAppServer.RegisterUser;

import java.nio.file.Files;

/**
 * Created by begad on 10/30/2016.
 */
public class Saver extends Thread implements FilesPath{
    //TODO #hazem
    //Replace file names with constants
    //rewrite saveTemp function
            // 1-here u have to save all info for the user in order to retrieve it whenever admin approve
            // 2-choose whether to generate id here or after admin approval
            // 3-choose whatever file structure u want its up to u
            // 4- we will choose whether u work deserve or just u have to reimplement it HF :)
    //hint: u can use begad's implementation for saveTemp as hint or u can just ignore
public Saver(RegisterInfo reg, HalfDuplexConnection connection){
    // hna hcheck
    String Id=Generator.GenerateID(reg.getLoginInfo().getEMAIL());
    if(!FilesManager.FileIsExist(UNREIGESTERDUSERS)){
        FilesManager.CreateFolder(UNREIGESTERDUSERS);
    }
    if(!FilesManager.FileIsExist(USERS)){
        FilesManager.CreateFolder(USERS);
    }
    if(!FilesManager.FileIsExist(BLOCKEDUSERS)){
        FilesManager.CreateFolder(BLOCKEDUSERS);
    }
    if(FilesManager.FileIsExist(UNREIGESTERDUSERS,Id+".txt")){
FilesManager.OpenToReade(UNREIGESTERDUSERS+"\\"+Id+".txt");
if(FilesManager.ReadLine(UNREIGESTERDUSERS+"\\"+Id+".txt","\"email\":\""+reg.getLoginInfo().getEMAIL()+"\",")==true){
    Command EMAILUSED = new Command();
    connection.sendCommand(EMAILUSED);
}else{
   // FilesManager.OpenToWrite(UNREIGESTERDUSERS+"\\"+Id+".txt");
    //FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
    FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
}
    }
    else{
     // FilesManager.CreateFile(UNREIGESTERDUSERS,Id+".txt");
       FilesManager.OpenToWrite(UNREIGESTERDUSERS+"\\"+Id+".txt");
        FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
    }
  //  FilesManager.OpenToWrite(UNREIGESTERDUSERS,Id+".txt");
    //FilesManager.WriteOnTop(UNREIGESTERDUSERS+"\\"+Generator.GenerateID(reg.getLoginInfo().getEMAIL())+".txt",reg.convertToJsonString());


}

}

