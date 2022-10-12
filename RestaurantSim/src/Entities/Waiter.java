package Entities;


public class Waiter extends Thread {
    public enum WaiterState {
        IDLE, TAKING_ORDER
    }

    public WaiterState state = WaiterState.IDLE;
    public Order order;
    public int id;

    private static int waiterCount;

    public Waiter() {
        this.id = ++waiterCount;
        System.out.println(this);
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName());
        while (!Thread.currentThread().isInterrupted()) {
            if (order != null) {
                Chef chef = Simulation.availableChef.poll();
                if (chef != null) {
                    chef.AddOrder(order);
                    order = null;
                    SetState(WaiterState.IDLE);
                    System.out.println(this);
                    Simulation.availableWaiter.add(this);
                }
            }
        }
    }

    
    public void TakeOrder(Order _order) {
        order = _order;
        order.SetStage(Order.OrderStages.AtWaiter);
        SetState(WaiterState.TAKING_ORDER);
        System.out.println(this);
    }

    
    public void SetState(WaiterState _state) {
        state = _state;
    }

    
    public boolean IsIdle() {
        return state == WaiterState.IDLE;
    }

    @Override
    public String toString() {
        return "Waiter [id=" + id + ", state=" + state + "]";
    }
}
