package App;

import java.io.File;
import java.io.IOException;

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

    protected void ls(String[] cmd, File wd, Boolean javitott_e) {
        File wdList[] = wd.listFiles();

        if (javitott_e) {


        } else {
            for (int i = 0; i < wdList.length; ++i) {
                System.out.println(wdList[i]);
            }
        }
    }

}
