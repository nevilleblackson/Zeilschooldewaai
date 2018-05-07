package blackson.n.zeilschooldewaai.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.ViewById;

import blackson.n.zeilschooldewaai.R;
import blackson.n.zeilschooldewaai.activities.MainActivity_;
import blackson.n.zeilschooldewaai.interfaces.SuccessListener;
import blackson.n.zeilschooldewaai.managers.ToolbarManager;

@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment implements SuccessListener<FirebaseUser> {


    ///////////////////////////////////////////////////////////////////////////
    // Variables
    ///////////////////////////////////////////////////////////////////////////

    @ViewById(R.id.email)
    EditText emailEditText;

    @ViewById(R.id.password)
    EditText passwordEditText;


    ///////////////////////////////////////////////////////////////////////////
    // lifeCycle callbacks
    ///////////////////////////////////////////////////////////////////////////

    @Override
    void afterViews() {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Click Listeners
    ///////////////////////////////////////////////////////////////////////////

    @Click(R.id.login_button)
    protected void onLoginClicked() {
        final String password = passwordEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();

        if (password.isEmpty() || email.isEmpty()) {
            Toast.makeText(getContext(), "vul alle velden in", Toast.LENGTH_SHORT).show();
            return;
        }

        // if the text is not empty try to login
        mFirebaseManager.login(email, password, this);
    }

    //DEBUG
    @LongClick(R.id.login_button)
    protected void onRegisterClicked() {
        final String password = passwordEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();

        if (password.isEmpty() || email.isEmpty()) {
            Toast.makeText(getContext(), "vul alle velden in", Toast.LENGTH_SHORT).show();
            return;
        }

        mFirebaseManager.register(email, password, this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Interface CallBacks
    ///////////////////////////////////////////////////////////////////////////


    @Override
    void initToolbar(final ToolbarManager toolbarManager) {
        super.initToolbar(toolbarManager);
        toolbarManager.setTitle("Login");
        toolbarManager.setDrawerVisibility(View.GONE);
    }

    @Override
    public void onSuccess(final FirebaseUser data) {
        prefManager.saveFirebaseUser(data);
        MainActivity_.intent(getContext()).start();
        getBaseActivity().finish();
    }

    @Override
    public void onFailure(final String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}