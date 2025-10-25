import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setup() {
        service = new ContactService();
    }

    @Test
    void addContact_enforces_unique_id() {
        Contact a = new Contact("ID1", "A", "A", "1111111111", "Addr A");
        Contact b = new Contact("ID1", "B", "B", "2222222222", "Addr B");

        service.addContact(a);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(b));
        assertEquals(1, service.getAll().size());
        assertTrue(service.getAll().containsKey("ID1"));
    }

    @Test
    void addContact_convenience_factory_works() {
        Contact c = service.addContact("ID2", "John", "Doe", "1234567890", "123 Main St");
        assertEquals(c, service.getAll().get("ID2"));
    }

    @Test
    void deleteContact_by_id() {
        service.addContact("ID3", "John", "Doe", "1234567890", "123 Main St");
        assertTrue(service.deleteContact("ID3"));
        assertFalse(service.deleteContact("ID3")); // already deleted
        assertFalse(service.deleteContact(null));
    }

    @Test
    void update_fields_by_id() {
        service.addContact("ID4", "John", "Doe", "1234567890", "123 Main St");

        service.updateFirstName("ID4", "Jane");
        service.updateLastName("ID4", "Smith");
        service.updatePhone("ID4", "0987654321");
        service.updateAddress("ID4", "456 Oak Ave");

        Contact c = service.getAll().get("ID4");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    void update_nonexistent_id_throws() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("NOPE", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("NOPE", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("NOPE", "1234567890"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("NOPE", "Addr"));
    }
}