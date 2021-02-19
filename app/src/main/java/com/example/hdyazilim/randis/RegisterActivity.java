package com.example.hdyazilim.randis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerUserName;
    private EditText registerPassword;
    private Button buttonRegister;
    private FirebaseAuth kayitliKimlik;
    private String userName;
    private String userPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUserName = findViewById(R.id.editTextRegisterUserName);
        registerPassword = findViewById(R.id.editTextRegisterPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        kayitliKimlik = FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = registerUserName.getText().toString();
                userPassword = registerPassword.getText().toString();
                if (userName.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen Gerekli Alanları Doldurunuz.",Toast.LENGTH_SHORT).show();
                }else {
                  register();
              }
            }
        });
    }
    private void register(){
        kayitliKimlik.createUserWithEmailAndPassword(userName,userPassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
