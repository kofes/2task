package com.a2task.a2task;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends Activity  implements View.OnClickListener {

    Button sign_in;
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        sign_in = (Button) findViewById(R.id.sign_in);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        sign_in.setOnClickListener(this);
    }

    //@Override
    public void onClick(View v) {

            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
    }

}
