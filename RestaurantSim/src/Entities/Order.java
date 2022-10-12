package Entities;

import java.util.ArrayList;
import java.util.List;

import Consumables.Drink;
import Consumables.Food;


public class Order {
    public enum OrderStages {
        NotGiving,
        AtWaiter,
        AtChef,
        OrderComplete
    }

    // Order Constraints
    /**
     * Siparişlerin maksimum yemek sayısı
     */
    public final int MAX_FOOD = 5;
    /**
     * Siparişlerin maksimum içecek sayısı
     */
    public final int MAX_DRINK = 3;

    /**
     * Sipariş sahibi
     */
    public Customer owner;

    /**
     * Siparişin aşaması
     */
    public OrderStages stage = OrderStages.NotGiving;

    public List<Food> foods = new ArrayList<>();
    public List<Drink> drinks = new ArrayList<>();

   
    
    public boolean IsComplete() {
        return stage == OrderStages.OrderComplete;
    }

    
    public boolean IsPreparing() {
        return stage == OrderStages.AtChef || stage == OrderStages.AtWaiter;
    }

    
    public float GetTotalConsumeTime() {
        float total = 0;
        for (Food food : foods) {
            total += food.consumeTime;
        }
        for (Drink drink : drinks) {
            total += drink.consumeTime;
        }
        return total;
    }

    
    public float GetTotalPrepareTime() {
        float total = 0;
        for (Food food : foods) {
            total += food.prepareTime;
        }
        for (Drink drink : drinks) {
            total += drink.prepareTime;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order [foods=" + foods + ", drinks=" + drinks + ", stage=" + stage + "]";
    }

	public void SetStage(OrderStages atwaiter) {
		// TODO Auto-generated method stub
		
	}
}
