package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteContactTests extends TestBase {

  @Test
  public void testDeleteContact() throws Exception {
    app.getNavigationHelper().goToContactPage();
    if (!app.getContactHelper().isThereIsContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Mikhail",
              "Sychev",
              "Testovich",
              "RogaAndCopyta",
              "OOO",
              "Pushkin street",
              "+77777777777",
              "test@testovich.google.org",
              "test123"));
    }
    List<ContactData> before = app.getContactHelper().getContactsList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().goToContactPage();

    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
