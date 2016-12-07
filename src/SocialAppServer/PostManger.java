package SocialAppServer;

import FileManagment.FilesManager;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import SocialAppGeneral.PostAtachmment;


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
    private static final String Comment_ID="\\commenta_ID";
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
    static void saveAtachment(Post postNew,String path){
        FilesManager.CreateFolder(path,POSTS);
        FilesManager.CreateFolder(path+POSTS,"\\"+postNew.getId()+"");
        Post post1= (Post) FilesManager.ReadFromBinaryFile(path + POSTS + "\\" +postNew.getId() + Post_FILE);
        if(postNew.getLike().size() !=0) {
            if (postNew.getLike().size() != 0 && postNew.getLike().get(0).getLike() != -1) {

                post1.addlike((postNew.getLike().get(0)));
            } else if (postNew.getLike().size() != 0 && postNew.getLike().get(0).getLike() == -1) {
                int i = 0;
                int check = -1;
                do {
                    if (post1.getLike().get(i).getOwnerID() == postNew.getLike().get(0).getOwnerID()) {
                        check = i;
                    }
                    i++;
                }
                while (i < post1.getLike().size() && post1.getLike().get(i).getOwnerID() != postNew.getLike().get(0).getOwnerID());
                post1.deletelike(check);

            }
        }
        if(postNew.getComments().size() != 0 && postNew.getComments().get(0).isShow()){
            postNew.getComments().get(0).setCommentId((Long.valueOf(Generator.GenerateUnigueId(path + POSTS + "\\" +
                    post1.getId()))));
           post1.addcomment(postNew.getComments().get(0));
        }
        else if(postNew.getComments().size() !=0 &&!postNew.getComments().get(0).isShow() ){
            int i = 0;
            int check = -1;
            do {
                if(post1.getComments().get(i).getOwnerID() == postNew.getComments().get(0).getOwnerID()) {
                    check = i;
                }
                i++;
            }while (i<post1.getComments().size()&&post1.getComments().get(i).getOwnerID() !=  postNew.getComments().get(0).getOwnerID());
            post1.deletecomment(check);
        }
        FilesManager.CreateFileBinary(post1,path+POSTS+"\\"+postNew.getId()+Post_FILE);

    }

}
