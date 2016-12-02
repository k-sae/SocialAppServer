package SocialAppGeneral;

import FileManagment.FilesManager;
import FileManagment.FilesPath;

/**
 * Created by mosta on 02-Dec-16.
 */
public class UserPicker implements FilesPath{
    public Object pickUserInfo(String ID){
        UserInfo USer=new UserInfo();
        String Line=FilesManager.ReadLine(USERS+ID+"\\"+INFO+".txt",1);
       USer= USer.fromJsonString(Line);
return USer;
    }
}
