import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class API {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="http://adapter.dev/v1";
    }

    @Test
    public void getTest(){
        RestAssured
                .when().get("/orders/78451213")
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("orderStatus", is("IN_PROCESS"));
    }

    @Test
    public void postTest(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("documentId", "74258425748");

        RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/downloads")
                .then()
                .statusCode(500)
                .assertThat()
                .body(containsString("Error"));
    }

    @Test
    @Parameters("FZ")
    public void postTestWithDto(String FZ){
        Dto dto = new Dto();
        dto.setBankGuaranteeSum(1000000);
        dto.setProcurementType(FZ);

        Gson gson = new Gson();
        JSONObject requestBody = new JSONObject(dto);

        String response = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/calculate").getBody().asString();

        DtoResponse dtoResponse = gson.fromJson(response, DtoResponse.class);
        Assert.assertEquals(dtoResponse.getCommissionAmount(), "304.97");

        String response2 = JsonPath.from(response).getString("commissionAmount");
        Assert.assertEquals(response2, "304.97");
    }
}
