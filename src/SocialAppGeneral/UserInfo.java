package SocialAppGeneral;


/**
 * Created by kemo on 24/10/2016.
 */
//created it for debugging
public class UserInfo implements Shareable {
  private final String FULL_NAME = "fullName";
  private final String BIRTH_DATE = "birthDate";
  private final String GENDER = "gender";
  private String fullName;
  private String birthDate;
  private String gender;
  public UserInfo() {
    this.fullName = "";
    this.birthDate = "";
    this.gender = "";
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public void setAttributes(String s) {
    FormedLine formedLine = new FormedLine();
    formedLine.setLine(s);
    fullName = formedLine.ReadPartition(FULL_NAME).Value;
    birthDate = formedLine.ReadPartition(BIRTH_DATE).Value;
    gender = formedLine.ReadPartition(GENDER).Value;
  }

  @Override
  public String convertToString() {
    //use Begad Class
    FormedLine formedLine = new FormedLine();
    formedLine.AddPartition(FULL_NAME, fullName);
    formedLine.AddPartition(BIRTH_DATE, birthDate);
    formedLine.AddPartition(GENDER, gender);
    return formedLine.getLine();
  }
}