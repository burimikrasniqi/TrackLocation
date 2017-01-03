package net.zagzag.tracklocation.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import net.zagzag.tracklocation.View.SignUp.SignUp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FirebaseConectionn {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "authentication....";

    private DatabaseReference mDataBaseReference;
    private ValueEventListener mValueEventListener;

    //dummy data from fire
    private Map<String, UserData> mUsers;
    private Map<String, Position> mPosition;
    private Map<String, UserStatus> mUserStatus;

    //adding value event listeners
    private ValueEventListener mUserListener;
    private ValueEventListener mPositionListener;
    private ValueEventListener mUserStatusListener;

    //populate data in arrays
    public void populateUsers() {
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("users");
        Query query = dbr.orderByChild("name");
        mUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    String key = child.getKey();
                    UserData ud = child.getValue(UserData.class);
                    mUsers.put(key, ud);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException();
            }
        };
        query.addValueEventListener(mUserListener);
    }


    public void init(Activity a) {
        mAuth = FirebaseAuth.getInstance();  //initiate authentication
        /*mAuthListener = new FirebaseAuth.AuthStateListener() {   //initiate listener for authentication
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };*/

        signIn(a, "h@google.com", "123456");

        mDataBaseReference = FirebaseDatabase.getInstance().getReference();
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("onDataChange", "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("onDataChange", "");
            }
        };
        mDataBaseReference.addValueEventListener(mValueEventListener);


    }


    public void signIn(Activity activity, String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                        }
                    }
                });
    }


    public void onStart() {
        if (mAuthListener != null) {
            // mAuth.addAuthStateListener(mAuthListener);
        }
    }

    public void onStop() {
        if (mAuthListener != null) {
            // mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
