package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void checkTheFirstCoordinate() {
    Assert.assertEquals(new Point(0,0).distance(new Point(5,0)), 5);
  }

  @Test
  public void checkTheSecondCoordinate() {
    Assert.assertEquals(new Point(5,0).distance(new Point(0,0)), 5);
  }

  @Test
  public void checkTheThirdCoordinate() {
    Assert.assertEquals(new Point(0,0).distance(new Point(0,5)), 5);
  }

  @Test
  public void checkTheFourthCoordinate() {
    Assert.assertEquals(new Point(0,5).distance(new Point(0,0)), 5);
  }
}
