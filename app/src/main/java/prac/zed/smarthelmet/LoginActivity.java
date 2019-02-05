package prac.zed.smarthelmet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity ;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button   buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
//    private ProgressDialog  progressDialog;
    private FirebaseAuth firebaseAuth;
    ViewDialog viewDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }

//        progressDialog = new ProgressDialog(this);
        viewDialog = new ViewDialog(this);
        editTextEmail     = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword  = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn      = (Button) findViewById(R.id.buttonSignIn);
        textViewSignup    = (TextView) findViewById(R.id.textViewSignUp);

        buttonSignIn   . setOnClickListener(this);
        textViewSignup . setOnClickListener(this);

    }
    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

//        progressDialog.setMessage("Lets Go...");
//        progressDialog.show();
        viewDialog.showDialog();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
                        viewDialog.hideDialog();
                        if(task.isSuccessful()){
                            //STARTS AFTER AUTHENTICATION
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                        }


                    }
                });

    }
    @Override
    public void onClick(View view){
        if(view == buttonSignIn){
            userLogin();
        }
        if(view == textViewSignup )
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
