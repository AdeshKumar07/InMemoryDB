package org.example.service;

public interface IDbservice<T> {
    void put(Integer key, T data, long ttl);
    T get(Integer key);
    void delete(Integer key) ;
    void put(Integer key, Object data);
}
