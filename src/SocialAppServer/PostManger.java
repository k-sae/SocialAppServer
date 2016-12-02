package SocialAppServer;

import FileManagment.FilesManager;
import SocialAppGeneral.Post;


import java.io.IOException;
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
    public static Post PickPost(String path,long id){
         Post post=new Post();
        FilesManager.ReadFromBinaryFile(post,path+POSTS+"\\"+id+Post_FILE);
        return (post);
    }

}
