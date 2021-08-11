package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
            .withCompany("OOO").withAddress("Pushkin street").withHomePhone("123").withMobilePhone("+77777777777").withWorkPhone("321").withEmail("test@testovich.google.org").withGroup("test123");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    app.logout();
  }

  @Test
  public void testBadContactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("Mikhail'").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
            .withCompany("OOO").withAddress("Pushkin street").withMobilePhone("+77777777777").withEmail("test@testovich.google.org").withGroup("test123");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
    app.logout();
  }
}
