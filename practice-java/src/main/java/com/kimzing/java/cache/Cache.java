package com.kimzing.java.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 本地缓存.
 *
 * @author KimZing - kimzing@163.com
 * @since 2019-06-14 17:03
 */
public class Cache {

    private static final Map<String, Entity> cache = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    /**
     * 添加缓存
     *
     * @param key  键
     * @param data 值
     */
    public synchronized static void put(String key, Object data) {
        Cache.put(key, data, 0);
    }

    /**
     * 添加缓存
     *
     * @param key    键
     * @param data   值
     * @param expire 过期时间，单位：毫秒， 0表示无限长
     */
    public synchronized static void put(String key, Object data, long expire) {
        if (expire <= 0) {
            cache.put(key, new Entity(data, null));
            return;
        }
        cache.put(key, new Entity(data, EXECUTOR.schedule(() -> {
            cache.remove(key);
        }, expire, TimeUnit.MILLISECONDS)));
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return
     */
    public synchronized static Object get(String key) {
        Entity entity = cache.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 读取缓存
     *
     * @param key 键
     *            * @param clazz 值类型
     * @return
     */
    public synchronized static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(Cache.get(key));
    }

    /**
     * 清除缓存
     *
     * @param key
     * @return
     */
    public synchronized static Object remove(String key) {
        Entity entity = cache.remove(key);
        if (entity == null) {
            return null;
        }
        Future future = entity.getFuture();
        if (future != null) {
            future.cancel(true);
        }
        return entity.getValue();
    }

    /**
     * 查询当前缓存的键值对数量
     *
     * @return
     */
    public synchronized static int size() {
        return cache.size();
    }

    private static class Entity {

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        private Object value;

        private Future future;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Future getFuture() {
            return future;
        }

        public void setFuture(Future future) {
            this.future = future;
        }
    }

}
