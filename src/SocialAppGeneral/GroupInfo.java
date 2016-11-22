package SocialAppGeneral;

/**
 * Created by khaled hesham on 11/12/2016.
 */
 public class GroupInfo {
    private  String Groupname;
    private  Boolean Pinpost;
    private  int Imageid;
    private  int Groupid;
    public int getGroupid(){
      return Groupid;
    }
    public int getImageid(){
        return Imageid;
    }
    public String getGroupname(){
        return  Groupname;
    }
    public Boolean getPinpost(){
        return  Pinpost;
    }
     public void setGroupname(String name){
         Groupname=name;
     }
     public void  setPinpost(Boolean pin){
         Pinpost=pin;
     }
    public void setImageid(int imageid) {
        Imageid = imageid;
    }
    public void setGroupid(int groupid){
        Groupid=groupid;
    }

}
