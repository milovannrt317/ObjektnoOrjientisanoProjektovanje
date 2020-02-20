package ostalo;

import izuzetci.NepravilanUnosPodatakaException;
import java.util.ArrayList;

public class Ponuda {
    private int idAutomobila;
    private int cenaPoDanu;

    public Ponuda(int idAutomobila, int cenaPoDanu) {
        this.idAutomobila = idAutomobila;
        this.cenaPoDanu = cenaPoDanu;
    }
    public static void proveraPreklapanja(ArrayList<Ponuda> lista,int id) throws NepravilanUnosPodatakaException
    {
        for (Ponuda ponuda : lista) {
            if(ponuda.getIdAutomobila()==id)
                throw new NepravilanUnosPodatakaException("\nVozilo već ima ponudu!");
        }
    }
    public int getIdAutomobila() {
        return idAutomobila;
    }

    public void setIdAutomobila(int idAutomobila) {
        this.idAutomobila = idAutomobila;
    }

    public int getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(int cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    @Override
    public String toString() {
        return "ID vozila: " + idAutomobila + "  cena po danu: " + cenaPoDanu+" din";
    }
}
