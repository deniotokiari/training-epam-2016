package com.example.mikhail_sianko.myapplication.adapter;


import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.model.gson.TwitterSearchStatuses;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends AbstractAdapter<TwitterSearchStatuses> {

    public static final int TYPE_CHANGED = 0;
    public static final int TYPE_DEFAULT = 1;
    List<TwitterSearchStatuses> data = new ArrayList<>();

    public void setOnItemListener(RecyclerViewAdapter.onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private onItemListener onItemListener;


    public interface onItemListener {
        void onClick(TwitterSearchStatuses item, int position);
    }

    public void setData(List<TwitterSearchStatuses> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(AbstractViewHolder holder, final TwitterSearchStatuses data, final int position, int viewType) {
        holder.<TextView>get(R.id.user).setText(data.getTwitterUser().getName());
        holder.<TextView>get(R.id.value).setText(data.getText());
        if (viewType == TYPE_CHANGED) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorAccent));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener != null) {
                    onItemListener.onClick(data, position);
                }
            }
        });

    }

    @Override
    public TwitterSearchStatuses getItem(int positoin) {
        return data.get(positoin);
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AbstractViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(TwitterSearchStatuses item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
        int itemCount = data.size() - position;
        notifyItemRangeChanged(position, itemCount);
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        TwitterSearchStatuses item = getItem(position);
        return item.changeMe ? TYPE_CHANGED : TYPE_DEFAULT;
    }

}
