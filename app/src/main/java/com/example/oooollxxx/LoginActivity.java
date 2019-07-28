package com.example.oooollxxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private FirebaseAuth mauth;
    private Button signin;
    private EditText uuser, ppaswrd;
    private TextView newuser,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.signin_btn);
        uuser = findViewById(R.id.email_input_log);
        ppaswrd = findViewById(R.id.pass_input_log);
        newuser = findViewById(R.id.need_account);
        mauth = FirebaseAuth.getInstance();
        reset=  findViewById(R.id.reset);

        Toolbar tlbr = findViewById(R.id.log_toolbar);
        setSupportActionBar(tlbr);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbr.setTitleTextColor(0xFFFFFFFF);
        tlbr.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ResetPassActivity.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void signin() {
        String Email = uuser.getText().toString();
        String pass = ppaswrd.getText().toString();

        loading = new ProgressDialog(this);
        loading.setTitle("Logging In...");
        loading.setMessage("Please Wait .....");
        loading.show();

        mauth.signInWithEmailAndPassword(Email, pass).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, WorkingActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            loading.dismiss();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                            loading.dismiss();

                        }

                    }
                });
    }}
