package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.*;

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
      Group loadGroup(long Id){
        return  GroupfileMangement.load(Id);

    }
     SocialArrayList homePost(long numberPost){
        ArrayList <String> ids=getFriends();
        ids.add(getID());
        return PostManger.pickPostHome(ids,numberPost);
    }
    //TODO# ovried to member
     Post savePostGroup(Post post){
       PostManger.SavePost(post,FilesPath.GROUPS+post.getPostPos());
       return  post;
   }
      Post savePost(Post post){
        PostManger.SavePost(post,FilesPath.USERS+post.getPostPos());
        return  post;
    }
      SocialArrayList loadPost(SocialArrayList posts){
       return PostManger.PickPosts(FilesPath.USERS+posts.getExtra(), Long.parseLong(posts.getTarget()));
    }
    SocialArrayList loadPostGroup(SocialArrayList posts){
        return PostManger.PickPosts(FilesPath.GROUPS+posts.getExtra(), Long.parseLong(posts.getTarget()));
    }
    Post Edit(Post post){
        post=PostManger.saveAtachment(post, FilesPath.USERS+post.getPostPos());
        return post;
    }
    Post EditGroup(Post post){
        post=PostManger.saveAtachment(post, FilesPath.GROUPS+post.getPostPos());
        return post;
    }
    void deletePost(Post post){
        FilesManager.delete(FilesPath.USERS+"\\"+post.getPostPos()+FilesPath.POSTS+"\\"+post.getId());
    }
    void deletePostGroup(Post post){
        FilesManager.delete(FilesPath.GROUPS+"\\"+post.getPostPos()+FilesPath.POSTS+"\\"+post.getId());
    }
}
