package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import FileManagment.Saver;
import SocialAppGeneral.*;

import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by kemo on 25/10/2016.
 */
class ReceiveClientCommand extends ReceiveCommand implements FilesPath {
    private HalfDuplexConnection connection;
//    private String loggedUserId;
    private ServerLoggedUser serverLoggedUser;
    ReceiveClientCommand(Socket remote, HalfDuplexConnection connection) {
        super(remote);
        this.connection = connection;
    }
    @Override
    public void Analyze(Command command) {
        //TODO #AllTeam mem
        //our code starts Here HF
        //TODO #Server Command prototype
        if(command.getKeyWord().equals("changeColor"))
        {
            //DO ur algorithm
            Command command1 = new Command();
            command1.setKeyWord("changeColor");
            command1.setSharableObject("#000");
            //lastly send new command to the client
            connection.sendCommand(command1);
        }
       else if(command.getKeyWord().equals(RegisterInfo.KEYWORD)){
         // h3ml constrain el fe saver 7alyin
            RegisterInfo reg =RegisterInfo.fromJsonString(command.getObjectStr());
            reg.getUserInfo().setProfileImage("default");
            Saver s=new Saver(reg,connection);
      //      Admin a=new Admin("0"); //pass zero for now till we have a real admi with id
        //  a.convertIntoPermnantUser(reg.getLoginInfo().getEMAIL());
            if(reg.getUserInfo().getAdminShip()){
                if(ServerAdmin.adminCheck(reg.getLoginInfo().getEMAIL())){
                    new ServerAdmin("").approveAsAdmin(reg.getLoginInfo().getEMAIL());
                    System.out.println("admin created");
                }

            }

          //  Admin a=new Admin();
        //  a.convertIntoPermnantUser(reg.getLoginInfo().getEMAIL());
            //System.out.println("in");
        }
        else if(command.getKeyWord().equals(Admin.KEYWORD)){
                //String ID=command.getObjectStr();
                if(serverLoggedUser instanceof Admin){
                    command.setSharableObject("true");
                }else{
                    command.setSharableObject("false");
                }
                connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Admin.APPROVEASADMIN)){
            ((ServerAdmin)serverLoggedUser).approveAsAdmin(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Admin.REJECT)){
            ((ServerAdmin)serverLoggedUser).reject(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
       else if(command.getKeyWord().equals(LoginInfo.KEYWORD)){
            LoginInfo log=LoginInfo.fromJsonString(command.getObjectStr());
            String id = UserFinder.validate(log.getEMAIL(),log.getPassword());
            if (!id.equals("-1")) {
                if (ServerAdmin.adminChecker(id)) {
                    serverLoggedUser = new ServerAdmin(id);
                } else {
                    serverLoggedUser = new ServerLoggedUser(id);
                }
                serverLoggedUser.setLoginInfo(log);
            }
            command.setSharableObject(id);
            connection.sendCommand(command);
        }else if(command.getKeyWord().equals(Admin.RetrieveData)){

            ArrayList<Object> objects = new ArrayList<>();
            ArrayList<String> strings = ((ServerAdmin)serverLoggedUser).fetchRequests();
            objects.addAll(strings);
            SocialArrayList socialArrayList = new SocialArrayList(objects);
            command.setSharableObject(socialArrayList.convertToJsonString());
            connection.sendCommand(command);
        }else if(command.getKeyWord().equals(Admin.APPROVE)){
            ((ServerAdmin)serverLoggedUser).approve(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(Group.CREATE_GROUP))
        {
            command.setSharableObject(serverLoggedUser.createGroup(Group.fromJsonString(command.getObjectStr())));
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(Group.DELETE_GROUP))
        {
            Group group= Group.fromJsonString(command.getObjectStr());
            FilesManager.delete(FilesPath.GROUPS+"\\"+group.getId());
            connection.sendCommand(command);
        }

       else if(command.getKeyWord().equals(Post.SAVE_POST_USER)){
            Post post=Post.fromJsonString(command.getObjectStr());
           PostManger.SavePost(post,FilesPath.USERS+post.getPostPos());
            command.setSharableObject(post.convertToJsonString());
            connection.sendCommand(command);

        }
        else if(command.getKeyWord().equals(Post.SAVE_POST_GROUP)){
            Post post=Post.fromJsonString(command.getObjectStr());
            PostManger.SavePost(post,FilesPath.GROUPS+post.getPostPos());
            command.setSharableObject(post.convertToJsonString());
            connection.sendCommand(command);

        }

        else if(command.getKeyWord().equals(Post.LOAD_POST_USERS)){
            ArraylistPost posts;
            posts=(ArraylistPost.fromJsonString(command.getObjectStr()));
            posts.setPosts(PostManger.PickPosts(FilesPath.USERS+posts.getOwnerPosts(),posts.getNumberpost()));
            command.setSharableObject(posts.convertToJsonString());
            connection.sendCommand(command);

        }
        else if(command.getKeyWord().equals(Post.LOAD_POST_GROUPS)){
            ArraylistPost posts;
            posts=(ArraylistPost.fromJsonString(command.getObjectStr()));
            posts.setPosts(PostManger.PickPosts(FilesPath.GROUPS+posts.getOwnerPosts(),posts.getNumberpost()));
            command.setSharableObject(posts.convertToJsonString());
            connection.sendCommand(command);

        }
       else if(command.getKeyWord().equals(Post.EDITE_POST_USERS)){
       Post post1= Post.fromJsonString(command.getObjectStr());
            post1=PostManger.saveAtachment(post1, FilesPath.USERS+post1.getPostPos());
            command.setSharableObject(post1.convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Post.EDITE_POST_GROUPS)){
            Post post1= Post.fromJsonString(command.getObjectStr());
            post1=PostManger.saveAtachment(post1, FilesPath.GROUPS+post1.getPostPos());
            command.setSharableObject(post1.convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Post.DELETE_POST_GROUPS)){
            Post post1= Post.fromJsonString(command.getObjectStr());
            FilesManager.delete(FilesPath.GROUPS+"\\"+post1.getPostPos()+FilesPath.POSTS+"\\"+post1.getId());
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(Post.DELETE_POST_USERS)){
            Post post1= Post.fromJsonString(command.getObjectStr());
            FilesManager.delete(FilesPath.USERS+"\\"+post1.getPostPos()+FilesPath.POSTS+"\\"+post1.getId());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Group.LOAD_GROUPS)){

            command.setSharableObject(serverLoggedUser.getgroups().convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(Group.LOAD_GROUP)){

            command.setSharableObject(serverLoggedUser.loadGroup(Long.parseLong(command.getObjectStr())));
            connection.sendCommand(command);
        }

           else if (command.getKeyWord().equals(UserInfo.PICK_INFO))
        {
            command.setSharableObject(UserPicker.pickUserInfo(command.getObjectStr()));
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(UserInfo.EDIT_INFO))
        {

            FilesManager.Removefile(FilesPath.USERS + serverLoggedUser.getID()+"\\" + FilesPath.INFO, command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);


        }else if(command.getKeyWord().equals(LoggedUser.ADD_FRIEND)){

            String  id =command.getObjectStr();
            FilesManager.AddLine(USERS+id+"\\"+ FRIEND_REQUEST,serverLoggedUser.getID());
            command.setSharableObject("true");
            connection.sendCommand(command);
            command.setKeyWord(LoggedUser.FRIEND_REQ);
            command.setSharableObject(serverLoggedUser.getID());
            SecondaryConnection.sendNotification(id,command);
        }
        else if(command.getKeyWord().equals("Search")) {
            UserFinder f = new UserFinder();
            //ArrayList <String>a=new ArrayList<String>();
            ArrayList<Object> objects = new ArrayList<>();
            ArrayList<String> strings = new ArrayList<>();
            strings = f.Search(command.getObjectStr());
            objects.addAll(strings);
            SocialArrayList socialArrayList = new SocialArrayList(objects);
            command.setSharableObject(socialArrayList.convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.FETCH_REQS))
        {
            ArrayList<Object> objects = new ArrayList<>();
            objects.addAll(FilesManager.readAllLines(USERS+serverLoggedUser.getID()+"\\"+ FRIEND_REQUEST));
            command.setSharableObject(new SocialArrayList(objects).convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.GET_RELATION_STATUS))
        {

            command.setSharableObject(serverLoggedUser.getRelation().getStatus(command.getObjectStr()) + "");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.ACCEPT_FRIEND))
        {

            serverLoggedUser.getRelation().acceptFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.REMOVE_FRIEND))
        {
            serverLoggedUser.getRelation().removeFriend(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.CANCEL_FRIEND_REQ))
        {
            serverLoggedUser.getRelation().cancelFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.DECLINE_FRIEND))
        {
            serverLoggedUser.getRelation().declineFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.DEACTIVATE)){
            UserInfo userInfo = UserInfo.fromJsonString(command.getObjectStr());
            command.setSharableObject(userInfo.convertToJsonString());
            connection.sendCommand(command);
            /**SENDING AN EVALUATION E-MAIL*/
            ServerAdmin.sendMail(Credentials.E_MAIL,Credentials.PASSWORD,
                    serverLoggedUser.getLoginInfo().getEMAIL(),
                    EmailContent.DEACTIVATE_MSG_SUBJECT,
                    EmailContent.DEACTIVATE_MSG_BODY
                    );
        }
    }
}
