package net.zagzag.tracklocation.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseNewUser {
    private FirebaseAuth mAuth;
    private String TAG = "Creating email";
    public FirebaseNewUser(Activity activity, String email, String password, String name, String surname) {
        mAuth = FirebaseAuth.getInstance();
        createEmail(activity, email, password);
        createUser(email,name,surname);
    }

    public void createEmail(final Activity activity, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Toast.makeText(activity, "Email is created successfully", Toast.LENGTH_LONG);
                        }else{
                            Toast.makeText(activity, "Email not created", Toast.LENGTH_LONG);
                        }
                    }
                });
    }

    public void createUser(String email,String name,String surname){
        DatabaseReference usersKey = FirebaseDatabase.getInstance().getReference("userskey");
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference online = FirebaseDatabase.getInstance().getReference("online");
        DatabaseReference position = FirebaseDatabase.getInstance().getReference("position");

        String key = usersKey.push().getKey();
        String uid = mAuth.getCurrentUser().getUid();
        //add users key
        UsersKey k = new UsersKey(key);
        usersKey.child(uid).setValue(k);
        //add users
        UserData userData = new UserData(name,surname,email);
        users.child(key).setValue(userData);
        //add status
        UserStatus status = new UserStatus(false);
        online.child(key).setValue(status);
        //add position
        Position p = new Position();
        position.child(key).setValue(p);
    }
}
