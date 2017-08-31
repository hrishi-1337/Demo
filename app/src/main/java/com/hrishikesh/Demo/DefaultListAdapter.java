package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DefaultListAdapter extends ArrayAdapter<DemoItem> implements DemoAdapter {

    private final LayoutInflater layoutInflater;


    public DefaultListAdapter(Context context, List<DemoItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    public DefaultListAdapter(Context context) {
        super(context, 0);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        View v;

        DemoItem item = getItem(position);

        if (convertView == null) {
            v = layoutInflater.inflate(R.layout.adapter_item , parent, false);
        } else {
            v = convertView;
        }

        TextView textView;
        textView = (TextView) v.findViewById(R.id.textview);
        textView.setText(String.valueOf(item.getText()));
        ImageView imageView;
        imageView = (ImageView) v.findViewById(R.id.imageView);
        Glide.with(getContext()).load(item.getUrl()).into(imageView);
        return v;
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public int getItemViewType(int position) {
        return position ;
    }

    public void appendItems(List<DemoItem> newItems) {
        addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<DemoItem> moreItems) {
        clear();
        appendItems(moreItems);
    }


}