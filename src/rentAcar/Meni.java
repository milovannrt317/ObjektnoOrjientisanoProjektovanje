package rentAcar;

import java.util.Scanner;
import korisnik.Administrator;
import korisnik.Korisnik;
import korisnik.Kupac;

public class Meni implements KorisnickiInterfejs{
    public static String pocetniMeni="\n1. Logovanje u sistem kao Administrator\n2. Logovanje u sistem kao Kupac\n3. Kreiranje novog naloga.\n0. Izlaz iz aplikacije";
    public static String AdminMeni="\nMeni za administratora\n1. Ažuriranje vozila\n2. Ažuriranje kupaca\n3. Ažuriranje ponuda\n4. Pregled rezervacija\n5. Prikaz statistike\n0. Izloguj se";
    public static String AdminMeniVozila="\nMeni za ažuriranje vozila\n1. Dodavanje vozila\n2. Izmena vozila\n3. Brisanje vozila\n0. Vrati se na prethodni meni";
    public static String AdminMeniKupci="\nMeni za ažuriranje kupaca\n1. Dodavanje kupaca sa liste cekanja\n2. Dodavanje kupaca\n3. Izmena kupaca\n4. Brisanje kupaca\n0. Vrati se na prethodni meni";
    public static String AdminMeniPonude="\nMeni za ažuriranje ponuda\n1. Dodavanje ponuda\n2. Izmena ponuda\n3. Brisanje ponuda\n0. Vrati se na prethodni meni";
    public static String KupacMeni="\nMeni za kupca\n1. Rezervacija vozila\n2. Pregled rezervacija\n3. Poništavanje željene rezervacije\n0. Izloguj se";
    
    @Override
    public void prikazMenija(int opcija)
    {
        switch(opcija)
        {
            case 1:System.out.println(pocetniMeni);break;
            case 2:System.out.println(AdminMeni);break;
            case 3:System.out.println(KupacMeni);break;
            case 4:System.out.println(AdminMeniVozila);break;
            case 5:System.out.println(AdminMeniKupci);break;
            case 6:System.out.println(AdminMeniPonude);break;
        }
    }
    @Override
    public void radMeni(RentACar r)
    {
        Scanner inp=new Scanner(System.in);
        Administrator admin;
        Kupac kupac;
        while (true) {   
            boolean flg=false;
            prikazMenija(1);
            System.out.print("\nOdaberite jednu od opcija: ");
            try {
                int opcija=Integer.parseInt(inp.nextLine());
                switch(opcija)
                {
                    case 1: ////////meni za Administratora
                        if ((admin=(Administrator)r.logovanje(opcija))!=null) 
                            while (true) 
                            {    
                                boolean flg1=false;
                                prikazMenija(2);
                                System.out.print("\nOdaberite jednu od opcija: ");
                                try {
                                    int opcija1=Integer.parseInt(inp.nextLine());
                                    switch(opcija1)
                                    {
                                        case 1:
                                            while (true) 
                                            {    
                                                boolean flg2=false;
                                                prikazMenija(4);  ////meni za vozila azuriranje
                                                System.out.print("\nOdaberite jednu od opcija: ");
                                                try {
                                                    int opcija2=Integer.parseInt(inp.nextLine());
                                                    switch(opcija2)
                                                    {
                                                       case 1: admin.dodajVozilo(r);break;
                                                       case 2: admin.izmeniVozilo(r);break;
                                                       case 3: admin.obrisiVozilo(r);break;
                                                       case 0:flg2=true;break;
                                                       default:System.out.println("\nNepostojeća opcija. Molim Vas unesite jednu od ponuđenih!\n");break;
                                                    }
                                                }catch(Exception e){System.out.println("\nPogrešan format unosa!");}
                                                if(flg2)
                                                    break;
                                            }
                                           break;
                                        case 2:
                                            while (true) 
                                            {    
                                                boolean flg3=false;
                                                prikazMenija(5);///////meni za kupce azuriranje
                                                System.out.print("\nOdaberite jednu od opcija: ");
                                                try {
                                                    int opcija3=Integer.parseInt(inp.nextLine());
                                                    switch(opcija3)
                                                    {
                                                       case 1: admin.dodajKupcaCekanje(r);break;
                                                       case 2: admin.dodajKupca(r);break;
                                                       case 3: admin.izmenaKupca(r);break;
                                                       case 4: admin.obrisiKupca(r);break;
                                                       case 0:flg3=true;break;
                                                       default:System.out.println("\nNepostojeća opcija. Molim Vas unesite jednu od ponuđenih!\n");break;
                                                    }
                                                }catch(Exception e){System.out.println("\nPogrešan format unosa!");}
                                                if(flg3)
                                                    break;
                                            }
                                           break;
                                        case 3:
                                            while (true) 
                                            {    
                                                boolean flg4=false;
                                                prikazMenija(6);//////meni za ponude azuriranje
                                                System.out.print("\nOdaberite jednu od opcija: ");
                                                try {
                                                    int opcija4=Integer.parseInt(inp.nextLine());
                                                    switch(opcija4)
                                                    {
                                                       case 1: admin.dodajPonudu(r);break;
                                                       case 2: admin.izmeniPonudu(r);break;
                                                       case 3: admin.obrisiPonudu(r);break;
                                                       case 0:flg4=true;break;
                                                       default:System.out.println("\nNepostojeća opcija. Molim Vas unesite jednu od ponuđenih!\n");break;
                                                    }
                                                }catch(Exception e){System.out.println("\nPogrešan format unosa!");}
                                                if(flg4)
                                                    break;
                                            }
                                           break;
                                        case 4: admin.pregledRezervacija(r);
                                            break;
                                        case 5: r.osveziListe(); admin.prikazStatistike(r.getRezervacije(),r.getVozila());
                                            break;
                                        case 0:
                                            flg1=true;break;
                                        default:
                                            System.out.println("\nNepostojeća opcija. Molim Vas unesite jednu od ponuđenih!\n");
                                            break;
                                    }
                                }
                                catch (Exception e) {
                                        System.out.println("\nPogrešan format unosa!");   
                                }
                                if(flg1)
                                    break;
                            }
                        else System.out.println("\nNema administratora sa ovim korisničkim imenom ili lozinkom!");
                                break;
                    case 2:
                        if ((kupac=(Kupac)r.logovanje(opcija))!=null) 
                            while (true) 
                            {      
                                boolean flg2=false;
                                prikazMenija(3); ////meni za kupca
                                System.out.print("\nOdaberite jednu od opcija: ");
                                try {
                                    int opcija2=Integer.parseInt(inp.nextLine());
                                    switch(opcija2)
                                    {
                                       case 1: r.osveziListe(); kupac.rezervisi(r.getPonude(),r.getVozila(),r.getRezervacije()); break;
                                       case 2: r.osveziListe(); kupac.pregledRezervacija(r.getVozila(),r.getRezervacije()); break;
                                       case 3: r.osveziListe(); kupac.ukloniRezervaciju(r.getRezervacije());break;
                                       case 0:flg2=true;break;
                                    }
                                }catch(Exception e){System.out.println("\nPogrešan format unosa!");}
                                if(flg2)
                                    break;
                            }
                        else System.out.println("\nNema kupca sa ovim korisničkim imenom ili lozinkom!");
                                break;
                    case 3:Korisnik.kreiranjeNaloga(r.getKupci(),r);break;
                    case 0:flg=true; break;
                    default:
                        System.out.println("\nNepostojeća opcija. Molim Vas unesite jednu od ponuđenih!\n");
                        break;
                }
                
                if(flg)
                    break;
            } catch (Exception e) {
                System.out.println("\nPogrešan format unosa!");   
            }
        }
    }
}
