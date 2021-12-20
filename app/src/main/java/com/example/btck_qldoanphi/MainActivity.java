package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    MaterialButton btnAddSV;
    ListView listviewSV;
    ArrayList<QLTPSinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        btnAddSV = (MaterialButton) findViewById(R.id.btnAddSV);

        listviewSV = (ListView) findViewById(R.id.listviewSV);
        arraySinhVien = new ArrayList<>();
        adapter = new SinhVienAdapter(this, R.layout.dong_sinh_vien, arraySinhVien);
        listviewSV.setAdapter(adapter);

        database = new Database(this, "QuanLy.sqlite", null, 1);

        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(Id INTEGER PRIMARY KEY AUTOINCREMENT, maSV VARCHAR(13) UNIQUE, " +
                "tenSV NVARCHAR(100), lopSV VARCHAR(10), tinhTrang NVARCHAR(50), ngayNop VARCHAR(11) NULL)");

        //get Data
        Cursor cursor = database.GetData("SELECT * FROM SinhVien");
        while (cursor.moveToNext()){
            arraySinhVien.add(new QLTPSinhVien(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            ));
        }
        adapter.notifyDataSetChanged();

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