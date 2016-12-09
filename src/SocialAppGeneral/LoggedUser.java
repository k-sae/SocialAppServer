package SocialAppGeneral;

import java.util.ArrayList;

/**
 * Created by mosta on 27-Nov-16.
 */
public class LoggedUser extends AppUser {
    public static final String ADD_FRIEND="add_Friend";
    public static final String FRIEND_REQ = "friend_req";
    public static final String FETCH_REQS = "fetch_reqs";
    public static final String GET_RELATION_STATUS = "get_relation_status";
    public static final String ACCEPT_FRIEND = "accept_friend_req";
    public static final String DECLINE_FRIEND = "decline_friend_req";
    public static final String REMOVE_FRIEND = "remove_friend";
    public static final String CANCEL_FRIEND_REQ = "cancel_friend_req";
    //TO DO
    // may change later check with kareem
    ArrayList<Integer> friends;
    ArrayList<Integer> Requests;
    ArrayList<String> Notifactions;
    public LoggedUser() {
        friends = new ArrayList<>();
        Requests = new ArrayList<>();
        Notifactions = new ArrayList<>();
    }

}
