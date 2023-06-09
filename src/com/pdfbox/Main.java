package com.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Main {
    private static String findName(String name) throws IOException {
        PDDocument doc = PDDocument.load(new File("C:\\Users\\smart\\Desktop\\AgroBook\\ProjectForBook\\AgroBookProjectJava\\AgroBook.pdf"));
        PDFTextStripper reader = new PDFTextStripper();
        String doneName = "None \n";
        for (int i = 1; i < doc.getNumberOfPages(); i++) {
            reader.setStartPage(i);
            reader.setEndPage(i);
            String pageText = reader.getText(doc).replace("\n","").replace("\r","");
            if (pageText.contains(name)) {
                doneName = String.format("%s  %s - стр \n", name, i);
                break;
            }
        }
        doc.close();
        return doneName;
    }

    private static void writeDoneName() throws IOException{
        Path path = Paths.get("C:\\Users\\smart\\Desktop\\AgroBook\\ProjectForBook\\AgroBookProjectJava\\ListOfNames.txt");
        List<String> listOfNames = Files.readAllLines(path);
        try (FileWriter writer = new FileWriter("C:\\Users\\smart\\Desktop\\AgroBook\\ProjectForBook\\AgroBookProjectJava\\AgroBookNamesDone.txt", false)) {
            String textDone;
            String textName;
            for (String name : listOfNames) {
                if(!name.equals("None \n")) {
                    textName = (name).replace("\n", "").replace("\r", "");
                    textDone = findName(textName);
                    System.out.println(textDone);
                    writer.write(textDone);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Work Done :)");
        }
    }
    public static void main(String[] args) throws IOException {}
}