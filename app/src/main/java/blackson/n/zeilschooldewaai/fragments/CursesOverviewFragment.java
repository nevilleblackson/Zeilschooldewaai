package blackson.n.zeilschooldewaai.fragments;

import android.app.DatePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.adapters.CursesOverviewAdapter;
import blackson.n.zeilschooldewaai.fragments.views.CursesOverviewView;
import blackson.n.zeilschooldewaai.managers.ToolbarManager;
import blackson.n.zeilschooldewaai.models.SoortenCursus;
import blackson.n.zeilschooldewaai.presenters.CursesOverviewPresenter;


@EFragment(R.layout.fragment_overview_cursis)
public class CursesOverviewFragment extends BaseFragment implements CursesOverviewView, blackson.n.zeilschooldewaai.adapters.Views.CursesOverviewAdapterView.CursesOverviewListener {

    ///////////////////////////////////////////////////////////////////////////
    // Variables
    ///////////////////////////////////////////////////////////////////////////

    @ViewById(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Bean
    protected CursesOverviewAdapter mAdapter;

    private CursesOverviewPresenter mPresenter = new CursesOverviewPresenter(this);

    private Calendar date = Calendar.getInstance(TimeZone.getDefault());

    ///////////////////////////////////////////////////////////////////////////
    // Lifecycle callbacks
    ///////////////////////////////////////////////////////////////////////////


    @Override
    void afterViews() {
        //clear the UI
        mAdapter.clear();
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        //get the data of this month
        mPresenter.provide(date.get(Calendar.MONTH), date.get(Calendar.YEAR));
    }

    @Override
    void initToolbar(final ToolbarManager toolbarManager) {
        super.initToolbar(toolbarManager);
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
        toolbarManager.setTitle("overzicht " + format.format(date.getTime()));
        toolbarManager.setEndText("change");

        toolbarManager.setEndIconListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
                        // date has been selected, mow get the new data to update the UI
                        toolbarManager.setTitle(getString(R.string.overview) + getMonth(month) + " " + year);
                        mPresenter.provide(month, year);
                    }
                }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Interface Callbacks
    ///////////////////////////////////////////////////////////////////////////

    /**
     * method used to display a error message
     */
    @Override
    public void onFailure(final String pDetails) {
        if (getView() != null && pDetails != null)
            Snackbar.make(getView(), pDetails, Snackbar.LENGTH_LONG);
    }

    /**
     * method to update the UI
     */
    @Override
    public void addItem(final SoortenCursus pCursus) {
        mAdapter.remove(pCursus);
        mAdapter.add(pCursus);
    }

    @Override
    public void onClick(final SoortenCursus pCursus) {
        //change fragment show to users that are  following the
        CursesDetailsFragment fragment = CursesDetailsFragment_.builder().build();
        fragment.setCursus(pCursus);
        gotoFragment(fragment, true);
    }

    /**
     * @return the name of the month provided
     */
    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}
