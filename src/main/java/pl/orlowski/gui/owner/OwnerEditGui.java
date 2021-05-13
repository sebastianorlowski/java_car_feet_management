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

        List<String> getCarWithoutOwner = carService.getCarsWithoutOwnerByRegistration();

        TextField textFieldId = new TextField("ID");
        TextField textFieldPesel = new TextField("Pesel");
        TextField textFieldFirstName = new TextField("First name");
        TextField textFieldLastName = new TextField("Last name");
        TextField textFieldPhoneNumber = new TextField("Phone number");
        TextField textFieldEmail = new TextField("Email");
        ComboBox<String> comboBoxCar = new ComboBox<>("Car: ", getCarWithoutOwner);

        TextField textFieldFindOwner = new TextField("Search owner");
        Button buttonFindOwner = new Button("Find owner", new Icon(VaadinIcon.SEARCH));
        Button buttonSetDefaultCar = new Button("Set default Car", new Icon(VaadinIcon.CAR));

        buttonFindOwner.addClickListener(buttonClickEvent -> {

                String value = textFieldFindOwner.getValue();
                Owner owner = ownerService.getOwnerByPeselOrPhoneOrEmail(value);
                Car car = owner.getCar();

                textFieldId.setValue(String.valueOf(owner.getId()));
                textFieldId.setReadOnly(true);
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
                comboBoxCar.setValue(car.getRegistration());
                comboBoxCar.setReadOnly(true);
        });

        Button buttonEditOwner = new Button("Edit", new Icon(VaadinIcon.EDIT));

        buttonEditOwner.addClickListener(buttonClickEvent -> {
            textFieldPesel.setReadOnly(false);
            textFieldFirstName.setReadOnly(false);
            textFieldLastName.setReadOnly(false);
            textFieldPhoneNumber.setReadOnly(false);
            textFieldEmail.setReadOnly(false);
            comboBoxCar.setReadOnly(false);
        });

        Button buttonUpdateOwner = new Button("Update", new Icon(VaadinIcon.USER));

        buttonUpdateOwner.addClickListener(buttonClickEvent -> {

            Long id = Long.parseLong(textFieldId.getValue());
            Long pesel = Long.parseLong(textFieldPesel.getValue());
            String firstName = textFieldFirstName.getValue();
            String lastName = textFieldLastName.getValue();
            String phoneNumber = textFieldPhoneNumber.getValue();
            String email = textFieldEmail.getValue();
            String registration = comboBoxCar.getValue();
            Car car = carService.getCarByRegistration(registration);

            Owner owner = Owner.builder()
                    .id(id)
                    .pesel(pesel)
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .car(car)
                    .build();

            ownerService.edit(owner);

            textFieldPesel.clear();
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldPhoneNumber.clear();
            textFieldEmail.clear();
            comboBoxCar.clear();
            comboBoxCar.setItems(getCarWithoutOwner);
        });

        buttonSetDefaultCar.addClickListener(buttonClickEvent -> {
            Long id = Long.parseLong(textFieldId.getValue());
            Long pesel = Long.parseLong(textFieldPesel.getValue());
            String firstName = textFieldFirstName.getValue();
            String lastName = textFieldLastName.getValue();
            String phoneNumber = textFieldPhoneNumber.getValue();
            String email = textFieldEmail.getValue();
            Car car = carService.getCarByRegistration("0");

            Owner owner = Owner.builder()
                    .id(id)
                    .pesel(pesel)
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .car(car)
                    .build();

            ownerService.edit(owner);

            textFieldPesel.clear();
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldPhoneNumber.clear();
            textFieldEmail.clear();
            comboBoxCar.clear();
        });

        add(textFieldFindOwner,
                buttonFindOwner,
                textFieldId,
                textFieldPesel,
                textFieldFirstName,
                textFieldLastName,
                textFieldPhoneNumber,
                textFieldEmail,
                comboBoxCar,
                buttonEditOwner,
                buttonUpdateOwner,
                buttonSetDefaultCar);
    }
}
