package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  private final ContactData contact = new ContactData("Mikhail",
          "Sychev",
          "Testovich",
          "RogaAndCopyta",
          "OOO",
          "Pushkin street",
          "+77777777777",
          "test@testovich.google.org",
          "test123");

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToContactPage();
    if (!app.getContactHelper().isThereIsContact()) {
      app.getContactHelper().createContact(contact);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactsList();
    int index = before.size() - 1;
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
