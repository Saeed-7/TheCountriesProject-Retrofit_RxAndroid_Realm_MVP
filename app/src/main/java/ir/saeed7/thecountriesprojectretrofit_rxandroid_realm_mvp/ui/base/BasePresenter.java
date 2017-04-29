package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * @return mvpView instance to use in presenter
     */

    public T getMvpView() {
        return this.mvpView;
    }
}
