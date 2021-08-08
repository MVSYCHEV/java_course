package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  private final ContactData contact = new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
          .withCompany("OOO").withAddress("Pushkin street").withPhone("+77777777777").withEmail("test@testovich.google.org").withGroup("test123");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    contact.withId(modifyContact.getId());
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contact);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
