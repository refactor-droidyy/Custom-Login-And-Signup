 package com.example.oooollxxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

 public class ResetPassActivity extends AppCompatActivity {

    private EditText emailpass;
    private Button sendd;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        emailpass = findViewById(R.id.email_input_forgot);
        sendd = findViewById(R.id.sendpass_btn);

        Toolbar tlbr = findViewById(R.id.forget);
        setSupportActionBar(tlbr);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tlbr.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();

        sendd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailpass.getText().toString();

                if(email.equals("")){
                    Toast.makeText(ResetPassActivity.this,"Please Enter Your Email",Toast.LENGTH_LONG).show();
                }else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassActivity.this,"Please Check Your Email",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(ResetPassActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                finish();

                            }else{
                                Toast.makeText(ResetPassActivity.this,""+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                }
            }
        });
    }
}
