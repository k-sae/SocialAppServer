package SocialAppServer;

import FileManagment.FilesPath;
import SocialAppGeneral.Group;
import SocialAppGeneral.LoggedUser;
import SocialAppGeneral.ReqGroup;
import SocialAppGeneral.SocialArrayList;

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

    @Override
    public void getFriends() {

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
      GroupfileMangement.reqFile(req,FilesPath.USERS);

  }
    public  void  reqtogroup(ReqGroup req){
        GroupfileMangement.reqFile(req,FilesPath.GROUPS);

    }

    Relation getRelation() {
        return relation;
    }
    public  Group loadGroup(long Id){
        return  GroupfileMangement.load(Id);

    }

}
