package blackson.n.zeilschooldewaai.adapters.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.SimpleFormatter;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.models.SoortenCursus;

@EViewGroup(R.layout.view_curses_overview)
public class CursesOverviewAdapterView extends FrameLayout {

    @ViewById(R.id.name)
    TextView name;

    @ViewById(R.id.opbrengste)
    TextView opbrengste;

    private SoortenCursus mCursus;

    private CursesOverviewListener mListener;

    public CursesOverviewAdapterView(@NonNull final Context context) {
        super(context);
    }

    public void bind(final SoortenCursus pCursus, CursesOverviewListener pListener) {
        name.setText(pCursus.getCursusSoort());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        opbrengste.setText("€ " + decimalFormat.format(pCursus.getTotal()));
        mCursus = pCursus;
        mListener = pListener;
    }

    @Click(R.id.container)
    void onContainerClicked() {
        mListener.onClick(mCursus);
    }

    public interface CursesOverviewListener {

        void onClick(SoortenCursus pCursus);

    }
}