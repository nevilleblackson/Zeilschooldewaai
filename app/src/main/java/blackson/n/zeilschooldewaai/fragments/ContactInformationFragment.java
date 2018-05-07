package blackson.n.zeilschooldewaai.fragments;

import org.androidannotations.annotations.EFragment;

import blackson.n.zeilschooldewaai.R;

@EFragment(R.layout.fragment_contact_information)
public class ContactInformationFragment extends BaseFragment {

    ///////////////////////////////////////////////////////////////////////////
    // lifeCycle callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    void afterViews() {
        getToolbarManager().setTitle("contactpagina");
    }
}
