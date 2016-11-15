package FileManagment;

import SocialAppGeneral.FormedLine;
import SocialAppServer.Generator;
import SocialAppServer.RegisterUser;

/**
 * Created by begad on 10/30/2016.
 */
public class Saver {
    //TODO #hazem
    //Replace file names with constants
    //rewrite saveTemp function
            // 1-here u have to save all info for the user in order to retrieve it whenever admin approve
            // 2-choose whether to generate id here or after admin approval
            // 3-choose whatever file structure u want its up to u
            // 4- we will choose whether u work deserve or just u have to reimplement it HF :)
    //hint: u can use begad's implementation for saveTemp as hint or u can just ignore
    public static boolean saveTemp(RegisterUser RUser) {
        FilesManager.CreateFolder("Temp");

        if (!FilesManager.FileIsExist("Registered Users\\Register Requests.txt"))
        {
            FilesManager.OpenToWrite("Registered Users","Register Requests.txt");
        }
        String Id = Generator.GenerateID(RUser.getLoginInfo().getEmail());

        if (FilesManager.FileIsExist("Registered Users\\"+Id)) {
            return false;
        } else {
            //Create User Folder and his Info File
            FilesManager.CreateFolder("Registered Users", Id);
            FilesManager.OpenToWrite(("Registered Users\\"+Id),"Info.txt");

            //Form UserInfo on a Formed InfoLine
            FormedLine InfoLine = new FormedLine();
            InfoLine.AddPartition("FullName",RUser.getUserInfo().getFullName());
            InfoLine.AddPartition("BirthDate",RUser.getUserInfo().getBirthDate());
            InfoLine.AddPartition("Gender",RUser.getUserInfo().getGender());

            //Writ The Formed InfoLine on The User Info File
            FilesManager.AddLine(("Registered Users\\"+Id+"\\Info.txt"),InfoLine.getLine());

            //write User Login Info on a Formed Line
            FormedLine LoginInfoLine = new FormedLine();
            LoginInfoLine.AddPartition("Email",RUser.getLoginInfo().getEmail());
            LoginInfoLine.AddPartition("Password",RUser.getLoginInfo().getPassword());

            //Writ The Formed LoginInfoLine on The User Info File
            FilesManager.AddLine("Registered Users\\Register Requests.txt",LoginInfoLine.getLine());

            return true;
        }
    }
}

