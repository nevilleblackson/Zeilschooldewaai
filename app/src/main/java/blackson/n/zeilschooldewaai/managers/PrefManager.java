package blackson.n.zeilschooldewaai.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class PrefManager {

    ///////////////////////////////////////////////////////////////////////////
    // variables
    ///////////////////////////////////////////////////////////////////////////

    private SharedPreferences mSharedPreferences;
    private final Gson gson = new Gson();

    ///////////////////////////////////////////////////////////////////////////
    // Presence Keys
    ///////////////////////////////////////////////////////////////////////////

    private static final String KEY = "CREDIT_KEY";
    private static final String KEY_FIREBASE_USER = "KEY_FIREBASE_USER";

    public PrefManager(Activity pActivity) {
        mSharedPreferences = pActivity.getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters
    ///////////////////////////////////////////////////////////////////////////

    public FirebaseUser loadFirebaseUser() {
        return gson.fromJson(loadObject(KEY_FIREBASE_USER), FirebaseUser.class);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////

    public void saveFirebaseUser(final FirebaseUser pData) {
        SaveObject(pData, KEY_FIREBASE_USER);
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Functions
    ///////////////////////////////////////////////////////////////////////////

    private void SaveObject(Object data, String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String json = gson.toJson(data);
        editor.putString(key, json);
        editor.apply();
    }

    private String loadObject(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
