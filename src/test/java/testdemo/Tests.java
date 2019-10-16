package testdemo;

import com.google.inject.Guice;
import com.google.inject.Injector;
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
    public void stubTest() throws IOException {

        String apiResult = testUtils.getStringResponse("https://reqres.in/api/unknown/4");
        logger.info(apiResult);
        String stubResult = testUtils.getStringResponse("http://localhost:7777/getStub");
        logger.info(stubResult);
        assertThat(apiResult, equalTo(stubResult));
    }

    @Test
    public void brokenStubTest() throws IOException {

        String apiResult = testUtils.getStringResponse("https://reqres.in/api/unknown/2");
        logger.info(apiResult);
        String stubResult = testUtils.getStringResponse("http://localhost:7777/getStub");
        logger.info(stubResult);
        assertThat(apiResult, equalTo(stubResult));
    }
}
