package com.hrishikesh.Demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by j on 26-09-2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {

    private List<Product> productList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;


    // data is passed into the constructor
    ProductAdapter(Context context,  List<Product> products) {
        this.mInflater = LayoutInflater.from(context);
        this.productList = products;
        this.context = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_product, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Product product = productList.get(position);
        // set the data in items
        holder.myTextView.setText(product.getName());
        Glide.with(context).load(product.getUrl()).into(holder.myImageView);
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                // open another activity on item click
                */
            }
        });
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
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

                    final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(
                                        v, "scaleX", 1f);
                                ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
                                        v, "scaleY", 1f);
                                scaleDownX2.setDuration(200);
                                scaleDownY2.setDuration(200);

                                AnimatorSet scaleDown2 = new AnimatorSet();
                                scaleDown2.play(scaleDownX2).with(scaleDownY2);

                                scaleDown2.start();
                            }
                        }, 300);

                        break;
                }
                return true;
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return productList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView myImageView;
        ViewHolder(View v) {
            super(v);
            myTextView = (TextView) v.findViewById(R.id.textView);
            myImageView = (ImageView) v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}