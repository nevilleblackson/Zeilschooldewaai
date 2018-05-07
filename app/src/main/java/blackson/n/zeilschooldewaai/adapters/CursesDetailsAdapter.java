package blackson.n.zeilschooldewaai.adapters;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import blackson.n.zeilschooldewaai.adapters.Views.CursesDetailsAdapterView;
import blackson.n.zeilschooldewaai.adapters.Views.CursesDetailsAdapterView_;
import blackson.n.zeilschooldewaai.models.Cursist;

@EBean
public class CursesDetailsAdapter extends BaseRecyclerAdapter<Cursist, CursesDetailsAdapterView> {

    @Override
    CursesDetailsAdapterView onCreateView(final ViewGroup parent, final int viewType) {
        return CursesDetailsAdapterView_.build(parent.getContext());
    }

    @Override
    void initView(final CursesDetailsAdapterView pView, final int pPosition) {
        pView.bind(getItemAt(pPosition));
    }
    
}
