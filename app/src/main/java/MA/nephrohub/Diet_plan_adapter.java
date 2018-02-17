package usmanali.nephrohub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SAJIDCOMPUTERS on 2/15/2018.
 */

public class Diet_plan_adapter extends RecyclerView.Adapter<diet_plan_viewholder> {
ArrayList<Nutrients> nutrientsArrayList;
Context con;
    public Diet_plan_adapter(ArrayList<Nutrients> nutrientsArrayList,Context con) {
        this.nutrientsArrayList = nutrientsArrayList;
        this.con=con;
    }

    @Override
    public diet_plan_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_plan_layout,parent,false);
        return new diet_plan_viewholder(v);
    }

    @Override
    public void onBindViewHolder(diet_plan_viewholder holder, int position) {
        Nutrients n=nutrientsArrayList.get(position);
        if(position%2==0){

            holder.main_layout.setBackgroundColor(con.getResources().getColor(R.color.lightgray));
        }
        holder.Nutrient_name.setText(n.getTitle());
        holder.Nutrient_per_day.setText(n.getPer_day_value());
        holder.Nutrient_per_meal.setText(n.getPer_meal_value());
        }

    @Override
    public int getItemCount() {
        return nutrientsArrayList.size();
    }
}
class diet_plan_viewholder extends RecyclerView.ViewHolder{
    TextView Nutrient_name,Nutrient_per_meal,Nutrient_per_day;
    LinearLayout main_layout;
    public diet_plan_viewholder(View itemView) {
        super(itemView);
        Nutrient_name=(TextView) itemView.findViewById(R.id.textViewName);
        Nutrient_per_day=(TextView) itemView.findViewById(R.id.textViewPerDay);
        Nutrient_per_meal=(TextView) itemView.findViewById(R.id.textViewPerMeal);
        main_layout=(LinearLayout) itemView.findViewById(R.id.main_layout);
    }
}