package usmanali.nephrohub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class medical_records extends AppCompatActivity {
ListView medical_records_list;
dbhelper dbhelper;
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbhelper=new dbhelper(this);
        medical_records_list=(ListView) findViewById(R.id.medical_records_list);
        medical_records_list.setAdapter(new medical_records_list_adapter(medical_records.this,get_all_reports()));
    }
  public ArrayList<Reports> get_all_reports(){

      cursor=dbhelper.get_reports_cursur();
          ArrayList<Reports> all_reports_list=new ArrayList<>();

          if (cursor.getCount()==0){
              Toast.makeText(this,"No Records added Yet",Toast.LENGTH_LONG).show();
          }
          if (cursor.moveToFirst()) {
              while (cursor.moveToNext()) {
                  Reports records = new Reports(cursor.getString(cursor.getColumnIndex("report_title")), cursor.getString(cursor.getColumnIndex("report_date")), cursor.getBlob(cursor.getColumnIndex("report_pic")), cursor.getString(cursor.getColumnIndex("doctor_name")), cursor.getInt(cursor.getColumnIndex("report_id")));
                  all_reports_list.add(records);
              }
          }
          return all_reports_list;
      }

}
