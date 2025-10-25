import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void constructor_and_getters_work() {
        Contact c = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("ABC123", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void id_is_required_and_max10_and_immutable() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A", "B", "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "A", "B", "1234567890", "Addr"));

        Contact c = new Contact("ID10", "A", "B", "1234567890", "Addr");
        assertEquals("ID10", c.getContactId());
        // no setter for id; compile-time enforced immutability
    }

    @Test
    void firstName_rules() {
        Contact c = new Contact("ID1", "John", "Doe", "1234567890", "Addr");
        c.setFirstName("Jane");
        assertEquals("Jane", c.getFirstName());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("ThisIsWayTooLong"));
    }

    @Test
    void lastName_rules() {
        Contact c = new Contact("ID1", "John", "Doe", "1234567890", "Addr");
        c.setLastName("Smith");
        assertEquals("Smith", c.getLastName());

        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("ThisIsWayTooLong"));
    }

    @Test
    void phone_must_be_exactly_10_digits() {
        Contact c = new Contact("ID1", "John", "Doe", "1234567890", "Addr");
        c.setPhone("0987654321");
        assertEquals("0987654321", c.getPhone());

        assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123456789"));  // 9 digits
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345678901")); // 11 digits
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345abcde")); // non-digits
    }

    @Test
    void address_rules() {
        Contact c = new Contact("ID1", "John", "Doe", "1234567890", "Addr");
        c.setAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("x".repeat(31)));
    }
}