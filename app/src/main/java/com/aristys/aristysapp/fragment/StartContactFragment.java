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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.aristys.aristysapp.R;
import com.bluejamesbond.text.DocumentView;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

import java.util.ArrayList;

public class StartContactFragment extends Fragment {

  public static StartContactFragment newInstance() {
    return new StartContactFragment();
  }

  private NestedScrollView mScrollView;
  private EditText inputFirstName, inputLastName, inputPhoneNumber, inputEmail, inputActivityArea, inputMessage;
  private TextInputLayout inputLayoutFirstName, inputLayoutLastName, inputLayoutPhoneNumber, inputLayoutEmail, inputLayoutActivityArea, inputLayoutMessage;
  private CheckBox checkBox_horizon, checkBox_hope, checkBox_fusion, checkBox_origin, checkBox_extra, checkBox_wedding;
  private Switch animation_Switch, seo_Switch, eco_Switch, translation_Switch, photo_Switch, blog_Switch, menu_Switch, contact_Switch;
  private String subject, first_name, last_name, phone_number, email, activity_area, message, animation, seo, eco, translation, photo, blog, menu, contact;
  private Button btnSend;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_startcontact, container, false);

    mScrollView = (NestedScrollView) view.findViewById(R.id.scrollView);

    inputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.input_layout_firstname);
    inputLayoutLastName = (TextInputLayout) view.findViewById(R.id.input_layout_lastname);
    inputLayoutPhoneNumber = (TextInputLayout) view.findViewById(R.id.input_layout_phonenumber);
    inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
    inputLayoutActivityArea = (TextInputLayout) view.findViewById(R.id.input_layout_activityarea);
    inputLayoutMessage = (TextInputLayout) view.findViewById(R.id.input_layout_message);

    inputFirstName = (EditText) view.findViewById(R.id.input_firstname);
    inputLastName = (EditText) view.findViewById(R.id.input_lastname);
    inputPhoneNumber = (EditText) view.findViewById(R.id.input_phonenumber);
    inputEmail = (EditText) view.findViewById(R.id.input_email);
    inputActivityArea = (EditText) view.findViewById(R.id.input_activityarea);
    inputMessage = (EditText) view.findViewById(R.id.input_message);

    inputFirstName.addTextChangedListener(new MyTextWatcher(inputFirstName));
    inputLastName.addTextChangedListener(new MyTextWatcher(inputLastName));
    inputPhoneNumber.addTextChangedListener(new MyTextWatcher(inputPhoneNumber));
    inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
    inputActivityArea.addTextChangedListener(new MyTextWatcher(inputActivityArea));

    checkBox_horizon = (CheckBox) view.findViewById(R.id.checkBox_horizon);
    checkBox_hope = (CheckBox) view.findViewById(R.id.checkBox_hope);
    checkBox_fusion = (CheckBox) view.findViewById(R.id.checkBox_fusion);
    checkBox_origin = (CheckBox) view.findViewById(R.id.checkBox_origin);
    checkBox_extra = (CheckBox) view.findViewById(R.id.checkBox_extra);
    checkBox_wedding = (CheckBox) view.findViewById(R.id.checkBox_wedding);

    animation_Switch = (Switch) view.findViewById(R.id.animation_Switch);
    seo_Switch = (Switch) view.findViewById(R.id.seo_Switch);
    eco_Switch = (Switch) view.findViewById(R.id.eco_Switch);
    translation_Switch = (Switch) view.findViewById(R.id.translation_Switch);
    photo_Switch = (Switch) view.findViewById(R.id.photo_Switch);
    blog_Switch = (Switch) view.findViewById(R.id.blog_Switch);
    menu_Switch = (Switch) view.findViewById(R.id.menu_Switch);
    contact_Switch = (Switch) view.findViewById(R.id.contact_Switch);

    btnSend = (Button) view.findViewById(R.id.btn_send);
    btnSend.setOnClickListener(new View.OnClickListener() {
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

    MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView);
  }

  private void Options() {

    animation = (String) getText(R.string.start_animation);
    seo = (String) getText(R.string.start_seo);
    eco = (String) getText(R.string.start_eco);
    translation = (String) getText(R.string.start_translation);
    photo = (String) getText(R.string.start_photo);
    blog = (String) getText(R.string.start_blog);
    menu = (String) getText(R.string.start_menu);
    contact = (String) getText(R.string.start_contact);

    ArrayList arrayList = new ArrayList();

    if (animation_Switch.isEnabled()) {
      arrayList.add(animation);
    }
    if (seo_Switch.isEnabled()) {
      arrayList.add(seo);
    }
    if (eco_Switch.isEnabled()) {
      arrayList.add(eco);
    }
    if (translation_Switch.isEnabled()) {
      arrayList.add(translation);
    }
    if (photo_Switch.isEnabled()) {
      arrayList.add(photo);
    }
    if (blog_Switch.isEnabled()) {
      arrayList.add(blog);
    }
    if (menu_Switch.isEnabled()) {
      arrayList.add(menu);
    }
    if (contact_Switch.isEnabled()) {
      arrayList.add(contact);
    }

    int totalElements = arrayList.size();


    System.out.println("ArrayList contains...");
    for (int index = 0; index < totalElements; index++)
      System.out.println(arrayList.get(index));

  }

  private void submitForm() {

    subject = (String) getText(R.string.start_object);
    first_name = inputFirstName.getText().toString();
    last_name = inputLastName.getText().toString();
    phone_number = inputPhoneNumber.getText().toString();
    email = inputEmail.getText().toString();
    activity_area = inputActivityArea.getText().toString();
    message = inputMessage.getText().toString();

    if (!validateFirstName()) {
      return;
    }

    if (!validateLastName()) {
      return;
    }

    if (!validatePhoneNumber()) {
      return;
    }

    if (!validateEmail()) {
      return;
    }

    if (!validateActivityArea()) {
      return;
    }

    Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
    sendEmail.setType("plain/text");
    sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"contact@aristys-web.com"});
    sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
    sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
      "Nom: " + first_name + '\n'
        + "Prénom: " + last_name + '\n'
        + "Téléphone: " + phone_number + '\n'
        + "Adresse e-mail: " + email + '\n'
        + "Secteur d'activité: " + activity_area + '\n'
        + "Options choisies: " + "" + '\n'
        + "Message: " + message + '\n');

    startActivity(Intent.createChooser(sendEmail, "Send mail..."));
    Toast.makeText(getContext(), "Merci!", Toast.LENGTH_SHORT).show();
  }

  private boolean validateFirstName() {
    if (inputFirstName.getText().toString().trim().isEmpty()) {
      inputLayoutFirstName.setError(getString(R.string.err_msg_firstname));
      requestFocus(inputFirstName);
      return false;
    } else {
      inputLayoutFirstName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateLastName() {
    if (inputLastName.getText().toString().trim().isEmpty()) {
      inputLayoutLastName.setError(getString(R.string.err_msg_lastname));
      requestFocus(inputLastName);
      return false;
    } else {
      inputLayoutLastName.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validatePhoneNumber() {
    if (inputPhoneNumber.getText().toString().trim().isEmpty()) {
      inputLayoutPhoneNumber.setError(getString(R.string.err_msg_phonenumber));
      requestFocus(inputPhoneNumber);
      return false;
    } else {
      inputLayoutPhoneNumber.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateEmail() {
    String email = inputEmail.getText().toString().trim();

    if (email.isEmpty() || !isValidEmail(email)) {
      inputLayoutEmail.setError(getString(R.string.err_msg_email));
      requestFocus(inputEmail);
      return false;
    } else {
      inputLayoutEmail.setErrorEnabled(false);
    }

    return true;
  }

  private boolean validateActivityArea() {
    if (inputActivityArea.getText().toString().trim().isEmpty()) {
      inputLayoutActivityArea.setError(getString(R.string.err_msg_activityarea));
      requestFocus(inputActivityArea);
      return false;
    } else {
      inputLayoutActivityArea.setErrorEnabled(false);
    }

    return true;
  }

  private static boolean isValidEmail(String email) {
    return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  private void requestFocus(View view) {
    if (view.requestFocus()) {
      getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
  }

  private class MyTextWatcher implements TextWatcher {

    private View view;

    private MyTextWatcher(View view) {
      this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
      switch (view.getId()) {
        case R.id.input_firstname:
          validateFirstName();
          break;
        case R.id.input_lastname:
          validateLastName();
          break;
        case R.id.input_phonenumber:
          validatePhoneNumber();
          break;
        case R.id.input_email:
          validateEmail();
          break;
        case R.id.input_activityarea:
          validateActivityArea();
          break;
      }
    }
  }
}
