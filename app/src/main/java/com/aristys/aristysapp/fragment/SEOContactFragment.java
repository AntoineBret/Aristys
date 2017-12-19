package com.aristys.aristysapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aristys.aristysapp.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

public class SEOContactFragment extends Fragment {

  public static SEOContactFragment newInstance() {
    return new SEOContactFragment();
  }

  private NestedScrollView seocontactScrollView;
  private EditText seocontactInputFirstName, seocontactInputLastName, seocontactInputPhoneNumber, seocontactInputEmail, seocontactInputActivityArea, seocontactInputMessage;
  private TextInputLayout seocontactInputLayoutFirstName, seocontactInputLayoutLastName, seocontactInputLayoutPhoneNumber, seocontactInputLayoutEmail, seocontactInputLayoutActivityArea, seocontactInputLayoutMessage;
  private CheckBox checkBox_seo;
  private String seocontact_subject, seocontact_first_name, seocontact_last_name, seocontact_phone_number, seocontact_email, seocontact_activity_area, seocontact_message;
  private Button seocontact_btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_seocontact, container, false);

    seocontactScrollView = (NestedScrollView) view.findViewById(R.id.seocontactScrollView);

    seocontactInputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_firstname);
    seocontactInputLayoutLastName = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_lastname);
    seocontactInputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_phonenumber);
    seocontactInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_email);
    seocontactInputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_activityarea);
    seocontactInputLayoutMessage = (TextInputLayout) view.findViewById(R.id.seocontact_input_layout_message);

    seocontactInputFirstName = (EditText) view.findViewById(R.id.seocontact_input_firstname);
    seocontactInputLastName = (EditText) view.findViewById(R.id.seocontact_input_lastname);
    seocontactInputPhoneNumber = (EditText) view.findViewById(R.id.seocontact_input_phonenumber);
    seocontactInputEmail = (EditText) view.findViewById(R.id.seocontact_input_email);
    seocontactInputActivityArea = (EditText) view.findViewById(R.id.seocontact_input_activityarea);
    seocontactInputMessage = (EditText) view.findViewById(R.id.seocontact_input_message);

    seocontactInputFirstName.addTextChangedListener(new SEOContactFragment.SeoContactTextWatcher(seocontactInputFirstName));
    seocontactInputLastName.addTextChangedListener(new SEOContactFragment.SeoContactTextWatcher(seocontactInputLastName));
    seocontactInputPhoneNumber.addTextChangedListener(new SEOContactFragment.SeoContactTextWatcher(seocontactInputPhoneNumber));
    seocontactInputEmail.addTextChangedListener(new SEOContactFragment.SeoContactTextWatcher(seocontactInputEmail));
    seocontactInputActivityArea.addTextChangedListener(new SEOContactFragment.SeoContactTextWatcher(seocontactInputActivityArea));

    checkBox_seo = (CheckBox) view.findViewById(R.id.seo_checkBox);


    seocontact_btnSend = (Button) view.findViewById(R.id.seocontact_btn_send);
    seocontact_btnSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        submitForm();
      }
    });
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    MaterialViewPagerHelper.registerScrollView(getActivity(), seocontactScrollView);
  }

  private void Options() {
  }

  private void submitForm() {

    seocontact_subject = (String) getText(R.string.seo_object);
    seocontact_first_name = seocontactInputFirstName.getText().toString();
    seocontact_last_name = seocontactInputLastName.getText().toString();
    seocontact_phone_number = seocontactInputPhoneNumber.getText().toString();
    seocontact_email = seocontactInputEmail.getText().toString();
    seocontact_activity_area = seocontactInputActivityArea.getText().toString();
    seocontact_message = seocontactInputMessage.getText().toString();

    if (!validateContactFirstName()) {
      return;
    }

    if (!validateContactLastName()) {
      return;
    }

    if (!validateContactPhoneNumber()) {
      return;
    }

    if (!validateContactEmail()) {
      return;
    }

    if (!validateContactActivityArea()) {
      return;
    }

    Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
    sendEmail.setType("plain/text");
    sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"contact@aristys-web.com"});
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, seocontact_subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + seocontact_first_name + '\n'
        + "Prénom: " + seocontact_last_name + '\n'
        + "Téléphone: " + seocontact_phone_number + '\n'
        + "Adresse e-mail: " + seocontact_email + '\n'
        + "Secteur d'activité: " + seocontact_activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + seocontact_message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateContactFirstName() {
    if (seocontactInputFirstName.getText().toString().trim().isEmpty()) {
      seocontactInputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(seocontactInputFirstName);
      return false;
    } else {
      seocontactInputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactLastName() {
    if (seocontactInputLastName.getText().toString().trim().isEmpty()) {
      seocontactInputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(seocontactInputLastName);
      return false;
    } else {
      seocontactInputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactPhoneNumber() {
    if (seocontactInputPhoneNumber.getText().toString().trim().isEmpty()) {
      seocontactInputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(seocontactInputPhoneNumber);
      return false;
    } else {
      seocontactInputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactEmail() {
    String email = seocontactInputEmail.getText().toString().trim();

    if (email.isEmpty() || !isContactValidEmail(email)) {
      seocontactInputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(seocontactInputEmail);
      return false;
    } else {
      seocontactInputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactActivityArea() {
    if (seocontactInputActivityArea.getText().toString().trim().isEmpty()) {
      seocontactInputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(seocontactInputActivityArea);
      return false;
    } else {
      seocontactInputLayoutActivityArea.setErrorEnabled(false);
    }

    return true;
  }

  private static boolean isContactValidEmail(String email) {
    return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  private void requestFocus(View view) {
    if (view.requestFocus()) {
      getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
  }

  private class SeoContactTextWatcher implements TextWatcher {

    private View view;

    private SeoContactTextWatcher(View view) {
      this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
      switch (view.getId()) {
        case R.id.seocontact_input_firstname:
          validateContactFirstName();
          break;
        case R.id.seocontact_input_lastname:
          validateContactLastName();
          break;
        case R.id.seocontact_input_phonenumber:
          validateContactPhoneNumber();
          break;
        case R.id.seocontact_input_email:
          validateContactEmail();
          break;
        case R.id.seocontact_input_activityarea:
          validateContactActivityArea();
          break;
      }
    }
  }
}
