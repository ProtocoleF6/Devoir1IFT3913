import Code.*;

import java.io.File;

//testcommit
public class Main {
    public static void main(String args[])  //static method
    {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
       //System.out.println(CLOC.getDensityPackage(new File("./src/Code")));
        //Rapport.ProduceRapportClass("./src",true);
        //Rapport.ProduceRapportClass("/Users/leo/Documents/1_Code_Project/jfreechart/src",true);
        Rapport.ProduceRapportPackage("/Users/leo/Documents/1_Code_Project/jfreechart/src",true);
        //System.out.println(WMC.getWMCClass(new File("./src/Code/CLOC.java")));
    }
}
