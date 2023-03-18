package nk.unc.celadon.resource;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author N☰IL
 */
@Path("/index")
@Controller
public class IndexResource {

    /**
     * The logger used by this IndexResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexResource.class);

    @Inject
    private Models models;

    /**
     *
     */
    @GET
    @Produces("text/html")
    @View("index.html")
    public void getIndex() {
        models.put("today", LocalDate.now().toString());
        LOGGER.info("getIndex");
    }

}
