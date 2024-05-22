package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOSearcher {

    public static boolean search(String word, String... paths)
    {

        boolean exists = false;

        for (String path: paths)
        {

            File file = new File(path);
            try {
                
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.useDelimiter("\\Z").next().contains(word)) 
                {

                    exists = true;
                    fileScanner.close();
                    break;

                }
                else
                {

                    fileScanner.close();

                }
            
            }

            catch (FileNotFoundException e){}

        }

        return exists;

    }

}
