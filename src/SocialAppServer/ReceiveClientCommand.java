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
    private String loggedUserId;
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
            if(reg.getUserInfo().getAdminShip()){
                if(Admin.adminCheck(reg.getLoginInfo().getEMAIL())){
                 // answer server
                    System.out.println("admin created");
                }
                // answer server
            }

          //  Admin a=new Admin();
        //  a.convertIntoPermnantUser(reg.getLoginInfo().getEMAIL());
            //System.out.println("in");
        }
        else if(command.getKeyWord().equals("ADMIN_CHECK")){
         String ID=command.getObjectStr();
            if(Admin.adminChecker(ID)){
                command.setSharableObject("true");
            }else{
                command.setSharableObject("false");
            }
             connection.sendCommand(command);
        }
       else if(command.getKeyWord().equals(LoginInfo.KEYWORD)){
            LoginInfo log=LoginInfo.fromJsonString(command.getObjectStr());
            loggedUserId = UserFinder.validate(log.getEMAIL(),log.getPassword());
            command.setSharableObject(loggedUserId);
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(Group.CREATE_GROUP))
        {
            String name = command.getObjectStr();
            Group group=new Group(name);
           group.setId(Integer.parseInt(Generator.GenerateUnigueId(FilesPath.GROUPS)));
//            group.setAdminId(1);
//            group.setMember(1);
            GroupfileMangement g=new GroupfileMangement();
            g.create(group);
            Command command1 = new Command();
            command1.setKeyWord(Group.CREATE_GROUP);
            command1.setSharableObject(group);
            connection.sendCommand(command1);
        }

       else if(command.getKeyWord().equals(Post.SAVE_POST_USER)){
            Post post=Post.fromJsonString(command.getObjectStr());
           PostManger.SavePost(post,FilesPath.USERS+post.getPostPos());
            Command command1 = new Command();
            command1.setKeyWord(Post.SAVE_POST_USER);
            command1.setSharableObject(post.convertToJsonString());
            connection.sendCommand(command1);

        }

       else if(command.getKeyWord().equals(Post.SAVE_POST_GROUP)) {
            Post post = Post.fromJsonString(command.getObjectStr());
            PostManger.SavePost(post, FilesPath.GROUPS + post.getPostPos());
            Command command1 = new Command();
            command1.setKeyWord(Post.SAVE_POST_USER);
            command1.setSharableObject(post.convertToJsonString());
            connection.sendCommand(command1);
        }

        else if(command.getKeyWord().equals(Post.LOAD_POST_USERS)){
            ArraylistPost posts;
            posts=(ArraylistPost.fromJsonString(command.getObjectStr()));
            System.out.println(command.getObjectStr());
            posts.setPosts(PostManger.PickPosts(FilesPath.USERS+posts.getOwnerPosts(),posts.getNumberpost()));

            Command command1 = new Command();
            command1.setKeyWord(Post.LOAD_POST_USERS);
            command1.setSharableObject(posts.convertToJsonString());
            connection.sendCommand(command1);

        }
       else if(command.getKeyWord().equals(Post.EDITE_POST_USERS)){
       Post post1= Post.fromJsonString(command.getObjectStr());
            command.setSharableObject(String.valueOf(PostManger.saveAtachment(post1, FilesPath.USERS+post1.getPostPos())));
            connection.sendCommand(command);

        }
        else if (command.getKeyWord().equals(Post.DELETE_POST_USERS)){
            Post post1= Post.fromJsonString(command.getObjectStr());
            PostManger.deletepost(FilesPath.USERS+"\\"+post1.getPostPos()+FilesPath.POSTS+"\\"+post1.getId());
            connection.sendCommand(command);
        }
           else if (command.getKeyWord().equals(UserInfo.PICK_INFO))
        {
            command.setSharableObject(UserPicker.pickUserInfo(command.getObjectStr()));
            connection.sendCommand(command);
        }
        else if (command.getKeyWord().equals(UserInfo.EDIT_INFO))
        {

            FilesManager.Removefile(FilesPath.USERS + loggedUserId+"\\" + FilesPath.INFO, command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);


        }else if(command.getKeyWord().equals(LoggedUser.ADD_FRIEND)){

            String  id =command.getObjectStr();
            FilesManager.AddLine(USERS+id+"\\"+ FRIEND_REQUEST,loggedUserId);
            command.setSharableObject("true");
            connection.sendCommand(command);
            command.setKeyWord(LoggedUser.FRIEND_REQ);
            command.setSharableObject(loggedUserId);
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
            objects.addAll(FilesManager.readAllLines(USERS+loggedUserId+"\\"+ FRIEND_REQUEST));
            command.setSharableObject(new SocialArrayList(objects).convertToJsonString());
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.GET_RELATION_STATUS))
        {
            Relation relation = new Relation(loggedUserId);
            command.setSharableObject(relation.getStatus(command.getObjectStr()) + "");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.ACCEPT_FRIEND))
        {
            Relation relation = new Relation(loggedUserId);
            relation.acceptFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.REMOVE_FRIEND))
        {
            Relation relation = new Relation(loggedUserId);
            relation.removeFriend(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.CANCEL_FRIEND_REQ))
        {
            Relation relation = new Relation(loggedUserId);
            relation.cancelFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
        else if(command.getKeyWord().equals(LoggedUser.DECLINE_FRIEND))
        {
            Relation relation = new Relation(loggedUserId);
            relation.declineFriendReq(command.getObjectStr());
            command.setSharableObject("true");
            connection.sendCommand(command);
        }
    }
}
