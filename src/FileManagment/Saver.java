package FileManagment;

import SocialAppGeneral.*;
import SocialAppServer.Generator;
import SocialAppServer.HalfDuplexConnection;
import SocialAppServer.RegisterUser;
import SocialAppServer.Verifier;

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
public Saver(RegisterInfo reg, HalfDuplexConnection connection) {
    // hna hcheck
    String Id = Generator.GenerateID(reg.getLoginInfo().getEMAIL());
    if (!FilesManager.FileIsExist(UNREIGESTERDUSERS)) {
        FilesManager.CreateFolder(UNREIGESTERDUSERS);
    }
    if (!FilesManager.FileIsExist(USERS)) {
        FilesManager.CreateFolder(USERS);
    }
    if (!FilesManager.FileIsExist(BLOCKEDUSERS)) {
        FilesManager.CreateFolder(BLOCKEDUSERS);
    }
    Command command = new Command();
    command.setKeyWord(RegisterInfo.KEYWORD);
    if (FilesManager.FileIsExist(UNREIGESTERDUSERS, Id + ".txt")) {
        if(Verifier.valdaidatereginfo(UNREIGESTERDUSERS + Id + ".txt", reg.getLoginInfo().getEMAIL()) || Verifier.valdaidateLoginInfo(USERS + EMAILS + Id + ".txt", reg.getLoginInfo().getEMAIL())||FilesManager. ReadBanned(BLOCKEDUSERS+Id+".txt",reg.getLoginInfo().getEMAIL())){
            command.setSharableObject("false");
            connection.sendCommand(command);
    }else{
        FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
            FilesManager.AddLine(UNREIGESTERDUSERS+AllUSERS,reg.getLoginInfo().getEMAIL());
            command.setSharableObject("true");
            connection.sendCommand(command);
    }
}
    else{
        FilesManager.AddLine(UNREIGESTERDUSERS+"\\"+Id+".txt",reg.convertToJsonString());
            FilesManager.AddLine(UNREIGESTERDUSERS+AllUSERS,reg.getLoginInfo().getEMAIL());
        command.setSharableObject("true");
        connection.sendCommand(command);
    }
    }

}


