package top.rongxiaoli.plugins.DailyFortune;


import java.io.File;
import java.io.FilenameFilter;

public class UploadFileFilter implements FilenameFilter {
    private final String[] types;
    public UploadFileFilter(String... typeStrings) {
        types = typeStrings;
    }
    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return {@code true} if and only if the name should be
     * included in the file list; {@code false} otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {
        boolean accept = false;
        for (String str :
                types) {
            if (name.endsWith(str)) {
                accept = true;
                break;
            }
        }
        return accept;
    }
}
