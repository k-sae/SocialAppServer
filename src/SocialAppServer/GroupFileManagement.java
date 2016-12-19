package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import SocialAppGeneral.SocialArrayList;

import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/25/2016.
 */
class GroupFileManagement implements FilesPath{

    private static final String REQ = "\\reqGroups";
    private static final String INFO= "\\info";
    private static final String GROUP= "\\groupId";
    static void create (Group group) {
        FilesManager.CreateFolder(GROUPS);
        group.setId(Long.parseLong(Generator.GenerateUnigueId(FilesPath.GROUPS)));
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+INFO);
        FilesManager.AddLine(FilesPath.USERS+FilesPath.NAMES+Generator.GenerateID(group.getName()+".txt"),group.getName()+"&&&ID=["+group.getId()+"]");
        FilesManager.AddLineWithoutAppend(GROUPS+group.getId()+"\\"+MEMBERS,Long.toString(group.getAdminId()));
    }
    static SocialArrayList pickGroups(SocialArrayList id) {
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
    static SocialArrayList pickMemberGroup(long userid ){
        SocialArrayList list=new SocialArrayList();
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
//<<<<<<< HEAD:src/SocialAppServer/GroupfileMangement.java
        list.setItems((ArrayList<Object>)(ArrayList<?>)FilesManager.ReadArrayList(USERS+userid+"\\"+GROUP));
           // list.getItems().addAll(FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP)) ;

            System.out.println(list.convertToJsonString());
        }
        return list;



    }
    static  void addgrouptomember(String userid,String groupid){
       FilesManager.AddLine(USERS+userid+"\\"+GROUP,groupid);
        // SocialArrayList groups=new SocialArrayList();
     //   if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
       //      groups.getItems().addAll((ArrayList<Object>)(ArrayList<?>)FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP)) ;
         //   groups.getItems().add(groupid);
       // }
         //else {
           // groups.getItems().add(groupid);
        //}
        //FilesManager.CreateFileBinary(groups,FilesPath.USERS + "\\" + userid + GROUP);
//=======
//            list = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);
//        }
//        else {
//            list.getItems().add(0);
//        }
//            return list;
//    }
//    static  void addGroupToMember(long userid, long groupid){
//        SocialArrayList groups=new SocialArrayList();
//        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
//             groups = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);
//            groups.getItems().add(groupid);
//        }
//         else {
//            groups.getItems().add(groupid);
//        }
//        FilesManager.CreateFileBinary(groups,FilesPath.USERS + "\\" + userid + GROUP);
//>>>>>>> develop:src/SocialAppServer/GroupFileManagement.java
    }
    static  Group load(long id){
    Group group=new Group();
    if(FilesManager.FileIsExist(FilesPath.GROUPS+"\\"+id+INFO)) {
        group= (Group) FilesManager.ReadFromBinaryFile(FilesPath.GROUPS+"\\"+id+INFO);
    }
    return  group;
}
static  void deleteFromGroup(String id ,String groupId){
FilesManager.RemoveLine(USERS+id+"\\"+GROUP,groupId);

}
}

