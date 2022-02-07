package Code;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Rapport {
    public static void ProduceRapportPackage(String pathPackage, boolean withWMC) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {

            //PrintWriter writer = new PrintWriter("package.csv");
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("package.csv"));
            https:
//attacomsian.com/blog/read-write-csv-files-core-java
            if (withWMC) {
                writer.write("chemin,package,package_LOC,package_CLOC,package_DC,WCP,paquet_BC");
            } else {
                writer.write("chemin,package,package_LOC,package_CLOC,package_DC");
            }

            writer.newLine();
            ArrayList<ArrayList<String>> res = recursiveFCt(folder, folder.getName(), withWMC);
            for (ArrayList<String> row : res) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static ArrayList<ArrayList<String>> recursiveFCt(File folder, String name, boolean withWMC) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (!folder.isDirectory()) {

            ArrayList<String> temp = getSringPackage(folder, name, withWMC);
            res.add(temp);
        } else {
            for (final File fileEntry : folder.listFiles()) {

                ArrayList<ArrayList<String>> newRes = recursiveFCt(fileEntry, folder.getName(), withWMC);
                for (ArrayList<String> transf : newRes) {
                    res.add(transf);
                }
            }
        }
        return res;
    }

    private static ArrayList<String
            > getSringClass(File fileEntry, boolean withWMC) {
        ArrayList<String> res = new ArrayList<>();
        res.add(fileEntry.getAbsolutePath());
        res.add(fileEntry.getName());
        res.add("" + CLOC.getnumberLineClass(fileEntry));
        res.add("" + Code.CLOC.getNbrCommentaireClass(fileEntry));
        res.add("" + CLOC.getDensityClass(fileEntry));
        if (withWMC) {
            res.add("" + WMC.getWMCClass(fileEntry));
            res.add("" + WMC.getWMCClass(fileEntry));
        }
        return res;
    }

    private static ArrayList<String
            > getSringPackage(File fileEntry, String packageName, boolean withWMC) {
        ArrayList<String> res = new ArrayList<>();
        res.add(fileEntry.getAbsolutePath());
        res.add(packageName);
        res.add("" + CLOC.getnumberLineClass(fileEntry));
        res.add("" + Code.CLOC.getNbrCommentaireClass(fileEntry));
        res.add("" + CLOC.getDensityClass(fileEntry));
        if (withWMC) {
            res.add("" + WMC.getWMCClass(fileEntry));
            res.add("" + WMC.getWMCClass(fileEntry));
        }
        return res;
    }

    public static void ProduceRapportClass(String pathPackage, boolean withWMC) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {
            //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
            //PrintWriter writer = new PrintWriter("classes.csv");
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("classes.csv"));
            if (withWMC) {
                writer.write("chemin,class,classe_LOC,classe_CLOC,classe_DC,WMC,class_DC");
            } else {
                writer.write("chemin,class,classe_LOC,classe_CLOC,classe_DC");
            }

            writer.newLine();

            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {


                    ArrayList<String> temp = getSringClass(fileEntry, withWMC);
                    writer.write(String.join(",", temp));
                    writer.newLine();
                }


            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
