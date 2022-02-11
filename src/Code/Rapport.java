package Code;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Rapport {
    /**
     * @param pathPackage path vers le fichier du package
     * @param withWMC     si le rapport doit généré WCP
     */
    public static void ProduceRapportPackage(String pathPackage, boolean withWMC) {

        File folder = new File(pathPackage);
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {

            //écrit dans packages.csv
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("packages.csv"));
            //https://attacomsian.com/blog/read-write-csv-files-core-java
            if (withWMC) {
                writer.write("chemin,package,package_LOC,package_CLOC,package_DC,WCP,paquet_BC");
            } else {
                writer.write("chemin,package,package_LOC,package_CLOC,package_DC");
            }

            writer.newLine();
            //appel la fonction recursivr sur le dossier
            ArrayList<ArrayList<String>> res = recursiveFCt(folder, folder.getName(), withWMC);
            //prend tous les rangés et les écrits dans le fichier
            for (ArrayList<String> row : res) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * fonction récursive sur tout les fichiers/dossier
     *
     * @param folder  dossier actuel
     * @param name    nom du package actuel
     * @param withWMC si on doit mettre le WCP
     * @return
     */
    private static ArrayList<ArrayList<String>> recursiveFCt(File folder, String name, boolean withWMC) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        //si est une classe
        if (folder.isDirectory()) {
            //si est un répertoire
            ArrayList<String> temp = getSringPackage(folder, name, withWMC);
            res.add(temp);
            //appel la fonction récursive
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {

                    ArrayList<ArrayList<String>> newRes = recursiveFCt(fileEntry, fileEntry.getName(), withWMC);
                    //prend le resultat fonction recurvise et ajoute dans un tableau
                    for (ArrayList<String> transf : newRes) {
                        res.add(transf);
                    }
                }
            }
        }

        return res;
    }

    /**
     * @param fileEntry classe entree
     * @param withWMC   si doit mettre WCM
     * @return tableau avec les valeurs de la classe
     */
    private static ArrayList<String
            > getSringClass(File fileEntry, boolean withWMC) {
        ArrayList<String> res = new ArrayList<>();
        //ajoute les informations nécéssaire
        res.add(fileEntry.getAbsolutePath());
        res.add(fileEntry.getName());
        res.add("" + CLOC.getnumberLineClass(fileEntry));
        res.add("" + Code.CLOC.getNbrCommentaireClass(fileEntry));
        res.add("" + CLOC.getDensityClass(fileEntry));
        if (withWMC) {
            res.add("" + WMC.getWMCClass(fileEntry));
            res.add("" + WMC.getClassBC(fileEntry));
        }
        return res;
    }

    /**
     * @param fileEntry   classe d'entree
     * @param packageName nom du package de la classe
     * @param withWMC     WCP du paquet
     * @return tableau des valeurs de la classes
     */
    private static ArrayList<String
            > getSringPackage(File fileEntry, String packageName, boolean withWMC) {
        ArrayList<String> res = new ArrayList<>();
        res.add(fileEntry.getAbsolutePath());
        res.add(packageName);
        res.add("" + CLOC.getNumberLinePackage(fileEntry));
        res.add("" + CLOC.getNbrCommentairePackage(fileEntry));
        res.add("" + CLOC.getDensityPackage(fileEntry));
        if (withWMC) {
            res.add("" + WMC.getWMCPackage(fileEntry));
            res.add("" + WMC.getPackageBC(fileEntry));
        }
        return res;
    }

    /**
     *
     * @param folder repertoire actuel
     * @param withWMC si on doit avoir le WMC
     * @return array de chaque classes avec leur valeur
     */
    public static ArrayList<ArrayList<String>> recursifFctClass(File folder, boolean withWMC) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ArrayList<ArrayList<String>> newRes = new ArrayList<>();
        if (folder.isDirectory()) {
            //pour chaque file dans le fodler
            for (final File file : folder.listFiles()) {
                newRes = recursifFctClass(file, withWMC);
                //ajoute le resultat
                for (ArrayList<String> transf : newRes) {
                    res.add(transf);
                }
            }

        } else {
            //si cest classe, va chercher info
            res.add(getSringClass(folder, withWMC));
        }

        return res;
    }

    /**
     * @param pathPackage path de la classe
     * @param withWMC     si WMC
     */
    public static void ProduceRapportClass(String pathPackage, boolean withWMC) {

        File folder = new File(pathPackage);
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {
            //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
            //écrit dans classes.csv
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("classes.csv"));
            if (withWMC) {
                writer.write("chemin,class,classe_LOC,classe_CLOC,classe_DC,WMC,class_DC");
            } else {
                writer.write("chemin,class,classe_LOC,classe_CLOC,classe_DC");
            }
            writer.newLine();
            //va chercher toute les classes
            ArrayList<ArrayList<String>> res = recursifFctClass(folder, withWMC);
            //prend tous les rangés et les écrits dans le fichier
            for (ArrayList<String> row : res) {
                writer.write(String.join(",", row));
                writer.newLine();
            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
