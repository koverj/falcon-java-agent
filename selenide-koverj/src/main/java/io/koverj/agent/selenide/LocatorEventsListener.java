package io.koverj.agent.selenide;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;

import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by alpa on 2/25/20
 */
public class LocatorEventsListener implements LogEventListener {

    private final LocatorsLifecycle lifecycle;

    public LocatorEventsListener() {
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
            lifecycle.getCurrentContainer().ifPresent(containerUuid -> {
                String currentUrl = getWebDriver().getCurrentUrl();
                Optional<LocatorResult> locatorResultOptional = lifecycle.getStorage().get(containerUuid, LocatorResult.class);
                LocatorResult locatorResult = locatorResultOptional.get();
                LinkedList<Locator> locators = locatorResult.getLocators();
                String locatorUuid = UUID.randomUUID().toString();

                if (!locators.isEmpty()) {
                    Locator previousLocator = locators.getLast();
                    if (previousLocator != null) {
                        if (previousLocator.getSubject().contains("$")) {
                            String parentUuid = previousLocator.getUuid();
                            lifecycle.updateTestContainer(containerUuid,
                                    l -> l.add(new Locator(locatorUuid, currentUrl, subject,
                                    currentLog.getElement(), parentUuid)));
                        } else {
                            lifecycle.updateTestContainer(containerUuid,
                                    l -> l.add(new Locator(locatorUuid, currentUrl, subject,
                                    currentLog.getElement(), null)));
                        }
                    }
                } else {
                    lifecycle.updateTestContainer(containerUuid,
                            l -> l.add(new Locator(locatorUuid, currentUrl, subject,
                                    currentLog.getElement(), null)));
                }
            });
        }
    }
}
