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

public class OtherContactFragment extends Fragment {

  public static OtherContactFragment newInstance() {
    return new OtherContactFragment();
  }

  private NestedScrollView othercontactScrollView;
  private EditText othercontactInputFirstName, othercontactInputLastName, othercontactInputPhoneNumber, othercontactInputEmail, othercontactInputActivityArea, othercontactInputMessage;
  private TextInputLayout othercontactInputLayoutFirstName, othercontactInputLayoutLastName, othercontactInputLayoutPhoneNumber, othercontactInputLayoutEmail, othercontactInputLayoutActivityArea, othercontactInputLayoutMessage;
  private CheckBox checkBox_print, checkBox_photo, checkBox_virtual, checkBox_safety, checkBox_mobileapp;
  private String othercontact_subject, othercontact_first_name, othercontact_last_name, othercontact_phone_number, othercontact_email, othercontact_activity_area, othercontact_message;
  private Button othercontact_btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_othercontact, container, false);

    othercontactScrollView = (NestedScrollView) view.findViewById(R.id.othercontactScrollView);

    othercontactInputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_firstname);
    othercontactInputLayoutLastName = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_lastname);
    othercontactInputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_phonenumber);
    othercontactInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_email);
    othercontactInputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_activityarea);
    othercontactInputLayoutMessage = (TextInputLayout) view.findViewById(R.id.othercontact_input_layout_message);

    othercontactInputFirstName = (EditText) view.findViewById(R.id.othercontact_input_firstname);
    othercontactInputLastName = (EditText) view.findViewById(R.id.othercontact_input_lastname);
    othercontactInputPhoneNumber = (EditText) view.findViewById(R.id.othercontact_input_phonenumber);
    othercontactInputEmail = (EditText) view.findViewById(R.id.othercontact_input_email);
    othercontactInputActivityArea = (EditText) view.findViewById(R.id.othercontact_input_activityarea);
    othercontactInputMessage = (EditText) view.findViewById(R.id.othercontact_input_message);

    othercontactInputFirstName.addTextChangedListener(new OtherContactFragment.ContactTextWatcher(othercontactInputFirstName));
    othercontactInputLastName.addTextChangedListener(new OtherContactFragment.ContactTextWatcher(othercontactInputLastName));
    othercontactInputPhoneNumber.addTextChangedListener(new OtherContactFragment.ContactTextWatcher(othercontactInputPhoneNumber));
    othercontactInputEmail.addTextChangedListener(new OtherContactFragment.ContactTextWatcher(othercontactInputEmail));
    othercontactInputActivityArea.addTextChangedListener(new OtherContactFragment.ContactTextWatcher(othercontactInputActivityArea));

    checkBox_print = (CheckBox) view.findViewById(R.id.checkBox_print);
    checkBox_photo = (CheckBox) view.findViewById(R.id.checkBox_photo);
    checkBox_virtual = (CheckBox) view.findViewById(R.id.checkBox_virtual);
    checkBox_safety = (CheckBox) view.findViewById(R.id.checkBox_safety);
    checkBox_mobileapp = (CheckBox) view.findViewById(R.id.checkBox_mobileapp);

    othercontact_btnSend = (Button) view.findViewById(R.id.othercontact_btn_send);
    othercontact_btnSend.setOnClickListener(new View.OnClickListener() {
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

    MaterialViewPagerHelper.registerScrollView(getActivity(), othercontactScrollView);
  }

  private void Options() {
  }

  private void submitForm() {

    othercontact_subject = (String) getText(R.string.other_object);
    othercontact_first_name = othercontactInputFirstName.getText().toString();
    othercontact_last_name = othercontactInputLastName.getText().toString();
    othercontact_phone_number = othercontactInputPhoneNumber.getText().toString();
    othercontact_email = othercontactInputEmail.getText().toString();
    othercontact_activity_area = othercontactInputActivityArea.getText().toString();
    othercontact_message = othercontactInputMessage.getText().toString();

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
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, othercontact_subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + othercontact_first_name + '\n'
        + "Prénom: " + othercontact_last_name + '\n'
        + "Téléphone: " + othercontact_phone_number + '\n'
        + "Adresse e-mail: " + othercontact_email + '\n'
        + "Secteur d'activité: " + othercontact_activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + othercontact_message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateContactFirstName() {
    if (othercontactInputFirstName.getText().toString().trim().isEmpty()) {
      othercontactInputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(othercontactInputFirstName);
      return false;
    } else {
      othercontactInputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactLastName() {
    if (othercontactInputLastName.getText().toString().trim().isEmpty()) {
      othercontactInputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(othercontactInputLastName);
      return false;
    } else {
      othercontactInputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactPhoneNumber() {
    if (othercontactInputPhoneNumber.getText().toString().trim().isEmpty()) {
      othercontactInputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(othercontactInputPhoneNumber);
      return false;
    } else {
      othercontactInputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactEmail() {
    String email = othercontactInputEmail.getText().toString().trim();

    if (email.isEmpty() || !isContactValidEmail(email)) {
      othercontactInputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(othercontactInputEmail);
      return false;
    } else {
      othercontactInputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactActivityArea() {
    if (othercontactInputActivityArea.getText().toString().trim().isEmpty()) {
      othercontactInputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(othercontactInputActivityArea);
      return false;
    } else {
      othercontactInputLayoutActivityArea.setErrorEnabled(false);
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

  private class ContactTextWatcher implements TextWatcher {

    private View view;

    private ContactTextWatcher(View view) {
      this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
      switch (view.getId()) {
        case R.id.othercontact_input_firstname:
          validateContactFirstName();
          break;
        case R.id.othercontact_input_lastname:
          validateContactLastName();
          break;
        case R.id.othercontact_input_phonenumber:
          validateContactPhoneNumber();
          break;
        case R.id.othercontact_input_email:
          validateContactEmail();
          break;
        case R.id.othercontact_input_activityarea:
          validateContactActivityArea();
          break;
      }
    }
  }
}
