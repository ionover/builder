package org.example;

public class PersonBuilder {

    private String name;
    private String surname;
    private Integer age;
    private String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше 0");
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (name == null) {
            throw new IllegalStateException("Имя обязательно для указания");
        }
        if (surname == null) {
            throw new IllegalStateException("Фамилия обязательна для указания");
        }

        if (age != null && address != null) {
            return new Person(name, surname, age, address);
        } else if (age != null) {
            return new Person(name, surname, age);
        } else {
            return new Person(name, surname);
        }
    }
}
