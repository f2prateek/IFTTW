package com.ifttw.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.R;
import com.ifttw.model.User;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

// This activity shows a list of fences
// It also adds an item to the menu bar to add a new fence and action
public class MainActivity extends RoboSherlockFragmentActivity {

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fence:
                startActivity(new Intent(this, CreateFenceActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * Fetches the user from the Account Manager to allow a session to be continued after exit.
     * Place holder for now.
     */
    private User getUser() {

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
        if (user == null) {

            displayAuthDialog();

        } else {

//            populateFencesView();

        }

        return null;

    }

    private void setUser(ParseUser user) {

        this.user = user;

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
}
