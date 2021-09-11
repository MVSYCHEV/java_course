package ru.stqa.pft.rest.Tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = RestHelper.getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = RestHelper.createIssue(newIssue);
    System.out.println(issueId);
    Set<Issue> newIssues = RestHelper.getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testSkipOpenIssue() {
    try {
      skipIfNotFixed(1432);
    } catch (SkipException | IOException e) {
      System.out.println(e);
    }
  }
}
