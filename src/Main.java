import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args)
            throws Exception
    {

        Interpreter I=new Interpreter();
        List<String> records=I.findMatches(I.readFileAsString("C:\\test.txt"));
        int i=0;
        for(String e:records){
            if(i>2)break;
            System.out.println(e);
            I.parseRecord(I.getRecords(),e);
            i++;
        }
    }


}
