package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;

import SocialAppGeneral.SocialArrayList;

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
    }
    static SocialArrayList pickGroups(SocialArrayList id) {
        SocialArrayList groups = new SocialArrayList();
        if ( !id.getItems().get(0).equals(0)){
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
            list = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);
        }
        else {
            list.getItems().add(0);
        }
            return list;
    }
    static  void addGroupToMember(long userid, long groupid){
        SocialArrayList groups=new SocialArrayList();
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
             groups = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);
            groups.getItems().add(groupid);
        }
         else {
            groups.getItems().add(groupid);
        }
        FilesManager.CreateFileBinary(groups,FilesPath.USERS + "\\" + userid + GROUP);
    }
    static  Group load(long id){
    Group group=new Group();
    if(FilesManager.FileIsExist(FilesPath.GROUPS+"\\"+id+INFO)) {
        group= (Group) FilesManager.ReadFromBinaryFile(FilesPath.GROUPS+"\\"+id+INFO);
    }
    return  group;
}
}

