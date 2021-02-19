package com.example.hdyazilim.randis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private Button buttonLogin;
    private TextView kayitOl;
    private FirebaseAuth kayitliKimlik;
    private FirebaseUser firebaseUser;
    private String userName;
    private String userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName=findViewById(R.id.editTextUserName);
        etUserPassword=findViewById(R.id.editTextUserPassword);
        buttonLogin=findViewById(R.id.buttonGiris);
        kayitOl=findViewById(R.id.kayitOl);

        kayitliKimlik=FirebaseAuth.getInstance();
        firebaseUser=kayitliKimlik.getCurrentUser();

        if (firebaseUser != null){

            Intent i = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etUserName.getText().toString();
                userPassword = etUserPassword.getText().toString();
                if(userName.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen Gerekli Alanları Doldurunuz.",Toast.LENGTH_SHORT).show();

                }else{
                    login();
                }
            }
        });

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    private void login(){
        kayitliKimlik.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(MainActivity.this,HomeActivity.class);
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
