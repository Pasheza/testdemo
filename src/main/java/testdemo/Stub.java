package testdemo;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import static spark.Spark.*;

public class Stub implements ServiceInterface {

    @Inject
    @Named("PORT")
    Integer port;

    public void initService() {
        port(port);
        get("/getStub", (req, res) -> {
            res.type("application/json");
            return "{\"data\":{\"id\":4,\"name\":\"aqua sky\",\"year\":2003,\"color\":\"#7BC4C4\",\"pantone_value\":\"14-4811\"}}";
        });
    }

}
