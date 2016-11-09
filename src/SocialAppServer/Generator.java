package SocialAppServer;

/**
 * Created by begad on 10/30/2016.
 */
public class Generator {
    public static String GenerateID(String Name) {
        String ID = new String();
        for (int i = 0; i < Name.length(); i++) {
            ID += (int) Name.charAt(i);
        }
        return ID;
    }
}
