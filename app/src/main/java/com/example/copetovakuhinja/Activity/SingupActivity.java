package com.example.copetovakuhinja.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.copetovakuhinja.R;
import com.example.copetovakuhinja.databinding.ActivitySingupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SingupActivity extends BaseActivity {

    ActivitySingupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

        binding.prijaviSeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignupActivity
                startActivity(new Intent(SingupActivity.this, LoginActivity.class));
            }
        });
    }

    private void setVariable() {
    binding.signupBtn.setOnClickListener(v -> {
        String email=binding.userEdt.getText().toString();
        String password=binding.passEdt.getText().toString();

        if(password.length()<6){
            Toast.makeText(SingupActivity.this,"Vasa lozinka mora sadrzati 6 znakova",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SingupActivity.this, task -> {
            if(task.isSuccessful()){
                Log.i(TAG, "onComplete:");
                startActivity(new Intent(SingupActivity.this,MainActivity.class));
            }else{
                Log.i(TAG, "Failure: "+task.getException());
                Toast.makeText(SingupActivity.this,"Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });
    });
    }
}