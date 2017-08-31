package com.hrishikesh.Demo;

/**
 * Created by j on 30-08-2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

public class DemoItem implements AsymmetricItem {

    private int columnSpan;
    private int rowSpan;
    private int position;
    public String text;
    public String url;

    public DemoItem() {
        this(1, 1, 0,"","");
    }

    public DemoItem(int columnSpan, int rowSpan, int position, String text, String url) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
        this.text = text;
        this.url = url;
    }

    public DemoItem(Parcel in) {
        readFromParcel(in);
    }

    @Override public int getColumnSpan() {
        return columnSpan;
    }

    @Override public int getRowSpan() {
        return rowSpan;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    @Override public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan,text,url);
    }

    @Override public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
        text = in.readString();
    }

    @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
        dest.writeString(text);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<DemoItem> CREATOR = new Parcelable.Creator<DemoItem>() {

        @Override public DemoItem createFromParcel(@NonNull Parcel in) {
            return new DemoItem(in);
        }

        @Override @NonNull public DemoItem[] newArray(int size) {
            return new DemoItem[size];
        }
    };
}
