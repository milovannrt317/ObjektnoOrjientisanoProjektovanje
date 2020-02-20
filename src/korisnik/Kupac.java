package korisnik;

import izuzetci.NepravilanUnosPodatakaException;
import izuzetci.PreklapanjeDatumaException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import ostalo.*;
import rentAcar.*;
import vozila.*;

public class Kupac extends Korisnik {

    private String jmbg;
    private String datRodjenja;
    private String telefon;

    public Kupac(int id, String ime, String prezime, String korsnickoIme, String lozinka, String jmbg, String datRodjenja, String telefon) {
        super(id, ime, prezime, korsnickoIme, lozinka);
        this.jmbg = jmbg;
        this.datRodjenja = datRodjenja;
        this.telefon = telefon;
    }

    public void rezervisi(ArrayList<Ponuda> pon, ArrayList<Vozilo> voz, ArrayList<Rezervacija> rez) {
        for (Rezervacija r : rez) {
            if (r.getIdKupca() == this.id) {
                System.out.println("\nVeć ste rezervisali vozilo.\nAko želite da rezervišete drugo vozilo morate da otkažete trenutnu rezervaciju.\nRezervaciju ne možete otkazati ako ste preuzeli vozilo!\n");
                return;
            }
        }
        pon.sort((x,y)->Integer.compare(x.getIdAutomobila(), y.getIdAutomobila()));
        Scanner s = new Scanner(System.in);
        System.out.print("\nIzabarite vozilo iz ponude (unesite id vozila ili 0 za povratak na prethodni meni):\n");
        for (Ponuda p : pon) {
            System.out.println(p);
            for (Vozilo v : voz) {
                if (p.getIdAutomobila() == v.getId()) {
                    System.out.println(v);
                }
            }
            System.out.println("");
        }
        System.out.print("Unesite opciju:  ");
        int izbor = s.nextInt();

        if (izbor == 0) {
            return;
        }
        boolean nema=true;
        for (Ponuda p : pon) {
            if (izbor == p.getIdAutomobila()) {
                nema=false;
            }
        }
        if(nema){
            System.out.println("\nNema vozila sa zadatim ID");
            return;
        }
        //dostupni dani
        ArrayList<String> dani = new ArrayList<>();
        ArrayList<String> daniS = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            LocalDate dan = LocalDate.now().plusDays((long) i);
            daniS.add(dan.toString());
            for (Rezervacija r : rez) {
                if (izbor == r.getIdAutomobila()) {
                    String[] datum = r.getDanPreuzimanja().split("-");
                    LocalDate uzet = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                    datum = r.getDanVracanja().split("-");
                    LocalDate vracen = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                    if (!dan.isEqual(uzet) && !dan.isEqual(vracen) && !(dan.isAfter(uzet) && dan.isBefore(vracen))) {//samo zauzete dane u else vraca pa te dane oduzmem 
                    } else {
                        dani.add(dan.toString());
                    }
                }
            }
        }
        daniS.removeAll(dani);
        Collections.sort(daniS);
        int i = 0;
        if (daniS.isEmpty()) {
            System.out.println("\nNema slobodnih datuma!");
            return;
        } else {
            System.out.println("\nSlobodni datumi:");
        }
        for (String d : daniS) {
            i++;
            String[] datum = d.split("-");
            System.out.print(datum[2] + "-" + datum[1] + "-" + datum[0] + "  ");
            if (i % 5 == 0) {
                System.out.println("");
            }
        }

        for (Ponuda p : pon) {
            if (izbor == p.getIdAutomobila()) {

                try {
                    System.out.println("\nUnesite datum preuzimanja (npr. 12-03-2019)");
                    String dat1 = s.next();
                    ProveraUnosa.proveraFormataDatuma(dat1);
                    System.out.println("\nUnesite datum vraćanja (npr. 12-03-2019)");
                    String dat2 = s.next();
                    ProveraUnosa.proveraFormataDatuma(dat2);
                    //za cenu ukupnu cenu
                    String[] datum = dat1.split("-");
                    LocalDate uzet = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                    datum = dat2.split("-");
                    LocalDate vracen = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                    long cena = (DAYS.between(uzet, vracen) + 1) * p.getCenaPoDanu();
                    Rezervacija r = new Rezervacija(izbor, this.id, dat1, dat2, (int) cena);
                    r.proveraPreklapanja(rez);
                    rez.add(r);
                    System.out.println(r);
                    System.out.println("\nUspešna rezervacija!");
                    radSaDatotekama.upisUDatoteku(rez,6);
                } catch (PreklapanjeDatumaException | NepravilanUnosPodatakaException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    public void pregledRezervacija(ArrayList<Vozilo> voz, ArrayList<Rezervacija> rez) {
        boolean flag = false;
        for (Rezervacija r : rez) {
            if (r.getIdKupca() == this.id) {
                for (Vozilo v : voz) {
                    if (v.getId() == r.getIdAutomobila()) {
                        System.out.println("\nVaša trenutna rezervacija:");
                        System.out.println(v);
                        flag = true;
                    }
                }
                System.out.println(r + "\n");
            }
        }
        if (!flag) {
            System.out.println("\nNemate rezervaciju\n");
        }
    }

    public void ukloniRezervaciju(ArrayList<Rezervacija> rez) {
        boolean flag = false;
        for (Rezervacija r : rez) {
            if (r.getIdKupca() == this.id) {
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("\nNemate rezervaciju\n");
            return;
        }
        Scanner s = new Scanner(System.in);
        System.out.println("\nDa li ste sigurni da želite da otkažete rezervaciju?\n1. Da\n2. Ne");
        System.out.print("\nUnesite opciju: ");
        if ("1".equals(s.next())) {
            for (Rezervacija r : rez) {
                if (r.getIdKupca() == this.id) {
                    String[] datum = r.getDanPreuzimanja().split("-");
                    LocalDate preuzet = LocalDate.parse(datum[2] + "-" + datum[1] + "-" + datum[0]);
                    if (preuzet.isBefore(LocalDate.now())) {
                        
                        System.out.println("\nNe možete otkazati rezervaciju jer ste vec zadužili vozilo!");
                        return;
                    }
                    rez.remove(r);
                    System.out.println("\nUspešno otkazana rezervacija\n");
                    radSaDatotekama.upisUDatoteku(rez,6);
                    break;
                }
            }
        }

    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getDatRodjenja() {
        return datRodjenja;
    }

    public void setDatRodjenja(String datRodjenja) {
        this.datRodjenja = datRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}