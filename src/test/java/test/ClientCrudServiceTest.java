package test;

import com.example.dto.Client;
import com.example.service.ClientCrudService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientCrudServiceTest {

    private ClientCrudService clientService;

    @Before
    public void setUp() {
        clientService = new ClientCrudService();
    }

    @Test
    public void testCreateOrUpdateClient() {
        Client client = new Client("Daniil");
        clientService.createOrUpdateClient(client);

        Client retrievedClient = clientService.getClientById(client.getId());
        assertEquals("Daniil", retrievedClient.getName());
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client("Alice");
        clientService.createOrUpdateClient(client);

        client.setName("Alice Smith");
        clientService.createOrUpdateClient(client);

        Client updatedClient = clientService.getClientById(client.getId());
        assertEquals("Alice Smith", updatedClient.getName());
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client("Bob");
        clientService.createOrUpdateClient(client);

        clientService.deleteClientById(client.getId());

        assertNull(clientService.getClientById(client.getId()));
    }

    @After
    public void tearDown() {
        clientService = null;
    }
}