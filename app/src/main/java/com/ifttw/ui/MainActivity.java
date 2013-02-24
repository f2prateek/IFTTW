package com.ifttw.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.ifttw.model.User;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends RoboSherlockFragmentActivity {

    private ParseUser user;

    public MainActivity() {

        this.user = getUserFromAccountManager();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                        EditText user = (EditText) findViewById(R.id.username);
                        EditText password = (EditText) findViewById(R.id.password);

                        try {

                            setUser(User.logIn(user.getText().toString(), password.getText().toString()));

                        } catch (ParseException e) {

                            user = null;
                            displayLoginFailedAlert();

                        }

                    }
                });

        builder.create();

    }

    private void displayLoginFailedAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Login Failed!");
        alertDialog.setMessage("The credentials you entered where in correct. Please try again.");
        // Set the Icon for the Dialog
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
                .setNeutralButton(R.string.signupLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        displaySignupScreen();
                    }
                })
                .setNeutralButton(R.string.loginLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.create();

    }

    private void setUser( ParseUser user ) {

        this.user = user;

    }

}
