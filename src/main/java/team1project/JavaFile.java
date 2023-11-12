package team1project;

class JavaFile implements AbstractFile {
    private String fileName;
    private StringBuilder contents;  // Using StringBuilder to efficiently concatenate strings

    public JavaFile(String fileName) {
        this.fileName = fileName;
        this.contents = new StringBuilder();
    }

    public void appendContents(String newContents) {
        this.contents.append(newContents);
    }

    @Override
    public void display() {
        System.out.println("Java File: " + fileName + "\nContents:\n" + contents.toString());
    }
}
