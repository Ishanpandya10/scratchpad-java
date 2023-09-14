package scratch;

import java.util.ArrayList;
import java.util.List;

public class TestCache {
    //Cache of Id, Customer name
    private static List<CustomerCache> customerDetailsCache = new ArrayList<>();

    public void addToCache(int id, String customerName) {
        CustomerCache customerCache = new CustomerCache(id, customerName);
        customerCache.addToList();
    }

    // id, customer name

    // id, fetch occurance

    /*public String getCustomerName(int id) {
        return customerDetailsCache.stream()
                .map()
                .filter(customerCache -> customerCache.customerId == id)
                .;
    }*/

    class CustomerCache {
        private int customerId;
        private String customerName;

        public CustomerCache(int customerId, String customerName) {
            this.customerId = customerId;
            this.customerName = customerName;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void addToList() {
            if (customerDetailsCache.size() > 100) {
                customerDetailsCache.remove(0);
            }
            customerDetailsCache.add(this);
        }
    }

}
