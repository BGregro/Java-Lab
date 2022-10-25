package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Commands {

    File dir;
	public Commands(File f) {
		this.dir=f;
	}

    protected void exit(String[] cmd) {
        System.exit(0);
    }

    protected void pwd(String[] cmd, File wd) {  
        try {
            System.out.print(wd.getCanonicalPath() + " ");
        } catch (IOException e) {
            System.out.print("Error: getCanonicalPath");
        }
        
        File flist[] = dir.listFiles();
        System.out.println(flist.length);
    }

    protected void ls(String[] cmd) {
        boolean l=false;

		if(cmd.length>1 && cmd[1].equals("-l"))
			l=true;
		
        if (!l) {
            for(File file: dir.listFiles()) {
                System.out.println(file.getName());
            }
        } else {
            for(File file: dir.listFiles()) {
                System.out.println(file.getName() + " " + file.length());
            }
        }
    }
    
    protected void mkdir(String[] cmd) {
		File newdir=new File(dir, cmd[1]);
		newdir.mkdir();
	}

    protected void cp(File file1, File file2) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file1);
        } catch (FileNotFoundException e) {
            System.out.println("Az 1. megadott file nem létezik");
            return;
        }

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file2);
        } catch (FileNotFoundException e) {
            System.out.println("A 2. megadott file nem létezik");
            return;
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        PrintWriter pr = new PrintWriter(new OutputStreamWriter(fos));

        while (true) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                return;
            }
            if (line != null) pr.println(line);
            else break;
        }
    }

    protected void head(File file, int n) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("A megadott file nem létezik");
            return;
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        for (int i = 0; i < n; ++i) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                return;
            }
            System.out.println(line);
        }
    }

    protected void cd(String[] cmd) {
		if(cmd[1].equals(".."))
			dir = dir.getParentFile();
		else
			dir = new File(dir, cmd[1]);
	}
}
