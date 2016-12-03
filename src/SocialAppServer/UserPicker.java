package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.UserInfo;

/**
 * Created by mosta on 02-Dec-16.
 */
class UserPicker implements FilesPath{
    static UserInfo pickUserInfo(String ID){
        String Line=FilesManager.ReadLine(USERS+ID+"\\"+INFO+".txt",1);
        return UserInfo.fromJsonString(Line);
    }
}
