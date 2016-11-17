package SocialAppServer;

import FileManagment.FilesManger;
import FileManagment.FilesPath;
import SocialAppGeneral.GroupInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/13/2016.
 */
public class Group implements FilesPath{
    private GroupInfo info;
    private ArrayList<Integer> postId;
    private  ArrayList<Integer> memberId;
    public Group(String name){
        info=new GroupInfo();
        info.setGroupname(name);
        info.setGroupid(1);
        info.setImageid(0);
        info.setPinpost(false);
    }
    public void creat(){
          FilesManger.CreateFolder(GROUPS);
        FilesManger.CreateFolder(GROUPS,String.valueOf(info.getGroupid()));
    }


}
