package pl.orlowski.gui.owner;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.service.CarService;
import pl.orlowski.service.OwnerService;

import java.util.List;

@Route(value = "/owner-list", layout = MainGui.class)
public class OwnerListGui extends VerticalLayout {

    public OwnerListGui(OwnerService ownerService) {
        TreeGrid<Owner> owner = new TreeGrid<>();

        List<Owner> allOwners = ownerService.getAllOwners();

        owner.setItems(allOwners);
        owner.addHierarchyColumn(Owner::getPesel)
                .setHeader("PESEL");

        owner.addColumn(Owner::getFirstName)
                .setHeader("First name");
        owner.addColumn(Owner::getLastName)
                .setHeader("Last name");
        owner.addColumn(Owner::getPhoneNumber)
                .setHeader("Phone");
        owner.addColumn(Owner::getEmail)
                .setHeader("Email");

        add(owner);
    }
}
