package part2;


import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @author Dang Kim Khanh S3372771
 */
public class TypeOpen extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".sqlite");
    }

    @Override
    public String getDescription() {
          return ".sqlite files";  
    }
}
