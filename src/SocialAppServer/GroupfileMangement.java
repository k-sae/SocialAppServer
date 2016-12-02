package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import java.awt.*;
import java.io.*;

/**
 * Created by khaled hesham on 11/25/2016.
 */
class GroupfileMangement implements FilesPath{
    private static final String IMAGE = "\\image";
    private static final String REQ = "\\req";
    private static final String INFO= "\\info";



    public static String getIMAGE() {
        return IMAGE;
    }

    public static String getREQ() {
        return REQ;
    }

    public static String getINFO() {
        return INFO;
    }

    void create(Group group) {

        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.OpenToWrite(GROUPS+group.getId(),IMAGE);
        FilesManager.OpenToWrite(GROUPS+group.getId(),REQ);
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+INFO);
        FilesManager.CreateFolder(GROUPS,NAMES);
        FilesManager.AddLine(GROUPS+NAMES+(Integer.parseInt(Generator.GenerateID(group.getName()))),group.getName());

    }
    /*public Group pickGroup(int id) throws IOException, ClassNotFoundException {
        Group group=new Group(id);
        FilesManager.ReadFromBinaryFile(group,GROUPS+id+INFO);
        return  group;
    }
    */
}
