package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData(
            "Mikhail",
            "Sychev",
            "Testovich",
            "RogaAndCopyta",
            "OOO",
            "Pushkin street",
            "+77777777777",
            "test@testovich.google.org"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
    app.logout();
  }
}
