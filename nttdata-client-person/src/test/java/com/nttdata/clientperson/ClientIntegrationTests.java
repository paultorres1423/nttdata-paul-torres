package com.nttdata.clientperson;

import com.nttdata.clientperson.modules.client.domain.ClientDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Clase de pruebas de integracion para el cliente.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ClientIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    /**
     * Contenedor de PostgreSQL para pruebas.
     */
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.1-alpine")
            .withDatabaseName("nttdata")
            .withUsername("root")
            .withPassword("NttD41@");

    /**
     * Registra las propiedades dinamicas de PostgreSQL.
     *
     * @param registry Registro de propiedades dinamicas.
     */
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    /**
     * Crea el URL
     *
     * @return URL completa con el puerto.
     */
    private String createURLWithPort() {
        return "http://localhost:" + port + "/clientes";
    }

    /**
     * Prueba de integracion para insertar un cliente.
     */
    @Test
    void testInsert() {
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setCcliente(2);
        clientDomain.setNombre("Integration Name Test");
        clientDomain.setIdentificacion("1234567890");
        clientDomain.setDireccion("Integration Address Test");
        clientDomain.setTelefono("1234567890");
        clientDomain.setContrasenia("Integration Password Test");
        clientDomain.setGenero("M");
        clientDomain.setEdad(30);
        clientDomain.setEstado(true);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ClientDomain> entity = new HttpEntity<>(clientDomain, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity,
                String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

}
