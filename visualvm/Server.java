import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.deploy.Verticle;
import java.util.List;
import java.util.ArrayList;

public class Server extends Verticle {
    private int counter = 0;
    List<Integer> listD = new ArrayList<Integer>(50000000);
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                System.out.println(counter++);
                /* with every request add 10000 integers @ floor(n/10000) + 1 request it will resize */
                /* gc */
                for(int i = 0; i < 10000000; i++) {
                  listD.add(i);
                }
                String file = req.path.equals("/") ? "index.html" : req.path;
                req.response.sendFile("webroot/" + file);
            }
        }).listen(8080);
    }
}
