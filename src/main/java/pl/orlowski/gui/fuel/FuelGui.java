package pl.orlowski.gui.fuel;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.orlowski.model.Car;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Route("add-fuel-report")
public class FuelGui extends VerticalLayout {

    private int kilometerStatus;
    private float fuelPrice;

    @ManyToOne
    @JoinColumn
    private Car car;


    public FuelGui() {
        TextField textFieldGetCarByRegistration = new TextField("Please enter car registration");
    }
}
