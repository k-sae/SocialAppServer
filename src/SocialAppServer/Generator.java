package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;

import java.io.File;

/**
 * Created by begad on 10/30/2016.
 */
public class Generator implements FilesPath {
    public static String GenerateID(String email) {
        email = email.toLowerCase();
        String modified = "";
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) < 97 || email.charAt(i) > 122) {

            } else {
                modified += (email.charAt(i));
            }
        }
        if (modified.length() > 9)
            modified = modified.substring(0, 9);
        String ID = "";
        for (int i = 0; i < modified.length(); i++)
            ID += modified.charAt(i) - 97;
        //try another ways
        String mod = "00";
        //String modf="";
        if (ID.length() >= 3) {
            ID = ID.substring(0, ID.length() - 2);
            ID = ID + mod;
        } else {
            ID = "0";
        }
        return ID;
    }

    public static String GenerateUnigueId(String path) {
        String uniqueID = "1";
        int uniqueidtemp = 0;
        if (FilesManager.FileIsExist(path + "\\uniqueID" + ".txt")) {
            uniqueID=(FilesManager.ReadLine(path+"\\uniqueID.txt",1));
            uniqueidtemp = Integer.parseInt(uniqueID);
            uniqueidtemp++;
            uniqueID = Integer.toString(uniqueidtemp);
            FilesManager.AddLineWithoutAppend(path + "\\uniqueID" + ".txt", uniqueID);
            return uniqueID;
        } else {
            FilesManager.OpenToWrite(path + "\\uniqueID" + ".txt");
            FilesManager.AddLineWithoutAppend(path + "\\uniqueID" + ".txt", uniqueID);
            return uniqueID;
        }
    }
}