package FileManagment;

import SocialAppGeneral.FormedLine;
import SocialAppServer.Generator;
import SocialAppServer.RegisterUser;

import java.nio.file.Files;

/**
 * Created by begad on 10/30/2016.
 */
public class Saver implements FilesPath{
    //TODO #hazem
    //Replace file names with constants
    //rewrite saveTemp function
            // 1-here u have to save all info for the user in order to retrieve it whenever admin approve
            // 2-choose whether to generate id here or after admin approval
            // 3-choose whatever file structure u want its up to u
            // 4- we will choose whether u work deserve or just u have to reimplement it HF :)
    //hint: u can use begad's implementation for saveTemp as hint or u can just ignore
public Saver(){
    // hna hcheck
    if(FilesManager.FileIsExist(UNREIGESTERDUSERS)){
        System.out.println("i am here");
    }else{
        System.out.println("fuck off");
    }
}
}

