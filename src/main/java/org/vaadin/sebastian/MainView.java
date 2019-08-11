package org.vaadin.sebastian;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;
import org.vaadin.sebastian.model.Person;
import org.vaadin.sebastian.model.PersonService;

import java.util.Comparator;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    final Grid.Column<Person> genderColumn;
    private final Grid<Person> personGrid;

    public MainView() {

        //Option 1: instanciate Grid with Class Type as parameter and add columns implicitly
        //personGrid = new Grid<>(Person.class);
        //personGrid.setColumns("name", "firstName");

        //Option 2: instanciate Grid with default constructor
        personGrid = new Grid<>();

        //add column for name
        personGrid
                .addColumn(Person::getName)
                .setSortable(true)
                .setHeader(new Html("<i>Name</i>"));
        //add column for firstname
        personGrid
                .addColumn(Person::getFirstName)
                .setSortable(true)
                .setHeader(new Html("<i>Firstname</i>"));

        //add column for gender
        genderColumn = personGrid
                .addColumn(person -> person.getGender() == Person.Gender.FEMALE ? "Ms." : "Mr.")
                .setSortable(true)
                .setWidth("200px")
                .setFrozen(true)
                .setHeader(new Html("<i>Gender</i>"));

        //add column for street
        final Grid.Column<Person> streetColumn = personGrid
                .addColumn(person -> person.getAddress().getStreet() + " " + person.getAddress().getNo())
                .setHeader(new Html("<i>Street + No.</i>"));
        //add column for city
        final Grid.Column<Person> cityColumn = personGrid
                .addColumn(person -> person.getAddress().getCity())
                .setHeader(new Html("<i>City</i>"));
        //add column for zip
        final Grid.Column<Person> zipColumn = personGrid
                .addColumn(person -> person.getAddress().getZip())
                .setComparator(Comparator.comparingInt(o -> Integer.valueOf(o.getAddress().getZip())))
                .setHeader(new Html("<i>Zip</i>"));

        //select only one item at one
        personGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        //show notification after select one item
        personGrid.addSelectionListener(selectionEvent -> {
            selectionEvent.getFirstSelectedItem().ifPresent(person -> {
                Notification.show(person.getName() + " is selected");
            });
        }) ;
        //fetch data from backend
        final List<Person> personList = PersonService.findAll();

        //Option A: provide a list of data to the component
        personGrid.setItems(personList);
        //or Option B: set dedicated DataProvider
        final ListDataProvider<Person> dataProvider = DataProvider.ofCollection(personList);
        personGrid.setDataProvider(dataProvider);

        //filter for address
        dataProvider.setFilter(person -> person.getAddress() != null);
        //sort for ascending name
        dataProvider.setSortOrder(Person::getName, SortDirection.ASCENDING);

        add(personGrid);
    }
}
