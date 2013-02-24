package com.ifttw.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.LocationService;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.ifttw.model.User;
import com.parse.*;

import java.util.List;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends RoboSherlockFragmentActivity {

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        checkAuth();

        //add button listener to allow new Fence Creation
        Button newFenceButton = (Button) findViewById(R.id.addFenceButton);
        newFenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewFence();
            }
        });

    }

    /**
     * Fetches the user from the Account Manager to allow a session to be continued after exit.
     * Place holder for now.
     */
    private void checkAuth() {

        //TODO: read from device
        ParseUser.logInInBackground("IFTTW", "IFTTW", new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });

        //this.user = getUserFromAccountManager();

        //if we have an authenticated user, skip auth
        if( user == null ) {

            displayAuthDialog();

        } else {

           buildFenceList();

        }

    }

    /**
     * Fetches the user from the Account Manager to allow a session to be continued after exit.
     * Place holder for now.
     */
    private User getUserFromAccountManager() {

        return null;

    }

    private void displayAuthDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.login, null))

                //Bring Up Login Screen If Clicked
                .setNeutralButton(R.string.signupLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        displaySignupScreen();
                    }
                })
                .setNeutralButton(R.string.loginLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText username = (EditText) findViewById(R.id.username);
                        EditText password = (EditText) findViewById(R.id.password);

                        try {

                            setUser(User.logIn(username.getText().toString(), password.getText().toString()));
                            buildFenceList();
                            dialog.dismiss();

                        } catch (ParseException e) {

                            user = null;
                            displayAlert("Login Failed!", "The credentials you entered where in correct. Please try again.");

                        }

                    }
                });

        builder.create();

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

    private void displaySignupScreen() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();


        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.login, null))

                //Bring Up Login Screen If Clicked
                .setNegativeButton(R.string.cancelLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.signupLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        EditText name = (EditText) findViewById(R.id.name);
                        EditText username = (EditText) findViewById(R.id.username);
                        EditText password = (EditText) findViewById(R.id.password);

                        user = new User(name.getText().toString(), username.getText().toString(), password.getText().toString());

                        try {

                            user.signUp();
                            displayAlert("Success!", "Sign up completed successfully!");
                            dialog.dismiss();

                        } catch (ParseException e) {

                            user = null;
                            displayAlert("Failure!", "Sign up failed, please try again!");


                        }

                    }
                });

        builder.create();
        builder.show();

    }

    private void buildFenceList() {

        ListView lView = (ListView) findViewById(R.id.listView);

        ParseQuery query = new ParseQuery( Fence.getObjectName() );

        try {

            List<ParseObject> fences = query.find();

            ArrayAdapter<ParseObject> adapter = new ArrayAdapter<ParseObject>(this, R.layout.row, R.id.rowTextView, fences);

            lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View arg1, int pos, long id) {

                    ArrayAdapter<ParseObject> ad = (ArrayAdapter<ParseObject>) adapter.getAdapter();
                    ParseObject fence = ad.getItem( pos );

                    editExistingFence(fence);

                }
            });

            lView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);

        } catch (ParseException e) {
            //TODO Some sort of error handling here
        }




    }

    private void createNewFence() {

        //TODO create fence logic here

    }

    private void editExistingFence(ParseObject fence){

        //TODO edit fence logic here

    }
    private void setUser( ParseUser user ) {

        this.user = user;

    }
}
