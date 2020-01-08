//package com.example.messagingscreen;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    Button btnSend;
//    EditText tvMessage;
//    EditText tvNumber;
//    IntentFilter intentFilter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.string_request);
//
//
//        btnSend = (Button) findViewById(R.id.btnSend);
//        tvMessage = (EditText) findViewById(R.id.tvMessage);
//        tvNumber = (EditText) findViewById(R.id.userNumber);
//
//        btnSend.setOnClickListener((View.OnClickListener) this);
//    }
//
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSend:
//                startActivity(new Intent(MainActivity.this,
//                        StringRequestActivity.class));
//        }
//    }
//}
