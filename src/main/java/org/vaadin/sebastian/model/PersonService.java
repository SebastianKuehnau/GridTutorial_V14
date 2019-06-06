package org.vaadin.sebastian.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PersonService {

    private final static List<Person> personList = new LinkedList<>();

    private final static int MAX_COUNT = 1000;

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
        return MAX_COUNT;
    }

    public static List<Person> findAll() {
        return personList;
    }

    private static class Generator {

        static List<String> exampleFirstNameList = Arrays.asList("Morgan", "Andrea", "Jackie", "Morgan", "Joe");
        static List<String> exampleLastNameList = Arrays.asList("Miller", "Smith", "Doe", "Jones", "Brown", "Simpson");
        static List<String> exampleStreetNameList = Arrays.asList("Main Street", "Glenn", "Dupont", "State Street", "New Jersey Avenue", "Fake Street", "Washington Road");
        static List<String> exampleCityNameList = Arrays.asList("Washington", "Springfield", "Franklin", "Greenville", "Clinton");

        public static Person create() {

            Random random = new Random();
            int randomFirstNameIndex = random.nextInt(exampleFirstNameList.size()) ;
            int randomLastNameIndex = random.nextInt(exampleLastNameList.size()) ;
            int randomStreetIndex = random.nextInt(exampleStreetNameList.size()) ;
            int randomCityIndex = random.nextInt(exampleCityNameList.size()) ;

            return new Person(
                    randomFirstNameIndex % 2 == 0 ? Person.Gender.MALE : Person.Gender.FEMALE,
                    exampleLastNameList.get(randomLastNameIndex),
                    exampleFirstNameList.get(randomFirstNameIndex),
                    new Address(
                            exampleStreetNameList.get(randomStreetIndex),
                            String.valueOf(random.nextInt(9999)),
                            String.valueOf(random.nextInt(99999)),
                            exampleCityNameList.get(randomCityIndex)));
        }
    }
}
