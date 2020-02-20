package vozila;

public class Motocikl extends Vozilo{
    private String tip;

    public Motocikl(int id, String marka, String model, int godiste, int kubikaza, String vrstaMenjaca, String gorivo, int brTockova,String tip) {
        super(id, marka, model, godiste, kubikaza, vrstaMenjaca, gorivo, brTockova);
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return  "ID: "+id+" "+marka + " " +model;
    }
    
}
