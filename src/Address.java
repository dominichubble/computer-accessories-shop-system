

public class Address {
    private int houseNumber;
    private String postcode;
    private String city;

    /**
     * Constructs a new Address with the specified attributes.
     *
     * @param houseNumber House number of the address.
     * @param postcode    Postcode of the address.
     * @param city        City of the address.
     */

    public Address(int houseNumber, String postcode, String city) {
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String toString() {
        return houseNumber + " " + postcode + " " + city;
    }



}
