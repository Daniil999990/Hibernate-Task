package test;

import com.example.dto.Client;
import com.example.service.ClientCrudService;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientCrudServiceTest {
    private static ClientCrudService clientService;

    @Before
    public void init() {
        System.out.println("Init...");
        clientService = new ClientCrudService();
    }

    @Test
    public void unsuccessfulUpdateClientTest() {
        Assert.assertThrows(Exception.class, () -> clientService.updateClient(12, "Kila"));
    }

    @Test
    public void unSuccessfulDeleteClientTest() {
        Assert.assertThrows(Exception.class, () -> clientService.deleteById(14));
    }

    @Test
    public void SuccessfulGetByIdTest() throws Exception {
        Client clientById = clientService.getClientById(2);
        Assert.assertEquals("Kolia", clientById.getName());
    }

    @Test
    public void unSuccessfulGetByIdThrowsTest() {
        Assert.assertThrows(Exception.class, () -> clientService.getClientById(15));
    }

    @Test
    public void unSuccessfulGetByIdTest() throws Exception {
        Client clientById = clientService.getClientById(4);
        Assert.assertNotEquals(clientById.getName(), "Kila");
    }

    @Test
    public void createPlanetTest() {
        Session session = HibernateUtil.getConfiguration().openSession();
        clientService.createClient(new Client("ASDS"));
        Client client = session.get(Client.class, 11);
        Assert.assertEquals("ASDS", client.getName());
        session.close();
    }

    @Test
    public void deletePlanetTest() {
        clientService.createClient(new Client("name"));
        clientService.deleteById(12);

        Assert.assertThrows(RuntimeException.class, () -> clientService.getClientById(12));
    }


    @After
    public void destroy() {
        System.out.println("Destroy ...");
        clientService = null;
    }
}
