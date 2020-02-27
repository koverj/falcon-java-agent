<<<<<<< HEAD:src/main/java/io/koverj/agent/listener/LocatorTestListener.java
package io.koverj.agent.listener;

import io.koverj.agent.KoverjClient;
import io.koverj.agent.model.Locator;
import io.koverj.agent.model.LocatorResult;
import io.koverj.agent.SimpleLocatorStorage;
=======
package io.kover.agent.listener;

import io.kover.agent.KoverClient;
import io.kover.agent.model.Locator;
import io.kover.agent.model.LocatorResult;
import io.kover.agent.SimpleLocatorStorage;
import okhttp3.ResponseBody;
>>>>>>> Move code to main dir for creation jar file:src/main/java/io/kover/agent/listener/LocatorTestListener.java
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.List;


/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private boolean isSendToKover = Boolean.parseBoolean(System.getProperty("use.kover", "true"));
    private final SimpleLocatorStorage storage;
    private final KoverjClient koverjClient;

    public LocatorTestListener() {
        this.storage = SimpleLocatorStorage.getInstance();
        this.koverjClient = new KoverjClient();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            LocatorResult locatorResult = new LocatorResult(testIdentifier.getDisplayName(), storage.get());
            processLocator(locatorResult);
            System.out.println(locatorResult);
            if (isSendToKover) {
                koverjClient.sendLocatorsResult(locatorResult);
            }
            storage.clear();
        }
    }

    private void processLocator(LocatorResult locatorResult) {
        List<Locator> locators = locatorResult.getLocators();
        locators.forEach(locator -> {
            String parentUuid = locator.getParentUuid();
            if (parentUuid != null) {
//         TODO add checking for css/xpath
                locators.stream()
                        .filter(parentLocator -> parentLocator.getUuid().equalsIgnoreCase(parentUuid))
                        .findFirst()
                        .ifPresent(value -> locator.setLocator(value.getLocator() + " " + locator.getLocator()));
            }
        });
    }

}