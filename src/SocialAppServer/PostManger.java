package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import SocialAppGeneral.PostAtachmment;


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
     static ArrayList<Post> PickPosts(String path)  {
          ArrayList <Post> posts= new ArrayList<>();
          String uniqueID="1";
         int countenr=1;
       if(FilesManager.FileIsExist(path+FilesPath.POSTS,"\\uniqueID.txt")) {
           uniqueID = (FilesManager.ReadLine(path + FilesPath.POSTS + "\\uniqueID.txt", 1));
           long uniqueidtemp = Long.valueOf(uniqueID);
           while (uniqueidtemp != 0 && countenr % 10 != 0) {
               if(FilesManager.FileIsExist(path +FilesPath.POSTS + "\\" + uniqueidtemp + Post_FILE)) {
                   posts.add((Post) FilesManager.ReadFromBinaryFile(path + FilesPath.POSTS + "\\" + uniqueidtemp + Post_FILE));

                   countenr++;
               }
               uniqueidtemp--;
           }
           return posts;
       }
       else return  null;
    }

    static void saveAtachment(Post postNew,String path){
        FilesManager.CreateFolder(path,FilesPath.POSTS);
        FilesManager.CreateFolder(path+FilesPath.POSTS,"\\"+postNew.getId()+"");
        Post post1= (Post) FilesManager.ReadFromBinaryFile(path +FilesPath.POSTS+ "\\" +postNew.getId() + Post_FILE);
        if(postNew.getLike().size() !=0) {
            int i = 0;
            int check = -1;
            if(post1.getLike().size() !=0) {
                do {
                    if (post1.getLike().get(i).getOwnerID() == postNew.getLike().get(0).getOwnerID()) {
                        check = i;
                    }
                    i++;
                }
                while (i < post1.getLike().size() && post1.getLike().get(i).getOwnerID() != postNew.getLike().get(0).getOwnerID());
            }
            if ( postNew.getLike().get(0).getLike() != -1) {
                     if(check ==-1){
                post1.addlike((postNew.getLike().get(0)));}
                else {
                         post1.getLike().get(check).setLike(postNew.getLike().get(0).getLike());
                     }
            } else if (  postNew.getLike().get(0).getLike() == -1) {
                if(check !=-1) {
                    post1.deletelike(check);
                }

            }
        }
        else {
            if (postNew.getComments().size() != 0) {
                if (postNew.getComments().get(0).getShow() == 1) {
                    postNew.getComments().get(0).setCommentId((Long.valueOf(Generator.GenerateUnigueId(path + FilesPath.POSTS + "\\" +
                            post1.getId()))));
                    post1.addcomment(postNew.getComments().get(0));
                } else {
                    int i = 0;
                    int check = -1;
                    do {
                        if (post1.getComments().get(i).getCommentId() == postNew.getComments().get(0).getCommentId()) {
                            check = i;
                        }
                        i++;
                    }
                    while (i < post1.getComments().size() && post1.getComments().get(i).getCommentId() != postNew.getComments().get(0).getCommentId());

                    if (postNew.getComments().get(0).getShow() == -1) {
                      if(check !=-1)
                        post1.deletecomment(check);
                    } else if (postNew.getComments().get(0).getShow() == 0) {
                        if(check !=-1)
                        post1.getComments().get(check).setCommentcontent(postNew.getComments().get(0).getCommentcontent());
                    }
                }
            }
            else{
                post1.setContent(postNew.getContent());
            }


        }


        FilesManager.CreateFileBinary(post1,path+FilesPath.POSTS+"\\"+postNew.getId()+Post_FILE);

    }

static void deletepost(String path){
    File file =new File (path);
     FilesManager.delete(file);
}
}
