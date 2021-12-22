package classes;


public class Transaction {
    public String  serieRef;
    public String period;
    public double dataValue;
    public TransInfo information;
    public SerieTitles serieTitles;

    public Transaction(String serieRef, String period, double dataValue,
                       String sup, String status, String unit, String magnitude, String subject, String group,
                       String t1, String t2, String t3, String t4, String t5){
        this.serieRef = serieRef;
        this.period = period;
        this.dataValue = dataValue;
        this.information = new TransInfo(sup, status, unit, magnitude, subject, group);
        this.serieTitles = new SerieTitles(t1, t2, t3, t4, t5);
    }

    public Transaction(String serieRef, String date, double doub, TransInfo trInfo, SerieTitles seTitles) {
        this.serieRef = serieRef;
        this.period = date;
        this.dataValue = doub;
        this.information = trInfo;
        this.serieTitles = seTitles;
    }

    @Override
    public String toString() {
        return this.serieRef + " " + this.period + " " + Double.toString(this.dataValue) + " " + this.information.toString() + " " + this.serieTitles.toString();
    }
}