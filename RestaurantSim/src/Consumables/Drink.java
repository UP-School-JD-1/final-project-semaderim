package Consumables;

public class Drink extends Product implements Consumable {

    public Drink(String _name, float _consumeTime, float _prepareTime) {
        super(_name, _consumeTime, _prepareTime);
    }
    @Override
    public void Consume() {
        System.out.println(name + " consumed in " + consumeTime);

    }

    @Override
    public boolean IsConsumed() {
        return isConsumed;
    }

    @Override
    public String toString() {
        return "Drink [name=" + name + ", consumeTime=" + consumeTime + ", prepareTime=" + prepareTime + "]";
    }
}
