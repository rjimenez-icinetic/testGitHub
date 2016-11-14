

package com.ibm.mobileappbuilder.pkg20161028105738.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.pkg20161028105738.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * ShirtsActivity list activity
 */
public class ShirtsActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.shirtsActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ShirtsFragment.class;
    }

}
