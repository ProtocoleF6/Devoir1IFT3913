import Code.*;

import java.io.File;

public class Main {
    public static void main(String args[])  //static method
    {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
       //System.out.println(CLOC.getDensityPackage(new File("./src/Code")));
        Rapport.ProduceRapportClass("./src/Code");
    }
}
