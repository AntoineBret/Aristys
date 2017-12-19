package com.aristys.aristysapp;

import android.app.Activity;
import android.os.Bundle;

import com.aristys.aristysapp.fragment.CRMFragment;
import com.github.scribejava.apis.TrelloApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class TrelloActivity extends Activity {
  private static final String PROTECTED_RESOURCE_URL = "https://trello.com/c/fo9t4N3s/10-votre-projet-de-a-%C3%A0-z.json";
  private static final String API_KEY = "262b4db409b7f8233f80ae9e51476dd3";
  private static final String API_SECRET = "faa3fc38297a6419cbe6d3ac6c1e38626cfa9c6b0812552742824321748e2882";
  private OAuth10aService service;
  Response response;
  private OAuthRequest request;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trello);

    service = new ServiceBuilder()
      .apiKey(API_KEY)
      .apiSecret(API_SECRET)
      .build(TrelloApi.instance());

    final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    service.signRequest(CRMFragment.accessToken, request);
    Single.fromCallable(new Callable <String>() {
      @Override
      public String call() throws Exception {
        final Response response = service.execute(request);
        return response.getBody();
      }
    }).subscribeOn(Schedulers.computation())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(new DisposableSingleObserver <String>() {
        @Override
        public void onSuccess(String url) {

        }
        @Override
        public void onError(Throwable e) {
          e.printStackTrace();
        }
      });
  }
}


