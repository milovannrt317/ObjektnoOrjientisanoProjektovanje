package korisnik;

import izuzetci.NepravilanUnosPodatakaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import ostalo.ProveraUnosa;
import rentAcar.RentACar;
import rentAcar.radSaDatotekama;

public abstract class Korisnik {
    protected int id;
    protected String ime;
    protected String prezime;
    protected String korsnickoIme;
    protected String lozinka;

    public Korisnik(int id, String ime, String prezime, String korsnickoIme, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korsnickoIme = korsnickoIme;
        this.lozinka = sifruj(lozinka);
    }
    private String sifruj(String lozinka)
     {
         String sifra="";
         for (Character c: lozinka.toCharArray()) {
             if (Character.isDigit(c))
                sifra+=c;
             else
                sifra+=(char)(((int)c+12-97)%26+97);
         }
         return sifra;
     }
     private String deSifruj()
     {
         String sifra="";
         for (Character c: lozinka.toCharArray()) {
             if (Character.isDigit(c))
                 sifra+=c;
             else
                sifra+=(char)(((int)c-12-97+26)%26+97);
         }
         return sifra;
     }
    public static int idMaker(ArrayList<Kupac> lista)
    {
        int ID=lista.size()+1;
        lista.sort((x,y)->Integer.compare(x.id, y.id));
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).id==ID)
            {
                ID++;
                i--;
                if(i<0)
                    i=0;
            }
        }
        return ID;
    }
    @Override
    public String toString() {
        return "ID: "+id+" "+ime + " " + prezime;
    }
    
    public static void kreiranjeNaloga(ArrayList<Kupac> kupci,RentACar r)
    {
        Scanner inp=new Scanner(System.in);
        r.osveziListe();
        ArrayList<Kupac> kupciCekanje=radSaDatotekama.citanjeIzDatoteke(2);
        kupci.addAll(kupciCekanje);
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
                kupciCekanje.add(new Kupac(0, Ime, Prezime, korIme, loz, jmbg, datRodj, tel));
                
                radSaDatotekama.upisUDatoteku(kupciCekanje,2);
                System.out.println("\nVaš nalog je uspešno dodat na listi čekanja koju odobrava admin!");
                
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

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorsnickoIme() {
        return korsnickoIme;
    }

    public void setKorsnickoIme(String korsnickoIme) {
        this.korsnickoIme = korsnickoIme;
    }

    public String getLozinka() {
        return deSifruj();
    }

    public void setLozinka(String lozinka) {
        this.lozinka = sifruj(lozinka);
    }
    
}
