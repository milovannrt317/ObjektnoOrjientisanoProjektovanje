package ostalo;

import izuzetci.PreklapanjeDatumaException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;

public class Rezervacija {

    private int idAutomobila;
    private int idKupca;
    private String danPreuzimanja;
    private String danVracanja;
    private int cena;

    public Rezervacija(int idAutomobila, int idKupca, String danPreuzimanja, String danVracanja, int cena) {
        this.idAutomobila = idAutomobila;
        this.idKupca = idKupca;
        this.danPreuzimanja = danPreuzimanja;
        this.danVracanja = danVracanja;
        this.cena = cena;
    }

    public void proveraPreklapanja(ArrayList<Rezervacija> rezervacije) throws PreklapanjeDatumaException {
        String[] datum = danPreuzimanja.split("-");
        LocalDate datP = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
        if (datP.isBefore(LocalDate.now())) {
            throw new PreklapanjeDatumaException("\nDatum preuzimanja nije korektno popunjen!");
        }
        if (datP.isAfter(LocalDate.now().plusDays(30))) {
            throw new PreklapanjeDatumaException("\nNe možete rezervisati više od 30 dana unapred!");
        }
        datum = danVracanja.split("-");
        LocalDate datV = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
        if (datV.isBefore(datP)) {
            throw new PreklapanjeDatumaException("\nDatum vraćanja ne sme biti pre datuma preuzimanja!");
        }
        for (Rezervacija r : rezervacije) {
            if (r.idAutomobila == this.idAutomobila) {
                datum = r.danPreuzimanja.split("-");
                LocalDate uzet = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                datum = r.danVracanja.split("-");
                LocalDate vracen = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                //3 slucaja
                if (datP.isAfter(uzet) && datP.isBefore(vracen) || datP.isEqual(uzet) || datP.isEqual(vracen)) //ako je datum uzimanja u nekoj rezervaciji
                {
                    throw new PreklapanjeDatumaException("\nDatumi su već zauzeti!");
                }
                if (datV.isAfter(uzet) && datV.isBefore(vracen) || datV.isEqual(uzet) || datV.isEqual(vracen)) //ako je datum vracanja u nekoj rezervaciji
                {
                    throw new PreklapanjeDatumaException("\nDatumi su već zauzeti!");
                }
                if (datP.isBefore(uzet) && datV.isAfter(vracen)) //ako oba datuma obuhvataju neku rezervaciju
                {
                    throw new PreklapanjeDatumaException("\nDatumi su već zauzeti!");
                }
                long brojdana = DAYS.between(datP, datV);
                if (brojdana > 27) //ako je premasen period od 28 dana, tj 4 nedelje
                {
                    throw new PreklapanjeDatumaException("\nPremašen je period od 30 dana!");
                }
            }
        }

    }

    public int getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(int idKupca) {
        this.idKupca = idKupca;
    }

    @Override
    public String toString() {
        return  "ID Kupca: " + idKupca +" ID Vozila: " + idAutomobila + " datum od: " + danPreuzimanja + " datum do: " + danVracanja + " cena: " + cena;
    }

    public int getIdAutomobila() {
        return idAutomobila;
    }

    public void setIdAutomobila(int idAutomobila) {
        this.idAutomobila = idAutomobila;
    }

    public String getDanPreuzimanja() {
        return danPreuzimanja;
    }

    public void setDanPreuzimanja(String danPreuzimanja) {
        this.danPreuzimanja = danPreuzimanja;
    }

    public String getDanVracanja() {
        return danVracanja;
    }

    public void setDanVracanja(String danVracanja) {
        this.danVracanja = danVracanja;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
    
}