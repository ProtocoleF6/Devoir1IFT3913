package Code;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Rapport {
    public static void ProduceRapportPackage(String pathPackage) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {

            //PrintWriter writer = new PrintWriter("package.csv");
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("package.csv"));
            https://attacomsian.com/blog/read-write-csv-files-core-java
            writer.write("chemin,package,package_LOC,package_CLOC,package_DC");writer.newLine();
            ArrayList<ArrayList<String>> res = recursiveFCt(folder);
            for (ArrayList<String> row : res) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

        writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static ArrayList<ArrayList<String>> recursiveFCt(File folder) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (!folder.isDirectory()) {

            ArrayList<String> temp = getSringClass(folder);
            res.add(temp);
        } else {
            for (final File fileEntry : folder.listFiles()) {

                ArrayList<ArrayList<String>> newRes = recursiveFCt(fileEntry);
                for (ArrayList<String> transf : newRes) {
                    res.add(transf);
                }
            }
        }
        return res;
    }

    private static ArrayList<String
            > getSringClass(File fileEntry) {
        ArrayList<String> res = new ArrayList<>();
        res.add(fileEntry.getAbsolutePath());
        res.add(fileEntry.getName());
        res.add("" + CLOC.getnumberLineClass(fileEntry));
        res.add("" + Code.CLOC.getNbrCommentaireClass(fileEntry));
        res.add("" + CLOC.getDensityClass(fileEntry));

        return res;
    }
    private static ArrayList<String
            > getSringPackage(File fileEntry) {
        ArrayList<String> res = new ArrayList<>();
        res.add(fileEntry.getAbsolutePath());
        res.add(fileEntry.getName());
        res.add("" + CLOC.getnumberLineClass(fileEntry));
        res.add("" + Code.CLOC.getNbrCommentaireClass(fileEntry));
        res.add("" + CLOC.getDensityClass(fileEntry));

        return res;
    }

    public static void ProduceRapportClass(String pathPackage) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {
            //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
            //PrintWriter writer = new PrintWriter("classes.csv");
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("classes.csv"));
            writer.write("chemin,class,classe_LOC,classe_CLOC,classe_DC");
            writer.newLine();

            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {


                    ArrayList<String> temp = getSringClass(fileEntry);
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
