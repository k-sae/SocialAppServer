package SocialAppServer;

import SocialAppGeneral.LoggedUser;

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
    public void createGroup() {

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
    public void getgroups() {

    }

    Relation getRelation() {
        return relation;
    }

}
