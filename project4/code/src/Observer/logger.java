package Observer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import Store.Store;
//a concrete observer that
//  1) prints the updated state
//  2) records the updated state in a txt file.

//Lazy loading singleton
public class logger implements Observer, Writer, Display {
    private String content;
    //private String file_name;
    //private String file_nameS;
    private File northFile;
    private File southFile;
    private String str1;
    private static logger instance;

    //String name1="project4//logs//North//Logger-"+North_store.get_daysPassed()+".txt";
    //String name2="project4//logs//South//Logger-"+South_store.get_daysPassed()+".txt";

    /*
    *
    * */
    //Used this link to see example of lazy loading singleton
    //https://www.tutorialkart.com/java/singleton-class-in-java/
    private logger() {
        //System.out.println("Logger instantiated");
    }
    public static logger GetInstance() {
        if (instance==null){
            instance = new logger();
            System.out.print("logger instantiated");
        }
        return instance;
    }


    public void makeTxt(Store Store) {
        if (Objects.equals(Store.get_location(), "North")){
            str1 = "project4//logs//North//Logger-"+Store.get_daysPassed()+".txt";
            northFile = new File(str1);
            try {
                Files.deleteIfExists(northFile.toPath());
                northFile.createNewFile();
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            str1 = "project4//logs//South//Logger-"+Store.get_daysPassed()+".txt";
            southFile = new File(str1);
            try {
                Files.deleteIfExists(southFile.toPath());
                southFile.createNewFile();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        /*File file = new File(str1);
        file_name = str1;
        try {
            Files.deleteIfExists(file.toPath());
            file.createNewFile();
        } catch (Exception e) {
            System.err.println(e);
        }*/
    }

    public void update(String content1, Store Store) {
        this.content = content1;
        display();
        Write(Store);



    }

    //source: https://stackoverflow.com/questions/9961292/write-to-text-file-without-overwriting-in-java
    //writes to text file
    public void Write(Store Store) {
        //write it
        //FileUtils.
        //File file = new File(file_name);

        try {
            if (Store.get_location() == "North") {
                FileWriter fileWriter = new FileWriter(northFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                FileWriter fileWriter = new FileWriter(southFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            System.err.println(e);
        }



    /*
        try {
            FileWriter scribe = new FileWriter(file, true);
            BufferedWriter bufferedScribe = new BufferedWriter(scribe);
            bufferedScribe.write(content);
            bufferedScribe.newLine();
            bufferedScribe.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
        */
        /*
        try{
            FileWriter scribe=new FileWriter(file,true);
            BufferedWriter bufferedScribe= new BufferedWriter(scribe);
            bufferedScribe.write(content);
            bufferedScribe.newLine();
        }catch(Exception e){
            System.err.println(e);
        }

         */

        //System.out.println(file_name);
        //System.out.println(content);
        /*
        try{
            FileWriter scribe=new FileWriter(file,true);
            BufferedWriter bufferedScribe= new BufferedWriter(scribe);
            bufferedScribe.write(content);
            bufferedScribe.newLine();
        }catch(Exception e){
            System.err.println(e);
        }

         */

    }

    public void display() {
        System.out.println(content);
    }
}

















