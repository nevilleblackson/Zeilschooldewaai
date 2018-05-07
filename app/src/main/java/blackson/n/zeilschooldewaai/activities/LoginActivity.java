package blackson.n.zeilschooldewaai.activities;

import android.annotation.SuppressLint;
import android.support.v4.widget.DrawerLayout;

import org.androidannotations.annotations.EActivity;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.fragments.LoginFragment_;

@SuppressLint("Registered")
@EActivity
public class LoginActivity extends BaseActivity {

    private boolean started;

    @Override
    protected void onStart() {
        super.onStart();
        // you cant open the drawer in the LoginActivity
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer.setEnabled(false);

        //only got to the login fragment if it is the first time the LoginActivity runs
        if (!started)
            gotoFragment(LoginFragment_.builder().build(), false);
        started = true;
    }

}
