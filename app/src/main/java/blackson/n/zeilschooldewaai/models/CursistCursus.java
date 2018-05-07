package blackson.n.zeilschooldewaai.models;

import com.google.gson.annotations.SerializedName;

public class CursistCursus extends BaseFirebaseModel {

    private String email;

    private Long cursuscode;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String pEmail) {
        email = pEmail;
    }

    public Long getCursusCode() {
        return cursuscode;
    }

    public void setCursusCode(final Long pCursusCode) {
        cursuscode = pCursusCode;
    }
}
