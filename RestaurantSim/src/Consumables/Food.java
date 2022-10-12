package Consumables;

public class Food extends Product implements Consumable {

    public Food(String _name, float _consumeTime, float _prepareTime) {
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
        return "Food [name=" + name + ", consumeTime=" + consumeTime + ", prepareTime=" + prepareTime + "]";
    }
}
