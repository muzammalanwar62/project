package usmanali.nephrohub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Diet_Plan extends AppCompatActivity {
RecyclerView nutrients_list;
ArrayList<Nutrients> nutrientsArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nutrients_list=(RecyclerView) findViewById(R.id.diet_plan_list);
        nutrients_list.setLayoutManager(new LinearLayoutManager(Diet_Plan.this));
        Nutrients Calories=new Nutrients("Calories","< 2,300","< 690");
        Nutrients Total_Fat=new Nutrients("Total Fats","< 89.4g","< 26.8g");
        Nutrients Saturated_fat=new Nutrients("Saturated Fat","< 25.6g","< 7.7g");
        Nutrients Trans_fat=new Nutrients("Trans Fat","< 0.50g","< 0.15g");

        nutrientsArrayList.add(Calories);
        nutrientsArrayList.add(Total_Fat);
        nutrientsArrayList.add(Saturated_fat);
        nutrientsArrayList.add(Trans_fat);
        nutrients_list.setAdapter(new Diet_plan_adapter(nutrientsArrayList,Diet_Plan.this));
    }

}
