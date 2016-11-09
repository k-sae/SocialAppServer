package SocialAppServer;

import SocialAppGeneral.Command;
import SocialAppGeneral.Connection;
import SocialAppGeneral.ReceiveCommand;

import java.net.Socket;

/**
 * Created by kemo on 25/10/2016.
 */
public class ReceiveClientCommand extends ReceiveCommand {
    public ReceiveClientCommand(Socket remote, Connection connection) {
        super(remote, connection);
    }

    @Override
    public void Analyze(Command command) {
        //TODO #AllTeam mem
        //our code starts Here HF
    }
}
