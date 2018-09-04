package com.aristys.aristysapp.ui.login_trello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aristys.aristysapp.R;
import com.github.scribejava.apis.TrelloApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.aristys.aristysapp.utils.Constants.trello_url.API_KEY;
import static com.aristys.aristysapp.utils.Constants.trello_url.API_SECRET;

public class AuthorizeFragment extends Fragment {

    public static AuthorizeFragment newInstance() {
        return new AuthorizeFragment();
    }

    // # Constants used
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    //OAuth
    private OAuth10aService service;
    private OAuth1RequestToken requestToken;
    private String authorizationUrl;
    private String oauthVerifier;
    public static OAuth1AccessToken accessToken;

    //JSOUP
    private Connection.Response authorizationFormResponse;

    //UI
    private Button accept_button;
    private Button refuse_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_authorize, container, false);

        accept_button = rootView.findViewById(R.id.accept_button);
        refuse_button = rootView.findViewById(R.id.refuse_button);

        service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .build(TrelloApi.instance());

        ConnectToTrello();

        return rootView;
    }

    @SuppressLint("CheckResult")
    public void ConnectToTrello() {
        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                requestToken = service.getRequestToken();
                authorizationUrl = service.getAuthorizationUrl(requestToken);
                Log.d("URL", authorizationUrl);
                return authorizationUrl;
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String url) {
                        try {
                            handleUrl();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void handleUrl() throws IOException {
        Single.fromCallable(new Callable<Connection.Response>() {

            @Override
            public Connection.Response call() throws Exception {
                // # Go to authorization page
                authorizationFormResponse = Jsoup.connect(authorizationUrl)
                        .method(Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();
                return authorizationFormResponse;
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Connection.Response>() {
                    @Override
                    public void onSuccess(Connection.Response response) {
                        try {
                            test();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void test() throws IOException {
        //        // ## Find the form first...
        FormElement validationForm = (FormElement) authorizationFormResponse.parse()
                .select("#surface > div.account-content.clearfix > div.buttons > form").first();
        //Check if exist
        checkElement("Authorization Form", validationForm);

//         ## ... then find the button ...
        Element button_connect = validationForm.getElementsByClass("button primary").first();
        checkElement("Button Connect", button_connect);

        // # Now send the form for login
//        Connection.Response loginActionResponse = connectButton.submit()
//                .cookies(authorizationFormResponse.cookies())
//                .userAgent(USER_AGENT)
//                .execute();

    }

    public static void checkElement(String name, Element elem) {
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }
}