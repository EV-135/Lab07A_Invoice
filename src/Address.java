public class Address {
    private String customerName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public Address(String customerName, String streetAddress, String city, String state, String zipCode) {
        this.customerName = customerName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return customerName + "\n" +
                streetAddress + "\n" +
                city + "\n" +
                state + "\n" +
                zipCode;
    }

}
