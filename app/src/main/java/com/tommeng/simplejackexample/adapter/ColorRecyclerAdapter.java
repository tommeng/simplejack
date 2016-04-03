package com.tommeng.simplejackexample.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tommeng.simplejackexample.R;
import com.tommeng.simplejackexample.model.MyColor;
import com.tommeng.simplejackexample.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ColorRecyclerAdapter extends RecyclerView.Adapter<ColorRecyclerAdapter.ColorViewHolder> {
    private List<MyColor> myColorList = new ArrayList<>();

    public ColorRecyclerAdapter(@NonNull List<MyColor> myColorList) {
        this.myColorList = myColorList;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ColorViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        MyColor myColor = myColorList.get(position);
        holder.bind(myColor);
    }

    @Override
    public int getItemCount() {
        return myColorList.size();
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout vContainer;
        private TextView tvColorName;
        private TextView tvColorDescription;

        public ColorViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_color, parent, false));
            vContainer = (LinearLayout) itemView.findViewById(R.id.vContainer);
            tvColorName = (TextView) itemView.findViewById(R.id.tvColorName);
            tvColorDescription = (TextView) itemView.findViewById(R.id.tvColorDescription);
        }

        public void bind(MyColor myColor) {
            String hex = myColor.getHex();
            if (!hex.contains("#")) {
                hex = "#" + hex;
            }
            int color = Color.parseColor(hex);
            vContainer.setBackgroundColor(color);
            tvColorName.setText(myColor.getTitle());
            tvColorDescription.setText(myColor.getDescription());
            boolean isDark = Utils.isColorDark(color);
            Resources resources = itemView.getContext().getResources();
            tvColorName.setTextColor(resources.getColor(isDark ? android.R.color.white : android.R.color.black));
            tvColorDescription.setTextColor(resources.getColor(isDark ? android.R.color.white : android.R.color.black));
        }
    }
}
