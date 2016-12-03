package SocialAppServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
class NotificationSimplexConnection extends ClientConnection {
    String id;
    NotificationSimplexConnection(Socket clientSocket) {
        super(clientSocket);
        //Read Client data im another thread so it doesnt disturb the server
        id= "-1";
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
}
