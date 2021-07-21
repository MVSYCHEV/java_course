package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);

  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void scrollToDown() {
    JavascriptExecutor js = (JavascriptExecutor) wd;
    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public void goToContactPage() {
    click(By.linkText("home"));
  }
}
