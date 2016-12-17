package SocialAppGeneral;

import java.util.ArrayList;

/**
 * Created by mosta on 27-Nov-16.
 */
public abstract class LoggedUser extends AppUser {
    public static final String ADD_FRIEND="add_Friend";
    public static final String FRIEND_REQ = "friend_req";
    public static final String FETCH_REQS = "fetch_reqs";
    public static final String GET_RELATION_STATUS = "get_relation_status";
    public static final String ACCEPT_FRIEND = "accept_friend_req";
    public static final String DECLINE_FRIEND = "decline_friend_req";
    public static final String REMOVE_FRIEND = "remove_friend";
    public static final String CANCEL_FRIEND_REQ = "cancel_friend_req";
    public static final String DEACTIVATE = "deactivate";
    //TO DO
    // may change later check with kareem
    protected ArrayList<Integer> friends;
    protected ArrayList<Integer> requests;
    protected ArrayList<String> notifactions;
    protected ArrayList<String> groups;
    public LoggedUser(String id) {
        friends = new ArrayList<>();
        requests = new ArrayList<>();
        notifactions = new ArrayList<>();
        groups = new ArrayList<>();
        setID(id);
    }
    public abstract Group createGroup(Group group);
    public abstract void joinGroup();
    public abstract void exitGroup();
    public abstract void setFriends();
    public abstract void getNotfications();
    public abstract SocialArrayList getgroups();
    public abstract void deactivate();

}
