import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Address address;
    private List <LineItem> lineItems;


    public Invoice(Address address) {
        this.address = address;
        lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
      lineItems.add(item);
    }

    public Address getAddress() {
        return address;
    }

    public List <LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotal() {
        double total = 0;

        for (LineItem item : lineItems) {
            total += item.getLineTotal();
        }
        return total;
    }
}
