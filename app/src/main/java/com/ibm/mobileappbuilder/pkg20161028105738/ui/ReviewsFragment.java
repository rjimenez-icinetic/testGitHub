package com.ibm.mobileappbuilder.pkg20161028105738.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ProductsDSItem;
import com.ibm.mobileappbuilder.pkg20161028105738.R;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.filter.StringFilter;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ViewHolder;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ReviewsDSItem;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ReviewsDS;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;
import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "ReviewsFragment" listing
 */
public class ReviewsFragment extends ListGridFragment<ReviewsDSItem>  {

    private Datasource<ReviewsDSItem> datasource;
    private ProductsDSItem productsDSItem;


    public static ReviewsFragment newInstance(Bundle args) {
        ReviewsFragment fr = new ReviewsFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        productsDSItem = getArguments() != null && getArguments().containsKey(ProductsDSItem.class.getName()) ? 
        	(ProductsDSItem)getArguments().getParcelable(ProductsDSItem.class.getName()) : 
        	new ProductsDSItem();
    }

    protected SearchOptions getSearchOptions() {
        SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        searchOptionsBuilder
            .withFixedFilters(Arrays.<Filter>asList(new StringFilter("productId", productsDSItem.dataField1)));
        return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.reviews_item;
    }

    @Override
    protected Datasource<ReviewsDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ReviewsDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(ReviewsDSItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.title != null){
            title.setText(item.title);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.rating != null){
            subtitle.setText(item.rating);
            
        }
    }


    @Override
    public void showDetail(ReviewsDSItem item, int position) {
        // If we have forms, then we have to refresh when an item has been edited
        // Also with this we support list without details
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), ReviewsDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

}
