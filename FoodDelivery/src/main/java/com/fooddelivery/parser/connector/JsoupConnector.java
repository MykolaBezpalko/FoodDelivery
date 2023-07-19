package com.fooddelivery.parser.connector;

import com.fooddelivery.parser.provider.UserAgentProvider;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Class-connector that has the only one method getConnection()
 */
@Slf4j
public class JsoupConnector {
    /**
     * The value of BASE_LINK_TO_GLOVO is {@value}
     */
    public static final String BASE_LINK_TO_GLOVO = "https://glovoapp.com";
    /**
     * The value of REFERRER is {@value}
     */
    public static final String REFERRER = "https://www.google.com";

    /**
     * Method that connects to glovo site by Jsoup <br>
     * Using {@link UserAgentProvider#provideRandomAgent()} method
     *
     * @param linkAfterBase (etc. /p-yatnytsia/)
     * @return {@link Document document} from Jsoup to handle next parses
     */
    public static Document getConnection(String linkAfterBase) {
        Document document = null;
        try {
            document = Jsoup.connect(BASE_LINK_TO_GLOVO + linkAfterBase)
                    .userAgent(UserAgentProvider.provideRandomAgent())
                    .referrer(REFERRER)
                    .get();
            if (document == null) {
                log.error("Error with connecting to" + BASE_LINK_TO_GLOVO + linkAfterBase);
            } else {
                log.debug("Glovo connected!...");
            }
        } catch (IOException e) {
            log.error("(IOEx) Error with connecting to " + BASE_LINK_TO_GLOVO + linkAfterBase);
            e.printStackTrace();
            document = null;
        }
        return document;
    }
}
