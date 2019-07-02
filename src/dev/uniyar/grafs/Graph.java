package dev.uniyar.grafs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private static int id = 1;

    private int size;
    private HashMap<Integer, Point> points;

    public Graph(int size) {
        this.size = size;
        points = new HashMap<>();
        for (int i = 1; i <= size; i++)
            addPoint(new Point());
    }

    public void addPoint(Point point) {
        points.put(id++, point);
    }

    public void removePoint(int id) {
        points.remove(id);
    }

    public Point getPoint(int id) {
        return points.get(id);
    }

    public List<Point> points() {
        return new ArrayList<>(points.values());
    }

    public void swap(int a, int b) {
        Point A = points.get(a);
        Point B = points.get(b);
        points.put(a, B);
        points.put(b, A);
    }

    public int size() {
        return size;
    }

    public boolean[][] getTable() {
        boolean table[][] = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            Point I = getPoint(i+1);
            for (int j = 0; j < size; j++) {
                Point J = getPoint(j+1);
                table[i][j] = I.isLink(J);
            }
        }
        return table;
    }

    public String graficalTable() {
        StringBuilder out = new StringBuilder();
        boolean[][] table = getTable();
        out.append("  ");
        for (int i = 0; i < size; i++) {
            out.append(String.format("%2d", i+1));
        }
        out.append("\n");
        for (int i = 0; i < size; i++) {
            out.append(String.format("%2d", i + 1));
            for (int j = 0; j < size; j++) {
                out.append(String.format("%2s", table[i][j] ? "x" : " "));
            }
            out.append("\n");
        }
        return out.toString();
    }
}
