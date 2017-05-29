package SocialAppServer.Control;

import FileManagment.Saver;
import SocialAppGeneral.Command;
import SocialAppGeneral.Error;
import SocialAppGeneral.RegisterInfo;
import SocialAppGeneral.SocialArrayList;
import SocialAppServer.Connections.HalfDuplexConnection;

import java.time.Year;
import java.util.ArrayList;
import java.util.regex.Pattern;



/**
 * Created by mosta on 09-Dec-16.
 */
public class validator {
    Error error;
    public validator(RegisterInfo info, HalfDuplexConnection connection){
        Command command = new Command();
        command.setKeyWord(RegisterInfo.KEYWORD);
        ArrayList<String> errors=new ArrayList<>();
      if(info.getLoginInfo().getEMAIL().equals("")||info.getLoginInfo().getPassword().equals("")||info.getUserInfo().getFullName().equals("")||info.getUserInfo().getBirthDate().equals("")||info.getUserInfo().getGender().equals("")){
          this.error=error.EmptyRegister;
          command.setSharableObject(error.EmptyRegister.toString());
          connection.sendCommand(command);
      }else {
          if(!valdiateEmail(info.getLoginInfo().getEMAIL())){
              errors.add(error.Wrongemail.toString());
          }if (!valdiatePass(info.getLoginInfo().getPassword())){
              errors.add(error.Wrongpass.toString());
          }if (!datecheck(info.getUserInfo().getBirthDate())){
              errors.add(error.WrongDate.toString());
          }if(!valdiateName(info.getUserInfo().getFullName())){
              errors.add(error.Wrongname.toString());
          }
          if(errors.size()!=0){
          SocialArrayList socialArrayList = new SocialArrayList(errors);
          command.setSharableObject(socialArrayList.convertToJsonString());
              connection.sendCommand(command);
          }else{
              new Saver(info,connection);
          }

      }


    }
    public static Boolean valdiateName(String name){
        return Pattern.matches("[A-Za-z]{1,10}+(\\s[A-Za-z]{1,10}+)?",name);
    }
    public static Boolean valdiatePass(String pass){
        return Pattern.matches("[a-zA-Z0-9]{8,18}",pass);
    }

    public static Boolean valdiateEmail(String email){
        if(Pattern.matches("^[a-zA-Z0-9._]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",email))
        {
            return !email.contains("..");
        }
        return false;
    }
    public static boolean datecheck(String date){
        int year = Year.now().getValue();
        return(Integer.parseInt(date.substring(0,4))<year&&Integer.parseInt(date.substring(0,4))>year-100);
    }
}
