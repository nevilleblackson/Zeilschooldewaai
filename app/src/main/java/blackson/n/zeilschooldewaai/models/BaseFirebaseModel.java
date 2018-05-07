package blackson.n.zeilschooldewaai.models;

import com.google.firebase.database.Exclude;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/***
 * Created by n on 24-3-18.
 */

public abstract class BaseFirebaseModel {

    /**
     * used to push data to firebase
     */
    @Exclude
    public Map<String, Object> toMap() {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return new Gson().fromJson(gson.toJson(this), type);
    }

}
