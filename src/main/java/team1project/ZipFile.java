package team1project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ZipFile implements AbstractFile{
    private String fileName;
    private List<AbstractFile> contents;

    public ZipFile(String fileName) {
        this.fileName = fileName;
        this.contents = new ArrayList<>();
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
}








