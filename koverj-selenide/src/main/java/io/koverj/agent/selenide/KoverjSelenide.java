package io.koverj.agent.selenide;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.model.Locator;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.Quotes;

import java.util.LinkedList;
import java.util.UUID;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by alpa on 2/25/20
 */
public class KoverjSelenide implements LogEventListener {

    private final LocatorsLifecycle lifecycle;

    public KoverjSelenide() {
        this.lifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void afterEvent(LogEvent currentLog) {

    }

    @Override
    public void beforeEvent(LogEvent currentLog) {
        String subject = currentLog.getSubject();
//         TODO find way to changes this if
        if (subject.contains("(") || subject.contains("$")) {
            String currentUrl = getWebDriver().getCurrentUrl();
            String uuid = UUID.randomUUID().toString();
            LinkedList<Locator> locators = lifecycle.getStorage().get();
            String element = currentLog.getElement();
            if (isTextLocator(element)) {
                element = convertByTextLocator(element);
            }
            if (!locators.isEmpty()) {
                Locator previousLocator = locators.getLast();
                if (previousLocator != null) {
                    if (previousLocator.getSubject().contains("$")) {
                        String parentUuid = previousLocator.getUuid();
                        saveLocatorToStorage(uuid, currentUrl, subject, element, parentUuid);
                    } else {
                        saveLocatorToStorage(uuid, currentUrl, subject, element, null);
                    }
                }
            } else {
                saveLocatorToStorage(uuid, currentUrl, subject, element, null);
            }
        }
    }

    private void saveLocatorToStorage(String uuid, String url, String subject, String locator, String parentUuid) {
        lifecycle.getStorage().put(new Locator(uuid, url, subject, locator, parentUuid));
    }

    private String convertByTextLocator(String element) {
        String normalizeSpaceXpath = "normalize-space(translate(string(.), '\t\n\r\u00a0', '    '))";
        String elementText = StringUtils.substringAfter(element, ": ");
        return ".//*/text()[" + normalizeSpaceXpath + " = " + Quotes.escape(elementText) + "]/parent::*";
    }

    private boolean isTextLocator(String element) {
        return element.contains("by text") || element.contains("with text");
    }
}
