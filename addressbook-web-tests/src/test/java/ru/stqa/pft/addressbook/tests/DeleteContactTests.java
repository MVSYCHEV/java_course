package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeleteContactTests extends TestBase{

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
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().goToContactPage();
    app.logout();
  }
}
