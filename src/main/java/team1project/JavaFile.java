package team1project;
/**
 * The JavaFile class implements the AbstractFile interface and represents a Java file.
 * It contains a file name and contents that can be appended to efficiently using a StringBuilder.
 */
class JavaFile implements AbstractFile {

    private String fileName;
    private StringBuilder contents;  // Using StringBuilder to efficiently concatenate strings

    /**
     * Constructs a JavaFile object with the specified file name.
     *
     * @param fileName The name of the Java file.
     */
    public JavaFile(String fileName) {
        this.fileName = fileName;
        this.contents = new StringBuilder();
    }

    /**
     * Appends new contents to the existing contents of the Java file.
     *
     * @param newContents The contents to append.
     */
    public void appendContents(String newContents) {
        this.contents.append(newContents);
    }

    /**
     * Displays information about the Java file, including its name and contents.
     */
    @Override
    public void display() {
        System.out.println("Java File: " + fileName + "\nContents:\n" + contents.toString());
    }

    /**
     * Gets the name of the Java file.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the contents of the Java file as a string.
     *
     * @return The contents of the Java file.
     */
    public String getContents() {
        return contents.toString();
    }
}
