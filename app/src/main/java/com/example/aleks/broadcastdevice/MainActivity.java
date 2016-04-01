package com.example.aleks.broadcastdevice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText channelEditText;
    private String channelName;
    private static final String TAG = "Tracker - MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelEditText = (EditText) findViewById(R.id.channelEditText);
        channelEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            channelName = channelEditText.getText().toString();
                            String message = "Chosen channel: " + channelName;
                            Toast.makeText(MainActivity.this, message,
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, message);
                            return true;
                        default:
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    public void shareLocation(View view) {
            Log.d(TAG, "Share Location With Google Maps Chosen on channel: "
                    + channelName);
            callActivity(ShareLocationActivity.class);
    }

    private void callActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("channel", channelName);
        startActivity(intent);
    }
}
