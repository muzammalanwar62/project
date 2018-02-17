package usmanali.nephrohub;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SAJIDCOMPUTERS on 1/29/2018.
 */

public class dbhelper extends SQLiteOpenHelper {
    public static String Database_Name="Reports.db";
    public static String Table_Name="reports";
    String[] columns={"report_id","report_pic","report_title","report_date","doctor_name"};
    Context context;
    public dbhelper(Context context) {
        super(context, Database_Name, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Table_Name+"( report_id INTEGER PRIMARY KEY AUTOINCREMENT,report_pic BLOB,report_title TEXT,report_date TEXT,doctor_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Name);
    onCreate(sqLiteDatabase);
    }
    public void insert_report(String report_title,String report_date,byte[] report_image,String doctor_name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("report_title",report_title);
        cv.put("report_date",report_date);
        cv.put("report_pic", report_image);
        cv.put("doctor_name",doctor_name);
     long i= db.insert(Table_Name,null,cv);
     if(i==-1){
         Toast.makeText(context,"Report not Added",Toast.LENGTH_LONG).show();
     }else{
         Toast.makeText(context,"Report Added",Toast.LENGTH_LONG).show();
     }
    }
    public Cursor get_reports_cursur(){
        SQLiteDatabase db=getReadableDatabase();
        return  db.rawQuery("SELECT * FROM "+Table_Name,null);
    }

public int get_num_of_rows(){
    SQLiteDatabase db=getReadableDatabase();
    Cursor all_reports_cursor = db.query(Table_Name,columns,null,null,null,null,null);
    return all_reports_cursor.getCount();
}
}
