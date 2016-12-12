package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.ArraylistGroup;
import SocialAppGeneral.Group;
import SocialAppGeneral.SocialArrayList;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/25/2016.
 */
class GroupfileMangement implements FilesPath{

    private static final String REQ = "\\req";
    private static final String INFO= "\\info";
    private static final String GROUP= "\\groupId";




    public static String getREQ() {
        return REQ;
    }

    public static String getINFO() {
        return INFO;
    }

    static void create (Group group) {
        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+INFO);
        FilesManager.AddLine(FilesPath.USERS+FilesPath.NAMES+Generator.GenerateID(group.getName()+".txt"),group.getName()+"&&&ID=["+group.getId()+"]");
    }
    static ArraylistGroup pickGroups(ArrayList<ArraylistGroup> id) {
        ArraylistGroup groups = new ArraylistGroup();
        for (int i = 0; i < id.size(); i++) {
            if (FilesManager.FileIsExist(FilesPath.GROUPS + "\\" + id.get(i))) {
                groups.getGroups().add((Group) FilesManager.ReadFromBinaryFile(FilesPath.GROUPS + "\\" + id.get(i) +INFO));

            }


        }
        return groups;
    }
    static ArraylistGroup pickMemberGroup(long id ){
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+id+GROUP)) {
            return (ArraylistGroup) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id + GROUP);
        }
        else return null;
    }
    static  void addgrouptomember(long id){
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+id+GROUP)) {
            ArraylistGroup groups = (ArraylistGroup) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id + GROUP);


        }
    }
    static void addMembertogroup(long id){
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+id+GROUP)) {
            ArraylistGroup groups = (ArraylistGroup) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id + GROUP);


        }
    }

}
