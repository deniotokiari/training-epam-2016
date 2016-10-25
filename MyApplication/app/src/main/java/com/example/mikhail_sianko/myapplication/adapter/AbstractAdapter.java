package com.example.mikhail_sianko.myapplication.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractAdapter<Item> extends RecyclerView.Adapter<AbstractAdapter.AbstractViewHolder> {

    public abstract void onBind(AbstractViewHolder holder, Item item, int position, int viewType);

    public abstract Item getItem(int positoin);

    @Override
    public void onBindViewHolder(final AbstractViewHolder holder, final int position) {
        onBind(holder, getItem(position), position, getItemViewType(position));
    }

    public static class AbstractViewHolder extends RecyclerView.ViewHolder {

        private SparseArrayCompat<View> mViewSparseArray;

        public AbstractViewHolder(final View itemView, final int... ids) {
            super(itemView);

            mViewSparseArray = new SparseArrayCompat<>(ids.length);

            for (final int id : ids) {
                mViewSparseArray.append(id, itemView.findViewById(id));
            }
        }

        public <T> T get(final int id) {
            View view = mViewSparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
            }
            return (T) view;
        }

    }

}
