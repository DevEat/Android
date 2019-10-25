package com.example.msm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    // View
    ImageButton sync_button, pop_button, home_button, search_button, msg_button, user_button;
    TextView menu_title_text;
    Button contact_button, recommand_button, schedule_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.SetListener();
        this.InitialzieView();
    }

    private void SetListener() {
        sync_button = (ImageButton)findViewById(R.id.sync_button);
        pop_button = (ImageButton)findViewById(R.id.pop_button);
        home_button = (ImageButton)findViewById(R.id.home_button);
        search_button = (ImageButton)findViewById(R.id.search_button);
        msg_button = (ImageButton)findViewById(R.id.msg_button);
        user_button = (ImageButton)findViewById(R.id.user_button);
        menu_title_text = (TextView)findViewById(R.id.menu_title_text);
        contact_button = (Button)findViewById(R.id.contact_button);
        recommand_button = (Button)findViewById(R.id.recommand_button);
        schedule_button = (Button)findViewById(R.id.schedule_button);
    }

    private void InitialzieView() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sync_button:
                        break;
                    case R.id.pop_button:
                        break;
                    case R.id.home_button:
                        break;
                    case R.id.search_button:
                        break;
                    case R.id.msg_button:
                        break;
                    case R.id.user_button:
                        break;
                    case R.id.contact_button:
                        break;
                    case R.id.recommand_button:
                        break;
                    case R.id.schedule_button:
                        break;

                }
            }
        };

        sync_button.setOnClickListener(Listener);
        pop_button.setOnClickListener(Listener);
        home_button.setOnClickListener(Listener);
        search_button.setOnClickListener(Listener);
        msg_button.setOnClickListener(Listener);
        user_button.setOnClickListener(Listener);
        contact_button.setOnClickListener(Listener);
        recommand_button.setOnClickListener(Listener);
        schedule_button.setOnClickListener(Listener);
    }


}
