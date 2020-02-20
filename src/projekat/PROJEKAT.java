package projekat;

import rentAcar.*;

public class PROJEKAT {
   
    public static void main(String[] args) {
        RentACar r=new RentACar();
        Meni m=new Meni();
        m.radMeni(r);
        r.porukaZavrsna();
    }
}
