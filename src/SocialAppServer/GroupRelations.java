package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;
import SocialAppGeneral.RelationGroup;

import java.util.ArrayList;

/**
 * Created by mosta on 17-Dec-16.
 */
public class GroupRelations implements FilesPath{
        private String id;
        private  String currentDirectory;
     public GroupRelations(String id) {
        this.id   = id ;
        currentDirectory = GROUPS + id + "\\";
    }
    RelationGroup getStatus(String id)
    {

         if(FilesManager.StringFinder(currentDirectory + MEMBERS, id )) {
            return RelationGroup.MEMBER;
        } else{
       return RelationGroup.NOT_MEMBER;
    }
    }
    //id user
    Boolean leaveGroup(String id){
        FilesManager.RemoveLine(currentDirectory+MEMBERS,id);
        GroupfileMangement.deleteFromGroup(id,this.id);
        return true;
    }
    Boolean addToGroup(String id){
        FilesManager.AddLine(currentDirectory+GROUP_REQUEST,id);
        GroupfileMangement.addgrouptomember(id,this.id);
        return true;
    }
    ArrayList<String> fetch(){
    return  FilesManager.ReadArrayList(currentDirectory+GROUP_REQUEST);
    }
    void AcceptRequest(String id){
FilesManager.RemoveLine(currentDirectory+GROUP_REQUEST,id);
        FilesManager.AddLine(currentDirectory+MEMBERS,id);
    }
}
