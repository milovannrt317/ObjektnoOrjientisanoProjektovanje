package rentAcar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import korisnik.*;
import ostalo.*;
import vozila.*;

public class radSaDatotekama {
    private static final String kupacPath="datoteke/kupac.json";
    private static final String kupacCekanjePath="datoteke/kupacCekanje.json";
    private static final String vozilaAutoPath="datoteke/vozilaAuto.json";
    private static final String vozilaMotoPath="datoteke/vozilaMoto.json";
    private static final String ponudePath="datoteke/ponude.json";
    private static final String rezervacijePath="datoteke/rezervacije.json";
    
    private static Type vratiTip(String opc)
    {
        switch(opc)
        {
            case kupacPath:
            case kupacCekanjePath:return new TypeToken<ArrayList<Kupac>>(){}.getType();
            case vozilaAutoPath:return new TypeToken<ArrayList<Automobil>>(){}.getType();
            case vozilaMotoPath:return new TypeToken<ArrayList<Motocikl>>(){}.getType();
            case ponudePath :return new TypeToken<ArrayList<Ponuda>>(){}.getType();
            case rezervacijePath:return new TypeToken<ArrayList<Rezervacija>>(){}.getType();
        }
        return null;
    }
    private static String vratiPath(int opc)
    {
        switch(opc)
        {
            case 1:return kupacPath;
            case 2:return kupacCekanjePath;
            case 3:return vozilaAutoPath;
            case 4:return vozilaMotoPath;
            case 5:return ponudePath;
            case 6:return rezervacijePath;
        }
        return null;
    }
    public static  ArrayList citanjeIzDatoteke(int opc)
    {
        try {
            Gson gson=new Gson();
            JsonReader jr=new JsonReader(new FileReader(vratiPath(opc)));
            ArrayList tmp=gson.fromJson(jr, vratiTip(vratiPath(opc)));
            jr.close();
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public static void upisUDatoteku(ArrayList p,int opc)
    {
        try {
            Gson gson=new Gson();
            BufferedWriter bw=new BufferedWriter(new FileWriter(vratiPath(opc)));
            bw.write(gson.toJson(p));
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
