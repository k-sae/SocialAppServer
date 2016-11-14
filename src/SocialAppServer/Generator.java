package SocialAppServer;

/**
 * Created by begad on 10/30/2016.
 */
public class Generator {
    public static String GenerateID(String email) {
        email=email.toLowerCase();
        String modified="";
        for (int i=0;i<email.length();i++){
            if(email.charAt(i)<97||email.charAt(i)>122){

            }else{
                modified+=(email.charAt(i));
            }
        }
        if(modified.length()>9)
            modified=modified.substring(0,9);
        String ID="";
        for(int i=0;i<modified.length();i++)
            ID+=modified.charAt(i)-97;

        return ID;
    }
    }
