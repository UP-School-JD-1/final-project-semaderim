package Entities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Chef extends Thread {
    public enum ChefState {
        IDLE, COOKING
    }

    public ChefState state = ChefState.COOKING;
    public int id;

    /**
     * Şefin hazırlamakta olduğu siparişlerin tutulduğu kuyruk
     */
    public Queue<Order> orders = new LinkedList<>();

    /**
     * Şefin hazırlamakta olduğu sipariş
     */
    public Order order;

    private static int chefCount;

    public Chef() {
        this.id = ++chefCount;
        System.out.println(this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (order == null) {
                order = orders.poll();
                if (order != null) {
                    try {
                        SetState(ChefState.COOKING);
                        System.out.println(this);
                        order.SetStage(Order.OrderStages.AtChef);
                        TimeUnit.MILLISECONDS.sleep((long) order.GetTotalPrepareTime());
                        order.SetStage(Order.OrderStages.OrderComplete);
                        SetState(ChefState.IDLE);
                        System.out.println("Chef [id=" + id + ", state= PREPARED ORDER " + order + "]");
                        order = null;
                        Simulation.availableChef.add(this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

   
    public void AddOrder(Order _order) {
        orders.add(_order);
    }

    
    public void SetState(ChefState _state) {
        state = _state;
    }

   
    public boolean IsIdle() {
        return state == ChefState.IDLE;
    }

    @Override
    public String toString() {
        return "Chef [id=" + id + ", state=" + state + "]";
    }
}
