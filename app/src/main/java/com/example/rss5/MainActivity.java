package com.example.rss5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView m1_txtLogin;
    EditText m1_etxtUsername,m1_etxtPassword;
    CheckBox m1_checkBox;
    Button m1_btnLogin;
    SharedPreferences sharedPreferences;
    private void AnhXa(){
        m1_txtLogin=(TextView) findViewById(R.id.m1_txtLogin);
        m1_etxtUsername=(EditText) findViewById(R.id.etxtUsername);
        m1_etxtPassword=(EditText) findViewById(R.id.m1_etxtPassword);
        m1_checkBox=(CheckBox) findViewById(R.id.m1_checkBox);
        m1_btnLogin=(Button) findViewById(R.id.m1_btnLogin);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        ///gán giá trị cho edittext
        m1_etxtUsername.setText(sharedPreferences.getString("Username",""));
        m1_etxtPassword.setText(sharedPreferences.getString("Password",""));
        m1_checkBox.setChecked(sharedPreferences.getBoolean("Checked",false));
        m1_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = m1_etxtUsername.getText().toString();
                String password = m1_etxtPassword.getText().toString();
                if(username.equals("Giang2001") && password.equals("26042001")){
                    if(m1_checkBox.isChecked()){
                        SharedPreferences.Editor  editor =  sharedPreferences.edit();
                        editor.putString("Username",username);
                        editor.putString("Password",password);
                        editor.putBoolean("Checked",true);
                        editor.commit();
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("Username");
                        editor.remove("Password");
                        editor.remove("Checked");
                        editor.commit();
                    }
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Sai tên tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}