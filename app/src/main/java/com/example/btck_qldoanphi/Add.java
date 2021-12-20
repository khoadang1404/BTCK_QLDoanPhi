package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add extends AppCompatActivity {
    EditText addID, addName, addClass, addDay, addFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addDay = (EditText) findViewById(R.id.addDay);
        addFaculty = (EditText) findViewById(R.id.addFaculty);
        addID = (EditText) findViewById(R.id.addID);
        addName = (EditText) findViewById(R.id.addName);
        addClass = (EditText) findViewById(R.id.addClass);
//        Calendar calendar1 = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
//        addDay.setText(simpleDateFormat1.format(calendar1.getTime()));

        ImageButton imgbtnBack = (ImageButton) findViewById(R.id.imgbtnBack);
        imgbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addFaculty.getText().toString() != "chưa nộp" || addFaculty.getText().toString() != "chua nop" ||
                        addFaculty.getText().toString() != "Chưa nộp" || addFaculty.getText().toString() != "Chua nop"){
                    addDay.setEnabled(true);
                }
            }
        });

        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addFaculty.getText().toString().equals("chưa nộp") || addFaculty.getText().toString().equals("chua nop") ||
                        addFaculty.getText().toString().equals("Chưa nộp") || addFaculty.getText().toString().equals("Chua nop")){
                    addDay.setEnabled(false);
                }else{
                    ChonNgay();
                }
                //ChonNgay();
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
}