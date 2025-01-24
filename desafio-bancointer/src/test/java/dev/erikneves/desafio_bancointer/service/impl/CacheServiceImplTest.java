package dev.erikneves.desafio_bancointer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class CacheServiceImplTest {
  private CacheServiceImpl<String> cacheServiceImpl;

  @BeforeEach
  void setUp() {
    this.cacheServiceImpl = new CacheServiceImpl<>();
  }

  @Test
  void itShouldReturnOptionalEmptyWhenKeyIsNotPresent() {
    // given
    var lookUpValue = "John Doe";
    var key = this.cacheKey(lookUpValue);

    // when
    var cachedValue = this.cacheServiceImpl.get(key);

    // then
    assertTrue(cachedValue.isEmpty());
  }

  @Test
  void itShouldReturnOptionalWithValueWhenKeyIsPresent() {
    // given
    var lookUpValue = "John Doe";
    var key = this.cacheKey(lookUpValue);
    this.cacheServiceImpl.put(key, lookUpValue);

    // when
    var cachedValue = this.cacheServiceImpl.get(key);

    // then
    assertTrue(cachedValue.isPresent());
    assertEquals(cachedValue.get(), lookUpValue);

  }

  @Test
  void itShouldRemoveEldestEntryWhenCacheSizeIsGreaterThanMaxEntries() {
    // given
    var lookupValue = "John Doe";
    var key = this.cacheKey(lookupValue);
    this.cacheServiceImpl.put(key, lookupValue);

    for (int i = 0; i < 10; i++) {
      var currentLookupValue = "John Doe " + i;
      var currentKey = this.cacheKey(currentLookupValue);
      this.cacheServiceImpl.put(currentKey, currentLookupValue);
    }

    // when
    var cachedValue = this.cacheServiceImpl.get(key);

    // then
    assertTrue(cachedValue.isEmpty());
  }

  private String cacheKey(String lookUpValue) {
    return "key: " + lookUpValue;
  }
}
