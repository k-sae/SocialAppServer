package SocialAppServer;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Admin;
import SocialAppGeneral.RegisterInfo;
import SocialAppServer.Generator;
import SocialAppServer.ServerLoggedUser;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by mosta on 12-Dec-16.
 */
public class ServerAdmin extends ServerLoggedUser implements FilesPath , Admin {
    public ServerAdmin(String id) {
        super(id);
    }
    public static Boolean adminChecker(String ID){
        return FilesManager.searcher(USERS+ADMINS,ID);
    }
    public static boolean adminCheck(String Email){
        if(FilesManager.FileIsExist(USERS+ADMINS)){
            //wait admin approval to add another admin
            return false;
        }else{
            return true;
        }
    }

    public  static void  convertIntoPermnantUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
        String ID = Generator.GenerateUnigueId(USERS);
        FilesManager.CreateFolder(USERS + ID);
        FilesManager.CreateFile(USERS+ID+"\\"+INFO);
        FilesManager.AddLineWithoutAppend(USERS + ID+"\\"+INFO,re.getUserInfo().convertToJsonString());
        if(FilesManager.FileIsExist(USERS+EMAILS)){
            FilesManager.AddLine(USERS+EMAILS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().convertToJsonString()+"ID=["+ID+"]");
        }else{
            FilesManager.CreateFolder(USERS+EMAILS);
            FilesManager.AddLine(USERS+EMAILS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().convertToJsonString()+"ID=["+ID+"]");
        }
        if(FilesManager.FileIsExist(USERS+NAMES)){
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"ID=["+ID+"]");
        }else{
            FilesManager.CreateFolder(USERS+NAMES);
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"ID=["+ID+"]");
        }

        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
    }
    //Check if needing modification
    public void convertIntoBannedUser(String Email) {
        String line = FilesManager.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
       // String ID = Generator.GenerateUnigueId(BLOCKEDUSERS);
        FilesManager.AddLine(BLOCKEDUSERS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().getEMAIL());
        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
    }
   public ArrayList<String> fetchRequests(){
       ArrayList<String> strings = new ArrayList<>();
       strings = FilesManager.ReadArrayList(UNREIGESTERDUSERS+AllUSERS);
       return strings;
   }
    @Override
    public void approve(String Email) {
        convertIntoPermnantUser(Email);
    }

    @Override
    public void reject(String Email) {
        convertIntoBannedUser(Email);
    }

    @Override
    public void approveAsAdmin(String Email) {
        convertIntoPermnantUser(Email);
        String Line=FilesManager.FileSearcherForID(USERS+EMAILS+ Generator.GenerateID(Email)+".txt",Email);
        Line=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        FilesManager.AddLine(USERS+ADMINS,Line);
    }
    public static boolean sendMail(String from,String pass,String to,String subject,String body)
    {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,null);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        return false;
    }
}
