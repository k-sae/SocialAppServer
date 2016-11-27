package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import java.awt.*;
import java.io.*;

/**
 * Created by khaled hesham on 11/25/2016.
 */
public class GroupfileMangement implements FilesPath {
    public void create(Group group) throws IOException, ClassNotFoundException {
        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FileOutputStream fos = new FileOutputStream("groups//0//info");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(group);
        oos.close();
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"post");
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"image");
        FilesManager.OpenToWrite(GROUPS+"\\"+group.getId(),"req");
        FileInputStream foss=new FileInputStream("groups//0//info");
        ObjectInputStream ooss=new ObjectInputStream(foss);
        group=(Group)ooss.readObject();
        System.out.println(group.getAdminId());
        System.out.println(group.getId());
        System.out.println(group.getImageId());
        System.out.println(group.getMember());
        System.out.println(group.getName());
        System.out.println(group.getReq());
        System.out.println(group.getPost());
        ooss.close();
    }
}
