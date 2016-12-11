package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/25/2016.
 */
class GroupfileMangement implements FilesPath{

    private static final String REQ = "\\req";
    private static final String INFO= "\\info";





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
    public ArrayList<Group> pickGroups(ArrayList<Integer> id) {
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            if (FilesManager.FileIsExist(FilesPath.GROUPS + "\\" + id.get(i))) {
                groups.add((Group) FilesManager.ReadFromBinaryFile(FilesPath.GROUPS + "\\" + id.get(i) + INFO));
            }


        }
        return groups;
    }


}
