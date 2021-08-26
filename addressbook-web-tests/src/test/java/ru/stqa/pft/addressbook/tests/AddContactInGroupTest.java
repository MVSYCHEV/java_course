package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class AddContactInGroupTest extends TestBase{

  private final ContactData contact = new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
          .withCompany("OOO").withAddress("Pushkin street").withMobilePhone("+77777777777").withEmail("test@testovich.google.org");


  @BeforeClass
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(contact);
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contactWithoutGroups().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(contact);
    }
  }

  @Test
  public void testAddContactToGroup() {
    ContactData before = app.db().getContactWithoutGroup();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.goTo().contactPage();
    app.contact().selectContactWithoutGroup(before);
    app.contact().selectGroup(group);
    app.contact().addContactToGroup();
    ContactData after = app.db().idContact(before.getId());
    assertTrue(after.getGroups().contains(group));
    verifyContactListInUi();
  }
}

