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
import com.google.firebase.database.ValueEventListener;

import net.zagzag.tracklocation.View.SignUp.SignUp;

public class FirebaseConectionn {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "authentication....";


    private DatabaseReference mDataBaseReference;
    private ValueEventListener mValueEventListener;

    public void init(Activity a) {
        mAuth = FirebaseAuth.getInstance();  //initiate authentication
        mAuthListener = new FirebaseAuth.AuthStateListener() {   //initiate listener for authentication
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
        };
        //createEmail(a,"h@google.com","123456");
        signIn(a,"h@google.com","123456");

        mDataBaseReference = FirebaseDatabase.getInstance().getReference("1/2/hello");
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
        String key = mDataBaseReference.getKey();



        mDataBaseReference.child("node").setValue("hello");
    }
class data{
    public String hello = "data in object";
}
    public void createEmail(Activity activity, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //not success scenario
                        }
                    }
                });
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
         //   mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
