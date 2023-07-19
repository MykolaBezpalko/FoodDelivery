package com.fooddelivery.parser.selectorExecutors;

import com.fooddelivery.parser.connector.JsoupConnector;
import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.validation.constraints.NotBlank;

/**
 * Base class for executing simple queries to get elements from Jsoup document
 */
public class SelectorsExecutor {

    @Getter
    private final Document document;

    public SelectorsExecutor(Document document) {
        this.document = document;
    }

    /**
     * @param linkAfterBase String value of link after base on the glovo site
     */
    public SelectorsExecutor(@NotBlank String linkAfterBase) {
        this.document = JsoupConnector.getConnection(linkAfterBase);
    }

    /**
     * @param selector CSS-like query
     * @return {@link Elements elements} from selector
     */
    public Elements getAllElementsBySelector(String selector) {
        return this.document.select(selector);
    }

    /**
     * @param selector CSS-like query
     * @return String text from selector
     */
    public String getTextBySelector(String selector) {
        return this.document.select(selector).first().text();
    }

    /**
     * @param element  {@link Element} object from what selects will execute
     * @param selector CSS-like query
     * @return String text from selector
     */
    public static String getTextBySelector(Element element, String selector) {
        return element.select(selector).first().text();
    }

    public String getWholeDocumentHTML() {
        return this.document.toString();
    }
}
