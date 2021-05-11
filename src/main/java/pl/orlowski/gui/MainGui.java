package pl.orlowski.gui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import pl.orlowski.gui.car.CarEditGui;
import pl.orlowski.gui.car.CarGui;
import pl.orlowski.gui.car.CarInformationGui;
import pl.orlowski.gui.car.CarListGui;
import pl.orlowski.gui.fuel.FuelGui;
import pl.orlowski.gui.owner.OwnerEditGui;
import pl.orlowski.gui.owner.OwnerGui;

public class MainGui extends AppLayout {

    public MainGui() {
        createHeader();
        createDrawer();
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

    private void createDrawer() {
        RouterLink car = new RouterLink("Car", CarGui.class);
        RouterLink carList = new RouterLink("Car list", CarListGui.class);
        RouterLink carEdit = new RouterLink("Car edit", CarEditGui.class);
        RouterLink carInformation = new RouterLink("Car information", CarInformationGui.class);
        RouterLink fuel = new RouterLink("Fuel", FuelGui.class);
        RouterLink owner = new RouterLink("Owner", OwnerGui.class);
        RouterLink ownerEdit = new RouterLink("Owner Edit", OwnerEditGui.class);

        addToDrawer(new VerticalLayout(
                car,
                carList,
                carEdit,
                carInformation,
                fuel,
                owner,
                ownerEdit
        ));
    }
}
