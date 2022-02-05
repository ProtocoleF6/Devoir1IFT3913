package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Rapport {
    public static void ProduceRapportPackage(String pathPackage) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {
            PrintWriter writer = new PrintWriter("package.csv");

            StringBuilder sb = new StringBuilder();
            sb.append("chemin,class,classe_LOC,classe_CLOC,classe_DC\n");
            ArrayList<String> res =recursiveFCt(folder);
            for(String row : res){
                sb.append(row);
            }

            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    private static ArrayList<String> recursiveFCt(File folder) {
        ArrayList<String> res=new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!folder.isDirectory()) {


                String resStringClass = getSringClass(fileEntry);
                resStringClass = folder.getAbsolutePath() + "," + resStringClass + "\n";
                res.add(resStringClass);

            } else {
                ArrayList<String> newRes=recursiveFCt(fileEntry);
                for(String transf:newRes){
                    res.add(transf);
                }
            }
        }
        return res;
    }

    private static String getSringClass(File fileEntry) {
        String res = "";

        String name = fileEntry.getName();
        String Loc = "" + CLOC.getnumberLineClass(fileEntry);
        String CLOCStr = "" + Code.CLOC.getNbrCommentaireClass(fileEntry);
        String DC = "" + CLOC.getDensityClass(fileEntry);
        res = name + "," + Loc + "," + CLOCStr + "," + DC;
        return res;
    }

    public static void ProduceRapportClass(String pathPackage) {

        File folder = new File(pathPackage);        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        try {
            //https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
            PrintWriter writer = new PrintWriter("classes.csv");

            StringBuilder sb = new StringBuilder();
            sb.append("chemin,class,classe_LOC,classe_CLOC,classe_DC\n");
            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {


                    String resStringClass = getSringClass(fileEntry);
                    resStringClass = pathPackage + "," + resStringClass + "\n";
                    sb.append(resStringClass);
                }


            }
            writer.print(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }


}
