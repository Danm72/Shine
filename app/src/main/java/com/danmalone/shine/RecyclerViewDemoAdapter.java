package com.danmalone.shine;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danmalone.shine.dummy.DummyContent;

import java.util.List;

class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerViewDemoAdapter.ViewHolder> {

    private List<DummyContent.DummyItem> items;
    private int itemLayout;

    public RecyclerViewDemoAdapter(List<DummyContent.DummyItem> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerViewDemoAdapter.ViewHolder holder, int position) {
        DummyContent.DummyItem item = items.get(position);
        holder.text.setText(item.content);
//        holder.image.setImageBitmap(null);
//        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
//        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.info_text);

        }
    }
}