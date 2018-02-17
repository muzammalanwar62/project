package usmanali.nephrohub;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Scan_Report extends AppCompatActivity {

    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    Bitmap bitmap;
    ImageView image;
    Button button;
    CameraSource cameraSource;
    TextRecognizer detector;
    private int PICK_IMAGE_REQUEST = 100;
    private Uri filepath;
    private static final int REQUEST_CAMERA_PERMISSION=10;

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_CAMERA_PERMISSION:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    try {
                        cameraSource.start(image.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_report);
        scanResults = (TextView) findViewById(R.id.results);
        image = (ImageView) findViewById(R.id.imageView);
        button=(Button) findViewById(R.id.button);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Scan Reports");
        detector = new TextRecognizer.Builder(getApplicationContext()).build();
       /* cameraSource = new CameraSource.Builder(getApplicationContext(), detector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(2.0f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1024, 1280)
                .build();
        image.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                   ActivityCompat.requestPermissions(Scan_Report.this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION);
                    return;
                }
                try {
                    cameraSource.start(image.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
                detector.setProcessor(new Detector.Processor<TextBlock>() {
                    @Override
                    public void release() {

                    }

                    @Override
                    public void receiveDetections(final Detector.Detections<TextBlock> detections) {
                        scanResults.post(new Runnable() {
                            @Override
                            public void run() {
                                process(detections);
                            }
                        });

                    }
                });*/
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               process();
           }
       });

            }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            try {

              //bitmap = decodeBitmapUri(getApplicationContext(),data.getData());
                bitmap=(Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
                        .show();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else*/  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE&&resultCode==RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
           String resultUri = result.getUri().getPath();
             bitmap = BitmapFactory.decodeFile(resultUri);
            image.setImageBitmap(bitmap);
        }
    }
  public void process() {
      try {
          if (detector.isOperational()&&bitmap!=null) {
              Frame frame = new Frame.Builder().setBitmap(bitmap).build();
              SparseArray<TextBlock> textBlocks = detector.detect(frame);
              String blocks = "";
              String lines = "";
              String words = "";
              for (int index = 0; index < textBlocks.size(); index++) {
                  //extract scanned text blocks here
                  TextBlock tBlock = textBlocks.valueAt(index);
                  blocks = blocks + tBlock.getValue() + "\n" + "\n";
                  for (Text line : tBlock.getComponents()) {
                      //extract scanned text lines here
                      lines = lines + line.getValue() + "\n";
                      for (Text element : line.getComponents()) {
                          //extract scanned text words here
                          words = element.getValue();
                          if (textBlocks.size() == 0) {
                              scanResults.setText("Scan Failed: Found nothing to scan");
                          } else if (try_parse_float(words) || try_parse_double(words) || try_parse_int(words)) {
                              scanResults.setText(scanResults.getText() + "values: " + "\n");
                              scanResults.setText(scanResults.getText() + words + "\n");
                              scanResults.setText(scanResults.getText() + "---------" + "\n");
                          } else {
                              scanResults.setText(scanResults.getText() + "Words: " + "\n");
                              scanResults.setText(scanResults.getText() + words + "\n");
                              scanResults.setText(scanResults.getText() + "---------" + "\n");
                          }
                      }
                  }
              }

          } else {
              scanResults.setText("Could not set up the detector!");
          }
      } catch (Exception e) {
         Log.e("ocr_exception",e.getMessage());
      }
  }
    public void chooseimg(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }
    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    public boolean try_parse_int(String input){
        try{
            int i=Integer.parseInt(input);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean try_parse_double(String input){
        try{
            Double d=Double.parseDouble(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public boolean try_parse_float(String input){
        try{
            float d=Float.parseFloat(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scan_report_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if(item.getTitle().equals("Choose Image")){
            //chooseimg();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        return super.onOptionsItemSelected(item);

    }
}
