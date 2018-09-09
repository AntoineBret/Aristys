package com.aristys.aristysapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aristys.aristysapp.session.AuthManager;
import com.google.android.gms.common.SignInButton;

public class StartSignInActivity extends PermissionActivity {
  private static final int RC_PERMISSIONS = 1;
  private AuthManager authManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start_sign_in);

    //Configure sign-in to request the user's ID, email address, and basic profile.
    authManager = AuthManager.getInstance();
    authManager.onCreate(this, HomeActivity.class);

    createSignInButton(R.id.sign_in_button, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switch (view.getId()) {
          case R.id.sign_in_button:
            authManager.signIn();
            break;
        }
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
/*
    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
    permissions.add(Manifest.permission.ACCESS_NOTIFICATION_POLICY);
    permissions.add(Manifest.permission.READ_CALENDAR);
    permissions.add(Manifest.permission.GET_ACCOUNTS);
*/
//    permissionUtils.checkPermissions(permissions, R.string.permission_rationale, RC_PERMISSIONS);

    authManager.onStart();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    authManager.onActivityResult(requestCode, resultCode, data);
  }

  public void createSignInButton(int id, View.OnClickListener listener) {
    SignInButton signInButton = (SignInButton) findViewById(id);
    signInButton.setSize(SignInButton.SIZE_WIDE);
    signInButton.setOnClickListener(listener);
    signInButton.setColorScheme(SignInButton.COLOR_DARK);
  }
}
