package Model;
import View.*;
import java.util.Arrays;


public class Model {
    int width;
    int height;

    Point[] points = {new Point(7,7), new Point(7,8), new Point(7,6), new Point(8,7), new Point(8,8), new Point(10,8), new Point(11,8), new Point(13,8), new Point(13,7), new Point(14,8)};
    public Model(int width, int height) {
    this.width = width;
    this.height = height;
    }

    public void update() {
        int die = 0;
        for (int i = 0; i<points.length; i++) {
            if (getGrannar(points, points[i]) < 2 || getGrannar(points, points[i]) > 3) {
                die++;
            }
        }
        int deaths = 0;
        int removed = 0;
        int[] dying = new int[die];
        for (int i = 0; i<points.length; i++) {
            //System.out.println(points[i] + " has " + getGrannar(points, points[i]) + " grannar");
            if(getGrannar(points, points[i]) < 2 || getGrannar(points, points[i]) > 3) {
                dying[deaths] = i;
                deaths++;
            }
        }
        System.out.println(Arrays.toString(dying));
        for (int i = 0; i<dying.length; i++) {
            Arrays.toString(points);
            points = removeTheElement(points, dying[i]-removed);
            removed++;
        }
        points = CheckRevive(width,height,points);
    }

    public int getGrannar(Point[] points, Point point) {
        int grannar = -1;

        for (int i = 0; i<points.length; i++) {
            if (point.getX() - points[i].getX() <= 1 && point.getX() - points[i].getX() >= -1 && point.getY() - points[i].getY() <= 1 && point.getY() - points[i].getY() >= -1) {
                grannar++;
            }
        }
        return grannar;
    }

    public Point[] CheckRevive(int width, int height, Point[] points) {
        int spawning = 0;
        int grannar = 0;
        int x;
        int y;


        for (int i = 0; i<points.length; i++) {
            for (int p = 1; p < height; p++) {
                y = p;
                x = 1;
                for (int o = 2; o < width; o++) {
                    if (x - points[i].getX() <= 1 && x - points[i].getX() >= -1 && y - points[i].getY() <= 1 && y - points[i].getY() >= -1) {
                        grannar++;
                    }
                    x = o;
                }
            }
            if (grannar == 3) {
                spawning++;
            }
            grannar = 0;
        }
        Point[] ny = new Point[points.length+spawning];
        int l = 0;
        int pixel = 0;
        for (int i = 0; i<points.length; i++) {
            for (int p = 1; p < height; p++) {
                y = p;
                x = 1;
                for (int o = 1; o < width; o++) {
                    pixel++;
                    if (x - points[i].getX() <= 1 && x - points[i].getX() >= -1 && y - points[i].getY() <= 1 && y - points[i].getY() >= -1) {
                        grannar++;
                    }
                    x = o;
                }
                if (grannar == 3) {
                    System.out.println(pixel + " has " + grannar + " grannar");
                    ny[l] = new Point(x,y);
                    l++;
                }
            }
            pixel = 0;
            grannar = 0;
        }
        for (int i = 0; i<points.length+spawning; i++) {
            ny[i+spawning] = points[i];
        }
        return ny;
    }

    public Point[] getPoints() {

        return points;
    }
    public Shape[] getShapes() {
        points = getPoints();

        return (Shape[])points;
    }

    // Function to remove the element
    public static Point[] removeTheElement(Point[] arr, int index)
    {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        Point[] anotherArray = new Point[arr.length - 1];

        // Copy the elements from starting till index
        // from original array to the other array
        System.arraycopy(arr, 0, anotherArray, 0, index);

        // Copy the elements from index + 1 till end
        // from original array to the other array
        System.arraycopy(arr, index + 1,
                anotherArray, index,
                arr.length - index - 1);

        // return the resultant array
        return anotherArray;
    }

}
