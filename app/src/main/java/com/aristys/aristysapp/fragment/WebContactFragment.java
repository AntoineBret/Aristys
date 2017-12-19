package com.aristys.aristysapp.fragment;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


public class WebContactFragment extends Fragment {

  public static WebContactFragment newInstance() {
    return new WebContactFragment();
  }

  private NestedScrollView contactScrollView;
  private EditText contactInputFirstName, contactInputLastName, contactInputPhoneNumber, contactInputEmail, contactInputActivityArea, contactInputMessage;
  private TextInputLayout contactInputLayoutFirstName, contactInputLayoutLastName, contactInputLayoutPhoneNumber, contactInputLayoutEmail, contactInputLayoutActivityArea, contactInputLayoutMessage;
  private CheckBox checkBox_solution, checkBox_superior, checkBox_ux, checkBox_seo, checkBox_print, checkBox_safety, checkBox_mobileapp, checkBox_virtual;
  private String contact_subject, contact_first_name, contact_last_name, contact_phone_number, contact_email, contact_activity_area, contact_message;
  private Button contact_btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_webcontact, container, false);

    contactScrollView = (NestedScrollView) view.findViewById(R.id.contactScrollView);

    contactInputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.contact_input_layout_firstname);
    contactInputLayoutLastName = (TextInputLayout) view.findViewById(R.id.contact_input_layout_lastname);
    contactInputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.contact_input_layout_phonenumber);
    contactInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.contact_input_layout_email);
    contactInputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.contact_input_layout_activityarea);
    contactInputLayoutMessage = (TextInputLayout) view.findViewById(R.id.contact_input_layout_message);

    contactInputFirstName = (EditText) view.findViewById(R.id.contact_input_firstname);
    contactInputLastName = (EditText) view.findViewById(R.id.contact_input_lastname);
    contactInputPhoneNumber = (EditText) view.findViewById(R.id.contact_input_phonenumber);
    contactInputEmail = (EditText) view.findViewById(R.id.contact_input_email);
    contactInputActivityArea = (EditText) view.findViewById(R.id.contact_input_activityarea);
    contactInputMessage = (EditText) view.findViewById(R.id.contact_input_message);

    contactInputFirstName.addTextChangedListener(new WebContactFragment.ContactTextWatcher(contactInputFirstName));
    contactInputLastName.addTextChangedListener(new WebContactFragment.ContactTextWatcher(contactInputLastName));
    contactInputPhoneNumber.addTextChangedListener(new WebContactFragment.ContactTextWatcher(contactInputPhoneNumber));
    contactInputEmail.addTextChangedListener(new WebContactFragment.ContactTextWatcher(contactInputEmail));
    contactInputActivityArea.addTextChangedListener(new WebContactFragment.ContactTextWatcher(contactInputActivityArea));

    checkBox_solution = (CheckBox) view.findViewById(R.id.checkBox_solution);
    checkBox_superior = (CheckBox) view.findViewById(R.id.checkBox_superior);

    contact_btnSend = (Button) view.findViewById(R.id.contact_btn_send);
    contact_btnSend.setOnClickListener(new View.OnClickListener() {
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

    MaterialViewPagerHelper.registerScrollView(getActivity(), contactScrollView);
  }

  private void Options() {
  }

  private void submitForm() {

    contact_subject = (String) getText(R.string.web_object);
    contact_first_name = contactInputFirstName.getText().toString();
    contact_last_name = contactInputLastName.getText().toString();
    contact_phone_number = contactInputPhoneNumber.getText().toString();
    contact_email = contactInputEmail.getText().toString();
    contact_activity_area = contactInputActivityArea.getText().toString();
    contact_message = contactInputMessage.getText().toString();

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
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, contact_subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + contact_first_name + '\n'
        + "Prénom: " + contact_last_name + '\n'
        + "Téléphone: " + contact_phone_number + '\n'
        + "Adresse e-mail: " + contact_email + '\n'
        + "Secteur d'activité: " + contact_activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + contact_message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateContactFirstName() {
    if (contactInputFirstName.getText().toString().trim().isEmpty()) {
      contactInputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(contactInputFirstName);
      return false;
    } else {
      contactInputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactLastName() {
    if (contactInputLastName.getText().toString().trim().isEmpty()) {
      contactInputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(contactInputLastName);
      return false;
    } else {
      contactInputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactPhoneNumber() {
    if (contactInputPhoneNumber.getText().toString().trim().isEmpty()) {
      contactInputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(contactInputPhoneNumber);
      return false;
    } else {
      contactInputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactEmail() {
    String email = contactInputEmail.getText().toString().trim();

    if (email.isEmpty() || !isContactValidEmail(email)) {
      contactInputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(contactInputEmail);
      return false;
    } else {
      contactInputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactActivityArea() {
    if (contactInputActivityArea.getText().toString().trim().isEmpty()) {
      contactInputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(contactInputActivityArea);
      return false;
    } else {
      contactInputLayoutActivityArea.setErrorEnabled(false);
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
        case R.id.input_firstname:
          validateContactFirstName();
          break;
        case R.id.input_lastname:
          validateContactLastName();
          break;
        case R.id.input_phonenumber:
          validateContactPhoneNumber();
          break;
        case R.id.input_email:
          validateContactEmail();
          break;
        case R.id.input_activityarea:
          validateContactActivityArea();
          break;
      }
    }
  }
}
