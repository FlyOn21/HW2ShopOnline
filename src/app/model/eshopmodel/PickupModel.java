package app.model.eshopmodel;

import app.model.interfaces.BaseModelCostCalculation;
import app.utilseshop.Rounder;

public class PickupModel implements BaseModelCostCalculation {

    public double calculatePrice(int quantity, double price) {
        return Rounder.round(quantity * price, 2);
    }
}
