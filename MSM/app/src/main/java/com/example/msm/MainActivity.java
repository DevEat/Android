package com.example.msm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // TAG
    private static String TAG = "Main";

    // View
    TextView title_text, sub_title_text, create_account_text;
    EditText user_edit, pass_edit;
    Button sign_in_button;
    ImageButton finger_button, google_button;

    // FirebaseAdmin Class
    FirebaseAdmin Fb = new FirebaseAdmin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.SetListener();
        this.InitialzieView();
    }

    private void SetListener() {
        title_text = (TextView)findViewById(R.id.title_text);
        sub_title_text = (TextView)findViewById(R.id.sub_title_text);
        create_account_text = (TextView)findViewById(R.id.create_account_text);
        user_edit = (EditText)findViewById(R.id.user_edit);
        pass_edit = (EditText)findViewById(R.id.pass_edit);
        sign_in_button = (Button)findViewById(R.id.sign_in_button);
        finger_button = (ImageButton) findViewById(R.id.finger_button);
        google_button = (ImageButton)findViewById(R.id.google_button);
    }

    private void InitialzieView() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.create_account_text:
                        Log.d(TAG, "create_account_text");
                        Fb.createAccount(user_edit.getText().toString(), pass_edit.getText().toString());
                        break;
                    case R.id.sign_in_button:
                        Log.d(TAG, "sign_in_button");
                        Fb.signIn(user_edit.getText().toString(), pass_edit.getText().toString());
                        break;
                    case R.id.finger_button:
                        Log.d(TAG, "finger_button");
                        break;
                    case R.id.google_button:
                        Log.d(TAG, "google_button");
                        break;
                }
            }
        };

        create_account_text.setOnClickListener(Listener);
        sign_in_button.setOnClickListener(Listener);
        finger_button.setOnClickListener(Listener);
        google_button.setOnClickListener(Listener);
    }

    public void CallAcitivty() {
        // menu_activity 로 전환
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
