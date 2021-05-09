package pl.orlowski.model;

public enum FuelType {

    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    HYBRID("Hybrid"),
    ELECTRIC("Electric"),
    LPG("Lpg");

    private final String type;

    FuelType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
