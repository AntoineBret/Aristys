package com.aristys.aristysapp.ui.login_trello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aristys.aristysapp.R;
import com.github.scribejava.apis.TrelloApi;
import com.github.scribejava.core.builder.ServiceBuilder;
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

    //JSOUP
    private Connection.Response authorizationFormResponse;
    private String redirectConnectionURl;
    //UI
    private Button accept_button;
    private Button refuse_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_authorize, container, false);

        accept_button = rootView.findViewById(R.id.buttonServiceAgree);
        refuse_button = rootView.findViewById(R.id.buttonServiceDecline);

        service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .build(TrelloApi.instance());

        ConnectToTrello();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ## ... and send it to connect to Trello fragment ...
                Bundle bundle = new Bundle();
                bundle.putString("redirectConnectionURl", redirectConnectionURl);

                Fragment connectToTrelloFragment = ConnectToTrelloFragment.newInstance();
                connectToTrelloFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_frame_container, connectToTrelloFragment)
                        .disallowAddToBackStack()
                        .commit();
            }
        });

        refuse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @SuppressLint("CheckResult")
    public void ConnectToTrello() {
        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                requestToken = service.getRequestToken();
                authorizationUrl = service.getAuthorizationUrl(requestToken);
                System.out.println(authorizationUrl);
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
                            parseURL();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void parseURL() throws IOException {
        // ## Find the form first...
        FormElement validationForm = (FormElement) authorizationFormResponse.parse()
                .selectFirst("#surface > div.account-content.clearfix > div.buttons > form");
        //Check if exist
        checkElement("Authorization Form", validationForm);

        // ## ... then find the button ...
        Element button_connect = validationForm.getElementsByClass("button primary").first();
        //Check if exist
        checkElement("Button Connect", button_connect);

        // ## ... get href value of class element ...
        redirectConnectionURl = "https://trello.com" + (button_connect.attr("href"));

    }

    public static void checkElement(String name, Element elem) {
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }
}