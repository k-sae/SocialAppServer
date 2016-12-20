package SocialAppServer.Module;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import SocialAppGeneral.SocialArrayList;
import SocialAppServer.Control.Generator;

import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/25/2016.
 */
public class GroupFileManagement implements FilesPath{

    private static final String REQ = "\\reqGroups";
    private static final String INFO= "\\info";
    private static final String GROUP= "\\groupId";
    public static void create(Group group) {
        FilesManager.CreateFolder(GROUPS);
        group.setId(Long.parseLong(Generator.GenerateUnigueId(FilesPath.GROUPS)));
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+INFO);
        FilesManager.AddLine(FilesPath.USERS+FilesPath.NAMES+Generator.GenerateID(group.getName()+".txt"),group.getName()+"&&&ID=["+group.getId()+"]");
        FilesManager.AddLineWithoutAppend(GROUPS+group.getId()+"\\"+MEMBERS,Long.toString(group.getAdminId()));
    }
    public static SocialArrayList pickGroups(SocialArrayList id) {
        SocialArrayList groups = new SocialArrayList();
        if ( !id.getItems().isEmpty()){
            for (int i = 0; i < id.getItems().size(); i++) {
                if (FilesManager.FileIsExist(FilesPath.GROUPS + "\\" + id.getItems().get(i))) {
                    groups.getItems().add(((Group)FilesManager.ReadFromBinaryFile(FilesPath.GROUPS + "\\" + id.getItems().get(i) + INFO)).convertToJsonString());

                }
            }
        }
            return groups;
    }
    public static SocialArrayList pickMemberGroup(long userid){
        SocialArrayList list=new SocialArrayList();
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
            //noinspection unchecked
            list.setItems((ArrayList<Object>)(ArrayList<?>)FilesManager.ReadArrayList(USERS+userid+"\\"+GROUP));
         System.out.println(list.convertToJsonString());
        }
        return list;



    }
    public static  void addgrouptomember(String userid, String groupid){
       FilesManager.AddLine(USERS+userid+"\\"+GROUP,groupid);
    }
    public static  Group load(long id){
    Group group=new Group(id);
    if(FilesManager.FileIsExist(FilesPath.GROUPS+"\\"+id+INFO)) {
        group= (Group) FilesManager.ReadFromBinaryFile(FilesPath.GROUPS+"\\"+id+INFO);
    }
    return  group;
}
public static  void deleteFromGroup(String id, String groupId){
FilesManager.RemoveLine(USERS+id+"\\"+GROUP,groupId);

}
}

