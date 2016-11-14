
package com.ibm.mobileappbuilder.pkg20161028105738.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ibm.mobileappbuilder.pkg20161028105738.R;
import ibmmobileappbuilder.ds.Datasource;
import android.widget.ImageView;
import android.widget.TextView;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.actions.PhoneAction;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ContactScreen1DSItem;
import com.ibm.mobileappbuilder.pkg20161028105738.ds.ContactScreen1DS;

public class ContactFragment extends ibmmobileappbuilder.ui.DetailFragment<ContactScreen1DSItem>  {

    private Datasource<ContactScreen1DSItem> datasource;
    private SearchOptions searchOptions;

    public static ContactFragment newInstance(Bundle args){
        ContactFragment card = new ContactFragment();
        card.setArguments(args);

        return card;
    }

    public ContactFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchOptions = SearchOptions.Builder.searchOptions().build();
    }

    @Override
    public Datasource getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ContactScreen1DS.getInstance(searchOptions);
        return datasource;
    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.contact_custom;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ContactScreen1DSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view0Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view0.setImageDrawable(null);
        }
        if (item.address != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.address);
            bindAction(view1, new MapsAction(new ActivityIntentLauncher(), "http://maps.google.com/maps?q=" + item.address));
        }
        if (item.phone != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.phone);
            bindAction(view2, new PhoneAction(new ActivityIntentLauncher(), item.phone));
        }
        if (item.email != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.email);
            bindAction(view3, new MailAction(new ActivityIntentLauncher(), item.email));
        }
    }

    @Override
    protected void onShow(ContactScreen1DSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Contact");
    }
}
