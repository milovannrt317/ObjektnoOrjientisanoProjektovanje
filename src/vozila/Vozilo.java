package vozila;

import java.util.ArrayList;

public abstract class Vozilo{
    protected int id;
    protected String marka;
    protected String model;
    protected int godiste;
    protected int kubikaza;
    protected String vrstaMenjaca;
    protected String gorivo;
    protected int brTockova;

    public Vozilo(int id, String marka, String model, int godiste, int kubikaza, String vrstaMenjaca, String gorivo, int brTockova) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.kubikaza = kubikaza;
        this.vrstaMenjaca = vrstaMenjaca;
        this.gorivo = gorivo;
        this.brTockova = brTockova;
    }
    public static int idMaker(ArrayList<Automobil> lista,ArrayList<Motocikl> lista1)
    {
        ArrayList<Vozilo> lst=new ArrayList<>();
        lst.addAll(lista);
        lst.addAll(lista1);
        int ID=lst.size()+1;
        lst.sort((x,y)->Integer.compare(x.getId(), y.getId()));
        for (int i = 0; i < lst.size(); i++) {
            if(lst.get(i).getId()==ID)
            {
                ID++;
                i--;
                if(i<0)
                    i=0;
            }
        }
        return ID;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public String getVrstaMenjaca() {
        return vrstaMenjaca;
    }

    public void setVrstaMenjaca(String vrstaMenjaca) {
        this.vrstaMenjaca = vrstaMenjaca;
    }

    public String getGorivo() {
        return gorivo;
    }

    public void setGorivo(String gorivo) {
        this.gorivo = gorivo;
    }

    public int getBrTockova() {
        return brTockova;
    }

    public void setBrTockova(int brTockova) {
        this.brTockova = brTockova;
    }
    
}
