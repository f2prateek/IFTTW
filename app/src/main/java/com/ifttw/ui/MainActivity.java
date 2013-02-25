package com.ifttw.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.*;

import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.ifttw.LocationService;
import com.ifttw.R;
import com.ifttw.model.User;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static com.ifttw.util.LogUtils.makeLogTag;

// This activity shows a list of fences
// It also adds an item to the menu bar to add a new fence and action
public class MainActivity extends RoboSherlockFragmentActivity {

    private static final String LOGTAG = makeLogTag(MainActivity.class);
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_fences);

        checkAuth();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fence:
                createNewFence();
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
     * This is called when the Login Activity returns with either a new or logged in user.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle bundle = data.getExtras();

        ArrayList<String> creds = (ArrayList<String>) bundle.getStringArrayList("User");

        user = new User( creds.get(0), creds.get(1) );

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

        List<ParseObject> fences = query.whereEqualTo("user", user).findInBackground(new FindCallback(){
            @Override
            public void done(List<ParseObject> fences, ParseException e) {
                Log.d(LOGTAG, "found fences # " + fences.size());
                ListView lView = (ListView) findViewById(R.id.listView);
                FenceAdapter adapter = new FenceAdapter(MainActivity.this, fences);
                lView.setAdapter(adapter);
            }
        });

    }

}
