package classes;

public class TransInfo {
    public String suppressed;
    public String status;
    public String units;
    public String magnitude;
    public String subject;
    public String group;

    public TransInfo(String sup, String status, String unit, String magnitude, String subject, String group){
        this.suppressed = sup;
        this.status = status;
        this.units = unit;
        this.magnitude = magnitude;
        this.subject = subject;
        this.group = group;
    }

    @Override
    public String toString() {
        return this.suppressed + " " + this.status + " " + this.units + " " + this.magnitude + " " + this.subject + " " + this.group;
    }
}
