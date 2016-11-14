
package com.ibm.mobileappbuilder.pkg20161028105738.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.pkg20161028105738.R;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ReviewsDSItem;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ReviewsDS;

public class ReviewsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<ReviewsDSItem> implements ShareBehavior.ShareListener  {

    private Datasource<ReviewsDSItem> datasource;
    public static ReviewsDetailFragment newInstance(Bundle args){
        ReviewsDetailFragment fr = new ReviewsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ReviewsDetailFragment(){
        super();
    }

    @Override
    public Datasource<ReviewsDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
    }
       datasource = ReviewsDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.reviewsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ReviewsDSItem item, View view) {
        if (item.rating != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.rating);
            
        }
        if (item.text != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.text);
            
        }
    }

    @Override
    protected void onShow(ReviewsDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Product Review");
    }
    @Override
    public void onShare() {
        ReviewsDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.rating != null ? item.rating : "" ) + "\n" +
                    (item.text != null ? item.text : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Product Review");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
