package org.vaadin.sebastian.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PersonService {

    private final static List<Person> personList = new LinkedList<>();

    private static final int MAX_COUNT = 1000;

    static {
        for (int i = 0; i < MAX_COUNT; i++) {
            personList.add(Generator.create());
        }
    }

    public static List<Person> findAll(Integer offset, Integer limit) {
        int toIndex = offset + limit < personList.size() ? offset + limit : personList.size();
        return personList.subList(offset, toIndex);
    }

    public static Integer count() {
        return MAX_COUNT * 3;
    }

    public static List<Person> findAll() {
        return personList;
    }

    private static class Generator {

        static List<String> exampleFirstNameList = Arrays.asList("Morgan", "Andrea", "Jackie", "Morgan", "Joe");
        static List<String> exampleLastNameList = Arrays.asList("Miller", "Smith", "Doe", "Jones", "Brown");
        static List<String> exampleStreetNameList = Arrays.asList("Main Street", "Glenn", "Dupont", "State Street", "New Jersey Avenue");
        static List<String> exampleCityNameList = Arrays.asList("Washington", "Springfield", "Franklin", "Greenville", "Clinton");


        static Person create() {

            Random random = new Random();
            int i = random.nextInt(5) ;
            return new Person(i % 2 == 0 ? Person.Gender.MALE : Person.Gender.FEMALE,
                    exampleLastNameList.get(i),
                    exampleFirstNameList.get(i),
                    new Address(exampleStreetNameList.get(i),
                            String.valueOf(i),
                            String.valueOf(random.nextInt(99999)),
                            exampleCityNameList.get(i)));
        }
    }
}
