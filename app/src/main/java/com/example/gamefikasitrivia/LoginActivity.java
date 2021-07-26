package com.example.gamefikasitrivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText tname,tPassword;
    Button btnLogin,btnRegister;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tname=(EditText) findViewById(R.id.tID);
        tPassword=(EditText) findViewById(R.id.tPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        databaseReference= FirebaseDatabase.getInstance().getReference("User");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd= null;
                try {
                    pwd = Security.hash(tPassword.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logIn(tname.getText().toString(),pwd);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intphto =new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intphto);
            }
        });
    }
    private void logIn(final String name,final String password) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(name).exists()){
                    if (!name.isEmpty()){
                        User user= dataSnapshot.child(name).getValue(User.class);
                        if (user.getPassword().equals(password)){
                            Users users= Users.getInstance();
                            users.setUsername(user.getUsername());

                            Intent intphto =new Intent(LoginActivity.this,HomeScreen.class);
                            startActivity(intphto);
                        }else {
                            Toast.makeText(LoginActivity.this,"Password Incorrect",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"Pengguna tidak terdaftar",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this,"Pengguna tidak terdaftar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
