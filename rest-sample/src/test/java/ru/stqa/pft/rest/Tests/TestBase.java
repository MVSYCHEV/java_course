package ru.stqa.pft.rest.Tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public static Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue myIssue = issue.iterator().next();
    String stateName = myIssue.getStateName();
    return !stateName.equals("Closed");
  }
}
