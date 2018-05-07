package blackson.n.zeilschooldewaai.managers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import blackson.n.zeilschooldewaai.interfaces.SuccessListener;

public class FirebaseManager {

    private String TAG = "DEBUG";

    private Context mContext;
    private FirebaseAuth mAuth;
    private Activity mActivity;

    public FirebaseManager(Context pContext, Activity pActivity) {
        mAuth = FirebaseAuth.getInstance();
        mActivity = pActivity;
    }

    public void login(String pEmail, String pPassword, final SuccessListener<FirebaseUser> pListener) {
        if (mActivity == null)
            return;

        mAuth.signInWithEmailAndPassword(pEmail, pPassword)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && mAuth != null) {
                            Log.d(TAG, "signInWithEmail:success");
                            pListener.onSuccess(mAuth.getCurrentUser());
                        } else {
                            if (task.getException() != null)
                                pListener.onFailure(task.getException().getMessage());
                            else
                                pListener.onFailure("sign in failed");
                        }
                    }
                });
    }

    public void register(String pEmail, String pPassword, final SuccessListener<FirebaseUser> pListener) {
        if (mActivity == null)
            return;
        mAuth.createUserWithEmailAndPassword(pEmail, pPassword)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && mAuth != null) {
                            Log.d(TAG, "createUserWithEmail:success");
                            pListener.onSuccess(mAuth.getCurrentUser());
                        } else {
                            if (task.getException() != null)
                                pListener.onFailure(task.getException().getMessage());
                            else
                            pListener.onFailure("register failed");
                        }
                    }
                });
    }
}
