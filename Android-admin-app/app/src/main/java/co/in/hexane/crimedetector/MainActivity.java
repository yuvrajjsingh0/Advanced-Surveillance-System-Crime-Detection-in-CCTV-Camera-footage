package co.in.hexane.crimedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        TextInputEditText emailEditText = findViewById(R.id.email_addr);
        TextInputEditText passwdEditText = findViewById(R.id.passwd);
        MaterialButton loginButt = findViewById(R.id.login);

        loginButt.setOnClickListener(view -> {
            if(emailEditText.getText() != null && passwdEditText.getText() != null && emailEditText.getText().toString().length() > 5 && passwdEditText.getText().toString().length() >= 6){
                String email = emailEditText.getText().toString();
                String password = passwdEditText.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MAINACTIVITY", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("MAINACTIVITY", "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                emailEditText.setError("Enter valid Email");
                passwdEditText.setError("Enter valid Password");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
    }
}