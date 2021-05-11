package pl.orlowski.gui.owner;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Car;
import pl.orlowski.model.Owner;
import pl.orlowski.service.CarService;
import pl.orlowski.service.OwnerService;

import java.util.List;

@Route(value = "owner-edit", layout = MainGui.class)
public class OwnerEditGui extends VerticalLayout {

    public OwnerEditGui(OwnerService ownerService,
                        CarService carService) {

        List<Car> getAllCars = carService.getAllCars();

        TextField textFieldPesel = new TextField("Pesel");
        TextField textFieldFirstName = new TextField("First name");
        TextField textFieldLastName = new TextField("Last name");
        TextField textFieldPhoneNumber = new TextField("Phone number");
        TextField textFieldEmail = new TextField("Email");
        ComboBox<Car> car = new ComboBox<>("Car: ", getAllCars);


        TextField textFieldFindOwner = new TextField("Search owner");
        Button buttonFindOwner = new Button("Find owner", new Icon(VaadinIcon.SEARCH));

        buttonFindOwner.addClickListener(buttonClickEvent -> {

                String value = textFieldFindOwner.getValue();
                Owner owner = ownerService.findOwnerByPeselOrPhoneOrEmail(value);

                textFieldPesel.setValue(String.valueOf(owner.getPesel()));
                textFieldPesel.setReadOnly(true);
                textFieldFirstName.setValue(owner.getFirstName());
                textFieldFirstName.setReadOnly(true);
                textFieldLastName.setValue(owner.getLastName());
                textFieldLastName.setReadOnly(true);
                textFieldPhoneNumber.setValue(owner.getPhoneNumber());
                textFieldPhoneNumber.setReadOnly(true);
                textFieldEmail.setValue(owner.getEmail());
                textFieldEmail.setReadOnly(true);
                car.setValue(owner.getCar());
                car.setReadOnly(true);
        });

        Button buttonEditOwner = new Button("Edit", new Icon(VaadinIcon.EDIT));

        buttonEditOwner.addClickListener(buttonClickEvent -> {
            textFieldPesel.setReadOnly(false);
            textFieldFirstName.setReadOnly(false);
            textFieldLastName.setReadOnly(false);
            textFieldPhoneNumber.setReadOnly(false);
            textFieldEmail.setReadOnly(false);
            car.setReadOnly(false);
        });

        Button buttonUpdateOwner = new Button("Update", new Icon(VaadinIcon.USER));

        buttonUpdateOwner.addClickListener(buttonClickEvent -> {

            Long pesel = Long.parseLong(textFieldPesel.getValue());
            String firstName = textFieldFirstName.getValue();
            String lastName = textFieldLastName.getValue();
            String phoneNumber = textFieldPhoneNumber.getValue();
            String email = textFieldPhoneNumber.getValue();

            Owner owner = Owner.builder()
                    .pesel(pesel)
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .build();

            ownerService.save(owner);
        });

        add(textFieldFindOwner,
                buttonFindOwner,
                textFieldPesel,
                textFieldFirstName,
                textFieldLastName,
                textFieldPhoneNumber,
                textFieldEmail,
                buttonEditOwner,
                buttonUpdateOwner);
    }

}
