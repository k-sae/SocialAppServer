package SocialAppServer.Control;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.*;
import SocialAppServer.Connections.NotificationSimplexConnection;

import java.util.ArrayList;
import java.util.Date;

import static FileManagment.FilesManager.FileIsExist;

/**
 * Created by khaled hesham on 12/2/2016.
 */
class PostManger {
    private static final String Post_FILE="\\post";
    static Post SavePost(Post post,String path)  {
        Log log = new Log();
        post.setDate(new Date());
        FilesManager.CreateFolder(path, FilesPath.POSTS);
        post.setId(Long.valueOf(Generator.GenerateUnigueId(path+FilesPath.POSTS)));
        FilesManager.CreateFolder(path+FilesPath.POSTS,"\\"+post.getId()+"");
        FilesManager.CreateFileBinary(post,path+FilesPath.POSTS+"\\"+post.getId()+Post_FILE);
        log.setKeyword(Relations.POST);
        log.setSenderId(String.valueOf(post.getPostPos()));
        log.setOwnerId(String.valueOf(post.getOwnerId()));
        saveLog(log);
        return (post);
    }
    static SocialArrayList PickPosts(String path,long numberPost)  {
          SocialArrayList posts= new SocialArrayList();
          String uniqueID;
         long counter=1;
         int counter2= 1;
       if(FilesManager.FileIsExist(path+FilesPath.POSTS,"\\uniqueID.txt")) {
           uniqueID = (FilesManager.ReadLine(path + FilesPath.POSTS + "\\uniqueID.txt", 1));
           long uniqueIdTemp = Long.valueOf(uniqueID);
           do{
               if(FilesManager.FileIsExist(path +FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)) {
                   if(counter2 >= ((numberPost-1)*10)+1) {
                       posts.getItems().add(((Post) FilesManager.ReadFromBinaryFile(path + FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)).convertToJsonString());
                       counter++;
                   }
                   counter2++;
         }
               uniqueIdTemp--;
           }
           while (uniqueIdTemp != 0 && counter % 11 != 0);
       }
        return  posts;
    }
     static SocialArrayList pickPostHome(ArrayList<String> id){
        SocialArrayList posts =new SocialArrayList();
        String uniqueID;
        int counter;
        for(int i=id.size()-1;i>=0;i--) {
            counter = 0;
            if (FilesManager.FileIsExist(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS, "\\uniqueID.txt")) {
                uniqueID = (FilesManager.ReadLine(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS + "\\uniqueID.txt", 1));
                long uniqueIdTemp = Long.valueOf(uniqueID);
                do {
                    if (FilesManager.FileIsExist(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)) {
                        counter = 1;
                        posts.getItems().add(((Post) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)).convertToJsonString());
                    }
                    uniqueIdTemp--;

                } while (uniqueIdTemp != 0 && counter != 1);
            }
        }

                return posts;
    }
    static  Post saveAttachment(Post postNew, String path){
        Post post1 =new Post();
        if(FilesManager.FileIsExist(path +FilesPath.POSTS+ "\\" +postNew.getId() + Post_FILE)) {
             post1 = (Post) FilesManager.ReadFromBinaryFile(path + FilesPath.POSTS + "\\" + postNew.getId() + Post_FILE);
            if (postNew.getLike().size() != 0) {
              post1=  setLike(postNew,post1);
            } else {
                if (postNew.getComments().size() != 0) {
                   post1=setComment(postNew,post1,path);

                } else {
                    post1.setContent(postNew.getContent());
                }
            }
            FilesManager.CreateFileBinary(post1, path + FilesPath.POSTS + "\\" + postNew.getId() + Post_FILE);
        }
        else{post1.setId(0);}
            return post1;
    }
    private static  Post setComment(Post postnew, Post post, String path){
        Notification notification=new Notification();
        Log log = new Log();
        if (postnew.getComments().get(0).getShow().equals(Relations.ADD)) {
            postnew.getComments().get(0).setCommentId((Long.valueOf(Generator.GenerateUnigueId(path + FilesPath.POSTS + "\\" +
                    post.getId()))));
            post.addcomment(postnew.getComments().get(0));
            if(postnew.getComments().get(0).getOwnerID()!=post.getOwnerId()) {
                notification.setKeyword(Relations.COMMENT);
                notification.setIdSender(String.valueOf(postnew.getComments().get(0).getOwnerID()));
                log.setKeyword(Relations.COMMENT);
                log.setSenderId(String.valueOf(postnew.getComments().get(0).getOwnerID()));
                log.setOwnerId(String.valueOf(post.getOwnerId()));
            }
        } else {
            int i = -1;
            int check = -1;
            do {
                i++;
                if (post.getComments().get(i).getCommentId() == postnew.getComments().get(0).getCommentId()) {
                    check = i;
                }

            }
            while (i < post.getComments().size() - 1 && post.getComments().get(i).getCommentId() != postnew.getComments().get(0).getCommentId());

            if (postnew.getComments().get(0).getShow().equals(Relations.DELETE)) {
                if (check != -1)
                    post.deletecomment(check);
            } else if (postnew.getComments().get(0).getShow().equals(Relations.EDIT)) {
                if (check != -1)
                    post.getComments().get(check).setCommentContent(postnew.getComments().get(0).getCommentContent());
            }
        }
            if(!notification.getIdSender().equals("")){
                notification.setPost(post);
                saveNoti(notification);
            }
            saveLog(log);
            return post;
    }
    private static  Post setLike(Post postnew, Post post){
        Notification notification=new Notification();
        Log log = new Log();
        int i = -1;
        int check = -1;
        if (post.getLike().size() != 0) {
            do {
                i++;
                if (post.getLike().get(i).getOwnerID() == postnew.getLike().get(0).getOwnerID()) {
                    check = i;
                }

            }
            while (i < post.getLike().size()-1 && post.getLike().get(i).getOwnerID() != postnew.getLike().get(0).getOwnerID());
        }
        if (!postnew.getLike().get(0).getLike().equals(Relations.DELETE)) {
            if (check == -1) {
                post.addlike((postnew.getLike().get(0)));

            } else {
                post.getLike().get(check).setLike(postnew.getLike().get(0).getLike());
            }
            if(postnew.getLike().get(0).getOwnerID()!=post.getOwnerId()) {
                if ((postnew.getLike().get(0).getLike().equals(Relations.THUMP_UP))) {
                    notification.setKeyword(Relations.THUMP_UP);
                    log.setKeyword(Relations.THUMP_UP);
                } else {
                    if ((postnew.getLike().get(0).getLike().equals(Relations.THUMP_DOWN))) {
                        notification.setKeyword(Relations.THUMP_DOWN);
                        log.setKeyword(Relations.THUMP_DOWN);
                    }
                }

                notification.setIdSender(String.valueOf(postnew.getLike().get(0).getOwnerID()));
                log.setSenderId(String.valueOf(postnew.getLike().get(0).getOwnerID()));
                log.setOwnerId(String.valueOf(post.getOwnerId()));
            }
        } else if (postnew.getLike().get(0).getLike().equals(Relations.DELETE)) {
            if (check != -1) {
                post.deletelike(check);
            }

        }
        if(!notification.getIdSender().equals("")){
            notification.setPost(post);
            saveNoti(notification);
        }
        saveLog(log);
        return  post;
    }
    private static  void  saveNoti(Notification noti){
        //TODO #now 6

            FilesManager.AddLine(FilesPath.USERS + noti.getPost().getOwnerId() +FilesPath.NOTI, noti.convertToJsonString());
        sendLiveNotification(noti);
    }
    static  SocialArrayList loadNoti(String id){
        SocialArrayList list =new SocialArrayList();
        if(FileIsExist(FilesPath.USERS + id+FilesPath.NOTI))
            list.getItems().addAll( FilesManager.readAllLines(FilesPath.USERS +"\\"+ id+FilesPath.NOTI));
       else  list.setExtra("1");
//        sendLiveNotification(list,id);
        return list;
    }
    private static void sendLiveNotification(Notification notification) {
        Command command = new Command();
        command.setKeyWord(Notification.NEW_NOTIFICATION);
        command.setSharableObject(notification);
        NotificationSimplexConnection.sendNotification(notification.getPost().getOwnerId() + "",command);
    }
    private static  void  saveLog(Log log){

        FilesManager.AddLine(FilesPath.USERS + FilesPath.LOG,log.convertToJsonString());
    }
    static  String loadLog(){
        SocialArrayList list =new SocialArrayList();
        if(FileIsExist(FilesPath.USERS + FilesPath.LOG))
            list.getItems().addAll( FilesManager.readAllLines(FilesPath.USERS + FilesPath.LOG));
        else  list.setExtra("1");
        return  list.convertToJsonString();
    }
}
