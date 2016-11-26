package SocialAppServer;

import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
class NotificationSimplexConnection extends ClientConnection {
    NotificationSimplexConnection(Socket clientSocket) {
        super(clientSocket);
        //Read Client data im another thread so it doesnt disturb the server
    }

    @Override
    public void startConnection() {
        //TODO #kareem
    }
}
