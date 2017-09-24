package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
        final String name = item.getText();

        TextView textView;
        textView = (TextView) v.findViewById(R.id.textview);
        textView.setText(String.valueOf(item.getText()));
        final ImageView imageView;
        imageView = (ImageView) v.findViewById(R.id.imageView);
        Glide.with(getContext()).load(item.getUrl()).into(imageView);

       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(view.isPressed()) {
                    view.setScaleY((float)0.9);
                    view.setScaleX((float)0.9);
                }else{
                    view.setScaleY(1);
                    view.setScaleX(1);
                }

            }
        }); */


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(v,
                                "scaleX", 0.9f);
                        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(v,
                                "scaleY", 0.9f);
                        scaleDownX.setDuration(200);
                        scaleDownY.setDuration(200);

                        AnimatorSet scaleDown = new AnimatorSet();
                        scaleDown.play(scaleDownX).with(scaleDownY);

                        scaleDown.start();

                        break;

                    case MotionEvent.ACTION_UP:
                        ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(
                                v, "scaleX", 1f);
                        ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
                               v, "scaleY", 1f);
                        scaleDownX2.setDuration(200);
                        scaleDownY2.setDuration(200);

                        AnimatorSet scaleDown2 = new AnimatorSet();
                        scaleDown2.play(scaleDownX2).with(scaleDownY2);

                        scaleDown2.start();

                        //v.setEnabled(false);
                        Intent intent = new Intent(getContext(), ShopActivity.class);
                        intent.putExtra("name",name);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) getContext(), imageView, "profile");
                        getContext().startActivity(intent, options.toBundle());
                        break;
                }
                return true;
            }
        });
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