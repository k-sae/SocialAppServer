package FilesManeger;

import SocialAppServer.*;

/**
 * Created by begad on 10/30/2016.
 */
public class Saver {

    public static boolean SaveTemp(RegisterUser RUser) {
        Files.CreateFolder("Registered Users");

        if (!Files.FileIsExist("Registered Users\\Register Requests.txt"))
        {
            Files.OpenToWrite("Registered Users","Register Requests.txt");
        }

        String Id = Generator.GenerateID(RUser.getLoginInfo().getEmail());

        if (Files.FileIsExist("Registered Users\\"+Id)) {
            return false;
        } else {
            //Create User Folder and his Info File
            Files.CreateFolder("Registered Users", Id);
            Files.OpenToWrite(("Registered Users\\"+Id),"Info.txt");

            //Form UserInfo on a Formed InfoLine
            FormedLine InfoLine = new FormedLine();
            InfoLine.AddPartition("FullName",RUser.getUserInfo().getFullName());
            InfoLine.AddPartition("BirthDate",RUser.getUserInfo().getBirthDate());
            InfoLine.AddPartition("Gender",RUser.getUserInfo().getGender());

            //Writ The Formed InfoLine on The User Info File
            Files.AddLine(("Registered Users\\"+Id+"\\Info.txt"),InfoLine.getLine());

            //write User Login Info on a Formed Line
            FormedLine LoginInfoLine = new FormedLine();
            LoginInfoLine.AddPartition("Email",RUser.getLoginInfo().getEmail());
            LoginInfoLine.AddPartition("Password",RUser.getLoginInfo().getPassword());

            //Writ The Formed LoginInfoLine on The User Info File
            Files.AddLine("Registered Users\\Register Requests.txt",LoginInfoLine.getLine());

            return true;
        }
    }
}

