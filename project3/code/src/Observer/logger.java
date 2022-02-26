package Observer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import Store.Store;

public class logger implements Observer, Writer, Display {
    private String content;
    private String file_name;

    public void makeTxt(String str1) {
        File file = new File(str1);
        file_name = str1;
        try {
            Files.deleteIfExists(file.toPath());
            file.createNewFile();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void update(String content1) {
        this.content = content1;
        display();
        Write();
    }

    //source: https://stackoverflow.com/questions/9961292/write-to-text-file-without-overwriting-in-java
    //writes to text file
    public void Write() {
        //write it
        //FileUtils.
        File file = new File(file_name);

        try {
            FileWriter scribe = new FileWriter(file, true);
            BufferedWriter bufferedScribe = new BufferedWriter(scribe);
            bufferedScribe.write(content);
            bufferedScribe.newLine();
            bufferedScribe.flush();
        } catch (Exception e) {
            System.err.println(e);
        }

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














