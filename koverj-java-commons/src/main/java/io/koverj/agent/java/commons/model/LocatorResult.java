package io.koverj.agent.java.commons.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by alpa on 2/25/20
 */
@Getter
@ToString
@RequiredArgsConstructor
public class LocatorResult {

    private final String buildId;
    private final String testName;
    private final  List<Locator> locators;
}
