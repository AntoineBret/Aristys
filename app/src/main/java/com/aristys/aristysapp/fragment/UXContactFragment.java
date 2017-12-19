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

public class UXContactFragment extends Fragment {

  public static UXContactFragment newInstance() {
    return new UXContactFragment();
  }

  private NestedScrollView uxcontactScrollView;
  private EditText uxcontactInputFirstName, uxcontactInputLastName, uxcontactInputPhoneNumber, uxcontactInputEmail, uxcontactInputActivityArea, uxcontactInputMessage;
  private TextInputLayout uxcontactInputLayoutFirstName, uxcontactInputLayoutLastName, uxcontactInputLayoutPhoneNumber, uxcontactInputLayoutEmail, uxcontactInputLayoutActivityArea, uxcontactInputLayoutMessage;
  private CheckBox checkBox_ux;
  private String uxcontact_subject, uxcontact_first_name, uxcontact_last_name, uxcontact_phone_number, uxcontact_email, uxcontact_activity_area, uxcontact_message;
  private Button uxcontact_btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_uxcontact, container, false);

    uxcontactScrollView = (NestedScrollView) view.findViewById(R.id.uxcontactScrollView);

    uxcontactInputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_firstname);
    uxcontactInputLayoutLastName = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_lastname);
    uxcontactInputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_phonenumber);
    uxcontactInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_email);
    uxcontactInputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_activityarea);
    uxcontactInputLayoutMessage = (TextInputLayout) view.findViewById(R.id.uxcontact_input_layout_message);

    uxcontactInputFirstName = (EditText) view.findViewById(R.id.uxcontact_input_firstname);
    uxcontactInputLastName = (EditText) view.findViewById(R.id.uxcontact_input_lastname);
    uxcontactInputPhoneNumber = (EditText) view.findViewById(R.id.uxcontact_input_phonenumber);
    uxcontactInputEmail = (EditText) view.findViewById(R.id.uxcontact_input_email);
    uxcontactInputActivityArea = (EditText) view.findViewById(R.id.uxcontact_input_activityarea);
    uxcontactInputMessage = (EditText) view.findViewById(R.id.uxcontact_input_message);

    uxcontactInputFirstName.addTextChangedListener(new UXContactFragment.UxContactTextWatcher(uxcontactInputFirstName));
    uxcontactInputLastName.addTextChangedListener(new UXContactFragment.UxContactTextWatcher(uxcontactInputLastName));
    uxcontactInputPhoneNumber.addTextChangedListener(new UXContactFragment.UxContactTextWatcher(uxcontactInputPhoneNumber));
    uxcontactInputEmail.addTextChangedListener(new UXContactFragment.UxContactTextWatcher(uxcontactInputEmail));
    uxcontactInputActivityArea.addTextChangedListener(new UXContactFragment.UxContactTextWatcher(uxcontactInputActivityArea));

    checkBox_ux = (CheckBox) view.findViewById(R.id.checkBox_ux);

    uxcontact_btnSend = (Button) view.findViewById(R.id.uxcontact_btn_send);
    uxcontact_btnSend.setOnClickListener(new View.OnClickListener() {
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

    MaterialViewPagerHelper.registerScrollView(getActivity(), uxcontactScrollView);
  }

  private void Options() {
  }

  private void submitForm() {

    uxcontact_subject = (String) getText(R.string.ux_object);
    uxcontact_first_name = uxcontactInputFirstName.getText().toString();
    uxcontact_last_name = uxcontactInputLastName.getText().toString();
    uxcontact_phone_number = uxcontactInputPhoneNumber.getText().toString();
    uxcontact_email = uxcontactInputEmail.getText().toString();
    uxcontact_activity_area = uxcontactInputActivityArea.getText().toString();
    uxcontact_message = uxcontactInputMessage.getText().toString();

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
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, uxcontact_subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + uxcontact_first_name + '\n'
        + "Prénom: " + uxcontact_last_name + '\n'
        + "Téléphone: " + uxcontact_phone_number + '\n'
        + "Adresse e-mail: " + uxcontact_email + '\n'
        + "Secteur d'activité: " + uxcontact_activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + uxcontact_message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateContactFirstName() {
    if (uxcontactInputFirstName.getText().toString().trim().isEmpty()) {
      uxcontactInputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(uxcontactInputFirstName);
      return false;
    } else {
      uxcontactInputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactLastName() {
    if (uxcontactInputLastName.getText().toString().trim().isEmpty()) {
      uxcontactInputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(uxcontactInputLastName);
      return false;
    } else {
      uxcontactInputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactPhoneNumber() {
    if (uxcontactInputPhoneNumber.getText().toString().trim().isEmpty()) {
      uxcontactInputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(uxcontactInputPhoneNumber);
      return false;
    } else {
      uxcontactInputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactEmail() {
    String email = uxcontactInputEmail.getText().toString().trim();

    if (email.isEmpty() || !isContactValidEmail(email)) {
      uxcontactInputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(uxcontactInputEmail);
      return false;
    } else {
      uxcontactInputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactActivityArea() {
    if (uxcontactInputActivityArea.getText().toString().trim().isEmpty()) {
      uxcontactInputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(uxcontactInputActivityArea);
      return false;
    } else {
      uxcontactInputLayoutActivityArea.setErrorEnabled(false);
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

  private class UxContactTextWatcher implements TextWatcher {

    private View view;

    private UxContactTextWatcher(View view) {
      this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
      switch (view.getId()) {
        case R.id.uxcontact_input_firstname:
          validateContactFirstName();
          break;
        case R.id.uxcontact_input_lastname:
          validateContactLastName();
          break;
        case R.id.uxcontact_input_phonenumber:
          validateContactPhoneNumber();
          break;
        case R.id.uxcontact_input_email:
          validateContactEmail();
          break;
        case R.id.uxcontact_input_activityarea:
          validateContactActivityArea();
          break;
      }
    }
  }
}
