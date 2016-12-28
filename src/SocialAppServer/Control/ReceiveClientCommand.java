package SocialAppServer.Control;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import FileManagment.Saver;
import SocialAppGeneral.*;
import SocialAppServer.Connections.HalfDuplexConnection;
import SocialAppServer.Connections.NotificationSimplexConnection;
import SocialAppServer.Module.UserFinder;
import SocialAppServer.Module.UserPicker;

import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by kemo on 25/10/2016.
 */
public class ReceiveClientCommand extends ReceiveCommand implements FilesPath {
    private HalfDuplexConnection connection;
    private ServerLoggedUser serverLoggedUser;
    public ReceiveClientCommand(Socket remote, HalfDuplexConnection connection) {
        super(remote);
        this.connection = connection;
    }
    @Override
    public void Analyze(Command command) {
        //TODO #AllTeam mem
        //our code starts Here HF
        //TODO #Server Command prototype
        switch (command.getKeyWord()) {
            case "changeColor":
                //DO ur algorithm
                Command command1 = new Command();
                command1.setKeyWord("changeColor");
                command1.setSharableObject("#000");
                //lastly send new command to the client
                connection.sendCommand(command1);
                break;
            case RegisterInfo.KEYWORD:
                // h3ml constrain el fe saver 7alyin
                RegisterInfo reg = RegisterInfo.fromJsonString(command.getObjectStr());
                reg.getUserInfo().setProfileImage("default");
                new Saver(reg,connection);
                if (reg.getUserInfo().getAdminShip()) {
                    if (ServerAdmin.adminCheck()) {
                        new ServerAdmin("").approveAsAdmin(reg.getLoginInfo().getEMAIL());
                        System.out.println("admin created");
                    }

                }

                break;
            case Admin.KEYWORD:
                //String ID=command.getObjectStr();
                if (serverLoggedUser instanceof Admin) {
                    command.setSharableObject("true");
                } else {
                    command.setSharableObject("false");
                }
                connection.sendCommand(command);
                break;
            case Admin.APPROVEASADMIN:
                ((ServerAdmin) serverLoggedUser).approveAsAdmin(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case Admin.REJECT:
                ((ServerAdmin) serverLoggedUser).reject(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case LoginInfo.NEW_LOGIN: {
                LoginInfo log = LoginInfo.fromJsonString(command.getObjectStr());
                String id = UserFinder.validate(log.getEMAIL(), log.getPassword());
                if (!id.equals("-1")) {
                    if (ServerAdmin.adminChecker(id)) {
                        serverLoggedUser = new ServerAdmin(id);
                    } else {
                        serverLoggedUser = new ServerLoggedUser(id);
                    }
                    serverLoggedUser.setLoginInfo(log);
                    serverLoggedUser.setUserInfo(UserPicker.pickUserInfo(id));
                }
                command.setSharableObject(id);
                connection.sendCommand(command);
                break;
            }
            case Admin.RetrieveData: {

                ArrayList<String> strings = ((ServerAdmin) serverLoggedUser).fetchRequests();
                SocialArrayList socialArrayList = new SocialArrayList(strings);
                command.setSharableObject(socialArrayList.convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case Admin.APPROVE:
                ((ServerAdmin) serverLoggedUser).approve(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case Group.CREATE_GROUP:
                command.setSharableObject(serverLoggedUser.createGroup(Group.fromJsonString(command.getObjectStr())));
                connection.sendCommand(command);
                break;
            case Group.DELETE_GROUP:
                Group group = Group.fromJsonString(command.getObjectStr());
                FilesManager.delete(FilesPath.GROUPS + "\\" + group.getId());
                connection.sendCommand(command);
                break;
            case Post.SAVE_POST_USER:
                command.setSharableObject(serverLoggedUser.savePost(Post.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.SAVE_POST_GROUP:
                command.setSharableObject(serverLoggedUser.savePostGroup(Post.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.LOAD_POST_USERS:
                try {
                    command.setSharableObject(serverLoggedUser.loadPost(PostSender.fromJsonString(command.getObjectStr())).convertToJsonString());
                } catch (Exception e) {
                    command.setSharableObject(new SocialArrayList());
                }
                connection.sendCommand(command);
                break;
            case Post.LOAD_POST_GROUPS:
                command.setSharableObject(serverLoggedUser.loadPostGroup( PostSender.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.LOAD_POST_HOME:
                command.setSharableObject(serverLoggedUser.homePost(PostSender.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.EDITE_POST_USERS:
                command.setSharableObject(serverLoggedUser.edit(Post.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.EDITE_POST_GROUPS:
                command.setSharableObject(serverLoggedUser.editGroup(Post.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case AttachmentSender.ATTACHMENT_USER:
                command.setSharableObject(serverLoggedUser.attachment(AttachmentSender.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case AttachmentSender.ATTACHMENT_GROUP:
                command.setSharableObject(serverLoggedUser.attachmentGroup(AttachmentSender.fromJsonString(command.getObjectStr())).convertToJsonString());
                connection.sendCommand(command);
                break;
            case Post.DELETE_POST_GROUPS:
                serverLoggedUser.deletePostGroup(Post.fromJsonString(command.getObjectStr()));
                connection.sendCommand(command);
                break;
            case Post.DELETE_POST_USERS:
                serverLoggedUser.deletePost(Post.fromJsonString(command.getObjectStr()));
                connection.sendCommand(command);
                break;
            case Notification.LOAD_NOTI:
                connection.sendCommand(serverLoggedUser.loadNotification());
                break;
            case Log.LOAD_LOG:
                command.setSharableObject(serverLoggedUser.loadLog());
                connection.sendCommand(command);
                break;
            case Group.LOAD_GROUPS:
                command.setSharableObject(serverLoggedUser.getgroups().convertToJsonString());
                connection.sendCommand(command);
                break;
            case Group.LOAD_GROUP:
                command.setSharableObject(serverLoggedUser.loadGroup(Long.parseLong(command.getObjectStr())));
                connection.sendCommand(command);
                break;
            case UserInfo.PICK_INFO:
                try {
                    command.setSharableObject(UserPicker.pickUserInfo(command.getObjectStr()));
                } catch (Exception e) {
                    command.setSharableObject(UserInfo.getDefaultUserInfo());
                }

                connection.sendCommand(command);
                break;
            case UserInfo.EDIT_INFO:
                serverLoggedUser.modify(UserInfo.fromJsonString(command.getObjectStr()));
                command.setSharableObject("true");
                connection.sendCommand(command);


                break;
            case LoggedUser.ADD_FRIEND: {

                String id = command.getObjectStr();
                FilesManager.AddLine(USERS + id + "\\" + FRIEND_REQUEST, serverLoggedUser.getID());
                command.setSharableObject("true");
                connection.sendCommand(command);
                command.setKeyWord(LoggedUser.FRIEND_REQ);
                command.setSharableObject(serverLoggedUser.getID());
                NotificationSimplexConnection.sendNotification(id, command);
                break;
            }
            case "Search": {
                UserFinder f = new UserFinder();
                //ArrayList <String>a=new ArrayList<String>();
                ArrayList<String> strings;
                strings = f.Search(command.getObjectStr());
                strings.addAll(f.SearchInGroups(command.getObjectStr()));
                SocialArrayList socialArrayList = new SocialArrayList(strings);
                command.setSharableObject(socialArrayList.convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case "Search_Group": {
                UserFinder f = new UserFinder();
                ArrayList<String> strings;
                strings = f.SearchInGroups(command.getObjectStr());
                SocialArrayList socialArrayList = new SocialArrayList(strings);
                command.setSharableObject(socialArrayList.convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case LoggedUser.FETCH_REQS: {
                ArrayList<String> strings = (FilesManager.readAllLines(USERS + serverLoggedUser.getID() + "\\" + FRIEND_REQUEST));
                command.setSharableObject(new SocialArrayList(strings).convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case LoggedUser.GET_RELATION_STATUS:

                command.setSharableObject(serverLoggedUser.getRelation().getStatus(command.getObjectStr()) + "");
                connection.sendCommand(command);
                break;
            case Group.Group_relation:

                command.setSharableObject(new Group(Long.parseLong(command.getObjectStr())).getGroupRelation().getStatus(serverLoggedUser.getID()) + "");
                connection.sendCommand(command);
                break;
            case Group.Group_Leave:
                command.setSharableObject(new Group(Long.parseLong(command.getObjectStr())).getGroupRelation().leaveGroup(serverLoggedUser.getID()) + "");
                connection.sendCommand(command);
                break;
            case Group.Group_ADD:
                command.setSharableObject(new Group(Long.parseLong(command.getObjectStr())).getGroupRelation().addToGroup(serverLoggedUser.getID()) + "");
                connection.sendCommand(command);
                break;
            case Group.GROUP_CANCEL_REQ:
                new Group(Long.parseLong(command.getObjectStr())).getGroupRelation().cancelRequest(serverLoggedUser.getID());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case Group.Group_Accept: {
                String Groupid = command.getObjectStr().substring(0, command.getObjectStr().indexOf(':'));
                String id = command.getObjectStr().substring(command.getObjectStr().indexOf(':') + 1, command.getObjectStr().length());
                new Group((Long.parseLong(Groupid))).getGroupRelation().AcceptRequest(id);
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            }
            case Group.FETCH_DATA: {
                ArrayList<String> strings;
                strings = new Group(Long.parseLong(command.getObjectStr())).getGroupRelation().fetch();
                SocialArrayList socialArrayList = new SocialArrayList(strings);
                command.setSharableObject(socialArrayList.convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case LoggedUser.ACCEPT_FRIEND:

                serverLoggedUser.getRelation().acceptFriendReq(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case LoggedUser.REMOVE_FRIEND:
                serverLoggedUser.getRelation().removeFriend(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case LoggedUser.CANCEL_FRIEND_REQ:
                serverLoggedUser.getRelation().cancelFriendReq(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case LoggedUser.DECLINE_FRIEND:
                serverLoggedUser.getRelation().declineFriendReq(command.getObjectStr());
                command.setSharableObject("true");
                connection.sendCommand(command);
                break;
            case LoggedUser.DEACTIVATE:
                /**SENDING AN EVALUATION E-MAIL*/
                serverLoggedUser.deactivate();
                connection.sendCommand(command);
                break;
            case LoggedUser.GET_FRIENDS: {
                SocialArrayList socialArrayList = new SocialArrayList(serverLoggedUser.getFriends());
                command.setSharableObject(socialArrayList.convertToJsonString());
                connection.sendCommand(command);
                break;
            }
            case LoggedUser.REACTIVATE:

                command.setSharableObject(Boolean.toString(serverLoggedUser.reactivate()));
                serverLoggedUser.setUserInfo(UserPicker.pickUserInfo(serverLoggedUser.getID()));
                connection.sendCommand(command);
                break;
        }
    }
}
