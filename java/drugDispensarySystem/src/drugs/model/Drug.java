package drugs.model;

import java.time.LocalDate;

public class Drug {
    private String name;
    private int quantity;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;

    public Drug() {

    }

    public Drug(String name, int quantity, String manufactureDate, String expiryDate ) {
        this.name = name;
        this.quantity = quantity;
        this.manufactureDate =  LocalDate.parse(manufactureDate);
        this.expiryDate = LocalDate.parse(expiryDate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }


    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

}
