package SocialAppServer.Connections;

/**
 * Created by kemo on 15/12/2016.
 */
public interface EmailContent {
    String ACCEPTED_MSG_SUBJECT = "ACCEPTED";
    String ACCEPTED_MSG_BODY = "u have been accepted you in Batates Network..HF!";
    String REJECTED_MSG_SUBJECT = "REJECTED";
    String REJECTED_MSG_BODY = "Sorry,u have been rejected in Batates Network!";
    String ACCEPTED_AS_ADMIN_MSG_SUBJECT = "ACCEPTED AS ADMIN";
    String ACCEPTED_AS_ADMIN_MSG_BODY = "u have been accepted as admin in Batates Network..HF!";
    String DEACTIVATE_MSG_SUBJECT = "Please help us to improve our service by participating in this brief survey";
    String DEACTIVATE_MSG_BODY = "We are sorry about that, please give us a feedback...\n" +
            "https://docs.google.com/forms/d/e/1FAIpQLSfkrDTKFo1MTiCyzXMccRaiL-D7Bm0FhgPFO82j-l3G3OdsFA/viewform";
}
