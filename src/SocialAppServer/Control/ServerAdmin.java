package SocialAppServer.Control;

import FileManagment.FilesManager;
import FileManagment.FilesPath;
import SocialAppGeneral.Admin;
import SocialAppGeneral.RegisterInfo;
import SocialAppServer.Connections.Credentials;
import SocialAppServer.Connections.EmailContent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by mosta on 12-Dec-16.
 */
public class ServerAdmin extends ServerLoggedUser implements FilesPath , Admin {
   public ServerAdmin(String id) {
        super(id);
    }
    static Boolean adminChecker(String ID){
        return FilesManager.searcher(USERS+ADMINS,ID);
    }
    static boolean adminCheck(){
        return !FilesManager.FileIsExist(USERS + ADMINS);
    }

    private static void  convertIntoPermnantUser(String Email) {
        String line = Verifier.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
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
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"&&&"+"ID=["+ID+"]");
        }else{
            FilesManager.CreateFolder(USERS+NAMES);
            FilesManager.AddLine(USERS+NAMES+Generator.GenerateID(re.getUserInfo().getFullName())+".txt",re.getUserInfo().getFullName()+"&&&"+"ID=["+ID+"]");
        }

        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
        /** SENDING ACCEPTED EMAIL IF THE ADMIN ACCEPT*/
        new Thread(() -> sendMail(Credentials.E_MAIL,Credentials.PASSWORD,Email, EmailContent.ACCEPTED_MSG_SUBJECT,EmailContent.ACCEPTED_MSG_BODY)).start();
      }
    //Check if needing modification
    private void convertIntoBannedUser(String Email) {
        String line = Verifier.FileSearcher(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt", Email);
        RegisterInfo re = RegisterInfo.fromJsonString(line);
       // String ID = Generator.GenerateUnigueId(BLOCKEDUSERS);
        FilesManager.AddLine(BLOCKEDUSERS+Generator.GenerateID(re.getLoginInfo().getEMAIL())+".txt",re.getLoginInfo().getEMAIL());
        FilesManager.RemoveLine(UNREIGESTERDUSERS + Generator.GenerateID(Email)+".txt",Email);
        FilesManager.RemoveLine(UNREIGESTERDUSERS+AllUSERS,Email);
        /** SENDING REJECTED EMAIL IF THE ADMIN REFUSE*/
        sendMail(Credentials.E_MAIL,Credentials.PASSWORD,Email,EmailContent.REJECTED_MSG_SUBJECT,EmailContent.REJECTED_MSG_BODY);

    }
   ArrayList<String> fetchRequests(){
       ArrayList<String> strings;
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
        String Line=Verifier.FileSearcherForID(USERS+EMAILS+ Generator.GenerateID(Email)+".txt",Email);
        Line=Line.substring(Line.indexOf('[')+1,Line.indexOf(']'));
        FilesManager.AddLine(USERS+ADMINS,Line);
        /** SENDING ACCEPTED AS ADMIN EMAIL IF THE ADMIN ACCEPT*/
        new Thread(() -> sendMail(Credentials.E_MAIL,Credentials.PASSWORD,Email, //u must pass a valid name and pass up here
                EmailContent.ACCEPTED_AS_ADMIN_MSG_SUBJECT
                ,EmailContent.ACCEPTED_AS_ADMIN_MSG_BODY)).start();


    }
    static boolean sendMail(String from, String pass, String to, String subject, String body)
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
        } catch (MessagingException me) {
          System.out.println("Failed to send mail");
        }
        return false;
    }
}
