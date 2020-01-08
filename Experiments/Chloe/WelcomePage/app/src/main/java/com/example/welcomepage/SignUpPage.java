package com.example.welcomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class SignUpPage extends AppCompatActivity {

    private String FirstName, LastName, UserEmail,  Password, PasswordCheck;
    private int month, day, year;
    EditText FN, LN, PW, PWC, UE, DOB;
    Button btnsignup;
    DatePickerDialog picker;
    private TextView birthDate;
    private DatePickerDialog.OnDateSetListener birthdaySetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        FN = (EditText) findViewById(R.id.FIrstName);
        LN = (EditText) findViewById(R.id.LastName);
        PW = (EditText) findViewById(R.id.Password);
        PWC = (EditText) findViewById(R.id.PasswordCheck);
        UE = (EditText) findViewById(R.id.UserEmail);
        btnsignup = (Button) findViewById(R.id.CreateAcc);
       // birthDate = (TextView) findViewById(R.id.Dob);
        DOB = (EditText) findViewById(R.id.Dob);


        DOB.setInputType(InputType.TYPE_NULL);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(SignUpPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                DOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
       /* birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpPage.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        birthdaySetListener,
                        month, day, year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });

        birthdaySetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                month = month +1;
                String date = month + "/" + day + "/" + year;
                birthDate.setText(date);
            }
        };*/

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstName = FN.getText().toString();
                LastName = LN.getText().toString();
                UserEmail = UE.getText().toString();
                Password = PW.getText().toString();
                PasswordCheck = PWC.getText().toString();
                final String TAG = SignUpPage.class.getSimpleName();
                Log.d(TAG, "tag ");
                Log.d(TAG, "first name: " + FirstName);
                Log.d(TAG, "last name: " + LastName);
                Log.d(TAG, "email: " + UserEmail);
                Log.d(TAG, "password: " +Password);
                Log.d(TAG, "password check: " +PasswordCheck);
                //Log.d(TAG, "birthday: " + birthDate.toString());
            }
        });


    }


}
