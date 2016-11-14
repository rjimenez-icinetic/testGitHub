
package com.ibm.mobileappbuilder.pkg20161028105738.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.pkg20161028105738.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "ContactScreen1DSService" REST Service implementation
 */
public class ContactScreen1DSService extends RestService<ContactScreen1DSServiceRest>{

    public static ContactScreen1DSService getInstance(){
          return new ContactScreen1DSService();
    }

    private ContactScreen1DSService() {
        super(ContactScreen1DSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://baked-devel-ibm.herokuapp.com";
    }

    @Override
    protected String getApiKey() {
        return "K8HUPN3Q";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://baked-devel-ibm.herokuapp.com/app/58132f2029ffbe0300d20e42",
                path,
                "apikey=K8HUPN3Q");
    }

}
