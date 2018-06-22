package com.udr013;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) {

        int dirCount = 0;
        int fileCount = 0;

        File dir = new File("/home/udr013/bla", "folder"); //not truly new, we don't create we just say we want to use that dir
        if (dir.exists() && dir.isDirectory()) { //
            System.out.println(dir.listFiles().length);
        }
        System.out.println(dir.getParent());


        String[] filearray = dir.list();
        System.out.println("this is the first name of StringArray from dir.list(): " + filearray[0]);
        File[] files = dir.listFiles();


        for (File file : files) {
            System.out.println(file.getName() + ": isDir: " + file.isDirectory() + "  is file: " + file.isFile() + " is absolute: " + file.isAbsolute());
            if (file.isFile()) {
                fileCount++;
            }
            if (file.isDirectory()) {
                dirCount++;

                System.out.println("/n this dir contains:");

                File[] subfiles = file.listFiles();
                for (File sub : subfiles) {
                    System.out.println("****" + sub.getName());
                }
            }

            System.out.println("this folder contains: " + fileCount + " files and " + dirCount + " directories");

        }

        File dirs = new File("/home/udr013/bla");

        try {
            File newdir = new File(dirs, "newfolder");
            //mkdir will actually create it, if you want to, returns false if already exists
            System.out.println(" a new dir was created: " + newdir.mkdir());

            System.out.println(newdir.getPath());
            System.out.println(newdir.getAbsolutePath());
            File file = new File(newdir, "newText.txt");
            System.out.println(file.createNewFile()); //this method throws IOException so try-catch, false if exists
        } catch (IOException io) {
            System.out.println("IOException was thrown");
//				throw io;
        }
    }
}
