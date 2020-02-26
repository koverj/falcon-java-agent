package com.kover.tests.realapp.coverage.listener;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.kover.tests.realapp.coverage.model.Locator;
import com.kover.tests.realapp.coverage.SimpleLocatorStorage;

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
        if (subject.contains("()") || subject.contains("$")) {
            storage.put(new Locator(subject, currentLog.getElement()));
        }

    }
}
