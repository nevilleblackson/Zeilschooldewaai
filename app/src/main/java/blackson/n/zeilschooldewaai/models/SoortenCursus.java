package blackson.n.zeilschooldewaai.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SoortenCursus extends BaseFirebaseModel {

    private String cursuscode;

    private Double prijs;

    private Long soortcode;

    private double mTotal;

    public String getCursusSoort() {
        return cursuscode;
    }

    public void setCursusSoort(final String pCursusSoort) {
        cursuscode = pCursusSoort;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(final Double pPrijs) {
        prijs = pPrijs;
    }

    public Long getSoortcode() {
        return soortcode;
    }

    public void setSoortcode(final Long pSoortcode) {
        soortcode = pSoortcode;
    }

    public void setTotal(final double pTotal) {
        mTotal = pTotal;
    }

    public double getTotal() {
        return mTotal;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && obj instanceof SoortenCursus && ((SoortenCursus) obj).soortcode.equals(soortcode);
    }
}
