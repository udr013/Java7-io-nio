package com.udr013;

import java.io.*;
import java.util.Map;

public class KeyboardInput {

    public static void main(String[] args) {
        //System.in = A InputStream (a byteStream) a binairy stream, as we want characters we need to convert
        InputStreamReader in  = new InputStreamReader(System.in);// this will create a characterStream
        BufferedReader reader = new BufferedReader(in); //though we want to read strings we need a buffered stream

        System.out.println("Please, state your name:");
        try{
            String input = reader.readLine();
            System.out.println("You're name is:" +  input);
            System.out.println("System  says" + System.getProperties().getProperty("user.name"));
            throw new IOException();
        } catch (IOException e){
            try{
                PrintStream logStream =  new PrintStream(new FileOutputStream("logfile.txt", true));
                System.setErr(logStream);
            }catch (FileNotFoundException ef) {
                System.err.println(ef.getMessage());

            }
             Map properties = System.getProperties();

            for( Object property :  properties.entrySet()){
                System.out.println(property);
            }
            Runtime runtime = Runtime.getRuntime();
            try {
                System.out.println(runtime.exec("/usr/share/code/code")); // you could use ProcessBuilder which gives more configuration
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.runFinalization();
            System.gc();
            System.exit(-2);
        }
    }
}
