package Code;

import java.io.File;
import java.util.ArrayList;

public class WMC {
    /**
     * @param file classes actuel
     * @return le nombre de McCabe
     */
    public static int getWMCClass(File file) {
        int res = 0;
        ArrayList<String> listString = CLOC.getClassString(file);
        String ouvreture=CLOC.getProperties("ouverture");
        String ifs=CLOC.getProperties("if");
        String whiles=CLOC.getProperties("while");
        //pour chaque ligne, regarde si ouverture de bracket
        for (String row : listString) {
            if (row.contains(ouvreture)) {
                res += 1;
            } else if (row.contains(ifs)) {
                res += 1;
            }
            //pour le do while
            else if (row.contains(whiles)) {
                res++;
            }

        }
        return res;
    }

    /**
     * @param folder package actuel
     * @return le nombre de WCP
     */
    public static int getWMCPackage(File folder) {
        int res = 0;//si nest pas un répertoire
        if (!folder.isDirectory()) {
            res += getWMCClass(folder);
        }
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        //pour chaque fichier du paquet
        else {
            for (final File fileEntry : folder.listFiles()) {
                res += getWMCPackage(fileEntry);
            }
        }
        return res;
    }

    /**
     * @param file fichier actuel
     * @return le BC de la classe
     */
    public static double getClassBC(File file) {
        double wmc=getWMCClass(file);
        if(wmc==0.0){
            return 0;
        }
        return CLOC.getDensityClass(file) / wmc;
    }

    /**
     * @param folder répertoire du paquet
     * @return BC du paquet
     */
    public static double getPackageBC(File folder) {
        double wcp=getWMCPackage(folder);
        if(wcp==0.0){
            return 0;
        }
        return CLOC.getDensityPackage(folder) / wcp;
    }


}
