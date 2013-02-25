package com.ifttw.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.ifttw.R;
import com.ifttw.model.User;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: deredowl
 * Date: 24/02/13
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends Activity {

    private View loginView;
    private View signupView;
    private Button signup;
    private Button login;
    private Button cancel;
    private Button register;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        loginView = findViewById(R.layout.login);

        signupView = getLayoutInflater().inflate(R.layout.sign_up, null, false);


        signup = (Button) findViewById(R.id.signupButton);
        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(signupView);
            }
        });

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                if( username.getText().toString().length() == 0 || password.getText().toString().length() == 0 ) {

                    displayAlert("Login Failed!", "Please enter a username and password. Please try again.");


                } else {

                    try {

                        ParseUser user = User.logIn(username.getText().toString(), password.getText().toString());

                        ArrayList<String> list = new ArrayList<String>();
                        list.add(user.getUsername());
                        list.add(password.getText().toString());

                        completeAuth(list);

                    } catch (ParseException e) {
                        e.printStackTrace();
                        displayAlert("Login Failed!", "The credentials you entered where incorrect. Please try again.");

                    }

                }
            }
        });

        cancel = (Button) signupView.findViewById(R.id.cancelButton);
        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(loginView);
        }});
        
        register = (Button) signupView.findViewById(R.id.registerButton);
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = (EditText) findViewById(R.id.name);
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                User user = new User(name.getText().toString(), username.getText().toString(), password.getText().toString());

                try {

                    user.signUp();
                    displayAlert("Success!", "Sign up completed successfully!");

                    ArrayList<String> list = new ArrayList<String>();
                    list.add(user.getUsername());
                    list.add(password.getText().toString());

                    completeAuth(list);
                    

                } catch (ParseException e) {
                    e.printStackTrace();
                    displayAlert("Failure!", "Sign up failed, please try again!");

                }

            }
        });

    }

    private void displayAlert( String title, String message ) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle( title );
        alertDialog.setMessage( message );
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }

    private void completeAuth( ArrayList<String> userCreds ) {

        Intent resultIntent = new Intent();
        Bundle b = new Bundle();
        b.putStringArrayList("User", userCreds);
        resultIntent.putExtra("User", b);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

}