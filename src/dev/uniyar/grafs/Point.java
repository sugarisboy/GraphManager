package dev.uniyar.grafs;

import java.util.HashSet;
import java.util.Set;

public class Point {

    private Set<Point> links;

    public Point() {
        links = new HashSet<>();
    }

    public void link(Point point) {
        links.add(point);
        point.links.add(this);
    }

    public void unlink(Point point) {
        links.remove(point);
        point.links.remove(this);
    }

    public boolean isLink(Point point) {
        return links.contains(point);
    }
}
