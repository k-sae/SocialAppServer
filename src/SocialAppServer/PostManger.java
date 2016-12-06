package SocialAppServer;

import FileManagment.FilesManager;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

import static FileManagment.FilesManager.*;

/**
 * Created by khaled hesham on 12/2/2016.
 */
class PostManger {
    private static final String Comment_ID="\\comment_ID";
    private static final String Post_FILE="\\post";
    private static final String POSTS= "\\posts";
    static Post SavePost(Post post,String path)  {
        post.setDate(new Date());
        FilesManager.CreateFolder(path,POSTS);
        post.setId(Long.valueOf(Generator.GenerateUnigueId(path+POSTS)));
        FilesManager.CreateFolder(path+POSTS,"\\"+post.getId()+"");
        FilesManager.CreateFileBinary(post,path+POSTS+"\\"+post.getId()+Post_FILE);
        return (post);
    }
     static Post PickonePost(String path,long id)  {
         return ((Post) FilesManager.ReadFromBinaryFile(path+POSTS+"\\"+id+Post_FILE));
    }
     static ArrayList<Post> PickPosts(String path)  {
          ArrayList <Post> posts= new ArrayList<>();
          String uniqueID="1";
         int countenr=1;
       if(FilesManager.FileIsExist(path+POSTS,"\\uniqueID.txt")) {
           uniqueID = (FilesManager.ReadLine(path + POSTS + "\\uniqueID.txt", 1));
           long uniqueidtemp = Long.valueOf(uniqueID);
           while (uniqueidtemp != 0 && countenr % 10 != 0) {
               posts.add((Post) FilesManager.ReadFromBinaryFile(path + POSTS + "\\" + uniqueidtemp + Post_FILE));
               uniqueidtemp--;
               countenr++;
           }
           return posts;
       }
       else return  null;
    }
    static Post addComment(String path,long id,Post post){
       Comment comment= post.getComments().get(post.getComments().size()-1);
        comment.setCommentId(Long.valueOf(Generator.GenerateUnigueId(path+POSTS+"\\"+id)));
        post.getComments().remove(post.getComments().size()-1);
        post.addcomment(comment);
        FilesManager.CreateFileBinary(post,path+POSTS+"\\"+post.getId()+Post_FILE);
        return post;
    }
    static void savePostWithoutId(Post post,String path){
        FilesManager.CreateFolder(path,POSTS);
        FilesManager.CreateFolder(path+POSTS,"\\"+post.getId()+"");
        FilesManager.CreateFileBinary(post,path+POSTS+"\\"+post.getId()+Post_FILE);
    }

}
