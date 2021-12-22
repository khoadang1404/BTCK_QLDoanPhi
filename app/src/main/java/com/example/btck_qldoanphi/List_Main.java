package com.example.btck_qldoanphi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class List_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        Button btn;
        RadioGroup rb_group;
        RadioButton rb_hello, rb_hi;

        btn = (Button) findViewById(R.id.btn);
        rb_group = (RadioGroup) findViewById(R.id.rb_group);
        rb_hello = (RadioButton) findViewById(R.id.rb_hello);
        rb_hi = (RadioButton) findViewById(R.id.rb_hi);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_hello.setChecked(true);
            }
        });
    }
}