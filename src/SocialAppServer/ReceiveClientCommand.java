package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import FileManagment.Saver;

import SocialAppGeneral.*;


import java.net.Socket;


/**
 * Created by kemo on 25/10/2016.
 */
class ReceiveClientCommand extends ReceiveCommand {
    private HalfDuplexConnection connection;
    ReceiveClientCommand(Socket remote, HalfDuplexConnection connection) {
        super(remote);
        this.connection = connection;
    }
    @Override
    public void Analyze(Command command) {
        //TODO #AllTeam mem
        //our code starts Here HF
        //TODO #Server Command prototype
        if(command.getKeyWord().equals("changeColor"))
        {
            //DO ur algorithm
            Command command1 = new Command();
            command1.setKeyWord("changeColor");
            command1.setSharableObject("#000");
            //lastly send new command to the client
            connection.sendCommand(command1);
        }
        if(command.getKeyWord().equals(RegisterInfo.KEYWORD)){
         // h3ml constrain el fe saver 7alyin
            RegisterInfo reg =RegisterInfo.fromJsonString(command.getObjectStr());
            Saver s=new Saver(reg,connection);
      // Admin a=new Admin();
    //   a.convertIntoPermnantUser("mostafa@yahoo.com");
        //    a.convertIntoPermnantUser("mostafahazem145@yahoo.com");
            //System.out.println("in");
        }
        if(command.getKeyWord().equals(LoginInfo.KEYWORD)){

        }
        if (command.getKeyWord().equals(Group.CREATE_GROUP))
        {
            String name = command.getObjectStr();
            Group group=new Group(name);
           group.setId(Integer.parseInt(Generator.GenerateUnigueId(FilesPath.GROUPS)));
//            group.setAdminId(1);
//            group.setMember(1);
            GroupfileMangement g=new GroupfileMangement();
            g.create(group);
            Command command1 = new Command();
            command1.setKeyWord(Group.CREATE_GROUP);
            command1.setSharableObject(group);
            connection.sendCommand(command1);
        }
        if(command.getKeyWord().equals(Post.SAVE_POST)){
            Post post=new Post();
            post=Post.fromJsonString(command.getObjectStr());
          post= PostManger.SavePost(post,FilesPath.GROUPS+post.getPostPos());
            System.out.println(post.getDate());
            Command command1 = new Command();
            command1.setKeyWord(Post.SAVE_POST);
            command1.setSharableObject(post.convertToJsonString());
            connection.sendCommand(command1);
            System.out.println("ok");
        }
    }
}