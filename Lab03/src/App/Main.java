package App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class Main {
    static File wd = new File(System.getProperty("user.dir"));

    public static void main(String[] args) throws Exception 
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Commands cmd = new Commands(new File (System.getProperty("user.dir")));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String inputList[] = line.split(" ", 0);

            if (inputList[0].equals("exit")) {
                cmd.exit(inputList);

            } else if (inputList[0].equals("pwd")) {
                cmd.pwd(inputList, wd);

            } else if (inputList[0].equals("ls")) {
                cmd.ls(inputList);

            } else if (inputList[0].equals("head")) {
                File inputFile = new File(inputList[1]);
                cmd.head(inputFile, Integer.parseInt(inputList[1]));

            } else if (inputList[0].equals("cp")) {
                File inputFile1 = new File(inputList[1]);
                File inputFile2 = new File(inputList[2]);
                cmd.cp(inputFile1, inputFile2);
            
            } else if (inputList[0].equals("mkdir")) {
                cmd.mkdir(inputList);
            }
        }
    }
}
