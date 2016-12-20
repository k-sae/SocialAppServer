package SocialAppServer.Module;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.UserInfo;

/**
 * Created by mosta on 02-Dec-16.
 */
public class UserPicker implements FilesPath{
    public static UserInfo pickUserInfo(String ID){
        String Line=FilesManager.ReadLine(USERS+ID+"\\"+INFO,1);
        return UserInfo.fromJsonString(Line);
    }

}
