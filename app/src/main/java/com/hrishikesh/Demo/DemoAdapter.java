package com.hrishikesh.Demo;

/**
 * Created by j on 27-08-2017.
 */

import android.widget.ListAdapter;

import java.util.List;

public interface DemoAdapter extends ListAdapter {

    void appendItems(List<DemoItem> newItems);

    void setItems(List<DemoItem> moreItems);
}