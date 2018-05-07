package blackson.n.zeilschooldewaai.adapters;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import blackson.n.zeilschooldewaai.adapters.Views.CursesOverviewAdapterView;
import blackson.n.zeilschooldewaai.adapters.Views.CursesOverviewAdapterView_;
import blackson.n.zeilschooldewaai.models.SoortenCursus;

@EBean
public class CursesOverviewAdapter extends BaseRecyclerAdapter<SoortenCursus, CursesOverviewAdapterView> {

    private final Context mContext;
    private CursesOverviewAdapterView.CursesOverviewListener listener;

    CursesOverviewAdapter(Context pContext) {
        mContext = pContext;
    }

    @Override
    CursesOverviewAdapterView onCreateView(final ViewGroup parent, final int viewType) {
        return CursesOverviewAdapterView_.build(mContext);
    }

    @Override
    void initView(final CursesOverviewAdapterView pView, final int pPosition) {
        pView.bind(getItemAt(pPosition),listener);
    }

    public void setListener(final CursesOverviewAdapterView.CursesOverviewListener pListener) {
        listener = pListener;
    }
}
