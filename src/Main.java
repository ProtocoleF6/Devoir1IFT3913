import Code.*;

import java.io.File;

//testcommit
public class Main {
    public static void main(String args[])  //static method
    {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
       //System.out.println(CLOC.getDensityPackage(new File("./src/Code")));
        Rapport.ProduceRapportPackage("./src",true);
        //System.out.println(WMC.getWMCClass(new File("./src/Code/CLOC.java")));
    }
}
