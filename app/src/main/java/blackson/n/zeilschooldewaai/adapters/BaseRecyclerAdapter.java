package blackson.n.zeilschooldewaai.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blackson.n.zeilschooldewaai.models.SoortenCursus;

/***
 * Created by n on 21-3-18.
 */

public abstract class BaseRecyclerAdapter<T, V extends View> extends RecyclerView.Adapter<BaseRecyclerAdapter.Holder<V>> {

    private List<T> mItems = new ArrayList<>();

    static class Holder<V extends View> extends RecyclerView.ViewHolder {

        V mView;

        Holder(V pView) {
            super(pView);
            mView = pView;
        }
    }

    @Override
    public final Holder<V> onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new Holder<>(onCreateView(parent, viewType));
    }

    @Override
    public final void onBindViewHolder(final Holder holder, final int position) {
        initView((V) holder.mView, position);
    }

    @Override
    public final int getItemCount() {
        return mItems.size();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Abstract functions
    ///////////////////////////////////////////////////////////////////////////

    abstract V onCreateView(final ViewGroup parent, final int viewType);

    abstract void initView(final V pView, final int pPosition);


    ///////////////////////////////////////////////////////////////////////////
    // Getter and Setters
    ///////////////////////////////////////////////////////////////////////////

    public final T getItemAt(int pos) {
        return mItems.get(pos);
    }

    public final List<T> getItems() {
        return mItems;
    }

    public final void setItems(final List<T> pItems) {
        this.mItems.clear();
        this.mItems.addAll(pItems);
        notifyDataSetChanged();
    }


    public final void add(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void remove(final T pT) {
        mItems.remove(pT);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

}
