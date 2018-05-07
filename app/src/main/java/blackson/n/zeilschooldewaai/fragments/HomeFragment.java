package blackson.n.zeilschooldewaai.fragments;

import org.androidannotations.annotations.EFragment;

import blackson.n.zeilschooldewaai.R;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {


    ///////////////////////////////////////////////////////////////////////////
    // lifeCycle callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    void afterViews() {
        getToolbarManager().setTitle("Zeilschool de waai");
    }

}
