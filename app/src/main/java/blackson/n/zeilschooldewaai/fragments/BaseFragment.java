package blackson.n.zeilschooldewaai.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;

import blackson.n.zeilschooldewaai.activities.BaseActivity;
import blackson.n.zeilschooldewaai.managers.FirebaseManager;
import blackson.n.zeilschooldewaai.managers.PrefManager;
import blackson.n.zeilschooldewaai.managers.ToolbarManager;

/***
 * Created by n on 9-2-18.
 */

@EBean
public abstract class BaseFragment extends Fragment {

    private BaseActivity mBaseActivity;
    private ToolbarManager mToolbarManager;

    protected FirebaseManager mFirebaseManager;
    protected PrefManager prefManager;


    ///////////////////////////////////////////////////////////////////////////
    // LifeCycle Callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseManager = new FirebaseManager(getContext(), getActivity());
        prefManager = new PrefManager(getActivity());
        mBaseActivity = (BaseActivity) getActivity();
        mToolbarManager = new ToolbarManager(mBaseActivity);
    }


    @AfterViews
    protected final void baseAfterViews() {
        mToolbarManager.reset(getBaseActivity());
        mToolbarManager.setDrawerListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getBaseActivity().openDrawer();
            }
        });
        initToolbar(mToolbarManager);
        afterViews();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters and Setters
    ///////////////////////////////////////////////////////////////////////////

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public ToolbarManager getToolbarManager() {
        return mToolbarManager;
    }

    ///////////////////////////////////////////////////////////////////////////
    // child functions
    ///////////////////////////////////////////////////////////////////////////

    /**
     * used when you want to switch fragments
     */
    protected void gotoFragment(BaseFragment pFragment, boolean backStack) {
        mBaseActivity.gotoFragment(pFragment, backStack);
    }

    void initToolbar(ToolbarManager toolbarManager) {

    }

    abstract void afterViews();

}