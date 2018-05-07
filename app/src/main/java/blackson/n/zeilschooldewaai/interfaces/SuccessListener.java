package blackson.n.zeilschooldewaai.interfaces;

public interface SuccessListener<T> {

    void onSuccess(T pData);

    void onFailure(String error);
}
