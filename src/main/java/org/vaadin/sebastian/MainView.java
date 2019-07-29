package org.vaadin.sebastian;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.JsModule;
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

//        personGrid = new Grid<>(Person.class);
//        personGrid.setColumns("name", "firstName");

        personGrid = new Grid<>();

        personGrid
                .addColumn(Person::getName)
                .setSortable(true)
                .setHeader(new Html("<i>Name</i>"));
        personGrid
                .addColumn(Person::getFirstName)
                .setSortable(true)
                .setHeader(new Html("<i>Firstname</i>"));

        genderColumn = personGrid
                .addColumn(person -> person.getGender() == Person.Gender.FEMALE ? "Ms." : "Mr.")
                .setSortable(true)
                .setWidth("200px")
                .setFrozen(true)
                .setHeader(new Html("<i>Gender</i>"));

        final Grid.Column<Person> streetColumn = personGrid
                .addColumn(person -> person.getAddress().getStreet() + " " + person.getAddress().getNo())
                .setHeader(new Html("<i>Street + No.</i>"));
        final Grid.Column<Person> cityColumn = personGrid
                .addColumn(person -> person.getAddress().getCity())
                .setHeader(new Html("<i>City</i>"));
        final Grid.Column<Person> zipColumn = personGrid
                .addColumn(person -> person.getAddress().getZip())
                .setComparator(Comparator.comparingInt(o -> Integer.valueOf(o.getAddress().getZip())))
                .setHeader(new Html("<i>Zip</i>"));

        personGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personGrid.addSelectionListener(selectionEvent -> {
            selectionEvent.getFirstSelectedItem().ifPresent(person -> {
                Notification.show(person.getName() + " is selected");
            });
        }) ;
        final List<Person> personList = PersonService.findAll();

        //provide a list of data to the component
        personGrid.setItems(personList);
        //or
        final ListDataProvider<Person> dataProvider = DataProvider.ofCollection(personList);
        personGrid.setDataProvider(dataProvider);

        dataProvider.setFilter(person -> person.getAddress() != null);
        dataProvider.setSortOrder(Person::getName, SortDirection.ASCENDING);

        add(personGrid);
    }
}
