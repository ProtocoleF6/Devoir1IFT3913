package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLOC {
    /**
     *
     * @param file fichier a lire
     * @return tableau avec chaque ligne du fichier
     */
    public static ArrayList<String>  getClassString(File file){
        ArrayList<String> res=new ArrayList<>();
        try {
            //scan le fichier
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //enlève les lignes vides
                if(!data.equals("")){
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
     *
     * @param file fichier actuel
     * @return nombre ligne fichier
     */
    public static int getnumberLineClass(File file ){
       return  getClassString(file).size();
    }

    /**
     *
     * @param folder paquet actuel
     * @return nombre ligne du paquet
     */
    public  static  int getNumberLinePackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                res+=getnumberLineClass(fileEntry);
            }
        }
        return res;
    }

    /**
     *
     * @param file fichier actuel
     * @return nombre de ligne de commentaire
     */
    public static int getNbrCommentaireClass(File file){
        ArrayList<String> tabString=getClassString(file);
        int res=0;
        //si est dans un commentaire multiligne
        boolean inComment=false;
        //pour chaque ligne de code
        for (String currentString:tabString){
            //fin de commentaire multiligne
            if(inComment&&!currentString.contains("*/")){
                res++;
            }
            else{
                //commentaire un ligne
                if(currentString.contains("//")){
                    res++;
                }
            }
            //debut de commentaire multilgine
            if(currentString.contains("/*")||currentString.contains("/**")){
                inComment=true;
                res++;
            }//fin commentaire multiple
            if(currentString.contains("*/")){
                inComment=false;
                res++;
            }

        }
        return res;
    }

    /**
     *
     * @param folder répertoire courant
     * @return nombre de commentaire pour un paquer
     */
    public  static  int getNbrCommentairePackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        for (final File fileEntry : folder.listFiles()) {
            //si est une classe
            if (!fileEntry.isDirectory()) {
                res+= getNbrCommentaireClass(fileEntry);
            }
        }
        return res;
    }

    /**
     *
     * @param file classe actuel
     * @return la densite de commentaire de la classe
     */
    public static double getDensityClass(File file){
        return ((double) getNbrCommentaireClass(file)/(double)getnumberLineClass(file));
    }

    /**
     *
     * @param folder répertoire actuel
     * @return densite de commentaire d'un paquet
     */
    public static double getDensityPackage(File folder){
        return ((double)getNbrCommentairePackage(folder)/(double)getNumberLinePackage(folder));
    }

}
