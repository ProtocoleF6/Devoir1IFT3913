package Code;

import java.io.File;
import java.util.ArrayList;

public class WMC {
    /**
     *
     * @param file classes actuel
     * @return le nombre de McCabe
     */
    public static int getWMCClass(File file){
        int res=0;
        ArrayList<String> listString=CLOC.getClassString(file);
        //pour chaque ligne, regarde si ouverture de bracket
        for(String row :listString){
            if(row.contains("){")){
                res+=1;
            }
            else if(row.contains("if")){
                res+=1;
            }
            //pour le do while
            else if(row.contains("while")){
                res++;
            }

        }
        return res;
    }

    /**
     *
     * @param folder package actuel
     * @return le nombre de WCP
     */
    public  static  int getWMCPackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        //pour chaque fichier du paquet
        for (final File fileEntry : folder.listFiles()) {
            //si nest pas un répertoire
            if (!fileEntry.isDirectory()) {
                res+=getWMCClass(fileEntry);
            }
        }
        return res;
    }

    /**
     *
     * @param file fichier actuel
     * @return le BC de la classe
     */
    public static  double getClassBC(File file){
        return CLOC.getDensityClass(file) /(double)getWMCClass(file);
    }

    /**
     *
     * @param folder répertoire du paquet
     * @return BC du paquet
     */
    public static  double getPackageBC(File folder){
        return CLOC.getDensityPackage(folder) /(double)getWMCPackage(folder);
    }


}
