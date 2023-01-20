package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandling {


    public File checkFile(String dirPath, String fileName) throws IOException {

        File newDir = new File(dirPath);
        boolean isDirCreated = newDir.mkdir();

        if (isDirCreated) {
            System.out.println("directory " + dirPath + " is created");
        } else {
            System.out.println("directory " + dirPath + " exists");
        }

        File newFile = new File(dirPath + File.separator + fileName);
        boolean isFileCreated = newFile.createNewFile();

        if (isFileCreated) {
            System.out.println("new file " + fileName + " is created");
        } else {
            System.out.println("file " + fileName + " exists");
        }

        return newFile;
    }

    public List<String> readCookie(String dirPath, String fileName) throws IOException {

        List<String> temp = new ArrayList<>();

        FileReader fr = new FileReader(dirPath + File.separator + fileName);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while (((line = br.readLine())!= null)) {
            temp.add(line);
        }

        br.close();
        fr.close();
        
        return temp;
    }

    public String pickRandomCookie(List<String> cookieList){

        Random rand = new Random();

        String randomCookie = cookieList.get((rand.nextInt(cookieList.size())));

        return randomCookie;

    }
}
