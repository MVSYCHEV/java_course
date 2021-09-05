package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void changePasswordTest() throws Exception {
    HttpSession httpSession = app.newSession();
    app.admin().login("administrator", "root");
    app.admin().manageUsers();

    Users users = app.db().users();
    UserData userData = users.iterator().next();
    app.admin().changePassword(userData);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String conformationLink = findConfirmationLink(mailMessages, userData.getEmail());
    String newPassword = "newPassword";
    app.admin().finish(conformationLink, userData, newPassword);

    assertTrue(httpSession.login(userData.getUsername(), newPassword));
    assertTrue(httpSession.isLoggedInAs(userData.getUsername()));
  }

  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

