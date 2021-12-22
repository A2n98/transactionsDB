package classes;

public class SerieTitles {
    public String title1;
    public String title2;
    public String title3;
    public String title4;
    public String title5;

    public SerieTitles(String t1, String t2, String t3, String t4, String t5){
        this.title1 = t1;
        this.title2 = t2;
        this.title3 = t3;
        this.title4 = t4;
        this.title5 = t5;
    }

    @Override
    public String toString() {
        return this.title1 + " " + this.title2 + " " + this.title3 + " " + this.title4 + " " + this.title5;
    }
}
