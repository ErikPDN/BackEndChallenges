package dev.erikneves.desafio_bancointer.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.erikneves.desafio_bancointer.service.CacheService;

@Service
public class CacheServiceImpl<T> implements CacheService<T> {

  Map<String, T> cache = new CacheMap<>();

  @Override
  public Optional<T> get(String key) {
    return Optional.ofNullable(this.cache.get(key));
  }

  @Override
  public void put(String key, T value) {
    this.cache.put(key, value);
  }
}

final class CacheMap<K, V> extends LinkedHashMap<K, V> {
  private static final Integer MAX_ENTRIES = 10;

  public CacheMap() {
    super(MAX_ENTRIES, 0.75f, true);
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > MAX_ENTRIES;
  }

}
