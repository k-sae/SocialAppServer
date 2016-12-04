package SocialAppGeneral;

import FileManagment.FilesPath;

import java.util.ArrayList;

/**
 * Created by mosta on 27-Nov-16.
 */
public class LoggedUser extends AppUser implements FilesPath{
    public static final String KEYWORD="add_Friend";
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
  public void addFriend(String ID,String ID2){
      
  }

}
