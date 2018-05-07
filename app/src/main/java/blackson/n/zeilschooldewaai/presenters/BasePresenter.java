package blackson.n.zeilschooldewaai.presenters;

abstract class BasePresenter<V> {

    V mView;

    BasePresenter(V pView) {
        mView = pView;
    }

}
