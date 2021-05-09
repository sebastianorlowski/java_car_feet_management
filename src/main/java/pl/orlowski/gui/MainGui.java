package pl.orlowski.gui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainGui extends AppLayout {

    public MainGui() {
        createHeader();
    }

    private void createHeader() {
        H2 name = new H2("Car Fuel Calculator");
        name.addClassName("name");

        Anchor logout = new Anchor("logout", "Logout");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), name, logout);
        header.expand(name);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }
}
