package rentAcar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import korisnik.*;
import ostalo.*;
import vozila.*;

public class RentACar implements Osvezavanje{
    private String naziv;
    private String adresa;
    private ArrayList<Korisnik> korisnici;
    private ArrayList<Kupac> kupci;
    private ArrayList<Rezervacija> rezervacije;
    private ArrayList<Vozilo> vozila;
    private ArrayList<Ponuda> ponude;
    private ArrayList<Automobil> vozilaAuto;
    private ArrayList<Motocikl> vozilaMoto;

    public RentACar() {
        naziv="VIŠER CAR";
        adresa="Vojvode Stepe 283";
        
        osveziListe();
        osveziRezervacije();
        porukaPocetna();
    }
    public void porukaPocetna()
    {
        String poruka="";
        for (int i = 0; i <= 5; i++) 
        {
            for (int j = 0; j < 80; j++) {
                if(i==0 || i==5 || j==0 ||( j==79 && i!=2 && i!=3))
                    poruka+='*';
                else if(i==2 && j==30)
                    poruka+=naziv;
                else if(i==3 && j==26)
                    poruka+=adresa;
                else if(i==2 && j==80-naziv.length())
                    poruka+='*';
                else if(i==3 && j==80-adresa.length())
                    poruka+='*';
                else
                    poruka+=' ';
            }
            poruka+='\n';
        }
        System.out.println(poruka);
        System.out.println("Pozdrav,\nDobro došli u aplikaciju za iznajmljivanje automobila!\nDa nastavite dalje molimo Vas da se ulogujete ili\nkreirate nalog u koliko nemate nalog!");
    }
    public void porukaZavrsna()
    {
        String poruka="\n";
        String poruka1="HVALA ŠTO STE KORISTILI NAŠU APLIKACIJU!";
        String poruka2="POZDRAV!";
        for (int i = 0; i <= 5; i++) 
        {
            for (int j = 0; j < 80; j++) {
                if(i==0 || i==5 || j==0 ||( j==79 && i!=2&& i!=3))
                    poruka+='*';
                else if(i==2 && j==18)
                    poruka+=poruka1;
                else if(i==3 && j==35)
                    poruka+=poruka2;
                else if(i==2 && j==80-poruka1.length())
                    poruka+='*';
                else if(i==3 && j==80-poruka2.length())
                    poruka+='*';
                else
                    poruka+=' ';
            }
            poruka+='\n';
        }
        System.out.println(poruka);
    }
    private Korisnik logovanjeAdmin(String kIme, String loz)
    {
        for (Korisnik korisnik : korisnici) {
            if(korisnik instanceof Administrator && korisnik.getKorsnickoIme().compareTo(kIme)==0 && korisnik.getLozinka().compareTo(loz)==0)
                return korisnik;
        }
        return null;
    }
    private Korisnik logovanjeKupac(String kIme, String loz)
    {
        for (Korisnik korisnik : korisnici) {
            if(korisnik instanceof Kupac && korisnik.getKorsnickoIme().compareTo(kIme)==0 && korisnik.getLozinka().compareTo(loz)==0)
                return korisnik;
        }
        return null;
    }
    public Korisnik logovanje(int opcija)
    {
        Scanner inp=new Scanner(System.in);
        System.out.println("\nUneti korisničko ime:");
        String korIme=inp.nextLine();
        System.out.println("\nUneti lozinku:");
        String loz=inp.nextLine();
        switch(opcija)
        {
            case 1: return logovanjeAdmin(korIme, loz);
            case 2: return logovanjeKupac(korIme, loz);
        }
        return null;
    }
    @Override
    public String toString() {
        
        return "Rent A Car: " + naziv + "\nadresa: " + adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public ArrayList<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public ArrayList<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(ArrayList<Vozilo> vozila) {
        this.vozila = vozila;
    }

    public ArrayList<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(ArrayList<Ponuda> ponude) {
        this.ponude = ponude;
    }
    
    public ArrayList<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(ArrayList<Kupac> kupci) {
        this.kupci = kupci;
    }

    public ArrayList<Automobil> getVozilaAuto() {
        return vozilaAuto;
    }

    public void setVozilaAuto(ArrayList<Automobil> vozilaAuto) {
        this.vozilaAuto = vozilaAuto;
    }

    public ArrayList<Motocikl> getVozilaMoto() {
        return vozilaMoto;
    }

    public void setVozilaMoto(ArrayList<Motocikl> vozilaMoto) {
        this.vozilaMoto = vozilaMoto;
    }
    
    @Override
    public void osveziRezervacije() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        
        try {
           Date tren=format.parse(dtf.format(now));
           for (int i=0;i<rezervacije.size();i++) {
                if(format.parse(rezervacije.get(i).getDanVracanja()).compareTo(tren)<=0)
                { 
                    rezervacije.remove(i);
                    i--;
                    if(i<0)
                        i=0;
                }
            }
        } catch (ParseException ex) {
        }
        radSaDatotekama.upisUDatoteku(rezervacije,6);
    }

    @Override
    public void osveziListe() {
        korisnici=new ArrayList<>();
        vozila=new ArrayList<>();
        vozilaAuto=radSaDatotekama.citanjeIzDatoteke(3);
        vozilaMoto=radSaDatotekama.citanjeIzDatoteke(4);
        vozila.addAll(vozilaAuto);
        vozila.addAll(vozilaMoto);
        kupci=radSaDatotekama.citanjeIzDatoteke(1);
        korisnici.addAll(kupci);
        korisnici.add(new Administrator(1, "admin", "admin", "admin", "admin"));
        ponude=radSaDatotekama.citanjeIzDatoteke(5);
        rezervacije=radSaDatotekama.citanjeIzDatoteke(6);
    }
}
