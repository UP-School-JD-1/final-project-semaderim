package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Consumables.Drink;
import Consumables.Food;

public class Customer extends Thread {
    public enum CustomerState {
        WAITING, ORDERING, WAIT_ORDER, EATING, LEAVING
    }

    /**
     * Müşteri şiparişi
     */
    public Order order;
    public int id;
    public CustomerState state = CustomerState.WAITING;

    private static int customerCount;

    // Customer Constants

    public static final float PROB_WAITING = 0.3f;

    public Customer() {
        this.id = ++customerCount;
        System.out.println(this);
        SetOrder();
    }
    
    public void SetOrder() {
        List<Food> foods = new ArrayList<>();
      
        foods.add(Simulation.GetRandomFood());
        foods.add(Simulation.GetRandomFood());

        
        
    }

    @Override
    public void run() {
        try {
            while (state != CustomerState.LEAVING) {
                if (state == CustomerState.WAITING) {
                    if (Simulation.TrySitTable(this)) {
                        state = CustomerState.ORDERING;
                        System.out.println(this + " " + order);
                        Simulation.orderQueue.add(order);
                    } else {
                        if (Math.random() > PROB_WAITING) {
                            state = CustomerState.LEAVING;
                            System.out.println(this);
                            Simulation.waitingCustomers.remove(this);
                            Simulation.LeaveTable(this);
                        }
                    }
                } else if (state == CustomerState.ORDERING) {
                    Waiter waiter = Simulation.availableWaiter.poll();
                    if (waiter != null) {
                        waiter.TakeOrder(order);
                        waiter.SetState(Waiter.WaiterState.TAKING_ORDER);
                        state = CustomerState.WAIT_ORDER;
                        System.out.println(this);
                    }
                }
                switch (order.stage) {
                    case NotGiving:
                        break;
                    case AtWaiter:
                        break;
                    case AtChef:
                        break;
                    case OrderComplete:
                        state = CustomerState.EATING;
                        System.out.println(this);
                        Eat();
                        state = CustomerState.LEAVING;
                       
                        System.out.println(this);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void Eat() {
        try {
            TimeUnit.MILLISECONDS.sleep((long) order.GetTotalConsumeTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", state=" + state + "]";
    }
}
