package ru.stqa.pft.rest.Tests;

import org.testng.SkipException;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    Set<Issue> issues = RestHelper.getIssues();
    Issue issue = issues.stream().filter(Issue -> Objects.equals(issueId, Issue.getId())).findFirst().get();
    return !issue.getStateName().equals("Closed");
  }
}
