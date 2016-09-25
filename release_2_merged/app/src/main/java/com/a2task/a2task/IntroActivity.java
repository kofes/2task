package com.a2task.a2task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntroActivity extends Activity implements OnClickListener {

    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(this);
    }

    //@Override
    public void onClick(View v) {

        if (R.id.sign_in == v.getId()) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

    }
}
