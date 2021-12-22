package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    public static Database database;
    MaterialButton btnAddSV;
    ListView listviewSV;
    ArrayList<QLTPSinhVien> arraySinhVien;
    SinhVienAdapter adapter;
    //Dialog dialog = new Dialog(this);

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

    public void DialogXoaCongViec(String tenSV, int Id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xoa);
        TextView tvDelete;
        MaterialButton btnDelete, btnCancel;

        tvDelete = (TextView) dialog.findViewById(R.id.tvDelete);
        btnDelete = (MaterialButton) dialog.findViewById(R.id.btnDelete);
        btnCancel = (MaterialButton) dialog.findViewById(R.id.btnCancel);

        tvDelete.setText("Xóa sinh viên: " + tenSV + "?");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("DELETE FROM SinhVien WHERE Id = '"+ Id +"'");
                Toast.makeText(MainActivity.this, "Xóa thành công sinh viên " + tenSV, Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                //get data
                Cursor cursor = database.GetData("SELECT * FROM SinhVien");
                arraySinhVien.clear();
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
            }
        });
        dialog.show();

    }

    public void DialogSuaCongViec(String maSV, String tenSV, String lopSV, String tinhTrang, String ngayNop, int Id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText fix_addID, fix_addName, fix_addClass, fix_addDay;
        RadioButton fix_rbAddDaNop, fix_rbAddChuaNop;
        RadioGroup fix_rbAddFaculty;
        MaterialButton fix_btnAdd, fix_btnCancel;

        fix_addID = (EditText) dialog.findViewById(R.id.fix_addID);
        fix_addName = (EditText) dialog.findViewById(R.id.fix_addName);
        fix_addClass = (EditText) dialog.findViewById(R.id.fix_addClass);
        fix_addDay = (EditText) dialog.findViewById(R.id.fix_addDay);
        fix_rbAddDaNop = (RadioButton) dialog.findViewById(R.id.fix_rbAddDaNop);
        fix_rbAddChuaNop = (RadioButton) dialog.findViewById(R.id.fix_rbAddChuaNop);
        fix_rbAddFaculty = (RadioGroup) dialog.findViewById(R.id.fix_rbAddFaculty);
        fix_btnAdd = (MaterialButton) dialog.findViewById(R.id.fix_btnAdd);
        fix_btnCancel = (MaterialButton) dialog.findViewById(R.id.fix_btnCancel);

        fix_addID.setText(maSV);
        fix_addName.setText(tenSV);
        fix_addClass.setText(lopSV);
        fix_addDay.setText(ngayNop);

        if(fix_addDay.getText().toString().equals("")) {
            fix_rbAddChuaNop.setChecked(true);
            fix_rbAddDaNop.setChecked(false);
            fix_addDay.setEnabled(false);
        }else{
            fix_rbAddDaNop.setChecked(true);
            fix_rbAddChuaNop.setChecked(false);
            fix_addDay.setEnabled(true);
            //fix_addDay.setText(ngayNop);
        }

        fix_btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        fix_addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        fix_addDay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam, thang, ngay);
                datePickerDialog.show();
            }
        });

        fix_rbAddFaculty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.fix_rbAddDaNop:
                        fix_addDay.setEnabled(true);
                        fix_addDay.setText(ngayNop);
                        break;
                    case R.id.fix_rbAddChuaNop:
                        fix_addDay.setEnabled(false);
                        fix_addDay.setText("");
                        break;
                }
            }
        });

        fix_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSVmoi = fix_addID.getText().toString().trim();
                String tenSVmoi = fix_addName.getText().toString().trim();
                String lopSVmoi = fix_addClass.getText().toString().trim();
                String tinhTrangmoi;
                if(fix_rbAddDaNop.isChecked()){
                    tinhTrangmoi = fix_rbAddDaNop.getText().toString().trim();
                }else{
                    tinhTrangmoi = fix_rbAddChuaNop.getText().toString().trim();
                }
                String daySVmoi = fix_addDay.getText().toString().trim();

                database.QueryData("UPDATE SinhVien SET maSV = '"+ maSVmoi +"', tenSV = '"+ tenSVmoi +"'," +
                        " lopSV = '"+ lopSVmoi +"', tinhTrang = '"+ tinhTrangmoi +"', ngayNop = '"+ daySVmoi +"' WHERE Id = '"+ Id +"'");
                Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                //get data
                Cursor cursor = database.GetData("SELECT * FROM SinhVien");
                arraySinhVien.clear();
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
            }
        });

        dialog.show();
    }
}