package vozila;

public class Automobil extends Vozilo{
   private String pogon;
   private int brVrata;
   private String karoserija;

    public Automobil(int id, String marka, String model, int godiste, int kubikaza, String vrstaMenjaca, String gorivo, int brTockova,String pogon, int brVrata, String karoserija) {
        super(id, marka, model, godiste, kubikaza, vrstaMenjaca, gorivo, brTockova);
        this.pogon = pogon;
        this.brVrata = brVrata;
        this.karoserija = karoserija;
    }
    
    @Override
    public String toString() {
        return  "ID: "+id+" "+marka + " " +model;
    }
    public String getPogon() {
        return pogon;
    }

    public void setPogon(String pogon) {
        this.pogon = pogon;
    }

    public int getBrVrata() {
        return brVrata;
    }

    public void setBrVrata(int brVrata) {
        this.brVrata = brVrata;
    }

    public String getKaroserija() {
        return karoserija;
    }

    public void setKaroserija(String karoserija) {
        this.karoserija = karoserija;
    }   
}
