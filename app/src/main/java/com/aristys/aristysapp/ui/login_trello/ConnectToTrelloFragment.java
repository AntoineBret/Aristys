package com.aristys.aristysapp.ui.login_trello;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aristys.aristysapp.R;
import com.github.scribejava.core.model.OAuthConstants;

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

public class ConnectToTrelloFragment extends Fragment {

    public static ConnectToTrelloFragment newInstance() {
        return new ConnectToTrelloFragment();
    }

    // createLoginSession variable declaration
    private EditText inputLog;
    private EditText inputPassword;
    private String logInLog;
    private String logInPassword;
    private ImageView showPassword;
    private Button buttonLogIn;
    private int i = 0;

    //JSOUP
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
    private Connection.Response authorizationFormResponse;
    private Connection.Response loginActionResponse;
    private FormElement validationForm;

    private String trelloURL;
    private String oauthVerifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_connect_to_trello, container, false);

        //get data (redirectConnectionURl) from previous fragment
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            trelloURL = bundle.getString("redirectConnectionURl");
            System.out.println(trelloURL);
        }

        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);
        buttonLogIn = rootView.findViewById(R.id.buttonLog);

        showPassword = rootView.findViewById(R.id.showPassword);
        showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_show));
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0 /* first click  = change icon + show password*/) {
                    showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_hide));
                    Toast.makeText(getContext(), "show password !", Toast.LENGTH_LONG).show();
                    inputPassword.setTransformationMethod(new PasswordTransformationMethod());
                    i++;

                } else if (i == 1 /* second click = change icon + hide password*/) {
                    showPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_show));
                    inputPassword.setTransformationMethod(null);
                    //return to default state
                    i = 0;
                }
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    handleTrelloUrl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @SuppressLint("CheckResult")
    private void handleTrelloUrl() throws IOException {
        Single.fromCallable(new Callable<Connection.Response>() {

            @Override
            public Connection.Response call() throws Exception {
                // # Go to authorization page
                authorizationFormResponse = Jsoup.connect(trelloURL)
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
                            connectToAccount();
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
    private void connectToAccount() throws IOException {
        initLogin(); //get data from username & password edittext

        // ## Find the form first...
        validationForm = (FormElement) authorizationFormResponse.parse()
                .selectFirst("form#login-form");
        checkElement("Authorization Form", validationForm);

        // ## ... then "type" the username ...
        Element loginField = validationForm.selectFirst("#user");
        checkElement("Login Field", loginField);
        loginField.val(logInLog);

        // ## ... and "type" the password
        Element passwordField = validationForm.selectFirst("#password");
        checkElement("Password Field", passwordField);
        passwordField.val(logInPassword);

        Single.fromCallable(new Callable<Connection.Response>() {
            @Override
            public Connection.Response call() throws Exception {
                // # Go to authorization page
                loginActionResponse = validationForm.submit()
                        .cookies(authorizationFormResponse.cookies())
                        .userAgent(USER_AGENT)
                        .followRedirects(true)
                        .method(Connection.Method.POST)
                        .execute();
                return loginActionResponse;
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Connection.Response>() {
                    @Override
                    public void onSuccess(Connection.Response response) {
                        try {
                            Uri uri = Uri.parse(loginActionResponse.parse().html());
                            if (uri.getQuery().contains(OAuthConstants.VERIFIER)) {
                                oauthVerifier = uri.getQueryParameter(OAuthConstants.VERIFIER);
                                nextFragment();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public static void checkElement(String name, Element elem) {
        //Check if exist
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }

    private void initLogin() {
        logInLog = inputLog.getText().toString().trim();
        logInPassword = inputPassword.getText().toString().trim();
    }

    private void nextFragment() {
        // ## ... and send it to connect to Trello fragment ...
        Bundle bundle = new Bundle();
        bundle.putString("redirectConnectionURl", oauthVerifier);

        Fragment connectToTrelloFragment = ConnectToTrelloFragment.newInstance();
        connectToTrelloFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame_container, connectToTrelloFragment)
                .disallowAddToBackStack()
                .commit();
    }
}