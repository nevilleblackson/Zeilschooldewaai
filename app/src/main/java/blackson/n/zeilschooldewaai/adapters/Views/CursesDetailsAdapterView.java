package blackson.n.zeilschooldewaai.adapters.Views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.models.Cursist;

@EViewGroup(R.layout.view_cursist)
public class CursesDetailsAdapterView extends LinearLayout {

    @ViewById(R.id.name)
    TextView mName;

    @ViewById(R.id.email)
    TextView mEmail;

    @ViewById(R.id.tel)
    TextView mTelefoon;

    public CursesDetailsAdapterView(final Context context) {
        super(context);
    }

    public void bind(final Cursist pItem) {
        mName.setText(pItem.getRoepnaam() + " " + pItem.getTussenvoegsel() + " " + pItem.getAchternaam());
        mTelefoon.setText(pItem.getTelefoon());
        mEmail.setText(pItem.getEmailadres());
    }

}
