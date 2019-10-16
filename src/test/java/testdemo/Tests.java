package testdemo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static spark.Spark.*;


public class Tests {

    private static final Logger logger = Logger.getLogger(Tests.class.getName());
    private TestUtils testUtils;

    @BeforeEach
    public void setUp() {
        Injector injector = Guice.createInjector(new StubModule());
        ServiceInterface service = injector.getInstance(Stub.class);
        service.initService();
        testUtils = new TestUtils();
    }

    @AfterEach
    public void tearDown() {
        stop();
    }

    @Test
    @DisplayName("Test example")
    @Description("Simple test using api from reqres.in and stub")
    public void stubTest() throws IOException {

        String apiResult = testUtils.getStringResponse("https://reqres.in/api/unknown/4");
        logger.info(apiResult);
        Allure.addAttachment("Api result", apiResult);

        String stubResult = testUtils.getStringResponse("http://localhost:7777/getStub");
        logger.info(stubResult);
        Allure.addAttachment("Stub result", stubResult);

        assertThat(apiResult, equalTo(stubResult));
    }

}
