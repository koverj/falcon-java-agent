package io.koverj.agent.java.commons.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


/**
 * Created by alpa on 3/13/20
 */
public class LocatorConverter {

    public static final String NORMALIZE_SPACE_XPATH = "normalize-space(translate(string(.), '\t\n\r\u00a0', '    '))";

    public static String convertLocator(String locator) {
        LocatorType locatorType = getLocatorType(locator);
        switch (Objects.requireNonNull(locatorType)) {
            case TEXT:
                return convertByTextLocatorToXpath(locator);
            case ID:
                return convertByIdLocatorToCss(locator);
            case NAME:
                return convertByNameLocatorToCss(locator);
            case CLASS_NAME:
                return convertByClassNameLocatorToCss(locator);
            case LINK_TEXT:
                return convertByLinkTextLocatorToXpath(locator);
            case PARTIAL_LINK_TEXT:
                return convertByPartialLinkTextLocatorToXpath(locator);
            case TAG_NAME:
            case CSS:
            case XPATH:
                return getLocatorValueForCssAndXpath(locator);

            default:
                return locator;
        }
    }


    private static LocatorType getLocatorType(String locator) {
        if (locator.startsWith("by text") || locator.startsWith("with text")) {
            return LocatorType.TEXT;
        }
        if (locator.startsWith("By.cssSelector") || locator.startsWith("[") ||
                locator.startsWith(".") || locator.startsWith("#")) {
            return LocatorType.CSS;
        }
        if (locator.startsWith("By.xpath:") || locator.startsWith("./") || locator.startsWith("//")) {
            return LocatorType.XPATH;
        }
        if (locator.startsWith("By.className:")) {
            return LocatorType.CLASS_NAME;
        }
        if (locator.startsWith("By.partialLinkText:")) {
            return LocatorType.PARTIAL_LINK_TEXT;
        }
        if (locator.startsWith("By.linkText:")) {
            return LocatorType.LINK_TEXT;
        }
        if (locator.startsWith("By.id:")) {
            return LocatorType.ID;
        }
        if (locator.startsWith("By.name:")) {
            return LocatorType.NAME;
        }
        if (locator.startsWith("By.tagName:")) {
            return LocatorType.TAG_NAME;
        }

        return LocatorType.CUSTOM;
    }


    private static String convertByTextLocatorToXpath(String element) {
        String elementText = getLocatorValue(element);
        return ".//*/text()[" + NORMALIZE_SPACE_XPATH + " = " + escapeQuotes(elementText) + "]/parent::*";
    }

    private static String convertByIdLocatorToCss(String element) {
        return "#" + getLocatorValue(element);
    }

    private static String convertByClassNameLocatorToCss(String element) {
        return "." + getLocatorValue(element);
    }

    private static String convertByNameLocatorToCss(String element) {
        return "[name='"+getLocatorValue(element)+"']";
    }

    private static String convertByLinkTextLocatorToXpath(String element) {
        String elementText = getLocatorValue(element);
        return ".//a/text()[" + NORMALIZE_SPACE_XPATH + " = " + escapeQuotes(elementText) + "]/parent::*";
    }

    private static String convertByPartialLinkTextLocatorToXpath(String element) {
        String elementText = getLocatorValue(element);
        return ".//a[contains(normalize-space(.), '" + escapeQuotes(elementText) + "')]";
    }

    private static String getLocatorValue(String element) {
        return StringUtils.substringAfter(element, ": ");
    }

    private static String getLocatorValueForCssAndXpath(String element) {
        return element
                .replaceAll("By.cssSelector:", "")
                .replaceAll("By.xpath:", "");
    }

    /**
     * See <a href="https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/Quotes.html">Quotes</a>
     */
    private static String escapeQuotes(String toEscape) {
        if (toEscape.contains("\"") && toEscape.contains("'")) {
            boolean quoteIsLast = false;
            if (toEscape.lastIndexOf("\"") == toEscape.length() - 1) {
                quoteIsLast = true;
            }
            String[] substringsWithoutQuotes = toEscape.split("\"");

            StringBuilder quoted = new StringBuilder("concat(");
            for (int i = 0; i < substringsWithoutQuotes.length; i++) {
                quoted.append("\"").append(substringsWithoutQuotes[i]).append("\"");
                quoted
                        .append(((i == substringsWithoutQuotes.length - 1) ? (quoteIsLast ? ", '\"')" : ")")
                                : ", '\"', "));
            }
            return quoted.toString();
        }

        if (toEscape.contains("\"")) {
            return String.format("'%s'", toEscape);
        }

        return String.format("\"%s\"", toEscape);
    }

    enum LocatorType {

        ID, NAME, LINK_TEXT, PARTIAL_LINK_TEXT, CLASS_NAME, CSS, XPATH, TEXT, TAG_NAME, CUSTOM

    }
}
