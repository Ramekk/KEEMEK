 package rus.ramek.project.apimodel;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

 /**
 * Created by Ramek on 12/9/2559.
 */
@Parcel
public class LoginResponseModel {
    @SerializedName("error") // 0 = Valid , 1 = Username is not exists ,
    private int error;

    @SerializedName("u_id")
    private String userId;

    @SerializedName("u_username")
    private String username;

    @SerializedName("u_name") // Full Name
    private String fullName;

    @SerializedName("u_status") // S0001 = Normal , S0000 = Approve waiting , S0002 = Account Disabled
    private String userStatus;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
