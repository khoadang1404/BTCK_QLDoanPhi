package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterForm extends AppCompatActivity {

    EditText username, password, repassword;
    MaterialButton btnRegister;
    TextView tvbacktoLoginForm;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        btnRegister = (MaterialButton) findViewById(R.id.btnRegister);
        tvbacktoLoginForm = (TextView) findViewById(R.id.tvbacktoLoginForm);

        myDB = new DBHelper(this);

        tvbacktoLoginForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(RegisterForm.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean usercheckResult =  myDB.checkusername(user);
                        if(usercheckResult == false){
                            Boolean regesult = myDB.insertData(user, pass);
                            if(regesult == true){
                                Toast.makeText(RegisterForm.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginForm.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterForm.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterForm.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterForm.this, "Không trùng khớp mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}