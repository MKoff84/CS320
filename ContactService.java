import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /** Adds a contact. The contact's ID must be unique. */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("A contact with id '" + id + "' already exists.");
        }
        contacts.put(id, contact);
    }

    /** Convenience factory to create and add a contact in one call. */
    public Contact addContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact c = new Contact(contactId, firstName, lastName, phone, address);
        addContact(c);
        return c;
    }

    /** Deletes a contact by id. Returns true if a contact was removed. */
    public boolean deleteContact(String contactId) {
        if (contactId == null) {
            return false;
        }
        return contacts.remove(contactId) != null;
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact c = getRequired(contactId);
        c.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact c = getRequired(contactId);
        c.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact c = getRequired(contactId);
        c.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact c = getRequired(contactId);
        c.setAddress(address);
    }

    /** Returns an unmodifiable view of all contacts (for inspection if needed). */
    public Map<String, Contact> getAll() {
        return Collections.unmodifiableMap(contacts);
    }

    private Contact getRequired(String contactId) {
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException("No contact with id '" + contactId + "' exists.");
        }
        return c;
    }
}