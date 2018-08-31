package com.aristys.aristysapp.remote;

import com.aristys.aristysapp.model.Post;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiEndPointInterface {

    //    get posts from wordpress
    @GET("rest/v1.1/sites/blogaristysweb.wordpress.com/posts")
    Observable<Response<Post>> getPost();

}
