package com.example.a20190920a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Log
    public static final String TAG = "Test_Btn_Clicked";

    // View
    TextView title_text, create_text;
    EditText id_text, pass_text;
    Button sign_button;
    ImageButton finger_button, google_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView() {

        title_text = (TextView)findViewById(R.id.title_text);
        create_text = (TextView)findViewById(R.id.create_text);
        id_text = (EditText)findViewById(R.id.id_text);
        pass_text = (EditText)findViewById(R.id.pass_text);
        sign_button = (Button)findViewById(R.id.sign_button);
        finger_button = (ImageButton)findViewById(R.id.finger_button);
        google_button = (ImageButton)findViewById(R.id.google_button);
    }

    public void SetListener() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_button:
                        //title_text.setTextColor(Color.RED);
                        break;
                    case R.id.create_text:
                        //title_text.setTextColor(Color.BLUE);
                        break;
                    case R.id.finger_button:
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.google_button:
                        //title_text.setTextColor(Color.GREEN);
                        break;
                }
            }
        };

        sign_button.setOnClickListener(Listener);
        create_text.setOnClickListener(Listener);
        finger_button.setOnClickListener(Listener);
        google_button.setOnClickListener(Listener);
    }

}
