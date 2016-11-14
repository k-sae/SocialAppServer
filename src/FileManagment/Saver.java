package FileManagment;

import SocialAppServer.*;

/**
 * Created by begad on 10/30/2016.
 */
public class Saver {
    //TODO #hazem
    //Replace file names with constants
    public static boolean SaveTemp(RegisterUser RUser) {
        FilesManger.CreateFolder("Temp");

        if (!FilesManger.FileIsExist("Registered Users\\Register Requests.txt"))
        {
            FilesManger.OpenToWrite("Registered Users","Register Requests.txt");
        }

        String Id = Generator.GenerateID(RUser.getLoginInfo().getEMAIL());

        if (FilesManger.FileIsExist("Registered Users\\"+Id)) {
            return false;
        } else {
            //Create User Folder and his Info File
            FilesManger.CreateFolder("Registered Users", Id);
            FilesManger.OpenToWrite(("Registered Users\\"+Id),"Info.txt");

            //Form UserInfo on a Formed InfoLine
            FormedLine InfoLine = new FormedLine();
            InfoLine.AddPartition("FullName",RUser.getUserInfo().getFullName());
            InfoLine.AddPartition("BirthDate",RUser.getUserInfo().getBirthDate());
            InfoLine.AddPartition("Gender",RUser.getUserInfo().getGender());

            //Writ The Formed InfoLine on The User Info File
            FilesManger.AddLine(("Registered Users\\"+Id+"\\Info.txt"),InfoLine.getLine());

            //write User Login Info on a Formed Line
            FormedLine LoginInfoLine = new FormedLine();
            LoginInfoLine.AddPartition("Email",RUser.getLoginInfo().getEMAIL());
            LoginInfoLine.AddPartition("Password",RUser.getLoginInfo().getPassword());

            //Writ The Formed LoginInfoLine on The User Info File
            FilesManger.AddLine("Registered Users\\Register Requests.txt",LoginInfoLine.getLine());

            return true;
        }
    }
}

