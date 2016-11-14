

package com.ibm.mobileappbuilder.pkg20161028105738.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "ReviewsDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ReviewsDS extends AppNowDatasource<ReviewsDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ReviewsDSService service;

    public static ReviewsDS getInstance(SearchOptions searchOptions){
        return new ReviewsDS(searchOptions);
    }

    private ReviewsDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ReviewsDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ReviewsDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ReviewsDSItem>>() {
                @Override
                public void onSuccess(List<ReviewsDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ReviewsDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getReviewsDSItemById(id, new Callback<ReviewsDSItem>() {
                @Override
                public void success(ReviewsDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<ReviewsDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ReviewsDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryReviewsDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ReviewsDSItem>>() {
            @Override
            public void success(List<ReviewsDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"dataField0", "title", "rating", "text", "productId"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(ReviewsDSItem item, Listener<ReviewsDSItem> listener) {
                          service.getServiceProxy().createReviewsDSItem(item, callbackFor(listener));
          }

    private Callback<ReviewsDSItem> callbackFor(final Listener<ReviewsDSItem> listener) {
      return new Callback<ReviewsDSItem>() {
          @Override
          public void success(ReviewsDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ReviewsDSItem item, Listener<ReviewsDSItem> listener) {
                          service.getServiceProxy().updateReviewsDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(ReviewsDSItem item, final Listener<ReviewsDSItem> listener) {
                service.getServiceProxy().deleteReviewsDSItemById(item.getIdentifiableId(), new Callback<ReviewsDSItem>() {
            @Override
            public void success(ReviewsDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ReviewsDSItem> items, final Listener<ReviewsDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ReviewsDSItem>>() {
            @Override
            public void success(List<ReviewsDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ReviewsDSItem> items){
        List<String> ids = new ArrayList<>();
        for(ReviewsDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
