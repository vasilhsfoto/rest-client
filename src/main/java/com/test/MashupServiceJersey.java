package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Employee;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

public class MashupServiceJersey {

    @Provider
    private static class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
        final ObjectMapper objectMapper;

        private MyObjectMapperProvider() {
            this.objectMapper = createDefaultMapper();
        }

        private ObjectMapper createDefaultMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper;
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return objectMapper;
        }
    }

    private final Client client;

    public MashupServiceJersey() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class)
                .register(MyObjectMapperProvider.class);

        this.client = ClientBuilder.newClient(clientConfig);

        client.property(ClientProperties.READ_TIMEOUT, 2000);
        client.property(ClientProperties.CONNECT_TIMEOUT, 2000);
    }

    public void doSomeGets() {
        try {
            URI uri = UriBuilder.fromUri("http://localhost:8080")
                    .path("vf")
                    .path("employees")
                    .build();

            WebTarget target = client.target(uri);

            Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE)
                    .get();

            checkResponseCode(response.getStatusInfo().getStatusCode());

            Employee employee = response.readEntity(Employee.class);

//            readByteByByte(response);

        } catch (ProcessingException pe) {
            System.out.println(pe.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {

        }
    }

    private void readByteByByte(Response response) throws IOException {
        InputStream is = response.readEntity(InputStream.class);
        String payload;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            payload = br.lines().collect(Collectors.joining("/n"));
        }
        System.out.println(payload);
    }

    private void checkResponseCode(int responseStatusCode) {
        System.out.println(responseStatusCode);
    }

}
