package ostalo;

import izuzetci.NepravilanUnosPodatakaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import korisnik.Kupac;

public class ProveraUnosa {
    public static void proveraImePrezime(String inp) throws NepravilanUnosPodatakaException
    {
        if(Character.isUpperCase(inp.charAt(0))==false)
            throw new NepravilanUnosPodatakaException("Unešeno ime/prezime ne sadrži veliko prvo slovo!");
        for (char c : inp.toCharArray()) {
            if(Character.isLetter(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešeno ime/prezime ne sadrži samo slova!");
            }
        }
        for (int i = 1; i < inp.length(); i++) {
            if (Character.isLowerCase(inp.charAt(i))==false) {
                throw new NepravilanUnosPodatakaException("Unešeno ime/prezime ne sadrži ostala mala slova sem prvog slova!");
            } 
        }
    }
    public static void proveraKorIme(String inp, ArrayList<Kupac> lista) throws NepravilanUnosPodatakaException
    {
        for (Kupac kupac : lista) {
            if(kupac.getKorsnickoIme().compareTo(inp)==0)
                throw new NepravilanUnosPodatakaException("Unešeno korisni�?ko ime već postoji u bazi!");
        }
        if(Character.isLetter(inp.charAt(0))==false)
            throw new NepravilanUnosPodatakaException("Unešeno korisni�?ko ime ne sadrži slovo kao prvi karakter !");
        for (char c : inp.toCharArray()) {
            if(Character.isLetterOrDigit(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešeno korisni�?ko ime ne sadrži samo slova ili brojeve!");
            }
        }
        for (int i = 0; i < inp.length(); i++) {
            if (Character.isLowerCase(inp.charAt(i))==false && Character.isLetter(inp.charAt(i))==true) {
                throw new NepravilanUnosPodatakaException("Unešeno korisni�?ko ime ne sadrži sva mala slova!");
            } 
        }
    }
    public static void proveraLozinke(String inp) throws NepravilanUnosPodatakaException
    {
        if(Character.isLetter(inp.charAt(0))==false)
            throw new NepravilanUnosPodatakaException("Unešeno lozinka ne sadrži slovo kao prvi karakter !");
        for (char c : inp.toCharArray()) {
            if(Character.isLetterOrDigit(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešeno lozinka ne sadrži samo slova ili brojeve!");
            }
        }
        for (int i = 0; i < inp.length(); i++) {
            if (Character.isLowerCase(inp.charAt(i))==false && Character.isLetter(inp.charAt(i))==true) {
                throw new NepravilanUnosPodatakaException("Unešeno lozinka ne sadrži sva mala slova!");
            } 
        }
    }
    public static void proveraTelefona(String inp, ArrayList<Kupac> lista) throws NepravilanUnosPodatakaException
    {
        for (Kupac kupac : lista) {
            if(kupac.getTelefon().compareTo(inp)==0)
                throw new NepravilanUnosPodatakaException("Unešeni telefon već postoji u bazi!");
        }
         
        for (char c : inp.toCharArray()) {
            if(Character.isDigit(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešeni telefon ne sadrži samo brojeve!");
            }
        }
        if(inp.startsWith("06")==false)
            throw new NepravilanUnosPodatakaException("Unešeni telefon mora po�?injati sa \"06\"!");
        if(inp.length()<9 || inp.length()>10 )
            throw new NepravilanUnosPodatakaException("Unešeni telefon mora sadržati 9 ili 10 cifara!");
       
    }
    public static void proveraDatuma(String date) throws NepravilanUnosPodatakaException
    {
        String[] datumi=date.split("-");
        if(datumi.length!=3)
            throw new NepravilanUnosPodatakaException("Datum ne sadrži sve delove!");
        for (String string : datumi) {
            for (char c : string.toCharArray()) {
                if(Character.isDigit(c)==false)
                    throw new NepravilanUnosPodatakaException("Neki deo datuma ne sadrži cifre!");
            }
        }
        if(datumi[0].length()>2 || datumi[1].length()>2 || datumi[2].length()!=4 || Integer.parseInt(datumi[0])>31 || Integer.parseInt(datumi[1])>12 || Integer.parseInt(datumi[0])==0 || Integer.parseInt(datumi[1])==0)
            throw new NepravilanUnosPodatakaException("Nepravilan format datuma!");
        int godina=Integer.parseInt(datumi[2]);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String trenutna=dtf.format(now);
        int trenutnaGodina=Integer.parseInt(trenutna);
        if(trenutnaGodina-godina<18)
            throw new NepravilanUnosPodatakaException("Korisnik nije punoletan!");
    }
    public static void proveraFormataDatuma(String date) throws NepravilanUnosPodatakaException
    {
        String[] datumi=date.split("-");
        if(datumi.length!=3)
            throw new NepravilanUnosPodatakaException("Datum ne sadrži sve delove!");
        for (String string : datumi) {
            for (char c : string.toCharArray()) {
                if(Character.isDigit(c)==false)
                    throw new NepravilanUnosPodatakaException("Neki deo datuma ne sadrži cifre!");
            }
        }
       if(datumi[0].length()>2 || datumi[1].length()>2 || datumi[2].length()!=4 || Integer.parseInt(datumi[0])>31 || Integer.parseInt(datumi[1])>12 || Integer.parseInt(datumi[0])==0 || Integer.parseInt(datumi[1])==0)
            throw new NepravilanUnosPodatakaException("Nepravilan format datuma!");
    }
    public static void proveraJmbg(String inp,String dat, ArrayList<Kupac> lista) throws NepravilanUnosPodatakaException
    {
        for (Kupac kupac : lista) {
            if(kupac.getJmbg().compareTo(inp)==0)
                throw new NepravilanUnosPodatakaException("Unešeni JMBG već postoji u bazi!");
        }
        if(inp.length()!=13 )
            throw new NepravilanUnosPodatakaException("Unešeni JMBG mora sadržati 13 cifara!");
        for (char c : inp.toCharArray()) {
            if(Character.isDigit(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešeni JMBG ne sadrži samo brojeve!");
            }
        }
        String datum=dat.replace("-","");
        String datumRodj="";
        for (int i = 0; i < datum.length(); i++) {
            if(i!=4)
                datumRodj+=datum.charAt(i); 
        }
        if(inp.startsWith(datumRodj)==false)
            throw new NepravilanUnosPodatakaException("Unešeni JMBG mora po�?injati sa datumom rođenja!");
    }
    public static void proveraGodiste(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isDigit(c)==false)
                throw new NepravilanUnosPodatakaException("Unešeno godište ne sadrži samo brojeve!");

            int godiste=Integer.parseInt(inp);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
            LocalDateTime now = LocalDateTime.now();
            String trenutna=dtf.format(now);
            int trenutnaGodina=Integer.parseInt(trenutna);
            if(godiste<1990 || godiste>trenutnaGodina)
                throw new NepravilanUnosPodatakaException("Unešeno godište mora biti veće ili jednako od 1990 a manje ili jednako od trenutne godine!");
    }
    public static void proveraKubikaza(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isDigit(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena kubikaža ne sadrži samo brojeve!");

            int kub=Integer.parseInt(inp);
            
            if(kub<1000 || kub>10000)
                throw new NepravilanUnosPodatakaException("Unešena kubikaža mora biti veća ili jednako od 1000 a manje ili jednako od 10000!");
    }
    public static void proveraMenjac(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena vrsta menja�?a ne sadrži samo slova!");
            
            if(inp.compareTo("automatski")!=0 && inp.compareTo("manuelni")!=0)
                throw new NepravilanUnosPodatakaException("Unešena vrsta menja�?a mora biti jedna od ponuđenih (manuelni ili automatski)!");
    }
    public static void proveraGorivo(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena vrsta goriva ne sadrži samo slova!");
            
            if(inp.compareTo("benzin")!=0 && inp.compareTo("dizel")!=0)
                throw new NepravilanUnosPodatakaException("Unešena vrsta goriva mora biti jedna od ponuđenih (benzin ili dizel)!");
    }
    public static void proveraPogon(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena vrsta pogona ne sadrži samo slova!");
            
            if(inp.compareTo("prednji")!=0 && inp.compareTo("zadnji")!=0)
                throw new NepravilanUnosPodatakaException("Unešena vrsta pogona mora biti jedna od ponuđenih (prednji ili zadnji)!");
    }
    public static void proveraKaroserija(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena vrsta karoserije ne sadrži samo slova!");
            
            if(inp.compareTo("limuzina")!=0 && inp.compareTo("kupe")!=0 && inp.compareTo("kabriolet")!=0)
                throw new NepravilanUnosPodatakaException("Unešena vrsta karoserije mora biti jedna od ponuđenih (limuzina, kupe ili kabriolet)!");
    }
    public static void proveraBrVrata(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isDigit(c)==false)
                throw new NepravilanUnosPodatakaException("Unešeni broj vrata ne sadrži samo brojeve!");

            int brVrata=Integer.parseInt(inp);
            
            if(brVrata<2 || brVrata>5)
                throw new NepravilanUnosPodatakaException("Unešeni broj vrata mora biti veća ili jednako od 2 a manje ili jednako od 5!");
    }
    public static void proveraMarka(String inp) throws NepravilanUnosPodatakaException
    {
        if(Character.isUpperCase(inp.charAt(0))==false)
            throw new NepravilanUnosPodatakaException("Unešena marka ne sadrži veliko prvo slovo!");
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena marka ne sadrži samo slova!");

        for (int i = 1; i < inp.length(); i++) 
            if (Character.isLowerCase(inp.charAt(i))==false) 
                throw new NepravilanUnosPodatakaException("Unešena marka ne sadrži ostala mala slova sem prvog slova!");
    }
    public static void proveraModel(String inp) throws NepravilanUnosPodatakaException
    {
        if(Character.isLetter(inp.charAt(0))==false)
            throw new NepravilanUnosPodatakaException("Unešen model ne sadrži slovo kao prvi karakter !");
        for (char c : inp.toCharArray()) 
            if(Character.isLetterOrDigit(c)==false)
                throw new NepravilanUnosPodatakaException("Unešen model ne sadrži samo slova ili brojeve!");

        for (int i = 0; i < inp.length(); i++) 
            if (Character.isLowerCase(inp.charAt(i))==false && Character.isLetter(inp.charAt(i))==true) 
                throw new NepravilanUnosPodatakaException("Unešen model ne sadrži sva mala slova!");
        
    }
    public static void proveraTip(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isLetter(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena vrsta motocikla ne sadrži samo slova!");
            
            if(inp.compareTo("sport")!=0 && inp.compareTo("kros")!=0 && inp.compareTo("enduro")!=0 && inp.compareTo("coper")!=0)
                throw new NepravilanUnosPodatakaException("Unešena vrsta motocikla mora biti jedna od ponuđenih (sport, kros, enduro ili coper)!");
    }
     public static void proveraKubikazaMoto(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray())
            if(Character.isDigit(c)==false)
                throw new NepravilanUnosPodatakaException("Unešena kubikaza ne sadrži samo brojeve!");

            int kub=Integer.parseInt(inp);
            if(kub<50 || kub>2000)
                throw new NepravilanUnosPodatakaException("Unešena kubikaža mora biti veća ili jednako od 50 a manje ili jednako od 2000!");
    }
    public static void proveraCena(String inp) throws NepravilanUnosPodatakaException
    {
        for (char c : inp.toCharArray()) {
            if(Character.isDigit(c)==false)
            {
                throw new NepravilanUnosPodatakaException("Unešena cena ne sadrži samo brojeve!");
            }
        }
        if(Integer.parseInt(inp)<1000 || Integer.parseInt(inp)>10000)
            throw new NepravilanUnosPodatakaException("Unešena cena mora biti u opsegu od 1000 do 10000!");
    }
    public static void proveraRezKp(int id, ArrayList<Rezervacija> lista) throws NepravilanUnosPodatakaException
    {
        for (Rezervacija rez : lista) {
            if(rez.getIdKupca()==id)
                throw new NepravilanUnosPodatakaException("Kupac koga želite da obrišete ima rezervaciju!");
        }
    }
    public static void proveraRezVz(int id, ArrayList<Rezervacija> lista) throws NepravilanUnosPodatakaException
    {
        for (Rezervacija rez : lista) {
            if(rez.getIdAutomobila()==id)
                throw new NepravilanUnosPodatakaException("Vozilo koje želite da obrišete ima rezervaciju!");
        }
    }
}
