package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;
import SocialAppGeneral.LoggedUser;
import SocialAppGeneral.ReqGroup;
import SocialAppGeneral.SocialArrayList;

import java.util.ArrayList;

import static FileManagment.FilesPath.FRIENDS;
import static FileManagment.FilesPath.USERS;

/**
 * Created by kemo on 10/12/2016.
 */
public class ServerLoggedUser extends LoggedUser {
    private Relation relation;
    public ServerLoggedUser(String id) {
        super(id);
        relation = new Relation(id);
    }

    @Override
    public Group createGroup(Group group) {
        group.setId(Long.parseLong(Generator.GenerateUnigueId(FilesPath.GROUPS)));
        GroupfileMangement.create(group);
        GroupfileMangement.addgrouptomember(group.getAdminId(),group.getId());
        return group;
    }

    @Override
    public void joinGroup() {

    }

    @Override
    public void exitGroup() {

    }

    @Override
    public void setFriends() {

    }

    public ArrayList<String> getFriends() {
       return FilesManager.readAllLines(USERS + getID()+"\\" + FRIENDS);
    }

    @Override
    public void getNotfications() {

    }

    @Override
    public SocialArrayList getgroups() {

       SocialArrayList groups= GroupfileMangement.pickGroups(GroupfileMangement.pickMemberGroup(Long.parseLong(getID())));
        System.out.println(groups.convertToJsonString());
        return groups;
    }
  public  void  reqtouser(ReqGroup req){
      GroupfileMangement.reqFile(req, USERS);

  }
    public  void  reqtogroup(ReqGroup req){
        GroupfileMangement.reqFile(req,FilesPath.GROUPS);

    }

    Relation getRelation() {
        return relation;
    }

}
