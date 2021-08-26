package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertFalse;

public class DeleteContactFromGroupTest extends TestBase {

  private final ContactData contact = new ContactData().withName("Mikhail").withLastname("Sychev").withNickname("Testovich").withTitle("RogaAndCopyta")
          .withCompany("OOO").withAddress("Pushkin street").withMobilePhone("+77777777777").withEmail("test@testovich.google.org");


  @BeforeMethod
  public void ensurePreconditionsSorted() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(contact);
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contactWithGroups().size() == 0) {
      ContactData before = app.db().getContactWithoutGroup();
      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();
      app.goTo().contactPage();
      app.contact().selectContactWithoutGroup(before);
      app.contact().selectGroup(group);
      app.contact().addContactToGroup();
    }
  }

  @Test
  public void testDeleteContactFromTheGroup() {
    ContactData before = app.db().getContactWithGroup();
    GroupData group = before.getGroups().iterator().next();
    app.goTo().contactPage();
    app.contact().getGroupData(group);
    app.contact().selectContactWithOutGroup(before);
    app.contact().removeContactFromGroup();
    ContactData after = app.db().idContact(before.getId());
    assertFalse(after.getGroups().contains(group));
    verifyContactListInUi();
  }
}
