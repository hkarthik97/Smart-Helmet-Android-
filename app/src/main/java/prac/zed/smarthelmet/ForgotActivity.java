package prac.zed.smarthelmet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotActivity extends AppCompatActivity {
    private Button ResetPassword;
    private EditText ForgetEmail;
    ViewDialog viewDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        ResetPassword = (Button) findViewById(R.id.ResetButton) ;
        ForgetEmail = (EditText) findViewById(R.id.ForgetEmail);






    }
}
