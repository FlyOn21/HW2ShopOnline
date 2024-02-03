package app.model.eshopmodel;

import app.model.interfaces.BaseModelCostCalculation;
import app.config.Config;
import app.utilseshop.Rounder;

public class DeliveryModel implements BaseModelCostCalculation {

    public double calculatePrice(int quantity, double price) {
        double deliveryPercentageCost = Config.DELIVERY_PERCENTAGE;
        double baseCost = quantity * price;
        double withDelivery = baseCost +  baseCost * deliveryPercentageCost;
        return Rounder.round(withDelivery, 2);
    }

    public static void main(String[] args) {
        DeliveryModel controller = new DeliveryModel();
        double result = controller.calculatePrice(1,1000.00);
        System.out.println(result);
    }
}
