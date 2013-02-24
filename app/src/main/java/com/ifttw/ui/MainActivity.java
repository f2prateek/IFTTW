package com.ifttw.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.ifttw.model.User;
import com.parse.*;

import java.util.List;

public class MainActivity extends RoboSherlockFragmentActivity {

    private ParseUser user;

    public MainActivity() {

        this.user = getUserFromAccountManager();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, "D6ygFDR2M418xIgbT4fdWJKUpTubDKHG1ZxvaHzS", "8lj260b0W5DsCqrm0kWl4oCv4NLNHLPpT0kZKCWm");

//        setContentView(R.layout.fences_fragment);

        //if we have an authenticated user, skip auth
        if( user == null ) {

            displayAuthDialog();

        } else {

//            populateFencesView();

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
                            populateViews();
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

    private void populateViews() {

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

                    editFence(fence);

                }
            });

            lView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);

        } catch (ParseException e) {
            //TODO Some sort of error handling here
        }




    }

    private void editFence( ParseObject fence ){

        //TODO edit fence logic here

    }

    private void setUser( ParseUser user ) {

        this.user = user;

    }
}
