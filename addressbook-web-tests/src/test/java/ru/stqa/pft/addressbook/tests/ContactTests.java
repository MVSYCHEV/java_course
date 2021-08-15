package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<>();
    File photo = new File("src/test/resources/test.png");
    list.add(new Object[]{new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
            .withCompany("OOO").withAddress("Pushkin street").withHomePhone("123").withMobilePhone("+77777777777").withWorkPhone("321")
            .withEmail("test@testovich.google.org").withGroup("test123").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
            .withCompany("OOO").withAddress("Pushkin street").withHomePhone("123").withMobilePhone("+77777777777").withWorkPhone("321")
            .withEmail("test@testovich.google.org").withGroup("test123").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
            .withCompany("OOO").withAddress("Pushkin street").withHomePhone("123").withMobilePhone("+77777777777").withWorkPhone("321")
            .withEmail("test@testovich.google.org").withGroup("test123").withPhoto(photo)});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
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
