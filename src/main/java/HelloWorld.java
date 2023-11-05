import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Port number can be adjusted as needed

        ContextHandler context = new ContextHandler("/");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(HelloWorld.class.getResource("/").toExternalForm());
        context.setHandler(resourceHandler);

        server.setHandler(context);
        server.start();
        server.join();
    }
}
