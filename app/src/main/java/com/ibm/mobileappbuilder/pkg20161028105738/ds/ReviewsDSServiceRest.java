
package com.ibm.mobileappbuilder.pkg20161028105738.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;

public interface ReviewsDSServiceRest{

	@GET("/app/58132f2029ffbe0300d20e42/r/reviewsDS")
	void queryReviewsDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ReviewsDSItem>> cb);

	@GET("/app/58132f2029ffbe0300d20e42/r/reviewsDS/{id}")
	void getReviewsDSItemById(@Path("id") String id, Callback<ReviewsDSItem> cb);

	@DELETE("/app/58132f2029ffbe0300d20e42/r/reviewsDS/{id}")
  void deleteReviewsDSItemById(@Path("id") String id, Callback<ReviewsDSItem> cb);

  @POST("/app/58132f2029ffbe0300d20e42/r/reviewsDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ReviewsDSItem>> cb);

  @POST("/app/58132f2029ffbe0300d20e42/r/reviewsDS")
  void createReviewsDSItem(@Body ReviewsDSItem item, Callback<ReviewsDSItem> cb);

  @PUT("/app/58132f2029ffbe0300d20e42/r/reviewsDS/{id}")
  void updateReviewsDSItem(@Path("id") String id, @Body ReviewsDSItem item, Callback<ReviewsDSItem> cb);

  @GET("/app/58132f2029ffbe0300d20e42/r/reviewsDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}
