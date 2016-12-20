package SocialAppServer.Control;

import FileManagment.FilesManager;
import SocialAppGeneral.LoginInfo;
import SocialAppGeneral.RegisterInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by mosta on 20-Dec-16.
 */
public class Verifier {
    public static boolean valdaidatereginfo(String FileName, String token) {
        ArrayList<String> lines;
        RegisterInfo re;
        Gson gson = new Gson();
       lines= FilesManager.ReadArrayList(FileName);
        for (String line:lines) {
           // line=line.substring(line.indexOf('{'),line.indexOf('}')+1);
            if(!line.isEmpty()) {
                    re = gson.fromJson(line, RegisterInfo.class);
                    if (re.getLoginInfo().getEMAIL().equals(token)) {
                    return true;
                        }
                 }
         }
        return false;
    }
    public static boolean valdaidateLoginInfo(String FileName, String token) {
        ArrayList<String> lines;
        LoginInfo re;
        Gson gson = new Gson();
        lines= FilesManager.ReadArrayList(FileName);
        for (String line:lines) {
            line=line.substring(line.indexOf('{'),line.indexOf('}')+1);
            if(!line.isEmpty()) {
                re = gson.fromJson(line, LoginInfo.class);
                if (re.getEMAIL().equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }
    static String FileSearcher(String FileName, String token) {
        ArrayList<String> lines;
        RegisterInfo re;
        Gson gson = new Gson();
        lines= FilesManager.ReadArrayList(FileName);
        for (String line:lines) {
            // line=line.substring(line.indexOf('{'),line.indexOf('}')+1);
            if(!line.isEmpty()) {
                re = gson.fromJson(line, RegisterInfo.class);
                if (re.getLoginInfo().getEMAIL().equals(token)) {
                    return line;
                }
            }
        }
        return "";
    }
    public static String FileSearcherForID(String FileName, String token) {
        ArrayList<String> lines;
        lines= FilesManager.ReadArrayList(FileName);
        Gson gson = new Gson();
        LoginInfo re;
        String WLine;
        for (String line:lines) {
            WLine=line;
            line=line.substring(line.indexOf('{'),line.indexOf('}')+1);
            re = gson.fromJson(line,LoginInfo.class);
            if (re.getEMAIL().equals(token)) {
                return WLine;
            }
        }
        return "";
    }
    public static boolean LoginValidatior(String FileName, String mail, String pass) {
            ArrayList<String> lines;
            lines= FilesManager.ReadArrayList(FileName);
            Gson gson = new Gson();
            LoginInfo re;
            for (String line:lines) {
                line=line.substring(line.indexOf('{'),line.indexOf('}')+1);
                re = gson.fromJson(line,LoginInfo.class);
                if (re.getEMAIL().equals(mail) && re.getPassword().equals(pass)) {
                    return true;
                }
            }
            return false;

    }
}
