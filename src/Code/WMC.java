package Code;

import java.io.File;
import java.util.ArrayList;

public class WMC {
    public static int getWMCClass(File file){
        int res=0;
        ArrayList<String> listString=CLOC.getClassString(file);
        for(String row :listString){
            if(row.contains("){")){
                res+=1;
            }
            else if(row.contains("if")){
                res+=1;
            }
            else if(row.contains("while")){
                res++;
            }

        }
        return res;
    }
    public  static  int getWMCPackage(File folder){
        int res=0;
        //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {

            } else {
                res+=getWMCClass(fileEntry);
            }
        }
        return res;
    }
    public static  double getClassBC(File file){
        return (double)CLOC.getDensityClass(file)/(double)getWMCClass(file);
    }
    public static  double getPackageBC(File folder){
        return (double)CLOC.getDensityPackage(folder)/(double)getWMCPackage(folder);
    }


}
