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

public class EcoContactFragment extends Fragment {

  public static EcoContactFragment newInstance() {
    return new EcoContactFragment();
  }

  private NestedScrollView ecocontactScrollView;
  private EditText ecocontactInputFirstName, ecocontactInputLastName, ecocontactInputPhoneNumber, ecocontactInputEmail, ecocontactInputActivityArea, ecocontactInputMessage;
  private TextInputLayout ecocontactInputLayoutFirstName, ecocontactInputLayoutLastName, ecocontactInputLayoutPhoneNumber, ecocontactInputLayoutEmail, ecocontactInputLayoutActivityArea, ecocontactInputLayoutMessage;
  private CheckBox checkBox_eco;
  private String ecocontact_subject, ecocontact_first_name, ecocontact_last_name, ecocontact_phone_number, ecocontact_email, ecocontact_activity_area, ecocontact_message;
  private Button ecocontact_btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ecocontact, container, false);

    ecocontactScrollView = (NestedScrollView) view.findViewById(R.id.ecocontactScrollView);

    ecocontactInputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_firstname);
    ecocontactInputLayoutLastName = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_lastname);
    ecocontactInputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_phonenumber);
    ecocontactInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_email);
    ecocontactInputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_activityarea);
    ecocontactInputLayoutMessage = (TextInputLayout) view.findViewById(R.id.ecocontact_input_layout_message);

    ecocontactInputFirstName = (EditText) view.findViewById(R.id.ecocontact_input_firstname);
    ecocontactInputLastName = (EditText) view.findViewById(R.id.ecocontact_input_lastname);
    ecocontactInputPhoneNumber = (EditText) view.findViewById(R.id.ecocontact_input_phonenumber);
    ecocontactInputEmail = (EditText) view.findViewById(R.id.ecocontact_input_email);
    ecocontactInputActivityArea = (EditText) view.findViewById(R.id.ecocontact_input_activityarea);
    ecocontactInputMessage = (EditText) view.findViewById(R.id.ecocontact_input_message);

    ecocontactInputFirstName.addTextChangedListener(new EcoContactFragment.EcoContactTextWatcher(ecocontactInputFirstName));
    ecocontactInputLastName.addTextChangedListener(new EcoContactFragment.EcoContactTextWatcher(ecocontactInputLastName));
    ecocontactInputPhoneNumber.addTextChangedListener(new EcoContactFragment.EcoContactTextWatcher(ecocontactInputPhoneNumber));
    ecocontactInputEmail.addTextChangedListener(new EcoContactFragment.EcoContactTextWatcher(ecocontactInputEmail));
    ecocontactInputActivityArea.addTextChangedListener(new EcoContactFragment.EcoContactTextWatcher(ecocontactInputActivityArea));

    checkBox_eco = (CheckBox) view.findViewById(R.id.checkBox_eco);

    ecocontact_btnSend = (Button) view.findViewById(R.id.ecocontact_btn_send);
    ecocontact_btnSend.setOnClickListener(new View.OnClickListener() {
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

    MaterialViewPagerHelper.registerScrollView(getActivity(), ecocontactScrollView);
  }

  private void Options() {
  }

  private void submitForm() {

    ecocontact_subject = (String) getText(R.string.eco_object);
    ecocontact_first_name = ecocontactInputFirstName.getText().toString();
    ecocontact_last_name = ecocontactInputLastName.getText().toString();
    ecocontact_phone_number = ecocontactInputPhoneNumber.getText().toString();
    ecocontact_email = ecocontactInputEmail.getText().toString();
    ecocontact_activity_area = ecocontactInputActivityArea.getText().toString();
    ecocontact_message = ecocontactInputMessage.getText().toString();

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
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, ecocontact_subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + ecocontact_first_name + '\n'
        + "Prénom: " + ecocontact_last_name + '\n'
        + "Téléphone: " + ecocontact_phone_number + '\n'
        + "Adresse e-mail: " + ecocontact_email + '\n'
        + "Secteur d'activité: " + ecocontact_activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + ecocontact_message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateContactFirstName() {
    if (ecocontactInputFirstName.getText().toString().trim().isEmpty()) {
      ecocontactInputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(ecocontactInputFirstName);
      return false;
    } else {
      ecocontactInputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactLastName() {
    if (ecocontactInputLastName.getText().toString().trim().isEmpty()) {
      ecocontactInputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(ecocontactInputLastName);
      return false;
    } else {
      ecocontactInputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactPhoneNumber() {
    if (ecocontactInputPhoneNumber.getText().toString().trim().isEmpty()) {
      ecocontactInputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(ecocontactInputPhoneNumber);
      return false;
    } else {
      ecocontactInputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactEmail() {
    String email = ecocontactInputEmail.getText().toString().trim();

    if (email.isEmpty() || !isContactValidEmail(email)) {
      ecocontactInputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(ecocontactInputEmail);
      return false;
    } else {
      ecocontactInputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateContactActivityArea() {
    if (ecocontactInputActivityArea.getText().toString().trim().isEmpty()) {
      ecocontactInputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(ecocontactInputActivityArea);
      return false;
    } else {
      ecocontactInputLayoutActivityArea.setErrorEnabled(false);
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

  private class EcoContactTextWatcher implements TextWatcher {

    private View view;

    private EcoContactTextWatcher(View view) {
      this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
      switch (view.getId()) {
        case R.id.ecocontact_input_firstname:
          validateContactFirstName();
          break;
        case R.id.ecocontact_input_lastname:
          validateContactLastName();
          break;
        case R.id.ecocontact_input_phonenumber:
          validateContactPhoneNumber();
          break;
        case R.id.ecocontact_input_email:
          validateContactEmail();
          break;
        case R.id.ecocontact_input_activityarea:
          validateContactActivityArea();
          break;
      }
    }
  }
}
