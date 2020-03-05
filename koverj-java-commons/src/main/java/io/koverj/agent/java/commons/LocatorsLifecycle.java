package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class LocatorsLifecycle {

    private final SimpleLocatorStorageMap storage;
    private final KoverjClient koverjClient;
    private static LocatorsLifecycle instance;
    private final KoverjThreadContext koverjThreadContext;
    private final UuidsStorage containers;


    private LocatorsLifecycle() {
        this.koverjClient = new KoverjClient();
        this.storage = new SimpleLocatorStorageMap();
        this.koverjThreadContext = new KoverjThreadContext();
        this.containers = new UuidsStorage();
    }

    public static LocatorsLifecycle getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LocatorsLifecycle();

        }
        return instance;
    }

    public void sendLocators(String uuid) {
        Optional<LocatorResult> locatorResultOptional = storage.get(uuid, LocatorResult.class);
        locatorResultOptional.ifPresent(locatorResult -> {
            processLocator(locatorResult);
            System.out.println(locatorResult);
            if (KoverjConfig.isSendToKover) {
                koverjClient.sendLocatorsResult(locatorResult);
            }
        });
    }

    public void processLocator(LocatorResult locatorResult) {
        List<Locator> locators = locatorResult.getLocators();
        locators.forEach(locator -> {
            String parentUuid = locator.getParentUuid();
            if (parentUuid != null) {
                locators.stream()
                        .filter(parentLocator -> parentLocator.getUuid().equalsIgnoreCase(parentUuid))
                        .findFirst()
                        .ifPresent(value -> locator.setLocator(value.getLocator() + " " + locator.getLocator()));
            }
        });
    }

    public SimpleLocatorStorageMap getStorage() {
        return storage;
    }

    public UuidsStorage getContainers() {
        return containers;
    }

    public void startTestContainer(final LocatorResult container) {
        koverjThreadContext.start(container.getUuid());
        storage.put(container.getUuid(), container);
    }

    public void updateTestContainer(final String uuid, final Consumer<LocatorResult> update) {
        final Optional<LocatorResult> found = storage.getContainer(uuid);
        if (!found.isPresent()) {
            return;
        }
        final LocatorResult container = found.get();
        update.accept(container);
    }

    public void stopTestContainer(final String uuid) {
        final Optional<LocatorResult> found = storage.getContainer(uuid);
        if (!found.isPresent()) {
            return;
        }
        storage.remove(uuid);
//        koverjThreadContext.clear();
        koverjThreadContext.stop();
    }

    public Optional<String> getCurrentContainer() {
        return koverjThreadContext.getCurrent();
    }

}
