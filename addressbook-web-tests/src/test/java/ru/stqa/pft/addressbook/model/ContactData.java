package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String name;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String phone;
  private final String email;
  private String group;

  public ContactData(String name, String lastname, String nickname, String title, String company, String address, String phone, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String name, String lastname, String nickname, String title, String company, String address, String phone, String email, String group) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }



  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData contactData = (ContactData) o;
    return Objects.equals(name, contactData.name) && Objects.equals(lastname, contactData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname);
  }
}
