import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.fail;


public class VehicleEndpointIT {
 private Client client;
 private WebTarget target;

 @BeforeEach
    public void initClient(){
     this.client = ClientBuilder.newClient();
     this.target = client.target("http://localhost:8080/vehicle/api/vehicle/");
 }
 @Test
    public void fetchVehicle(){
     Response response = this.target.request(MediaType.TEXT_PLAIN).get();
     assertThat(response.getStatus(),is(200));
     //String payload = response.readEntity(String.class);
     JsonArray payload = response.readEntity(JsonArray.class);
     System.out.println("payload = " + payload);
     JsonObject vehicle = payload.getJsonObject(0);
     assertThat(vehicle.getString("brand"),is("Opel 42"));
     assertThat(vehicle.getString("type"),is(("Commodore")));
     //fail();
 }
}
