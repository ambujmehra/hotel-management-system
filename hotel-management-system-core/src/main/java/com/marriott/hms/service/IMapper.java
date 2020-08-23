package com.marriott.hms.service;

/**
 * The interface Mapper.
 *
 * @param <T> the type parameter
 * @param <V> the type parameter
 */
public interface IMapper<T,V> {
    /**
     * Map v.
     *
     * @param t the t
     * @return the v
     */
    V map(T t);

}
