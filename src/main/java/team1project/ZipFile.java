package team1project;

import java.util.ArrayList;
import java.util.List;

/**
 * The ZipFile class implements the AbstractFile interface and represents a ZIP file.
 * It contains a file name, a list of submitted file names, a list of contents (Java files or other ZIP files),
 * and a path indicating the location of the ZIP file.
 */
class ZipFile implements AbstractFile {

    private String fileName;
    private ArrayList<String> submittedFileNames;
    private List<AbstractFile> contents;
    private String path;

    /**
     * Constructs a ZipFile object with the specified file name.
     *
     * @param fileName The name of the ZIP file.
     */
    public ZipFile(String fileName) {
        this.fileName = fileName;
        this.contents = new ArrayList<>();
        this.submittedFileNames = new ArrayList<>();
    }

    public ZipFile(){
        this.contents = new ArrayList<>();
        this.submittedFileNames = new ArrayList<>();
    }

    /**
     * Adds a component (Java file or ZIP file) to the list of contents.
     *
     * @param component The component to add.
     */
    public void addComponent(AbstractFile component) {
        contents.add(component);
    }

    /**
     * Displays information about the ZIP file, including its name and the contents of its components.
     */
    @Override
    public void display() {
        System.out.println("Zip File: " + fileName);
        for (AbstractFile component : contents) {
            component.display();
        }
    }

    /**
     * Gets the name of the ZIP file.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the list of components (Java files or ZIP files) contained in the ZIP file.
     *
     * @return The list of components.
     */
    public List<AbstractFile> getChildren() {
        return contents;
    }

    /**
     * Sets the path of the ZIP file.
     *
     * @param path The path to set.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the path of the ZIP file.
     *
     * @return The path of the ZIP file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the list of submitted file names associated with the ZIP file.
     *
     * @return The list of submitted file names.
     */
    public ArrayList<String> getSubmittedFileNames() {
        return submittedFileNames;
    }
}
