package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import java.awt.*;
import java.io.*;

/**
 * Created by khaled hesham on 11/25/2016.
 */
public class GroupfileMangement implements FilesPath{
    public  void create(Group group) {
        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"post");
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"image");
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"req");
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+"\\info");
    }
}
