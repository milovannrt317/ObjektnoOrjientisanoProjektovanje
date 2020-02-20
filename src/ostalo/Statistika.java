package ostalo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import vozila.Vozilo;

public class Statistika {
    public static void prikazZaIzabraniMesec(ArrayList<Rezervacija> lista, ArrayList<Vozilo> list)
    {
       
        while (true) {    
            
            ArrayList<String> datumi=new ArrayList<>();
            ArrayList<String> modeli=new ArrayList<>();

            String[] niz1=lista.stream().map(x->(x.getDanPreuzimanja())).toArray(String[]::new);
            String[] niz2=lista.stream().map(x->(x.getDanVracanja())).toArray(String[]::new);
            for (String string : niz1) {
                String[] dat=string.split("-");
                datumi.add(dat[1]);
            }
            for (String string : niz2) {
                String[] dat=string.split("-");
                datumi.add(dat[1]);
            }

            SimpleDateFormat format=new SimpleDateFormat("MM");
            SimpleDateFormat format2=new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format1=new SimpleDateFormat("MMMM");

            ArrayList<Date> mesec=new ArrayList<>();

            datumi.stream().distinct().forEach((t) -> {
                try {
                    mesec.add(format.parse(t));
                } catch (ParseException ex) {
                }
            }); 
        
            
            System.out.println("\nPrikaz meseca za izbor statistike:");
            for (int i = 0; i < mesec.size(); i++) {
                System.out.println((i+1)+". "+format1.format(mesec.get(i)));
            }
            System.out.println("\nIzabrati mesec za koji zelite da vidite statistiku:");
            try {
                 
                 if(mesec.isEmpty())
                {
                    System.out.println("\nNema meseca za prikaz statistike!\n");
                    break;
                }
               
                System.out.print("\nOdaberite mesec za prikaz statistike, za kraj unesite \"0\": ");
                int rbr=Integer.parseInt(new Scanner(System.in).nextLine());
                if(rbr==0)
                    break;
                int ukupno=0;
                for (Rezervacija rezervacija : lista)
                        if(format1.format(format2.parse(rezervacija.getDanPreuzimanja())).compareTo(format1.format(mesec.get(rbr-1)))==0 || format1.format(format2.parse(rezervacija.getDanVracanja())).compareTo(format1.format(mesec.get(rbr-1)))==0)
                            ukupno++;

                for (Vozilo vozilo : list) {
                    int br=0;
                    for (Rezervacija rezervacija : lista) {
                        if(vozilo.getId()==rezervacija.getIdAutomobila()&&(format1.format(format2.parse(rezervacija.getDanPreuzimanja())).compareTo(format1.format(mesec.get(rbr-1)))==0 || format1.format(format2.parse(rezervacija.getDanVracanja())).compareTo(format1.format(mesec.get(rbr-1)))==0))
                            br++;
                    }
                    modeli.add("ID:"+vozilo.getId()+" "+vozilo.getModel()+"-"+br);
                }
                System.out.println("\nPrikaz:");
                for (String string : modeli) {
                    String[] n=string.split("-");
                    double procenat=(Double.parseDouble(n[1])/ukupno)*100;
                    if(procenat!=0)
                        System.out.printf("%s %.2f%%\n",n[0],procenat);
                }
            } catch (Exception e) {
                System.err.println("\nGreška unosa!");
            }
        }
    }
    public static void prikazZaSveRezervacije(ArrayList<Rezervacija> lista, ArrayList<Vozilo> list)
    {
        System.out.println("\nStatistika za sve rezervacije:");
        ArrayList<String> modeli=new ArrayList<>();
        int ukupno=lista.size();
        for (Vozilo vozilo : list) {
                    int br=0;
            for (Rezervacija rezervacija : lista) {
                if(vozilo.getId()==rezervacija.getIdAutomobila())
                    br++;
            }
            modeli.add("ID:"+vozilo.getId()+" "+vozilo.getModel()+"-"+br);
        }
        System.out.println("\nPrikaz:");
        for (String string : modeli) {
            String[] n=string.split("-");
            double procenat=(Double.parseDouble(n[1])/ukupno)*100;
            if(procenat!=0)
                System.out.printf("%s %.2f%%\n",n[0],procenat);
        }
    }
}