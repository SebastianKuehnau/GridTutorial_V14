package org.vaadin.sebastian;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.sebastian.model.Person;
import org.vaadin.sebastian.model.PersonService;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Grid<Person> personGrid = new Grid<>(Person.class);
        personGrid.setItems(PersonService.findAll());

        add(personGrid);
    }
}
