package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectAllContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().goToContactPage();
    app.logout();
  }
}
