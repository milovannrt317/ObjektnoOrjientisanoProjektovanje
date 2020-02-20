package korisnik;

import izuzetci.NepravilanUnosPodatakaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import vozila.*;
import ostalo.*;
import rentAcar.*;

public class Administrator extends Korisnik{
    
    public Administrator(int id, String ime, String prezime, String korsnickoIme, String lozinka) {
        super(id, ime, prezime, korsnickoIme, lozinka);
    }
    public void dodajKupcaCekanje(RentACar r)
    {
        r.osveziListe();
        ArrayList<Kupac> kupci=r.getKupci();
        ArrayList<Kupac> kupciCekanje=radSaDatotekama.citanjeIzDatoteke(2);
       
        while (true) {
            System.out.println("\nLista kupaca na čekanju:");
            if(kupciCekanje.isEmpty())
            {
                System.out.println("Lista čekanja je prazna!");
                break;
            }
            for (int i = 0; i < kupciCekanje.size(); i++)
                System.out.println((i+1)+". "+kupciCekanje.get(i));

            System.out.print("\nIzabrati redni broj kupca koji želite da dodate/obrišete, \"0\" za izlazak!: ");
            int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
            if(rbr==0)
                break;
            if (rbr-1<kupciCekanje.size()) 
                while (true) {
                    boolean flg=true;
                    try {

                        System.out.println("\nIzabrati opciju (1. odobravanje kreiranja naloga ili 2. brisanje iz liste čekanja), \"0\" za izlazak!");
                        int opcija=Integer.parseInt(new Scanner(System.in).nextLine());
                        switch(opcija)
                        {
                            case 1:
                                kupciCekanje.get(rbr-1).setId(Korisnik.idMaker(kupci)); 
                                kupci.add(kupciCekanje.get(rbr-1));
                                kupciCekanje.remove(rbr-1);
                                System.out.println("\nUspešno dodat!");
                                break;
                            case 2:kupciCekanje.remove(rbr-1);System.out.println("\nUspešno obrisan sa liste čekanja!");break;
                            case 0:break;
                            default: flg=false; System.out.println("\nNepostojeća opcija!");break;
                        }

                    } catch (Exception e) {
                        System.out.println("\nGreška unosa!");
                        flg=false;
                    }
                    if(flg)
                       break;
                }
            else
                System.out.println("\nPogrešan redni broj kupca na čekanju!");
            
        }
        radSaDatotekama.upisUDatoteku(kupci,1);
        radSaDatotekama.upisUDatoteku(kupciCekanje,2);
    }
    public void dodajKupca(RentACar r)
    {
        r.osveziListe();
        ArrayList<Kupac> kupci=r.getKupci();
        Scanner inp=new Scanner(System.in);

        int ID=Korisnik.idMaker(kupci);
        int br=0;
        while (true) {
            if(br==3)
            {
                System.out.println("\nNapravili ste 3 greške pri unosu podataka!");
                break;
            }
            try {
               
                System.out.println("Uneti ime:");
                String Ime=inp.nextLine();
                ProveraUnosa.proveraImePrezime(Ime);

                System.out.println("Uneti prezime:");
                String Prezime=inp.nextLine();
                ProveraUnosa.proveraImePrezime(Prezime);

                System.out.println("Uneti korisničko ime:");
                String korIme=inp.nextLine();
                ProveraUnosa.proveraKorIme(korIme,kupci);

                System.out.println("Uneti lozinku:");
                String loz=inp.nextLine();
                ProveraUnosa.proveraLozinke(loz);

                System.out.println("Uneti telefon:");
                String tel=inp.nextLine();
                ProveraUnosa.proveraTelefona(tel,kupci);

                System.out.println("Uneti datum rođenja u formatu dd-MM-yyyy:");
                String datRodj=inp.nextLine();
                ProveraUnosa.proveraDatuma(datRodj);
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
                Date date=format.parse(datRodj);
                datRodj=format.format(date);
                
                System.out.println("Uneti JMBG:");
                String jmbg=inp.nextLine();
                ProveraUnosa.proveraJmbg(jmbg,datRodj,kupci);
                kupci.add(new Kupac(ID, Ime, Prezime, korIme, loz, jmbg, datRodj, tel));
                
                
                radSaDatotekama.upisUDatoteku(kupci,1);
                System.out.println("\nKupac je uspešno unet!");
                
                break;
            } catch (NepravilanUnosPodatakaException e) {
                System.out.println(e.getMessage()); 
                br++;
                if(3-br!=0)
                    System.out.println("\nImate još "+(3-br)+((3-br)==1?" grešku":" greške")+" da napravite!\n");
            }
            catch (Exception e) {  
            }
        }   
    }
    public void izmenaKupca(RentACar r)
    {
        r.osveziListe();
        ArrayList<Kupac> kupci=r.getKupci();
        Scanner inp=new Scanner(System.in);
        
        while(true){
            System.out.println("\nPrikaz svih podataka:");
            if(kupci.isEmpty())
            {
                System.out.println("\nNema kupaca za izmenu!");
                break;
            }
            for (int i = 0; i < kupci.size(); i++) {
                 System.out.println((i+1)+". "+kupci.get(i)); 
            }
            try {
                System.out.print("\nOdaberite koji podatak menjate za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                int idKp=kupci.get(rbr-1).id;
                kupci.remove(rbr-1);
                
                System.out.println("Uneti ime:");
                String Ime=inp.nextLine();
                ProveraUnosa.proveraImePrezime(Ime);

                System.out.println("Uneti prezime:");
                String Prezime=inp.nextLine();
                ProveraUnosa.proveraImePrezime(Prezime);

                System.out.println("Uneti korisničko ime:");
                String korIme=inp.nextLine();
                ProveraUnosa.proveraKorIme(korIme,kupci);

                System.out.println("Uneti lozinku:");
                String loz=inp.nextLine();
                ProveraUnosa.proveraLozinke(loz);

                System.out.println("Uneti telefon:");
                String tel=inp.nextLine();
                ProveraUnosa.proveraTelefona(tel,kupci);

                System.out.println("Uneti datum rođenja u formatu dd-MM-yyyy:");
                String datRodj=inp.nextLine();
                ProveraUnosa.proveraDatuma(datRodj);
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
                Date date=format.parse(datRodj);
                datRodj=format.format(date);
               

                System.out.println("Uneti JMBG:");
                String jmbg=inp.nextLine();
                ProveraUnosa.proveraJmbg(jmbg,datRodj,kupci);

                kupci.add(rbr-1, new Kupac(idKp, Ime, Prezime, korIme, loz, jmbg, datRodj, tel));
                radSaDatotekama.upisUDatoteku(kupci,1);
                System.out.println("\nUspešno ste izmenili podatke o kupcu!");
            } catch (Exception e) {
                System.out.println("\nGreška unosa! "+e.getMessage());
                r.osveziListe();
                kupci=r.getKupci();
            }
        }  
    }
    
    
    public void obrisiKupca(RentACar r)
    {
        r.osveziListe();
        ArrayList<Kupac> kupci=r.getKupci();
        ArrayList<Rezervacija> rezervacije=r.getRezervacije();
        while(true){
            System.out.println("\nPrikaz svih podataka:");
            if(kupci.isEmpty())
            {
                System.out.println("Nema kupaca za brisanje!");
                break;
            }
            for (int i = 0; i < kupci.size(); i++) {
                 System.out.println((i+1)+". "+kupci.get(i)); 
            }
            try {
                System.out.print("\nOdaberite koji podatak brišete za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                ProveraUnosa.proveraRezKp(kupci.get(rbr-1).getId(), rezervacije);
                kupci.remove(rbr-1);
                System.out.println("\nUspešno ste obrisali kupca");
            } catch (NepravilanUnosPodatakaException e) {
                System.out.println("\n"+e.getMessage());
            } catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }
        radSaDatotekama.upisUDatoteku(kupci,1);
    }
    public void dodajVozilo(RentACar r)
    {
        Scanner inp=new Scanner(System.in);
        r.osveziListe();
        ArrayList<Automobil> vozilaAuto=r.getVozilaAuto();
        ArrayList<Motocikl> vozilaMoto=r.getVozilaMoto();
                
        while (true) {            
            boolean flg=false;
            try{
                System.out.println("\nIzaberite koji tip vozila želite da unesete (1. automobil ili 2. motocikl), ako želite da prekinete unos unesite \"0\"");
                int opcija=Integer.parseInt(new Scanner(System.in).nextLine());
                switch(opcija)
                {
                    case 0: flg=true;break;
                    case 1:
                        int ID=Vozilo.idMaker(vozilaAuto,vozilaMoto);
                        int br=0;
                        
                        while (true) {
                            if(br==3)
                            {
                                System.out.println("\nNapravili ste 3 greške pri unosu podataka!");
                                break;
                            }
                            try {

                                System.out.println("Uneti marku:");
                                String marka=inp.nextLine();
                                ProveraUnosa.proveraMarka(marka);

                                System.out.println("Uneti model:");
                                String model=inp.nextLine();
                                ProveraUnosa.proveraModel(model);

                                System.out.println("Uneti godište (opseg od 1990 do trenutne godine):");
                                String godiste=inp.nextLine();
                                ProveraUnosa.proveraGodiste(godiste);
                                
                                System.out.println("Uneti kubikažu (opseg od 1000 do 10000):");
                                String kubikaza=inp.nextLine();
                                ProveraUnosa.proveraKubikaza(kubikaza);

                                System.out.println("Uneti vrstu menjača (automatski ili manuelni):");
                                String vrMenjac=inp.nextLine();
                                ProveraUnosa.proveraMenjac(vrMenjac);

                                System.out.println("Uneti vrstu goriva (benzin ili dizel):");
                                String gorivo=inp.nextLine();
                                ProveraUnosa.proveraGorivo(gorivo);

                                System.out.println("Uneti pogon (prednji ili zadnji):");
                                String pogon=inp.nextLine();
                                ProveraUnosa.proveraPogon(pogon);

                                System.out.println("Uneti broj vrata (opseg od 2 do 5):");
                                String brVrata=inp.nextLine();
                                ProveraUnosa.proveraBrVrata(brVrata);
                                
                                System.out.println("Uneti karoseriju (limuzina, kupe ili kabriolet):");
                                String karoserija=inp.nextLine();
                                ProveraUnosa.proveraKaroserija(karoserija);
                                
                                vozilaAuto.add(new Automobil(ID, marka, model, Integer.parseInt(godiste), Integer.parseInt(kubikaza), vrMenjac, gorivo, 4, pogon, Integer.parseInt(brVrata), karoserija));
                                System.out.println("\nAutomobil je uspešno unet!\n");
                                break;
                            } catch (NepravilanUnosPodatakaException e) {
                                System.out.println(e.getMessage()); 
                                br++;
                                if(3-br!=0)
                                    System.out.println("\nImate još "+(3-br)+((3-br)==1?" grešku":" greške")+" da napravite!\n");
                            }
                            catch (Exception e) {  
                            }
                        }
                        radSaDatotekama.upisUDatoteku(vozilaAuto,3);
                        
                        break;
                    case 2:
                        int IDM=Vozilo.idMaker(vozilaAuto,vozilaMoto);
                        int brr=0;
                        
                        while (true) {
                            if(brr==3)
                            {
                                System.out.println("\nNapravili ste 3 greške pri unosu podataka!");
                                break;
                            }
                            try {

                                System.out.println("Uneti marku:");
                                String marka=inp.nextLine();
                                ProveraUnosa.proveraMarka(marka);

                                System.out.println("Uneti model:");
                                String model=inp.nextLine();
                                ProveraUnosa.proveraModel(model);

                                System.out.println("Uneti godište (opseg od 1990 do trenutne godine):");
                                String godiste=inp.nextLine();
                                ProveraUnosa.proveraGodiste(godiste);
                                
                                System.out.println("Uneti kubikažu (opseg od 50 do 2000):");
                                String kubikaza=inp.nextLine();
                                ProveraUnosa.proveraKubikazaMoto(kubikaza);

                                System.out.println("Uneti vrstu menjača (automatski ili manuelni):");
                                String vrMenjac=inp.nextLine();
                                ProveraUnosa.proveraMenjac(vrMenjac);

                                System.out.println("Uneti tip (sport, kros, enduro ili coper):");
                                String tip=inp.nextLine();
                                ProveraUnosa.proveraTip(tip);
                                
                                vozilaMoto.add(new Motocikl(IDM, marka, model, Integer.parseInt(godiste), Integer.parseInt(kubikaza), vrMenjac, "benzin", 2, tip));
                                System.out.println("\nMotocikl je uspešno unet!\n");
                                break;
                            } catch (NepravilanUnosPodatakaException e) {
                                System.out.println(e.getMessage());
                                brr++;
                                if(3-brr!=0)
                                    System.out.println("\nImate još "+(3-brr)+((3-brr)==1?" grešku":" greške")+" da napravite!\n");
                            
                            }
                            catch (Exception e) {  
                            }
                        }
                        radSaDatotekama.upisUDatoteku(vozilaMoto,4);
                        
                        break;
                    default :
                        System.out.println("\nPogrešna opcija!");break;
                }
                if(flg)
                    break;
            }catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }
    }
    public void izmeniVozilo(RentACar r)
    {
        Scanner inp=new Scanner(System.in);
        r.osveziListe();
        ArrayList<Automobil> vozilaAuto=r.getVozilaAuto();
        ArrayList<Motocikl> vozilaMoto=r.getVozilaMoto();
        while (true) {            
            boolean flg=false;
            try{
                if(vozilaAuto.isEmpty() && vozilaMoto.isEmpty())
                {
                    System.out.println("\nNema vozila za izmenu!\n");
                    break;
                }
                System.out.println("\nIzaberite koji tip vozila želite da menjate (1. automobil ili 2. motocikl), ako želite da prekinete unos unesite \"0\"");
                int opcija=Integer.parseInt(new Scanner(System.in).nextLine());
                switch(opcija)
                {
                    case 0: flg=true;break;
                    case 1:
                        while (true) {
                            System.out.println("\nPrikaz svih podataka:");
                            
                            if(vozilaAuto.isEmpty())
                            {
                                System.out.println("Nema automobila za izmenu za izmenu!");
                                break;
                            }
                            for (int i = 0; i < vozilaAuto.size(); i++) {
                                 System.out.println((i+1)+". "+vozilaAuto.get(i)); 
                            }
                            try {
                                System.out.print("\nOdaberite koji podatak menjate za kraj unesite \"0\": ");
                                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                                if(rbr==0)
                                    break;
                                int ID=vozilaAuto.get(rbr-1).getId();
                              
                                System.out.println("Uneti marku:");
                                String marka=inp.nextLine();
                                ProveraUnosa.proveraMarka(marka);

                                System.out.println("Uneti model:");
                                String model=inp.nextLine();
                                ProveraUnosa.proveraModel(model);

                                System.out.println("Uneti godište (opseg od 1990 do trenutne godine):");
                                String godiste=inp.nextLine();
                                ProveraUnosa.proveraGodiste(godiste);
                                
                                System.out.println("Uneti kubikažu (opseg od 1000 do 10000):");
                                String kubikaza=inp.nextLine();
                                ProveraUnosa.proveraKubikaza(kubikaza);

                                System.out.println("Uneti vrstu menjača (automatski ili manuelni):");
                                String vrMenjac=inp.nextLine();
                                ProveraUnosa.proveraMenjac(vrMenjac);

                                System.out.println("Uneti vrstu goriva (benzin ili dizel):");
                                String gorivo=inp.nextLine();
                                ProveraUnosa.proveraGorivo(gorivo);

                                System.out.println("Uneti pogon (prednji ili zadnji):");
                                String pogon=inp.nextLine();
                                ProveraUnosa.proveraPogon(pogon);

                                System.out.println("Uneti broj vrata (opseg od 2 do 5):");
                                String brVrata=inp.nextLine();
                                ProveraUnosa.proveraBrVrata(brVrata);
                                
                                System.out.println("Uneti karoseriju (limuzina, kupe ili kabriolet):");
                                String karoserija=inp.nextLine();
                                ProveraUnosa.proveraKaroserija(karoserija);
                                
                                vozilaAuto.remove(rbr-1);
                                vozilaAuto.add(rbr-1,new Automobil(ID, marka, model, Integer.parseInt(godiste), Integer.parseInt(kubikaza), vrMenjac, gorivo, 4, pogon, Integer.parseInt(brVrata), karoserija));
                                
                                System.out.println("\nAutomobil je uspešno izmenjen!\n");
                                break;
                            } catch (NepravilanUnosPodatakaException e) {
                                System.out.println(e.getMessage()); 
                            }
                            catch (Exception e) {  
                            }
                        }
                        
                        break;
                    case 2:
                        while (true) {
                             System.out.println("\nPrikaz svih podataka:");
                            if(vozilaMoto.isEmpty())
                            {
                                System.out.println("Nema motocikala za izmenu!");
                                break;
                            }
                            for (int i = 0; i < vozilaMoto.size(); i++) {
                                 System.out.println((i+1)+". "+vozilaMoto.get(i)); 
                            }
                            try {
                                System.out.print("\nOdaberite koji podatak menjate, za kraj unesite \"0\": ");
                                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                                if(rbr==0)
                                    break;
                                int IDM=vozilaMoto.get(rbr-1).getId();
                                
                                System.out.println("Uneti marku:");
                                String marka=inp.nextLine();
                                ProveraUnosa.proveraMarka(marka);

                                System.out.println("Uneti model:");
                                String model=inp.nextLine();
                                ProveraUnosa.proveraModel(model);

                                System.out.println("Uneti godište (opseg od 1990 do trenutne godine):");
                                String godiste=inp.nextLine();
                                ProveraUnosa.proveraGodiste(godiste);
                                
                                System.out.println("Uneti kubikažu (opseg od 50 do 2000):");
                                String kubikaza=inp.nextLine();
                                ProveraUnosa.proveraKubikazaMoto(kubikaza);

                                System.out.println("Uneti vrstu menjača (automatski ili manuelni):");
                                String vrMenjac=inp.nextLine();
                                ProveraUnosa.proveraMenjac(vrMenjac);

                                System.out.println("Uneti tip (sport, kros, enduro ili coper):");
                                String tip=inp.nextLine();
                                ProveraUnosa.proveraTip(tip);
                                vozilaMoto.remove(rbr-1);
                                vozilaMoto.add(rbr-1,new Motocikl(IDM, marka, model, Integer.parseInt(godiste), Integer.parseInt(kubikaza), vrMenjac, "benzin", 2, tip));
                                
                                System.out.println("\nMotocikl je uspešno izmenjen!\n");
                                break;
                            } catch (NepravilanUnosPodatakaException e) {
                                System.out.println(e.getMessage()); 
                            }
                            catch (Exception e) {  
                            }
                        }
                        
                        break;
                    default :
                        System.out.println("\nPogrešna opcija!");break;
                }
                if(flg)
                    break;
            }catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        } 
        radSaDatotekama.upisUDatoteku(vozilaAuto,3);
        radSaDatotekama.upisUDatoteku(vozilaMoto,4);
    }
    
    public void obrisiVozilo(RentACar r)
    {
        r.osveziListe();
        ArrayList<Automobil> vozilaAuto=r.getVozilaAuto();
        ArrayList<Motocikl> vozilaMoto=r.getVozilaMoto();
        ArrayList<Rezervacija> rezervacije=r.getRezervacije();
       
        while (true) {            
            boolean flg=false;
            try{
                if(vozilaAuto.isEmpty() && vozilaMoto.isEmpty())
                {
                    System.out.println("\nNema vozila za brisanje!\n");
                    break;
                }
                System.out.println("\nIzaberite koji tip vozila želite da brišete (1. automobil ili 2. motocikl), ako želite da prekinete unos unesite \"0\"");
                int opcija=Integer.parseInt(new Scanner(System.in).nextLine());
                switch(opcija)
                {
                    case 0: flg=true;break;
                    case 1:
                        while(true){
                            System.out.println("\nPrikaz svih podataka:");
                            if(vozilaAuto.isEmpty())
                            {
                                System.out.println("Nema vozila za brisanje!");
                                break;
                            }
                            for (int i = 0; i < vozilaAuto.size(); i++) {
                                 System.out.println((i+1)+". "+vozilaAuto.get(i)); 
                            }
                            try {
                                System.out.print("\nOdaberite koji podatak brišete za kraj unesite \"0\": ");
                                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                                if(rbr==0)
                                    break;
                                ProveraUnosa.proveraRezVz(vozilaAuto.get(rbr-1).getId(), rezervacije);
                                vozilaAuto.remove(rbr-1);
                                System.out.println("\nUspešno ste obrisali vozilo!\n");
                            }catch (NepravilanUnosPodatakaException e) {
                                System.out.println("\n"+e.getMessage());
                            } catch (Exception e) {
                                System.out.println("\nGreška unosa!");
                            }
                        }
                        break;
                    case 2:
                        while(true){
                            System.out.println("\nPrikaz svih podataka:");
                            if(vozilaMoto.isEmpty())
                            {
                                System.out.println("Nema vozila za brisanje!");
                                break;
                            }
                            for (int i = 0; i < vozilaMoto.size(); i++) {
                                 System.out.println((i+1)+". "+vozilaMoto.get(i)); 
                            }
                            try {
                                System.out.print("\nOdaberite koji podatak brišete za kraj unesite \"0\": ");
                                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                                if(rbr==0)
                                    break;
                                ProveraUnosa.proveraRezVz(vozilaMoto.get(rbr-1).getId(), rezervacije);
                                vozilaMoto.remove(rbr-1);
                                System.out.println("\nUspešno ste obrisali vozilo!\n");
                            }catch (NepravilanUnosPodatakaException e) {
                                System.out.println("\n"+e.getMessage());
                            }catch (Exception e) {
                                System.out.println("\nGreška unosa!");
                            }
                        }
                        break;
                    default :
                        System.out.println("\nPogrešna opcija!");break;
                }
                if(flg)
                    break;
            }catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }
        radSaDatotekama.upisUDatoteku(vozilaAuto,3);
        radSaDatotekama.upisUDatoteku(vozilaMoto,4);
    }

    public void dodajPonudu(RentACar r)
    {
        r.osveziListe();
        ArrayList<Automobil> vozilaAuto=r.getVozilaAuto();
        ArrayList<Motocikl> vozilaMoto=r.getVozilaMoto();
        ArrayList<Ponuda> ponude=r.getPonude();
        ArrayList<Vozilo> vozila=new ArrayList<>();
        vozila.addAll(vozilaAuto);
        vozila.addAll(vozilaMoto);
        vozila.sort((x,y)->Integer.compare(x.getId(), y.getId()));
        
        while (true) {
            ///////da ne prikazuje vozila koja imaju vec ponudu
            vozila=(ArrayList<Vozilo>)vozila.stream().filter(x->!ponude.stream().anyMatch(y->y.getIdAutomobila()==x.getId())).collect(Collectors.toList());
            try {
                System.out.println("\nPrikaz svih podataka:");
                if(vozila.isEmpty())
                {
                    System.out.println("Nema vozila za dodavanje ponuda!\n");
                    break;
                }
                for (int i = 0; i < vozila.size(); i++) {
                     System.out.println((i+1)+". "+vozila.get(i)); 
                }
                System.out.print("\nOdaberite za koje vozilo dodajete ponudu, za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                
                int ID=vozila.get(rbr-1).getId();
                Ponuda.proveraPreklapanja(ponude,ID);
                System.out.println("Unesite cenu po danu:");
                String cena=new Scanner(System.in).nextLine();
                ProveraUnosa.proveraCena(cena);
                ponude.add(new Ponuda(ID, Integer.parseInt(cena)));
                System.out.println("\nUspešno ste dodali ponudu!");
            } catch (NepravilanUnosPodatakaException e) {
                System.out.println(e.getMessage()); 
            }
            catch (Exception e) {
                System.out.println("Greška unosa!");
            }
        }
        radSaDatotekama.upisUDatoteku(ponude,5);
    }
    
    public void izmeniPonudu(RentACar r)
    {
        r.osveziListe();
        ArrayList<Ponuda> ponude=r.getPonude();
        while (true) {
            
            try {
                System.out.println("\nPrikaz svih podataka:");
                if(ponude.isEmpty())
                {
                    System.out.println("\nNema ponuda za izmenu!\n");
                    break;
                }
                for (int i = 0; i < ponude.size(); i++) {
                     System.out.println((i+1)+". "+ponude.get(i)); 
                }
                System.out.print("\nOdaberite koju ponudu menjate, za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                
                int ID=ponude.get(rbr-1).getIdAutomobila();
                System.out.println("Unesite novu cenu po danu:");
                String cena=new Scanner(System.in).nextLine();
                ProveraUnosa.proveraCena(cena);
                ponude.remove(rbr-1);
                ponude.add(rbr-1,new Ponuda(ID, Integer.parseInt(cena)));
                System.out.println("Uspešno ste izimenili podatatke o ponudi!");
            } catch (NepravilanUnosPodatakaException e) {
                System.out.println(e.getMessage()); 
            }
            catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }
        radSaDatotekama.upisUDatoteku(ponude,5);
    }
     public void obrisiPonudu(RentACar r)
    {
        r.osveziListe();
        ArrayList<Ponuda> ponude=r.getPonude();
        
        while (true) {
            
            try {
                System.out.println("\nPrikaz svih podataka:");
                if(ponude.isEmpty())
                {
                    System.out.println("\nNema ponuda za brisanje!\n");
                    break;
                }
                for (int i = 0; i < ponude.size(); i++) {
                     System.out.println((i+1)+". "+ponude.get(i)); 
                }
                System.out.print("\nOdaberite koju ponudu brišete, za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                ponude.remove(rbr-1);
                System.out.println("\nUspešno ste obrisali podatatke o ponudi!");
            } catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }
        radSaDatotekama.upisUDatoteku(ponude,5);
    }
    public void pregledRezervacija(RentACar r)
    {
        r.osveziListe();
        ArrayList<Rezervacija> rezervacije=r.getRezervacije();
        System.out.println("\nPrikaz rezervacija:");
        for (Rezervacija rez : rezervacije) {
            System.out.println(rez);
        }
    }
    public void prikazStatistike(ArrayList<Rezervacija> lista, ArrayList<Vozilo> list)
    {
        while (true) {            
            System.out.println("\nIzaberite koju vrstu statistike želite da pogledate\n1. Statistika po mesecu za rezervacije\n2. Statistika za sve rezervacije");
            try {
                System.out.print("\nUneti jednu od ponuđenih opcija: ");
                int opcija=Integer.parseInt(new Scanner(System.in).nextLine());
                switch(opcija)
                {
                    case 1: Statistika.prikazZaIzabraniMesec(lista,list);break;
                    case 2: Statistika.prikazZaSveRezervacije(lista,list);break;
                    default: System.out.println("\nPogrešna opcija!");
                }
                break;
            } catch (Exception e) {
                System.out.println("\nGreška unosa!");
            }
        }         
    }
}
