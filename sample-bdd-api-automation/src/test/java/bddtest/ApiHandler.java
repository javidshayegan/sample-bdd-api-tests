package bddtest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.runner.Request;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.ParameterizedType;
import java.util.Dictionary;
import java.util.Properties;

import static io.restassured.RestAssured.requestSpecification;

public class ApiHandler {


    ResponseSpecification responseSpec;
    RequestSpecification reqObj;

    public static RequestSpecification req;

    public static void createRequest(String url, Method requestType) {

        Response response = requestSpecification.request(requestType).then().extract().response();

    }

    public static void executeRequest() {
        //executeRequestAsync().GetAwaiter().getResult();
    }

    public static String GetResponseStatusCode() {
        return requestSpecification.response().statusCode(Integer.parseInt("statusCode")).toString();
    }

    public static void validateResponseCode(String expectedResponse) {
        String responseCode = GetResponseStatusCode();
        switch (expectedResponse) {
            case "200 ok":
                Assert.assertArrayEquals("OK", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "201 created":
                Assert.assertArrayEquals("Created", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "202 accepted":
                Assert.assertArrayEquals("Accepted", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "204 no content":
                Assert.assertArrayEquals("NoContent", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "404 not found":
                Assert.assertArrayEquals("NotFound", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "400 bad request":
                Assert.assertArrayEquals("BadRequest", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            case "401 unauthorized":
                Assert.assertArrayEquals("Unauthorized", responseCode.getBytes(), ("Request Failed:" + responseCode).getBytes());
                break;
            default:
                throw new IllegalArgumentException(expectedResponse + "is not found in switch case options");
        }
    }

    public static void createAPIRequest(String url, String requestType) {
        switch (requestType.toLowerCase()) {
            case "GET":
                createRequest(url, Method.GET);
                break;
            case "POST":
                createRequest(url, Method.POST);
                break;
            case "PUT":
                createRequest(url, Method.PUT);
                break;
            case "DELETE":
                createRequest(url, Method.DELETE);
                break;
            case "PATCH":
                createRequest(url, Method.PATCH);
                break;
        }
    }

    public static RequestSpecification requestSpecificationMethod(String endPoint) throws IOException {

        if (req == null) {

            PrintStream log = new PrintStream(new FileOutputStream("src/test/java/logs/logs.txt"));

            req = new RequestSpecBuilder().setBaseUri(endPoint)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;

    }

    public static String baseUrl; /** Got the baseURI value from Property file and stored here.*/
    /**Static keyword is used here to because there is no main method in this class*/
    static {
        try {
            ApiHandler practice = new ApiHandler();
            baseUrl = practice.getBaseUriValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        String path = System.getProperty("user.dir");
        String end = "/src/test/resources/global.properties";
        String fullPath = path.concat(end);
        FileInputStream fis = new FileInputStream(fullPath);
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getBaseUriValue() throws IOException {
        // Location of your file
        FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
        // Create object of property file: coming from java.util
        Properties prop = new Properties();
        //Load the values from FileInputStream to properties object.
        prop.load(fis); // load accepts InputStream
        String baseUrl = prop.getProperty("baseUrl");
        return baseUrl;
    }

    public static void AddQueryParamatersAPI(Dictionary<String, String> queryParameter) {
        //foreach (var(key,value) in queryParameter).Request.AddQueryParameter(key, value);
    }

    public static void AddQueryParameters(Dictionary<String, String> properties) {
        AddQueryParamatersAPI(properties);
    }

    public static void AddBodyParameter(String name, Object value, ParameterizedType type) {
        //Request.AddParameter(name, value, type);

    }

    public void AddBodyParameter(String name, Object value) {
        // Request.AddParameter(name, value);
    }

    public static void AddQueryParamatersBody(String name, Object value, ParameterizedType type) {
        AddBodyParameter(name, value, type);
    }
}
