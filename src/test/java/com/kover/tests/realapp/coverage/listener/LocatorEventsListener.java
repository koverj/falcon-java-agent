package com.kover.tests.realapp.coverage.listener;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.kover.tests.realapp.coverage.model.Locator;
import com.kover.tests.realapp.coverage.SimpleLocatorStorage;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by alpa on 2/25/20
 */
public class LocatorEventsListener implements LogEventListener {

    private final SimpleLocatorStorage storage;

    public LocatorEventsListener() {
        this.storage = SimpleLocatorStorage.getInstance();
    }

    @Override
    public void afterEvent(LogEvent currentLog) {
    }

    @Override
    public void beforeEvent(LogEvent currentLog) {
        String subject = currentLog.getSubject();
//         TODO find way to changes this if
        if (subject.contains("(") || subject.contains("$")) {
            String uuid = UUID.randomUUID().toString();
            LinkedList<Locator> locators = storage.get();
            if (!locators.isEmpty()) {
                Locator previousLocator = locators.getLast();
                if (previousLocator != null) {
                    if (previousLocator.getSubject().contains("$")) {
                        String parentUuid = previousLocator.getUuid();
                        saveLocatorToStorage(uuid, subject, currentLog.getElement(), parentUuid);
                    } else {
                        saveLocatorToStorage(uuid, subject, currentLog.getElement(), null);
                    }
                }
            } else {
                saveLocatorToStorage(uuid, subject, currentLog.getElement(), null);
            }
        }
    }

    private void saveLocatorToStorage(String uuid, String subject, String locator, String parentUuid) {
        storage.put(new Locator(uuid, subject, locator, parentUuid));
    }
}
