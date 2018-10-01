package com.sabihamumcu.tez.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.helper.FirebaseDBHelper;
import com.sabihamumcu.tez.helper.Helper;
import com.sabihamumcu.tez.helper.InputVerification;

/**
 * Created by sabis on 3/11/2018.
 */

public class RegisterFragment extends Fragment {

    private EditText etMail;
    private EditText etPassword;
    private Button btnRegister;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        etMail = view.findViewById(R.id.et_mail);
        etPassword = view.findViewById(R.id.et_password);
        btnRegister = view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaili = etMail.getText().toString().trim();
                final String sifresi = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(emaili)) {
                    Helper.displayMessageToast(getActivity(), "Email adresinizi giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(sifresi)) {
                    Helper.displayMessageToast(getActivity(), "Şifrenizi giriniz!");
                    return;
                }

                String verificationState = InputVerification.isValidInput(emaili, sifresi);
                if (InputVerification.verify(verificationState, getActivity())) {
                    FirebaseDBHelper.addUser(emaili, sifresi, getActivity());
                }

            }
        });
        return view;
    }

}
