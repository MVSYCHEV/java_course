package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().editContactForm();
    app.getContactHelper().fillContactForm(new ContactData(
            "Mikhail",
            "Sychev",
            "Testovich",
            "RogaAndCopyta",
            "OOO",
            "Pushkin street",
            "+77777777777",
            "test@testovich.google.org",
            null),
            false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    app.logout();
  }
}
