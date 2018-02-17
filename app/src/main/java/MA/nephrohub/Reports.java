package usmanali.nephrohub;

/**
 * Created by SAJIDCOMPUTERS on 1/29/2018.
 */

public class Reports {
    String report_title;
    String report_date;
    byte[]  image;
    String ref_by;
    int report_id;

    public Reports(String report_title, String report_date, byte[] image, String ref_by, int report_id) {
        this.report_title = report_title;
        this.report_date = report_date;
        this.image = image;
        this.ref_by = ref_by;
        this.report_id = report_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getRef_by() {
        return ref_by;
    }

    public void setRef_by(String ref_by) {
        this.ref_by = ref_by;
    }
}
