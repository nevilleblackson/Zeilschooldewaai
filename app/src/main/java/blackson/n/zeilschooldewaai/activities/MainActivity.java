package blackson.n.zeilschooldewaai.activities;

import android.annotation.SuppressLint;

import org.androidannotations.annotations.EActivity;

import blackson.n.zeilschooldewaai.fragments.HomeFragment_;

@SuppressLint("Registered")
@EActivity
public class MainActivity extends BaseActivity {

    private boolean started;

    @Override
    protected void onStart() {
        super.onStart();

        //only got to the home fragment if it is the first time the MainActivity runs
        if (!started)
            gotoFragment(HomeFragment_.builder().build(), false);
        started = true;
    }
}

