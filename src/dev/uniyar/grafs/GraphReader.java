package dev.uniyar.grafs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {

    private Scanner scan;
    private Graph graph;

    public GraphReader(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException(String.format("File %s don't found", path));
        scan = new Scanner(file);
        if (!scan.hasNextInt())
            throw new GraphReaderException("File is not graph");

        int size = scan.nextInt();
        graph = new Graph(size);


        while (scan.hasNextLine())
            readCommand();
    }

    public Graph getGraph() {
        return graph;
    }

    private void readCommand() {
        String cmd = scan.next();
        int id = readId();

        switch (cmd) {
            case "A":
                link(id);
                break;
            case "R":
                unlink(id);
                break;
            case "I":
                removeIncidental(id);
                break;
            case "F":
                linkAll(id);
                break;
            case "C":
                swap(id);
        }
    }

    private void link(int id) {
        int second = readId();
        Point a = graph.getPoint(id);
        Point b = graph.getPoint(second);
        a.link(b);
    }

    private void unlink(int id) {
        int second = readId();
        Point a = graph.getPoint(id);
        Point b = graph.getPoint(second);
        a.unlink(b);
    }

    private void linkAll(int id) {
        Point point = graph.getPoint(id);
        graph.points().forEach(point::link);
    }

    private void removeIncidental(int id) {
        Point point = graph.getPoint(id);
        graph.points().forEach(p -> p.unlink(point));
    }

    private void swap(int id) {
        graph.swap(id, readId());
    }

    private int readId() {
        if (scan.hasNextInt()) {
            int id = scan.nextInt();
            if (id < 1 || id > graph.size())
                throw new GraphReaderException(String.format("Invalid point with id: %d", id));
            return id;
        } else
            throw new GraphReaderException("Integer read exception");
    }
}
