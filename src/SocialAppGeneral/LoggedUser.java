package SocialAppGeneral;

import FileManagment.FilesPath;

import java.util.ArrayList;

/**
 * Created by mosta on 27-Nov-16.
 */
public class LoggedUser extends AppUser {
    public static final String ADD_FRIEND="add_Friend";
    public static final String FRIEND_REQ = "friend_req";
   //TO DO
    // may change later check with kareem
   ArrayList<Integer> friends;
    ArrayList<Integer> Requests;
    ArrayList<String> Notifactions;
    public LoggedUser() {
        friends = new ArrayList<Integer>();
       Requests = new ArrayList<Integer>();
        Notifactions = new ArrayList<String>();
  }

}
