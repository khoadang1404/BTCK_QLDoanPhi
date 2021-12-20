package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add extends AppCompatActivity {
    EditText addID, addName, addClass, addDay;
    MaterialButton btnAdd;
    RadioButton rbAddDaNop, rbAddChuaNop;
    RadioGroup rbAddFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addDay = (EditText) findViewById(R.id.addDay);
        addID = (EditText) findViewById(R.id.addID);
        addName = (EditText) findViewById(R.id.addName);
        addClass = (EditText) findViewById(R.id.addClass);
        btnAdd = (MaterialButton) findViewById(R.id.btnAdd);
        ImageButton imgbtnBack = (ImageButton) findViewById(R.id.imgbtnBack);
        rbAddDaNop = (RadioButton) findViewById(R.id.rbAddDaNop);
        rbAddChuaNop = (RadioButton) findViewById(R.id.rbAddChuaNop);
        rbAddFaculty = (RadioGroup) findViewById(R.id.rbAddFaculty);
//        Calendar calendar1 = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
//        addDay.setText(simpleDateFormat1.format(calendar1.getTime()));


        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rbAddFaculty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbAddDaNop:
                        addDay.setEnabled(true);
                        break;
                    case R.id.rbAddChuaNop:
                        addDay.setEnabled(false);
                        addDay.setText("");
                        break;
                }
            }
        });


        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.INSERT_SINHVIEN(
                        addID.getText().toString().trim(),
                        addName.getText().toString().trim(),
                        addClass.getText().toString().trim(),
                        tinhTrang(),
                        addDay.getText().toString().trim()
                );
                Toast.makeText(Add.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Add.this, MainActivity.class));
            }
        });
    }

    private void ChonNgay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                addDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam, thang, ngay);
        datePickerDialog.show();
    }

    private String tinhTrang(){
        String tinhTrang;
        if(rbAddDaNop.isChecked()){
            tinhTrang = rbAddDaNop.getText().toString().trim();
        }else{
            tinhTrang = rbAddChuaNop.getText().toString().trim();
        }
        return tinhTrang;
    }
}