package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroupTests extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereIsGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test123", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.logout();
  }
}
