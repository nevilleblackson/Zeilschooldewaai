package blackson.n.zeilschooldewaai.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.adapters.CursesDetailsAdapter;
import blackson.n.zeilschooldewaai.fragments.views.CursesDetailsView;
import blackson.n.zeilschooldewaai.managers.ToolbarManager;
import blackson.n.zeilschooldewaai.models.SoortenCursus;
import blackson.n.zeilschooldewaai.models.Cursist;
import blackson.n.zeilschooldewaai.presenters.CursesDetailsPresenter;

@EFragment(R.layout.fragment_curses_details)
public class CursesDetailsFragment extends BaseFragment implements CursesDetailsView {

    ///////////////////////////////////////////////////////////////////////////
    // Variables
    ///////////////////////////////////////////////////////////////////////////

    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bean
    protected CursesDetailsAdapter mAdapter;

    private SoortenCursus mCursus;

    private CursesDetailsPresenter mPresenter = new CursesDetailsPresenter(this);

    ///////////////////////////////////////////////////////////////////////////
    // Lifecycle Callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    void afterViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.provide(mCursus);
    }

    @Override
    void initToolbar(final ToolbarManager toolbarManager) {
        super.initToolbar(toolbarManager);
        toolbarManager.setTitle(mCursus.getCursusSoort());
        toolbarManager.initBackButton(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //go back to the last fragment
                getBaseActivity().onBackPressed();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////

    public void setCursus(final SoortenCursus pCursus) {
        mCursus = pCursus;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Interface Callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onDataProvided(final List<Cursist> pData) {
        mAdapter.setItems(pData);
    }

    @Override
    public void onFailure(final String pDetails) {

    }
}
