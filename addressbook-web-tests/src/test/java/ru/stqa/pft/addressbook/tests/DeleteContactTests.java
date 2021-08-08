package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class DeleteContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
              .withCompany("OOO").withAddress("Pushkin street").withPhone("+77777777777").withEmail("test@testovich.google.org").withGroup("test123"));
    }
  }

  @Test
  public void testDeleteContact() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().contactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
