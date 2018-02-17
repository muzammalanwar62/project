package usmanali.nephrohub;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    Button btnSignin,btnRegister;
    MaterialEditText email,password,name,phone;
    RelativeLayout rootlayout;
    TextView txt_forgot_password;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Arkhip_font.ttf").setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        txt_forgot_password=(TextView) findViewById(R.id.txt_forgot_password);
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {show_forgot_password_dialog();
            }
        });
        btnSignin=(Button)findViewById(R.id.btnSignin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        rootlayout=(RelativeLayout) findViewById(R.id.rootlayout);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_register_dialog();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_login_dialog();
            }
        });
    }
    private void show_forgot_password_dialog(){
        AlertDialog.Builder forgot_password_dialog=new AlertDialog.Builder(MainActivity.this);
        forgot_password_dialog .setTitle("Forgot Password");
        forgot_password_dialog .setMessage("Please Enter Your Email");
        LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
        View v=inflater.inflate(R.layout.forgot_password_layout,null);
        forgot_password_dialog.setView(v);
        final MaterialEditText emailtxt=(MaterialEditText) v.findViewById(R.id.emailtxt);
        forgot_password_dialog .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                final android.app.AlertDialog waiting_dialog=new SpotsDialog(MainActivity.this);
                waiting_dialog.show();
                if(!TextUtils.isEmpty(emailtxt.getText().toString())) {

                }else{
                    //waiting_dialog.dismiss();
                    Snackbar.make(rootlayout,"Please Enter Email",Snackbar.LENGTH_LONG).show();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void show_register_dialog(){
        final AlertDialog.Builder register_dialog=new AlertDialog.Builder(MainActivity.this);
        register_dialog.setTitle("Register");
        register_dialog.setMessage("Use Email to Register");
        final View v=LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_register,null);
        email=(MaterialEditText) v.findViewById(R.id.emailtxt);
        password=(MaterialEditText) v.findViewById(R.id.passwordtxt);
        name=(MaterialEditText) v.findViewById(R.id.nametxt);
        phone=(MaterialEditText) v.findViewById(R.id.phone);
        register_dialog.setView(v);
        register_dialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                }else if (password.getText().toString().length() < 6){
                    Toast.makeText(MainActivity.this,"Password too short",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Phone",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_LONG).show();
                }else{

                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();

    }


    private void show_login_dialog(){
        AlertDialog.Builder login_dialog=new AlertDialog.Builder(MainActivity.this);
        login_dialog.setTitle("Sign In");
        login_dialog.setMessage("Use Email to Sign In");
        View v=LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_login,null);
        email=(MaterialEditText) v.findViewById(R.id.emailtxt);
        password=(MaterialEditText) v.findViewById(R.id.passwordtxt);
        login_dialog.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                }else if (password.getText().toString().length() < 6){
                    Toast.makeText(MainActivity.this,"Password too short",Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(MainActivity.this,Home.class));
                    finish();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setView(v).show();
    }
}
