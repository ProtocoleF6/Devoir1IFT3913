package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLOC {
    /**
     * @param file fichier a lire
     * @return tableau avec chaque ligne du fichier
     */
    public static ArrayList<String> getClassString(File file) {
        ArrayList<String> res = new ArrayList<>();
        try {
            //scan le fichier
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //enlève les lignes vides
                if (!data.equals("")) {
                    res.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param file fichier actuel
     * @return nombre ligne fichier
     */
    public static int getnumberLineClass(File file) {
        return getClassString(file).size();
    }

    /**
     * @param folder paquet actuel
     * @return nombre ligne du paquet
     */
    public static int getNumberLinePackage(File folder) {
        int res = 0;
        //si est une classe
        if (!folder.isDirectory()) {
            res += getnumberLineClass(folder);
        } else {
            //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
            for (final File fileEntry : folder.listFiles()) {
                //fonction recursive
                res+=getNumberLinePackage(fileEntry);
            }
        }
        return res;
    }

    /**
     * @param file fichier actuel
     * @return nombre de ligne de commentaire
     */
    public static int getNbrCommentaireClass(File file) {
        ArrayList<String> tabString = getClassString(file);
        String finMultiligne="*/";
        String debutMultiligne="/*";
        String uneLigne="//";
        String javadocCom="/**";
        int res = 0;
        //si est dans un commentaire multiligne
        boolean inComment = false;
        //pour chaque ligne de code
        for (String currentString : tabString) {
            //fin de commentaire multiligne
            if (inComment && !currentString.contains(finMultiligne)) {
                res++;
            } else {
                //commentaire un ligne
                if (currentString.contains(uneLigne)) {
                    res++;
                }
            }
            //debut de commentaire multilgine
            if (currentString.contains(debutMultiligne) || currentString.contains(javadocCom)) {
                inComment = true;
                res++;
            }//fin commentaire multiple
            if (currentString.contains(finMultiligne)) {
                inComment = false;
                res++;
            }

        }
        return res;
    }

    /**
     * @param folder répertoire courant
     * @return nombre de commentaire pour un paquer
     */
    public static int getNbrCommentairePackage(File folder) {
        int res = 0;//si est une classe
        //ajoute le resultat
        if (!folder.isDirectory()) {
            res += getNbrCommentaireClass(folder);
        } else {
            //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
            for (final File fileEntry : folder.listFiles()) {
                //appel fonction recursive
                res += getNbrCommentairePackage(fileEntry);
            }

        }
        return res;
    }

    /**
     * @param file classe actuel
     * @return la densite de commentaire de la classe
     */
    public static double getDensityClass(File file) {
        return ((double) getNbrCommentaireClass(file) / (double) getnumberLineClass(file));
    }

    /**
     * @param folder répertoire actuel
     * @return densite de commentaire d'un paquet
     */
    public static double getDensityPackage(File folder) {
        double nbrComment=getNbrCommentairePackage(folder);
        double nbrLine=getNumberLinePackage(folder);
        return (nbrComment/nbrLine);
    }

}
