package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToContactPage();
    ContactData contact = new ContactData("Mikhail",
            "Sychev",
            "Testovich",
            "RogaAndCopyta",
            "OOO",
            "Pushkin street",
            "+77777777777",
            "test@testovich.google.org",
            "test123");
    if (!app.getContactHelper().isThereIsContact()) {
      app.getContactHelper().createContact(contact);
    }
    List<ContactData> before = app.getContactHelper().getContactsList();
    app.getContactHelper().editContactForm(before.size() - 1);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();

    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
