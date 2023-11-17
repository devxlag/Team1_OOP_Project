package team1project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ZipFile implements AbstractFile{
    private String fileName;
    private ArrayList<String> submittedFileNames;
    private List<AbstractFile> contents;
    private String path;

    public ZipFile(String fileName) {
        this.fileName = fileName;
        this.contents = new ArrayList<>();
        this.submittedFileNames = new ArrayList<>();
    }

    public void addComponent(AbstractFile component) {
        contents.add(component);
    }

    @Override
    public void display() {
        System.out.println("Zip File: " + fileName);
        for (AbstractFile component : contents) {
            component.display();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public List<AbstractFile> getChildren() {
        return contents;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<String> getSubmittedFileNames(){
        return submittedFileNames;
    }
}








