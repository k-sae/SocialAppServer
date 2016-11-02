package Test;

import FilesManeger.*;
import SocialAppServer.*;

/**
 * Created by begad on 10/30/2016.
 */
public class Main {
    public static void main (String[] args)
    {
        RegisterUser RUser = new RegisterUser();

        RUser.getLoginInfo().setEmail("Soso");
        RUser.getLoginInfo().setPassword("123abc");

        RUser.getUserInfo().setFullName("sosn sophy");
        RUser.getUserInfo().setBirthDate("2/6/1990");
        RUser.getUserInfo().setGender("Female");

        System.out.println();
        System.out.println("Condition : "+(Saver.SaveTemp(RUser)? "User is Saved" : "User is Already Exist"));
    }
}
