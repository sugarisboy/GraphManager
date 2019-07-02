package dev.uniyar.grafs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Application {

    private static final boolean DEBUG = false;

    public static void main(String[] args) {

        Graph graph = null;
        try {
            GraphReader reader = new GraphReader("Graf.txt");
            graph = reader.getGraph();
        } catch (FileNotFoundException e) {
            if (DEBUG)
                e.printStackTrace();
            System.out.println("File don't found");
        } catch (GraphReaderException e) {
            if (DEBUG)
                e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
            System.out.println("Неизвестная ошибка");
        }

        try {
            FileWriter writer = new FileWriter(new File("Out.txt"));
            writer.write(graph.graficalTable());
            writer.close();
        } catch (IOException e) {
            if (DEBUG)
                e.printStackTrace();
            System.out.println("Ошибка записи в файл!");
        }
    }
}
