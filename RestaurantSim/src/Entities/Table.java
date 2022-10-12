package Entities;


public class Table {
    public enum TableStatus {
        Free,
        Occupied
    }

    
    private TableStatus status = TableStatus.Free;

    public int id;

    private static int tableCount;

    
    public Customer customer;

    public Table() {
        this.id = ++tableCount;
    }

    
    public void Sit(Customer _customer) {
        status = TableStatus.Occupied;
        this.customer = _customer;
    }

    public void Leave() {
        status = TableStatus.Free;
        this.customer = null;
    }

   
    public boolean IsEmpty() {
        return this.status == TableStatus.Free;
    }

    
    public void SetStatus(TableStatus _status) {
        this.status = _status;
    }

    
    public TableStatus GetStatus() {
        return this.status;
    }

}
