package Consumables;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    public List<Drink> drinks = new ArrayList<>();
    public List<Food> foods = new ArrayList<>();

    public ProductList() {
        drinks.add(new Drink("Kola", 5000, 8000));
        drinks.add(new Drink("Çay", 1000, 1000));
        foods.add(new Food("Tavuk", 15000, 8000));
        foods.add(new Food("Salata", 1000, 1000));
    }
}
