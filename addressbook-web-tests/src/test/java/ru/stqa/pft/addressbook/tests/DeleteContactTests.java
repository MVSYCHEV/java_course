package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().editContactForm();
    app.getNavigationHelper().scrollToDown();
    app.getContactHelper().deleteSelectedContactFromEditPage();
    app.getNavigationHelper().goToContactPage();
    app.logout();
  }
}
