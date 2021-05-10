package pl.orlowski.gui.fuel;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.model.Car;
import pl.orlowski.model.Fuel;
import pl.orlowski.service.CarService;
import pl.orlowski.service.FuelService;

import java.time.LocalDate;

@Route("add-fuel-report")
public class FuelGui extends VerticalLayout {

    public FuelGui(CarService carService,
                   FuelService fuelService) {

        TextField textFieldGetCarByRegistration = new TextField("Please enter car registration");
        Button buttonGetCarInformation = new Button("Find car", new Icon(VaadinIcon.SEARCH));

        TextField textFieldCarRegistration = new TextField("Car registration");
        TextField textFieldCarBrand = new TextField("Brand");
        TextField textFieldCarModel = new TextField("Model");
        textFieldCarRegistration.setReadOnly(true);
        textFieldCarBrand.setReadOnly(true);
        textFieldCarModel.setReadOnly(true);

        buttonGetCarInformation.addClickListener(buttonClickEvent -> {
            Car car = carService.getCarByRegistration(textFieldGetCarByRegistration.getValue());
            textFieldCarRegistration.setValue(car.getRegistration());
            textFieldCarBrand.setValue(car.getBrand());
            textFieldCarModel.setValue(car.getModel());
        });

        DatePicker datePickerDateRefueling = new DatePicker();
        datePickerDateRefueling.setValue(LocalDate.now());

        TextField textFieldKilometerStatus = new TextField("Kilometer status (example: 68172");
        TextField textFieldPrice = new TextField("Price (example: 215.17");
        TextField textFieldPricePerLiter = new TextField("Price per liter (example: 5.23");
        Button buttonAddFuel = new Button("Add refueling", new Icon(VaadinIcon.DROP));

        buttonAddFuel.addClickListener(buttonClickEvent -> {

            LocalDate dateRefueling = datePickerDateRefueling.getValue();
            int kilometerStatus = Integer.parseInt(textFieldKilometerStatus.getValue());
            float price = Float.parseFloat(textFieldPrice.getValue());
            float pricePerLiter = Float.parseFloat(textFieldPricePerLiter.getValue());
            Car car = carService.getCarByRegistration(textFieldCarRegistration.getValue());
            float amountOfFuel = fuelService.amountOfFuelCalculate(price, pricePerLiter);

            Fuel fuel = Fuel.builder()
                    .dateRefueling(dateRefueling)
                    .kilometerStatus(kilometerStatus)
                    .price(price)
                    .pricePerLiter(pricePerLiter)
                    .car(car)
                    .amountOfFuel(amountOfFuel)
                    .build();

            fuelService.save(fuel);
        });

        add(textFieldGetCarByRegistration,
                buttonGetCarInformation,
                textFieldCarRegistration,
                textFieldCarBrand,
                textFieldCarModel,
                datePickerDateRefueling,
                textFieldKilometerStatus,
                textFieldPrice,
                textFieldPricePerLiter,
                buttonAddFuel);
    }
}
