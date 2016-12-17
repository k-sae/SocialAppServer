package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static FileManagment.FilesManager.*;

/**
 * Created by khaled hesham on 12/2/2016.
 */
class PostManger {

    private static final String Post_FILE="\\post";

    static Post SavePost(Post post,String path)  {
        post.setDate(new Date());
        FilesManager.CreateFolder(path, FilesPath.POSTS);
        post.setId(Long.valueOf(Generator.GenerateUnigueId(path+FilesPath.POSTS)));
        FilesManager.CreateFolder(path+FilesPath.POSTS,"\\"+post.getId()+"");
        FilesManager.CreateFileBinary(post,path+FilesPath.POSTS+"\\"+post.getId()+Post_FILE);
        return (post);
    }
     static Post PickonePost(String path,long id)  {
         return ((Post) FilesManager.ReadFromBinaryFile(path+FilesPath.POSTS+"\\"+id+Post_FILE));
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
    static SocialArrayList pickPostsHome(ArrayList<String> id, long numberPost){
      SocialArrayList posts =new SocialArrayList();
        String uniqueID;
        int counter3;//count the file empty or no
        int counter1=1;// count 10 posts
        int counter2;//count number post
        boolean check;//check post add or no
        int  level = 0;//check level posts
        int counter4 ;//count number leves
        do {
           counter3=1;
            for(int i=id.size()-1;i>=0&&counter1 % 11 != 0;i--) {
                counter4=0;
                if (FilesManager.FileIsExist(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS, "\\uniqueID.txt")) {
                    uniqueID = (FilesManager.ReadLine(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS + "\\uniqueID.txt", 1));
                    long uniqueIdTemp = Long.valueOf(uniqueID);
                    counter2=1;
                    do {
                        check=true;
                        if (FilesManager.FileIsExist(FilesPath.USERS + "\\" +id.get(i)+ FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)) {
                            if(level==counter4) {
                                check = false;
                                counter2++;
                                if (counter2 >= ((numberPost - 1) * 10) + 1 ) {

                                    posts.getItems().add(((Post) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id.get(i) + FilesPath.POSTS + "\\" + uniqueIdTemp + Post_FILE)).convertToJsonString());
                                    counter1++;
                                }
                            }
                            counter4++;

                        }
                        uniqueIdTemp--;
                        if(uniqueIdTemp==0){
                            counter3++;
                        }
                    } while (uniqueIdTemp != 0 && check&& counter1 % 11 != 0);
                }
                else {counter3++;}
            }
        level++;
        }while(counter1 % 11 != 0 &&counter3 <= id.size());
        return posts;
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
    static  Post saveAtachment(Post postNew,String path){
        Post post1 =new Post();
        if(FilesManager.FileIsExist(path +FilesPath.POSTS+ "\\" +postNew.getId() + Post_FILE)) {
             post1 = (Post) FilesManager.ReadFromBinaryFile(path + FilesPath.POSTS + "\\" + postNew.getId() + Post_FILE);
            if (postNew.getLike().size() != 0) {
                int i = -1;
                int check = -1;
                if (post1.getLike().size() != 0) {
                    do {
                        i++;
                        if (post1.getLike().get(i).getOwnerID() == postNew.getLike().get(0).getOwnerID()) {
                            check = i;
                        }

                    }
                    while (i < post1.getLike().size() && post1.getLike().get(i).getOwnerID() != postNew.getLike().get(0).getOwnerID());
                }
                if (postNew.getLike().get(0).getLike() != -1) {
                    if (check == -1) {
                        post1.addlike((postNew.getLike().get(0)));
                    } else {
                        post1.getLike().get(check).setLike(postNew.getLike().get(0).getLike());
                    }
                } else if (postNew.getLike().get(0).getLike() == -1) {
                    if (check != -1) {
                        post1.deletelike(check);
                    }

                }
            } else {
                if (postNew.getComments().size() != 0) {
                    if (postNew.getComments().get(0).getShow() == 1) {
                        postNew.getComments().get(0).setCommentId((Long.valueOf(Generator.GenerateUnigueId(path + FilesPath.POSTS + "\\" +
                                post1.getId()))));
                        post1.addcomment(postNew.getComments().get(0));
                    } else {
                        int i = -1;
                        int check = -1;
                        do {
                            i++;
                            if (post1.getComments().get(i).getCommentId() == postNew.getComments().get(0).getCommentId()) {
                                check = i;
                            }

                        }
                        while (i < post1.getComments().size() && post1.getComments().get(i).getCommentId() != postNew.getComments().get(0).getCommentId());

                        if (postNew.getComments().get(0).getShow() == -1) {
                            if (check != -1)
                                post1.deletecomment(check);
                        } else if (postNew.getComments().get(0).getShow() == 0) {
                            if (check != -1)
                                post1.getComments().get(check).setCommentcontent(postNew.getComments().get(0).getCommentcontent());
                        }
                    }
                } else {
                    post1.setContent(postNew.getContent());
                }


            }
            FilesManager.CreateFileBinary(post1, path + FilesPath.POSTS + "\\" + postNew.getId() + Post_FILE);
        }
        else{post1.setId(0);}
            return post1;
    }
    static  void  saveNoti(Notification noti){
        FilesManager.CreateFolder(FilesPath.USERS + noti.getPost().getOwnerId(), FilesPath.NOTI);
        noti.setId(Long.valueOf(Generator.GenerateUnigueId(FilesPath.USERS + noti.getPost().getOwnerId()+FilesPath.NOTI)));
        FilesManager.CreateFileBinary(noti,FilesPath.USERS + noti.getPost().getOwnerId()  );
    }
}
