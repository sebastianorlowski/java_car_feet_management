package pl.orlowski.gui.owner;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Owner;
import pl.orlowski.service.OwnerService;

@Route(value = "owner/add", layout = MainGui.class)
public class OwnerGui extends VerticalLayout {

    public OwnerGui(OwnerService ownerService) {

        TextField textFieldPesel = new TextField("Pesel");
        TextField textFieldFirstName = new TextField("First name");
        TextField textFieldLastName = new TextField("Last name");
        TextField textFieldPhoneNumber = new TextField("Phone number");
        TextField textFieldEmail = new TextField("Email");
        Button buttonAddOwner = new Button("Add owner", new Icon(VaadinIcon.PLUS));

        buttonAddOwner.addClickListener(buttonClickEvent -> {

            Long pesel = Long.parseLong(textFieldPesel.getValue());
            String firstName = textFieldFirstName.getValue();
            String lastName = textFieldLastName.getValue();
            String phoneNumber = textFieldPhoneNumber.getValue();
            String email = textFieldEmail.getValue();

            Owner owner = Owner.builder()
                    .pesel(pesel)
                    .firstName(firstName)
                    .lastName(lastName)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .build();

            ownerService.save(owner);

            textFieldPesel.clear();
            textFieldFirstName.clear();
            textFieldLastName.clear();
            textFieldEmail.clear();
            textFieldPhoneNumber.clear();
        });

        add(textFieldPesel,
                textFieldFirstName,
                textFieldLastName,
                textFieldPhoneNumber,
                textFieldEmail,
                buttonAddOwner);
    }
}
