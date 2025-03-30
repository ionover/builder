package org.example;

import java.util.OptionalInt;
import java.util.Objects;

public class Person {

    private final String name;
    private final String surname;
    private Integer age; // null означает, что возраст неизвестен
    private String address;

    public Person(String name, String surname) {
        this.name = Objects.requireNonNull(name, "Имя не может быть null");
        this.surname = Objects.requireNonNull(surname, "Фамилия не может быть null");
        this.age = null;
        this.address = null;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        setInitialAge(age);
    }

    public Person(String name, String surname, int age, String address) {
        this(name, surname, age);
        this.address = address;
    }

    private void setInitialAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше 0");
        }
        this.age = age;
    }

    public boolean hasAge() {
        return age != null;
    }

    public boolean hasAddress() {
        return address != null && !address.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age != null ? OptionalInt.of(age) : OptionalInt.empty();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        } else {
            System.out.println("Возраст неизвестен — невозможно увеличить");
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                (hasAge() ? ", age=" + age : "") +
                (hasAddress() ? ", address='" + address + '\'' : "") +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder builder = new PersonBuilder();
        builder.setSurname(this.getSurname());
        builder.setAddress(this.getAddress());
        builder.setAge(0); // новорождённый
        return builder;
    }

}
