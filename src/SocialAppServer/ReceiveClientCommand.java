package SocialAppServer;

import FileManagment.Saver;
import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import SocialAppGeneral.ReceiveCommand;
import java.io.IOException;
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
            command1.setSharableObject("#023345");
            //lastly send new command to the client
            connection.sendCommand(command1);
        }
        if(command.getKeyWord().equals("new register")){
         // h3ml constrain el fe saver 7alyin

            Saver s=new Saver();


        }
        if (command.getKeyWord().equals(Group.CREATE_GROUP))
        {
            String name = command.getObjectStr();
            Group group=new Group(name);
            GroupfileMangement g=new GroupfileMangement();
            g.create(group);

        }
    }
}
