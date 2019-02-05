package prac.zed.smarthelmet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister  ;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private TextView textViewSignin;
    ViewDialog viewDialog;
    //private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=firebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));


        }
        viewDialog = new ViewDialog(this);

        //progressDialog =new ProgressDialog(this);

        buttonRegister =(Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        //setting on click listener
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


    }
    private void buttonRegister(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //Shows toast when the email field is left empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;

        }
        //if the details are autheticated
//        progressDialog.setMessage("Registering Please Wait...");
//        progressDialog.show();
        viewDialog.showDialog();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        //progressDialog.dismiss();
                        viewDialog.hideDialog();
//                        final Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //...here i'm waiting 5 seconds before hiding the custom dialog
//                                //...you can do whenever you want or whenever your work is done
//                                viewDialog.hideDialog();
//                            }
//                        }, 5000);


                    }
                });

    }
    @Override
    public void onClick(View view){
        if(view == buttonRegister ) {
            buttonRegister();
        }
        if(view==textViewSignin){
            startActivity(new Intent(this,LoginActivity.class));
        }

    }

}
