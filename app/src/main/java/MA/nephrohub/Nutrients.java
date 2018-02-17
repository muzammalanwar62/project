package usmanali.nephrohub;

/**
 * Created by SAJIDCOMPUTERS on 2/15/2018.
 */

public class Nutrients {
    String Title,per_day_value,per_meal_value;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPer_day_value() {
        return per_day_value;
    }

    public void setPer_day_value(String per_day_value) {
        this.per_day_value = per_day_value;
    }

    public String getPer_meal_value() {
        return per_meal_value;
    }

    public void setPer_meal_value(String per_meal_value) {
        this.per_meal_value = per_meal_value;
    }

    public Nutrients(String title, String per_day_value, String per_meal_value) {
        Title = title;
        this.per_day_value = per_day_value;
        this.per_meal_value = per_meal_value;
    }

}
