package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;

public interface Observer {
    /**
     * get the current file.
     * 
     * @return return the current file
     */
    File getCurrentFile();

    /**
     * setting the current file.
     * 
     * @param filename
     *                     the file to open
     */
    void setCurrentFile(String filename);

    /**
     * get the path of the current file.
     * 
     * @return path referring to the current file
     */
    String getPath();

    /**
     * write content to the current file.
     * 
     * @param content
     *                    the thing you want to write
     * 
     */
    void writeFiles(String content);

}
