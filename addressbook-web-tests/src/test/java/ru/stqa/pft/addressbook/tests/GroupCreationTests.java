package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test123", "test123", "test123"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.logout();
  }
}
