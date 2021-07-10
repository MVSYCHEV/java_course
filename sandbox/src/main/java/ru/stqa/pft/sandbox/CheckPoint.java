package ru.stqa.pft.sandbox;

public class CheckPoint {
  public static void main(String[] args) {
//    check functional
//    System.out.println("Расстояние между точками равно " + Point.distance(new Point(5.0, 6.0), new Point(10.0, -11.3)));

    //check method
    System.out.println("Расстояние между точками равно " + new Point(5.0, 6.0).distance(new Point(10.0, -11.3)));
  }
}
