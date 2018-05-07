package blackson.n.zeilschooldewaai.models;

public class Cursist extends BaseFirebaseModel {

    private String roepnaam;

    private String tussenvoegsel = "";

    private String achternaam;

    private String emailadres;

    private String telefoon;

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(final String pTelefoon) {
        telefoon = pTelefoon;
    }

    public String getRoepnaam() {
        return roepnaam;
    }

    public void setRoepnaam(final String pRoepnaam) {
        roepnaam = pRoepnaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(final String pTussenvoegsel) {
        tussenvoegsel = pTussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(final String pAchternaam) {
        achternaam = pAchternaam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(final String pEmailadres) {
        emailadres = pEmailadres;
    }
}
