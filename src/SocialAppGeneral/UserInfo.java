package SocialAppGeneral;


import com.google.gson.Gson;

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

  public String convertToJsonString() {
    //TODO #hazem
    Gson gson = new Gson();
    return gson.toJson(this);
  }
  public static UserInfo fromJsonString(String jsonStr) {
    //TODO #prototype GSON
    //Read from JSON
    Gson gson = new Gson();
    return  gson.fromJson(jsonStr,UserInfo.class);
  }
}