package pl.orlowski.gui.report;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.orlowski.gui.MainGui;
import pl.orlowski.model.Fuel;
import pl.orlowski.model.Report;
import pl.orlowski.service.CarService;
import pl.orlowski.service.FuelService;
import pl.orlowski.service.ReportService;
import java.util.List;

@Route(value = "report", layout = MainGui.class)
public class ReportGui extends VerticalLayout {

    private final ReportService reportService;
    private final FuelService fuelService;

    public ReportGui(ReportService reportService, FuelService fuelService) {
        this.reportService = reportService;
        this.fuelService = fuelService;

        reportAboutCar();
    }

    public void reportAboutCar() {
        TextField textFieldGetCarByRegistration = new TextField();
        textFieldGetCarByRegistration.setPlaceholder("Registration");
        Button buttonFindCar = new Button("Get report about car", new Icon(VaadinIcon.SEARCH));

        Grid<Fuel> fuel = new Grid<>();

        fuel.addColumn(Fuel::getDateRefueling)
                .setHeader("Date");
        fuel.addColumn(Fuel::getKilometerStatus)
                .setHeader("Kilometer");
        fuel.addColumn(Fuel::getPrice)
                .setHeader("Price");
        fuel.addColumn(Fuel::getPricePerLiter)
                .setHeader("Price / L");
        fuel.addColumn(Fuel::getAmountOfFuel)
                .setHeader("Amount of Fuel");

        Grid<Report> reportTotal = new Grid<>();

        reportTotal.addColumn(Report::getKilometerStatus)
                .setHeader("Kilometer status");
        reportTotal.addColumn(Report::getAverageFuelConsumption)
                .setHeader("Average fuel consumption");
        reportTotal.addColumn(Report::getTotalAmountOfFuelConsumption)
                .setHeader("Total fuel consumption");
        reportTotal.addColumn(Report::getTotalPriceForFuel)
                .setHeader("Total price");
        reportTotal.addColumn(Report::getLastRefueling)
                .setHeader("Last Refueling");

        buttonFindCar.addClickListener(buttonClickEvent -> {
            fuel.setItems();
            List<Fuel> fuelList = fuelService.getAllFuelsByRegistration(textFieldGetCarByRegistration.getValue());
            fuel.setItems(fuelList);

            Report report = Report.builder()
                    .lastRefueling(reportService.getLastRefueling(fuelList))
                    .kilometerStatus(reportService.getKilometerStatus(fuelList))
                    .averageFuelConsumption(reportService.getAverageFuelConsumption(fuelList))
                    .totalPriceForFuel(reportService.getTotalPriceForFuel(fuelList))
                    .totalAmountOfFuelConsumption(reportService.getTotalAmountOfFuelConsumption(fuelList))
                    .build();

            reportTotal.setItems();
            reportTotal.setItems(report);

            add(fuel,
                    reportTotal);
        });

        add(textFieldGetCarByRegistration,
                buttonFindCar);
    }
}
