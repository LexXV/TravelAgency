package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String login;
    private char[] password;
    private String name;
    private String surname;
    private Discount discount;
    private double cash;
    private String phone;
    private Role role;

    public User(String login, char[] password, String name,
                String surname, double cash, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cash = cash;
        this.phone = phone;
        this.discount = new Discount();
    }

    public User(String login, char[] password, String name,
                String surname, String phone, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.role = role;
        this.discount = new Discount();
    }

    public User(int id, String login) {
        this.id = id;
        this.login = login;
        discount = new Discount();
    }

    public User() {
        discount = new Discount();
    }

    public int getUserId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getPasswordStr() {
        return String.valueOf(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", discount=" + discount.getDiscountSize() +
                ", cash=" + cash +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Double.compare(user.getCash(), getCash()) == 0 &&
                getLogin().equals(user.getLogin()) &&
                Arrays.equals(getPassword(), user.getPassword()) &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                Objects.equals(getDiscount(), user.getDiscount()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getUserId(), getLogin(), getName(), getSurname(), getDiscount(), getCash(), getPhone(), getRole());
        result = 31 * result + Arrays.hashCode(getPassword());
        return result;
    }
}
