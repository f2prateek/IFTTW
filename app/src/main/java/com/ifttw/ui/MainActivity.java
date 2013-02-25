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

import java.util.ArrayList;
import java.util.List;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends RoboSherlockFragmentActivity {

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_fences);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle bundle = data.getExtras();

        ArrayList<String> creds = (ArrayList<String>) bundle.getStringArrayList("User");

        user = new User(creds.get(0), creds.get(1));

        buildFenceList();

    }

    /**
     * Fetches the user from the Account Manager to allow a session to be continued after exit.
     * Place holder for now.
     */
    private void checkAuth() {

        //TODO: read from device
//        ParseUser.logInInBackground("IFTTW", "IFTTW", new LogInCallback() {
//            public void done(ParseUser user, ParseException e) {
//                if (user != null) {
//                    // Hooray! The user is logged in.
//                } else {
//                    // Signup failed. Look at the ParseException to see what happened.
//                }
//            }
//        });

        //if we have an authenticated user, skip auth
        if( user == null ) {

//            displayAuthDialog();
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);

        } else {

           buildFenceList();

        }
    }


    private void buildFenceList() {

        ListView lView = (ListView) findViewById(R.id.listView);

        ParseQuery query = new ParseQuery( Fence.getObjectName() );


        try {

            List<ParseObject> fences = query.whereEqualTo("user", user).find();

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

}
