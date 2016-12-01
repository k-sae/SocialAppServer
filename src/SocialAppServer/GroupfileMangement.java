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
    private static final String POST= "\\post";
    private static final String IMAGE = "\\image";
    private static final String REQ = "\\req";
    private static final String INFO= "\\info";
    void create(Group group) {
        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.OpenToWrite(GROUPS+group.getId(),POST);
        FilesManager.OpenToWrite(GROUPS+group.getId(),IMAGE);
        FilesManager.OpenToWrite(GROUPS+group.getId(),REQ);
        FilesManager.CreateFileBinary(group,GROUPS+group.getId()+INFO);
        FilesManager.CreateFolder(GROUPS,NAMES);
        System.out.println(group.getName());
        FilesManager.AddLine(GROUPS+NAMES+(Integer.parseInt(Generator.GenerateID(group.getName()))),group.getName());

    }
    public Group pickGroup(int id){
        Group group=new Group(id);
        FilesManager.ReadFromBinaryFile(group,GROUPS+id+INFO);
        return  group;
    }
}
