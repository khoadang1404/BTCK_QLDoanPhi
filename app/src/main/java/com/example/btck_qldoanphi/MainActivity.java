package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        MaterialButton btnAddSV = (MaterialButton) findViewById(R.id.btnAddSV);

        Database database = new Database(this, "QuanLy.sqlite", null, 1);

        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(Id INTEGER)");

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginForm.class);
                startActivity(intent);
            }
        });
    }
}