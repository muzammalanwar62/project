package usmanali.nephrohub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_report_picture extends AppCompatActivity {
Button Upload_Picture_btn,add_report_btn;
ImageView report_pic;
TextView report_title,ref_by;
    Bitmap bitmap;
    dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new dbhelper(add_report_picture.this);
        Upload_Picture_btn=(Button) findViewById(R.id.upload_pic_btn);
        report_title=(TextView)  findViewById(R.id.report_title);
        ref_by=(TextView) findViewById(R.id.doctor_name);
        add_report_btn=(Button) findViewById(R.id.add_report_btn);
        report_pic=(ImageView) findViewById(R.id.report_pic);
        Upload_Picture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               choose_img();
            }
        });
        add_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_report();
            }
        });
    }
private void choose_img(){
    CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .start(this);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE&&resultCode==RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            String resultUri = result.getUri().getPath();
            bitmap = BitmapFactory.decodeFile(resultUri);
            report_pic.setImageBitmap(bitmap);
        }
    }
    private void add_report(){
    if(bitmap!=null&&!report_title.getText().toString().isEmpty()&&!ref_by.getText().toString().isEmpty()) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        Calendar c=Calendar.getInstance();
        SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");
        String date=df.format(c.getTime());
        db.insert_report(report_title.getText().toString(),date,imageBytes,ref_by.getText().toString());
        Toast.makeText(add_report_picture.this,String.valueOf(db.get_num_of_rows()),Toast.LENGTH_LONG).show();
    }else{
        Toast.makeText(add_report_picture.this,"Please add all required information",Toast.LENGTH_LONG).show();
        Toast.makeText(add_report_picture.this,String.valueOf(db.get_num_of_rows()),Toast.LENGTH_LONG).show();
    }
    }
}
