
package com.ibm.mobileappbuilder.pkg20161028105738.ds;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ReviewsDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("dataField0") public String dataField0;
    @SerializedName("title") public String title;
    @SerializedName("rating") public String rating;
    @SerializedName("text") public String text;
    @SerializedName("productId") public String productId;
    @SerializedName("id") public String id;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataField0);
        dest.writeString(title);
        dest.writeString(rating);
        dest.writeString(text);
        dest.writeString(productId);
        dest.writeString(id);
    }

    public static final Creator<ReviewsDSItem> CREATOR = new Creator<ReviewsDSItem>() {
        @Override
        public ReviewsDSItem createFromParcel(Parcel in) {
            ReviewsDSItem item = new ReviewsDSItem();

            item.dataField0 = in.readString();
            item.title = in.readString();
            item.rating = in.readString();
            item.text = in.readString();
            item.productId = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public ReviewsDSItem[] newArray(int size) {
            return new ReviewsDSItem[size];
        }
    };

}

