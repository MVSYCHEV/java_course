package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneAndAddressesTests extends TestBase{

  @Test
  public void testContactPhone() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(merge(contactInfoFromEditForm.getHomePhone(),
            contactInfoFromEditForm.getMobilePhone(), contactInfoFromEditForm.getWorkPhone())));
  }

  @Test
  public void testContactAddress() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test
  public void testContactEmail() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(merge(contactInfoFromEditForm.getEmail(), contactInfoFromEditForm.getEmail2(), contactInfoFromEditForm.getEmail3())));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }

  private String merge(String firstObject, String secondObject, String thirdObject) {
    return Arrays.asList(firstObject, secondObject, thirdObject)
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneAndAddressesTests::cleaned).collect(Collectors.joining("\n"));
  }
}
