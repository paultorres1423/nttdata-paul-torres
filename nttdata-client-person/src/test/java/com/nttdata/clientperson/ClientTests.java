package com.nttdata.clientperson;

import com.nttdata.clientperson.modules.client.application.ClientApplication;
import com.nttdata.clientperson.modules.client.domain.ClientDomain;
import com.nttdata.clientperson.modules.client.infrastructure.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Clase de pruebas para la aplicacion de clientes.
 */
@SpringBootTest
public class ClientTests {

    @Autowired
    private ClientApplication clientApplication;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Prueba para insertar un cliente y verificar que se ha insertado correctamente.
     */
    @Test
    void testInsert() {
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setCcliente(1);
        clientDomain.setNombre("Name Test");
        clientDomain.setIdentificacion("1234567890");
        clientDomain.setDireccion("Address Test");
        clientDomain.setTelefono("1234567890");
        clientDomain.setContrasenia("Password Test");
        clientDomain.setGenero("M");
        clientDomain.setEdad(30);
        clientDomain.setEstado(true);
        clientApplication.insert(clientDomain);
        assertNotNull(clientRepository.findByCclienteAndEstadoTrue(1).orElse(null));
    }

}
