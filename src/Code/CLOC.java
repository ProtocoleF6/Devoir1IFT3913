package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLOC {
    public static ArrayList<String>  getClassString(File file){
        ArrayList<String> res=new ArrayList<>();
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                if(!data.equals("")){
                    res.add(data);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return res;
    }
    public static int getnumberLineClass(File file ){
       return  getClassString(file).size();
    }
    public  static  int getNumberLinePackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {

            } else {
                res+=getnumberLineClass(fileEntry);
            }
        }
        return res;
    }
    public static int getNbrCommentaireClass(File file){
        ArrayList<String> tabString=getClassString(file);
        int res=0;
        boolean inComment=false;
        for (String currentString:tabString){
            if(inComment&&!currentString.contains("*/")){
                res++;
            }
            else{
                if(currentString.contains("//")){
                    res++;
                }
            }
            if(currentString.contains("/*")||currentString.contains("/**")){
                inComment=true;
                res++;
            }
            if(currentString.contains("*/")){
                inComment=false;
                res++;
            }

        }
        return res;
    }
    public  static  int getNbrCommentairePackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {

            } else {
                res+= getNbrCommentaireClass(fileEntry);
            }
        }
        return res;
    }
    public static double getDensityClass(File file){
        return ((double) getNbrCommentaireClass(file)/(double)getnumberLineClass(file));
    }
    public static double getDensityPackage(File folder){
        return ((double)getNbrCommentairePackage(folder)/(double)getNumberLinePackage(folder));
    }

}
