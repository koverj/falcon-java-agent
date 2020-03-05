package io.koverj.agent.java.commons;

import org.junit.platform.launcher.TestIdentifier;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by alpa on 3/4/20
 */
public class UuidsStorage {

    private final Map<TestIdentifier, String> storage = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    public Optional<String> get(final TestIdentifier testIdentifier) {
        try {
            lock.readLock().lock();
            return Optional.ofNullable(storage.get(testIdentifier));
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getOrCreate(final TestIdentifier testIdentifier) {
        try {
            lock.writeLock().lock();
            return storage.computeIfAbsent(testIdentifier, ti -> UUID.randomUUID().toString());
        } finally {
            lock.writeLock().unlock();
        }
    }
}
