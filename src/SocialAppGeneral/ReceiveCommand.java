package SocialAppGeneral;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * Created by kemo on 25/10/2016.
 */
public abstract class ReceiveCommand extends Thread {
    private Socket remote;
    public ReceiveCommand(Socket remote)
    {
        this.remote = remote;
    }
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                //TODO update 1 #kareem
                //choose whether to
                //make this before the while loop
                //close it each iterate
                DataInputStream objectInputStream = new DataInputStream(remote.getInputStream()); //open remote stream
                Command command = Command.fromString(objectInputStream.readUTF()); //generate command from string
                Analyze(command); //send it to the abstract function Analyze so other team members do there work
            }
        }catch (Exception e)
        {
            //Export to log
            System.out.println("ReadClientData\t" + e.getMessage());
            e.printStackTrace();
        }
    }
    public abstract void Analyze(Command command);
}
