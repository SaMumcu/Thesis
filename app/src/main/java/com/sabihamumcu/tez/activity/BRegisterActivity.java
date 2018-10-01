package com.sabihamumcu.tez.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.helper.FirebaseDBHelper;
import com.sabihamumcu.tez.helper.Helper;
import com.sabihamumcu.tez.helper.InputVerification;

public class BRegisterActivity extends AppCompatActivity {

    private EditText etMail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bregister);
        etMail = findViewById(R.id.et_mail);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaili = etMail.getText().toString().trim();
                final String sifresi = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(emaili)) {
                    Helper.displayMessageToast(BRegisterActivity.this, "Email adresinizi giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(sifresi)) {
                    Helper.displayMessageToast(BRegisterActivity.this, "Şifrenizi giriniz!");
                    return;
                }

                String verificationState = InputVerification.isValidInput(emaili, sifresi);
                if (InputVerification.verify(verificationState, BRegisterActivity.this)) {
                    FirebaseDBHelper.addUser(emaili, sifresi, BRegisterActivity.this);
                }

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BRegisterActivity.this, BLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
