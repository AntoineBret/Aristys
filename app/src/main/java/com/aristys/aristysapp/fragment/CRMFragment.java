package com.aristys.aristysapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aristys.aristysapp.GoogleActivity;
import com.aristys.aristysapp.R;
import com.aristys.aristysapp.TrelloActivity;
import com.github.scribejava.apis.TrelloApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CRMFragment extends Fragment {

  private static final String API_KEY = "262b4db409b7f8233f80ae9e51476dd3";
  private static final String API_SECRET = "faa3fc38297a6419cbe6d3ac6c1e38626cfa9c6b0812552742824321748e2882";
  private static final String VERIFIER = "oauth_verifier=";
  private static final String CALLBACK_URL = "https://trello.com/c/fo9t4N3s/10-votre-projet-de-a-%C3%A0-z";
  private static final String GOOGLE_REDIRECT = "https://accounts.google.com";

  private WebView webView;
  private ProgressDialog pd;
  private OAuth10aService service;
  private OAuth1RequestToken requestToken;
  public static OAuth1AccessToken accessToken;
  private OAuthRequest request;
  private String oauthVerifier;
  Response response;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_crm, container, false);

  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

   webView = (WebView) view.findViewById(R.id.trello_webView);
    webView.requestFocus(View.FOCUS_DOWN);
    webView.getSettings().setJavaScriptEnabled(true);
    pd = ProgressDialog.show(getContext(), "", "Loading..", true);

    service = new ServiceBuilder()
      .apiKey(API_KEY)
      .apiSecret(API_SECRET)
      .callback(CALLBACK_URL)
      .build(TrelloApi.instance());

    Single.fromCallable(new Callable <String>() {
      @Override
      public String call() throws Exception {
        requestToken = service.getRequestToken();
        return service.getAuthorizationUrl(requestToken);
      }
    }).subscribeOn(Schedulers.computation())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(new DisposableSingleObserver <String>() {
        @Override
        public void onSuccess(String url) {
          loadUrl(url);
        }

        @Override
        public void onError(Throwable e) {
          e.printStackTrace();
        }
      });
  }

  private void loadUrl(String url) {
    webView.loadUrl(url);
    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {

          /*Choix 1 : Se connecter via Google*/
        if (url.startsWith(GOOGLE_REDIRECT)) {
          Log.i("Authorize", "");
          Intent intent = new Intent(getContext(), GoogleActivity.class);
          getContext().startActivity(intent);

          /*Choix 2 : Se connecter via Trello*/
        } else {
          Uri uri = Uri.parse(url);
          if (uri.getQuery().contains(OAuthConstants.VERIFIER)) {
            oauthVerifier = uri.getQueryParameter(OAuthConstants.VERIFIER);
            Single.fromCallable(new Callable() {
              @Override
              public OAuth1AccessToken call() throws Exception {
                accessToken = service.getAccessToken(requestToken, oauthVerifier);
                return accessToken;
              }
            }).subscribeOn(Schedulers.computation())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(new DisposableSingleObserver <Object>() {
                @Override
                public void onSuccess(Object value) {
                  Intent intent = new Intent(getContext(), TrelloActivity.class);
                  getContext().startActivity(intent);
                }

                @Override
                public void onError(Throwable e) {
                  e.printStackTrace();
                }
              });
          }
        }
        return false;
      }

      @Override
        public void onPageFinished(WebView view, String url) {
          super.onPageFinished(view, url);
          if (pd != null && pd.isShowing()) {
            pd.dismiss();
          }
      }
    });
  }
}
