package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Group;
import SocialAppGeneral.SocialArrayList;

import java.util.ArrayList;

/**
 * Created by khaled hesham on 11/25/2016.
 */
class GroupfileMangement implements FilesPath{

    private static final String REQ = "\\req";
    private static final String INFO= "\\info";
    private static final String GROUP= "\\groupId";




    public static String getREQ() {
        return REQ;
    }

    public static String getINFO() {
        return INFO;
    }

    static void create (Group group) {
        FilesManager.CreateFolder(GROUPS);
        FilesManager.CreateFolder(GROUPS,group.getId()+"");
        FilesManager.CreateFileBinary(group,GROUPS+"\\"+group.getId()+INFO);
        FilesManager.AddLine(FilesPath.USERS+FilesPath.NAMES+Generator.GenerateID(group.getName()+".txt"),group.getName()+"&&&ID=["+group.getId()+"]");
    }
    static SocialArrayList pickGroups(SocialArrayList id) {
        SocialArrayList groups = new SocialArrayList();
        if ( !id.getItems().get(0).equals(0)){
            for (int i = 0; i < id.getItems().size(); i++) {
                if (FilesManager.FileIsExist(FilesPath.GROUPS + "\\" + id.getItems().get(i))) {
                    groups.getItems().add(FilesManager.ReadFromBinaryFile(FilesPath.GROUPS + "\\" + id.getItems().get(i) + INFO));

                }


            }
        }
            return groups;

    }
    static SocialArrayList pickMemberGroup(long userid ){
        SocialArrayList list=new SocialArrayList();
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
            list = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);

            System.out.println(list.convertToJsonString());
        }
        else {
            list.getItems().add(0);
        }
            return list;



    }
    static  void addgrouptomember(long userid,long groupid){
        SocialArrayList groups=new SocialArrayList();
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+userid+GROUP)) {
             groups = (SocialArrayList) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + userid + GROUP);
            System.out.println(groups.convertToJsonString());
            groups.getItems().add(groupid);
        }
         else {
            groups.getItems().add(groupid);
        }
        FilesManager.CreateFileBinary(groups,FilesPath.USERS + "\\" + userid + GROUP);
    }
    /*
    static void addMembertogroup(long id){
        if(FilesManager.FileIsExist(FilesPath.USERS+"\\"+id+GROUP)) {
            ArraylistGroup groups = (ArraylistGroup) FilesManager.ReadFromBinaryFile(FilesPath.USERS + "\\" + id + GROUP);


        }
    }
    */

}
