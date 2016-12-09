package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Relations;

/**
 * Created by kemo on 05/12/2016.
 */
public class Relation implements FilesPath {
     //add friend
     //remove friend
     //send req
     private String id;
     private  String currentDirectory;
     public Relation(String id) {
          this.id   = id ;
          currentDirectory =  USERS + id + "\\";
     }
     // return 1 if is a friend
     // return 2 if req sent
     // return 3 if req received
     // return 0 if no Relation
     Relations getStatus(String id)
     {
          if (FilesManager.StringFinder(currentDirectory + FRIEND_REQUEST, id))
          {
               return Relations.FRIEND_REQ;
          }
          else if(FilesManager.StringFinder(currentDirectory + FRIENDS, id )) {
               return Relations.FRIEND;
          }else if (FilesManager.StringFinder(USERS + id+"\\" + FRIEND_REQUEST, this.id))
          {
               return Relations.PENDING;
          }
          return Relations.NOT_FRIEND;
     }
     void acceptFriendReq(String id)
     {
          addFriend(id);
          removeFromFriendReq(id);
     }
     void declineFriendReq(String id)
     {
          removeFromFriendReq(id);
     }
    private void removeFromFriendReq(String id)
     {
          FilesManager.RemoveLine(currentDirectory + FRIEND_REQUEST,id);
     }
     private void addFriend(String id)
     {
          FilesManager.AddLine(currentDirectory + FRIENDS,id);
          FilesManager.AddLine(USERS + id+"\\" + FRIENDS,this.id);
     }
     void removeFriend(String id)
     {
          FilesManager.RemoveLine(currentDirectory + FRIENDS, id);
          FilesManager.RemoveLine(USERS + id+"\\" + FRIENDS,this.id);
     }
     void cancelFriendReq(String id)
     {
          FilesManager.RemoveLine(USERS + id+"\\" + FRIEND_REQUEST,this.id);
     }
}
