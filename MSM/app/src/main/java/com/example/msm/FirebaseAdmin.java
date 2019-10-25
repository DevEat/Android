package com.example.msm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAdmin extends AppCompatActivity {

    // https://firebase.google.com/docs/auth/android/manage-users?hl=ko
    // Firebase Guide
    private FirebaseAuth mAuth;
    private static String TAG = "FirebaseAdmin";
    private Context mContext;

    FirebaseAdmin(Context context) {
        Log.d(TAG, "FirebaseAdmin");

        mContext = context; // MainActivity에서 전달받은 context
        mAuth = FirebaseAuth.getInstance();
        signOut();
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Log.d(TAG, "User is signed in");

            // MenuActivity 화면 전환
            Intent intent = new Intent(mContext, MenuActivity.class);
            mContext.startActivity(intent);
        } else {
            Log.d(TAG, "No user is signed in");
        }
    }

    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }

}
