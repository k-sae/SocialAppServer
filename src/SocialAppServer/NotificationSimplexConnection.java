package SocialAppServer;

import SocialAppGeneral.Command;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by kemo on 06/11/2016.
 */
class NotificationSimplexConnection extends ClientConnection {
    String id;
    private volatile static ArrayList<NotificationSimplexConnection> connections;
    private volatile static ArrayList<NotificationSimplexConnection> connectionsToKill;
    NotificationSimplexConnection(Socket clientSocket) {
        super(clientSocket);
        //Read Client data im another thread so it doesnt disturb the server
        id= "-1";
        if (connections == null)
        {
            connections= new ArrayList<>();
            connectionsToKill = new ArrayList<>();
        }
        connections.add(this);
    }

    @Override
    public void startConnection() {
        //TODO #kareem
        new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                id = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public static void sendNotification(String id, Command cmd)
    {
        //IDK i have just wrote a normal foreach statement then intellij modified it :D
        //i will try it if it worked i'll leave it
        new Thread(() -> loopThrough(id, cmd)).start();


    }
    private synchronized static void loopThrough(String id, Command cmd)
    {
        connections.stream().filter(connection -> Objects.equals(connection.id, id)).forEach(connection -> connection.sendCommand(cmd));
        removeDcUsers();
    }
    @Override
    protected void onUserDisconnection() {
        super.onUserDisconnection();
        connectionsToKill.add(this);
    }
    private static void removeDcUsers()
    {
        connections.removeAll(connectionsToKill);
        connectionsToKill.clear();
    }
}
