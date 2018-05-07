package blackson.n.zeilschooldewaai.models;

import com.google.gson.annotations.SerializedName;

public class Cursus extends BaseFirebaseModel {

    private Long begindatum;

    private Long cursuscode;

    private Long einddatum;

    private Long soortcode;

    public Long getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(final Long pBegindatum) {
        begindatum = pBegindatum;
    }

    public Long getCursuscode() {
        return cursuscode;
    }

    public void setCursuscode(final Long pCursuscode) {
        cursuscode = pCursuscode;
    }

    public Long getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(final Long pEinddatum) {
        einddatum = pEinddatum;
    }

    public Long getSoortcode() {
        return soortcode;
    }

    public void setSoortcode(final Long pSoortcode) {
        soortcode = pSoortcode;
    }
}
