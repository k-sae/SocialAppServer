package SocialAppServer;

import FileManagment.FilesManager;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
     static Post PickPost(String path,long id)  {
         return ((Post) FilesManager.ReadFromBinaryFile(path+POSTS+"\\"+id+Post_FILE));
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
