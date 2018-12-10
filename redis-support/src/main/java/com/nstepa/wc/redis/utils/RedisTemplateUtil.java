package com.nstepa.wc.redis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nstepa.wc.commons.utils.JSONUtil;
import com.nstepa.wc.commons.utils.JsonMapper;
import com.nstepa.wc.commons.utils.SpringBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;


/*
 * RedisTemplate操作工具类
  expireTime 有效时间，小于0或者不设置永久有效，单位：秒钟
   * */
public class RedisTemplateUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisTemplateUtil.class);

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate = SpringBeanUtil.getBean("redisTemplate", RedisTemplate.class);
    private static StringRedisTemplate stringRedisTemplate = SpringBeanUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);

    /*********************************************************************Key键相关*************************************8**************************/
    /**
     * 添加字符串缓存，有返回值
     *
     * @param key   KEY
     * @param value 值
     */
    public static boolean setStr(final String key, final String value) {
        return setStr(key, value, 0L);
    }

    /**
     * 添加字符串缓存,有返回值
     *
     * @param key        KEY
     * @param value      值
     * @param expireTime 有效时间，小于等于0永久有效，单位：毫秒
     */
    public static boolean setStr(final String key, final String value, long expireTime) {
        if (key == null || key.equals("") || value == null || value.equals("")) return false;
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                byte[] keys = serStr.serialize(key);
                connection.set(keys, serStr.serialize(value));
                if (expireTime > 0) connection.expire(keys, expireTime);
                return true;
            }
        });
        return result;
    }

    /**
     * 添加字符串缓存,实现2，无返回值
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public static void setStr3(final String key, final String value, final long expireTime) {
                redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 添加对象2，原方法名为set2
     *
     * @param key
     * @param value
     * @param expireTime 失效时间(秒)
     */
    public static void setStr2(String key, Object value, Long expireTime) {
        if (value.getClass().equals(String.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Integer.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Double.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Float.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Short.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Long.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Boolean.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 添加对象缓存,有返回值
     *
     * @param key KEY
     * @param obj 对象
     */
    public static <T> boolean setObj(final String key, T obj) {
        return setObj(key, obj, 0);
    }

    /**
     * 添加对象缓存,有返回值
     *
     * @param key        KEY
     * @param obj        对象
     * @param expireTime 有效时间，小于等于0永久有效，单位：秒钟
     */
    public static <T> boolean setObj(final String key, T obj, long expireTime) {
        if (obj == null) return false;
        String value = JSONUtil.toJson(obj);
        //  stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
        return setStr(key, value, expireTime);
    }

    /**
     * 添加集合缓存
     *
     * @param key  KEY
     * @param list 集合
     */
    public static <T> boolean setCollection(final String key, List<T> list) {
        return setCollection(key, list, 0L);
    }

    /**
     * 添加集合缓存
     *
     * @param key        KEY
     * @param list       集合
     * @param expireTime 有效时间，小于等于0永久有效，单位：秒钟
     */
    public static <T> boolean setCollection(final String key, List<T> list, long expireTime) {
        if (list == null) return false;
        String value = JSONUtil.toJson(list);
        return setStr(key, value, expireTime);
    }

    /*
    先获取key对应的value值，若不存在则返回nil，然后将旧的value更新为新的value
     */
    public static <T> T getSet(final String key, Object value, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 根据key获取对象
     */
    public Object getObj(final String keyId) {
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return 注：基本数据类型(Character除外)，请直接使用getObj(String key, Class<T> clazz)取值
     */
    public static Object getObj2(final String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 读取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObj(final String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取对象缓存
     *
     * @param key KEY
     */
    public static <T> T getObj2(final String key, Class<T> clz) {
        String result = getStr(key);
        if (result == null) return null;
        return JSONUtil.toBean(result, clz);
    }

    /**
     * 获取缓存<br>
     * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
     *
     * @param key
     * @param retain 是否保留
     * @return
     */
    public static Object getObj(String key, boolean retain) {
        Object obj = redisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return obj;
    }


    /**
     * 取得缓存（int型）
     *
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Integer.valueOf(value);
        }
        return null;
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public static String getStr(final String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 取得缓存（字符串类型）*
     *
     * @param key
     * @return
     */
    public static String getStr(String key, boolean retain) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return value;
    }

    /**
     * 获取字符串缓存
     *
     * @param key KEY
     */
    public static String getStr2(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serStr.serialize(key));
                if (value == null) return null;

                return serStr.deserialize(value);
            }
        });
        return result;
    }

    /**
     * 获取double类型值
     *
     * @param key
     * @return
     */
    public static double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param expireTime 失效时间(秒)
     */
    public static void setDouble(String key, double value, Long expireTime) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置Int类型值
     *
     * @param key
     * @param value
     * @param expireTime 失效时间(秒)
     */
    public static void setInt(String key, int value, Long expireTime) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取缓存json对象<br>
     *
     * @param key   key
     * @param clazz 类型
     * @return
     */
    public static <T> T getJson(String key, Class<T> clazz) {
        return JsonMapper.fromJsonString(stringRedisTemplate.boundValueOps(key).get(), clazz);
    }


    /**
     * 修改对象
     */
    public boolean update(final String key, final String value) {
        if (getStr(key) == null) {
            //  throw new NullPointerException("数据行不存在, key = " + key);
            logger.warn("数据行不存在, key = {}", key);
            return false;
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    /**
     * 删除对象 ,依赖key
     */
    public void del(final String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除，根据key精确匹配
     *
     * @param key
     */
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
//                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 删除缓存
     *
     * @param key KEY列表
     */
    public static boolean del2(final String key) {
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                return connection.del(serStr.serialize(key));
            }
        });
        return result > 0;
    }


    /**
     * 批量删除，根据key模糊匹配
     * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     *
     * @param pattern
     */
    public static void delpn(final String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }


    /*
       缓存锁
       “set if not exits”，若该key-value不存在，则成功加入缓存并且返回1，否则返回0。
     */
    public static Boolean setnx(final String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * key是否存在
     *
     * @param key
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据KEY判断缓存是否存在
     *
     * @param key KEY
     */
    public static boolean exists1(final String key) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                return connection.exists(serStr.serialize(key));
            }
        });
        return result;
    }


    /**
     * 更新key对象field的值
     * 功能跟map类似
     *
     * @param key   缓存key
     * @param field 缓存对象field
     * @param value 缓存对象field值
     */
    public static void setObjField(String key, String field, String value) {
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }


    /**
     * 递减操作
     *
     * @param key
     * @param by
     * @return
     */
    public static double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    public static double decr2(String key, double by) {
        double result = 0;
        if (getInt(key) > 0) {
            result = redisTemplate.opsForValue().increment(key, -by);
        }
        return result;
    }

    /**
     * 递增操作
     *
     * @param key
     * @param by
     * @return
     */
    public static double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }


    /**
     * 指定缓存的失效时间
     *
     * @param key        缓存KEY
     * @param expireTime 失效时间(秒)
     */
    public static void expire(String key, Long expireTime) {
//        if (expireTime > 0) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//        }
    }

    /**
     * 设置缓存有效时间
     *
     * @param key    KEY
     * @param expire 有效时间，单位：秒
     */
    public static boolean expire2(final String key, long expire) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                return connection.expire(serStr.serialize(key), expire);
            }
        });
        return result;
    }

    /**
     * 重命名
     *
     * @param oldkey
     * @param newkey
     */
    public static void rename(String oldkey, String newkey) {
        redisTemplate.boundValueOps(oldkey).rename(newkey);
    }

    /**
     * 模糊查询keys
     *
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 模糊查询KEY
     *
     * @param keyWord KEY的模糊关键字<br/>
     *                ● h?llo matches hello, hallo and hxllo<br/>
     *                ● h*llo matches hllo and heeeello<br/>
     *                ● h[ae]llo matches hello and hallo, but not hillo<br/>
     *                ● h[^e]llo matches hallo, hbllo, ... but not hello<br/>
     *                ● h[a-b]llo matches hallo and hbllo<br/>
     *                ■ Use \ to escape special characters if you want to match them verbatim.<br/>
     */
    public static List<String> keys2(final String keyWord) {
        List<String> result = redisTemplate.execute(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                Set<byte[]> keys = connection.keys(serStr.serialize(keyWord));
                if (keys == null || keys.isEmpty()) return null;
                List<String> list = new ArrayList<String>();
                for (byte[] item : keys) {
                    list.add(serStr.deserialize(item));
                }
                return list;
            }
        });
        return result;
    }

    /**
     * 获取集合缓存
     *
     * @param key KEY
     */
    public static <T> List<T> getCollection(final String key, Class<T> clz) {
        String result = getStr(key);
        if (result == null) return null;
        return JSONUtil.toList(result, clz);
    }
    /***************************************Map相关**************************************/
    /**
     * 添加Map
     */
    public static boolean setMap(final Map<String, String> map) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    byte[] key = serializer.serialize(entry.getKey());
                    byte[] name = serializer.serialize(entry.getValue());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 将map写入缓存
     *
     * @param key
     * @param map
     * @param expireTime 失效时间(秒)
     */
    public static <T> void setMap(String key, Map<String, T> map, Long expireTime) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 将map写入缓存
     *
     * @param key
     * @param obj
     * @param expireTime 失效时间(秒)
     */
    @SuppressWarnings("unchecked")
    public static <T> void setMap(String key, T obj, Long expireTime) {
        Map<String, String> map = (Map<String, String>) JsonMapper.parseObject(obj, Map.class);
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key   cache对象key
     * @param field map对应的key
     * @param obj   对象
     */
    public static <T> void addMap(String key, String field, T obj) {
        redisTemplate.opsForHash().put(key, field, obj);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key
     * @param map
     */
    public static <T> void addMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key   cache对象key
     * @param field map对应的key
     * @param value 值
     */
    public static void addMap(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取map缓存
     *
     * @param key
     * @return
     */
    public static <T> Map<String, T> mget(String key) {
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取map缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getMap(String key, Class<T> clazz) {
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        Map<String, String> map = boundHashOperations.entries();
        return JsonMapper.parseObject(map, clazz);
    }

    /**
     * 获取map缓存中的某个对象
     *
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getMapField(String key, String field, Class<T> clazz) {
        if (redisTemplate.hasKey(key) && StringUtils.isNotBlank(field)) {
            return (T) redisTemplate.boundHashOps(key).get(field);
        } else {
            return null;
        }
    }

    public static <T> void setMapField(String key, String field, T obj) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.boundHashOps(key).put(field, obj);
        } else {
            addMap(key, field, obj);
        }
    }

    /*
     * 查看哈希表 key 中，给定域 field 是否存在
     */
    public static boolean hexist(String key, String field) {
        return redisTemplate.boundHashOps(key).hasKey(field);
    }

    /**
     * 删除map中的某个对象
     *
     * @param key   map对应的key
     * @param field map中该对象的key
     */
    public static void delMapField(String key, String field) {
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }
    /********************************************Set相关***********************************************/
    /**
     * 添加set
     *
     * @param key
     * @param value
     */
    public static void sadd(String key, String value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除set集合中的对象
     *
     * @param key
     * @param value
     */
    public static void srem(String key, String value) {
        redisTemplate.boundSetOps(key).remove(value);
    }


/************************************************SortedSet相关*************************************/
    /**
     * 添加SortedSet
     *
     * @param key    sortedSet的key
     * @param score  值可以是整数值或双精度浮点数
     * @param member 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     *               当 key 存在但不是有序集类型时，返回一个错误。
     */
    public static Boolean zadd(String key, long score, String member) {
        return redisTemplate.boundZSetOps(key).add(member, score);

    }

    /* public static Boolean zadd(String key, double score, String... member) {
         return redisTemplate.boundZSetOps(key).add(member, score);

     }*/
    public static Long zaddByTypeTuple(String key, double score, String member) {
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<Object>(member, score);
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
        typedTupleSet.add(typedTuple1);
        return redisTemplate.boundZSetOps(key).add(typedTupleSet);

    }

    /**
     * 返回返回有序集 key 的基数（SortedSet）
     *
     * @param key sortedSet的key
     *            当 key 存在且是有序集类型时，返回有序集的基数。
     *            当 key 不存在时，返回 0 。
     */
    public static Long zcard(String key) {
        return redisTemplate.boundZSetOps(key).zCard();
    }

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。（SortedSet）
     *
     * @param key sortedSet的key。
     */
    public static Long zcount(String key, double min, double max) {
        return redisTemplate.boundZSetOps(key).count(min, max);
    }

    /**
     * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递增(从小到大)来排序（SortedSet）
     *
     * @param key sortedSet的key。
     *            可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
     *            客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
     *            其中成员的位置按 score 值递增(从小到大)来排序。
     *            <p>
     *            下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     *            <p>
     *            你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     *            <p>
     *            <p>
     *            超出范围的下标并不会引起错误。
     *            <p>
     *            比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
     *            <p>
     *            另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
     *            注意：返回的set元素是数组
     */
    public static Set zrange(String key, Long min, Long max, Boolean withScores) {
        if (withScores) {
            return redisTemplate.boundZSetOps(key).rangeWithScores(min, max);
        }
        return redisTemplate.boundZSetOps(key).range(min, max);
    }

    /*
    返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。

    具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。

    可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。

    可选的 WITHSCORES 参数决定结果集是单单返回有序集的成员，还是将有序集成员及其 score 值一起返回。
    默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
    ZRANGEBYSCORE salary 1 2.3 withscores limit 0 2
    该选项自 Redis 2.0 版本起可用。
    注意：在spring-data-redis中没有封装对limit的支持，需要使用原生对象。参照下面的zRevRangeByScore
     */
    public static <T> Set zrangeByScore(String key, double min, double max, Boolean withScores) {
        if (withScores) {
            return redisTemplate.boundZSetOps(key).rangeByScoreWithScores(min, max);
        }
        return redisTemplate.boundZSetOps(key).rangeByScore(min, max);
    }

    /*
    ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]
    返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
    具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。
    除了成员按 score 值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
    可用版本：>= 2.2.0
    时间复杂度:O(log(N)+M)， N 为有序集的基数， M 为结果集的基数。
    返回值:指定区间内，带有 score 值(可选)的有序集成员的列表。
     */
    public static <T> Set zRevRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        Set<RedisZSetCommands.Tuple> result = redisTemplate.execute(new RedisCallback<Set<RedisZSetCommands.Tuple>>() {
            @Override
            public Set<RedisZSetCommands.Tuple> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.zSetCommands().zRevRangeByScoreWithScores(key.getBytes(), min, max, offset, count);
            }
        });
        return result;
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
     * <p>
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     * <p>
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
     * <p>
     * 当 key 不是有序集类型时，返回一个错误。
     * <p>
     * score 值可以是整数值或双精度浮点数。
     * 返回值:
     * member 成员的新 score 值，以字符串形式表示
     */
    public static Double zincrby(String key, Object member, Double score) {
        return redisTemplate.boundZSetOps(key).incrementScore(member, score);
    }


    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
     * <p>
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     * <p>
     * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
     */
    public static Long zrank(String key, Object memeber) {
        return redisTemplate.boundZSetOps(key).rank(memeber);
    }

    /*
    移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
    当 key 存在但不是有序集类型时，返回一个错误。
    返回值:
    被成功移除的成员的数量，不包括被忽略的成员。
     */
    public static Long zrem(String key, Object... memebers) {
        return redisTemplate.boundZSetOps(key).remove(memebers);
    }

    /*
    移除有序集 key 中，指定排名(rank)区间内的所有成员。
    区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
    下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
    你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
    返回值:
    被移除成员的数量。
     */
    public static void zremByRank(String key, Long min, Long max) {
        redisTemplate.boundZSetOps(key).removeRange(min, max);
    }

    /*
    移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
    返回值:
    被移除成员的数量。
     */
    public static void zremByScore(String key, double min, double max) {
        redisTemplate.boundZSetOps(key).removeRangeByScore(min, max);
    }

    /*
    返回有序集 key 中，成员 member 的 score 值。
    如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
    返回值:
    member 成员的 score 值，以字符串形式表示
     */
    public static <T> T zscore(final String key, Object o, Class<T> clazz) {
        return (T) redisTemplate.boundZSetOps(key).score(o);
    }

    /****************************************List相关******************************************/
/*
将一个或多个值 value 插入到列表 key 的表头
如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，
列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作
返回值：
执行 LPUSH 命令后，列表的长度
 */
    public static Long lpush(String key, Object value) {
        return redisTemplate.boundListOps(key).leftPush(value);
    }

    public static Long lpush(String key, Object... value) {
        return redisTemplate.boundListOps(key).leftPushAll(value);
    }

    /*
    移除并返回列表 key 的头元素
    返回值：
    列表的头元素。
    当 key 不存在时，返回 nil 。
     */
    public static Object lpop(String key) {
        return redisTemplate.boundListOps(key).leftPop();
    }

    /*
    移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
    timeout 参数表示的是一个指定阻塞的最大秒数的整型值。当 timeout 为 0 是表示阻塞时间无限制。
     */
    public static Object blpop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.boundListOps(key).leftPop(timeout, unit);
    }

    /*
    移除并返回列表 key 的尾元素
    返回值：
  列表的尾元素。
  当 key 不存在时，返回 nil 。
     */
    public static Object rpop(String key) {
        return redisTemplate.boundListOps(key).rightPop();
    }

    public static Object brpop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.boundListOps(key).rightPop(timeout, unit);
    }


    /*
    将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
    和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
    返回值：
    LPUSHX 命令执行之后，表的长度。
     */
    public static Long lpushx(String key, Object value) {
        return redisTemplate.boundListOps(key).leftPushIfPresent(value);
    }

    /*
    将一个或多个值 value 插入到列表 key 的表尾(最右边)。
    如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，
    得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。
     */
    public static Long rpush(String key, Object value) {
        return redisTemplate.boundListOps(key).rightPush(value);
    }

    public static Long rpush(String key, Object... value) {
        return redisTemplate.boundListOps(key).rightPushAll(value);
    }

    public static Long rpushx(String key, Object value) {
        return redisTemplate.boundListOps(key).rightPushIfPresent(value);
    }


    /*
    返回列表 key 的长度。
    如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
    如果 key 不是列表类型，返回一个错误。
    返回值：
    列表 key 的长度
     */
    public static long llen(String key) {
        return redisTemplate.boundListOps(key).size();
    }

    /*
    返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。

 假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10 ，结果是一个包含11个元素的列表，这表明 stop 下标也在 LRANGE 命令的取值范围之内(闭区间)，这和某些语言的区间函数可能不一致，比如Ruby的 Range.new 、 Array#slice 和Python的 range() 函数。
 超出范围的下标值不会引起错误。
如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，那么 LRANGE 返回一个空列表。
如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。
     */
    public static List lrange(String key, long star, long stop) {
        return redisTemplate.boundListOps(key).range(star, stop);
    }
   /*
   对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用，举个例子：
LPUSH log newest_log
LTRIM log 0 99
这个例子模拟了一个日志程序，每次将最新日志 newest_log 放到 log 列表中，并且只保留最新的 100 项。
注意当这样使用 LTRIM 命令时，时间复杂度是O(1)，因为平均情况下，每次只有一个元素被移除。
    */

    public static void ltrim(String key, long star, long stop) {
        redisTemplate.boundListOps(key).trim(star, stop);
    }

    /*
    根据参数 count 的值，移除列表中与参数 value 相等的元素。
    count 的值可以是以下几种：
    count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
    count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
    count = 0 : 移除表中所有与 value 相等的值。
    返回值：
    被移除元素的数量。
    因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
     */
    public static long lrem(String key, long count, Object value) {
        return redisTemplate.boundListOps(key).remove(count, value);
    }

    /*
     将列表 key 下标为 index 的元素的值设置为 value 。
 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。

      */
    public static void lset(String key, long index, Object value) {
        redisTemplate.boundListOps(key).set(index, value);
    }

    /*
    返回列表 key 中，下标为 index 的元素。
     */
    public static Object lindex(String key, long index) {
        return redisTemplate.boundListOps(key).index(index);
    }

    public static Object lexpire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.boundListOps(key).expire(timeout, unit);
    }

    /****************************************其它***********************************************/
/*
     获取redis服务的系统时钟
     注意：谨慎获取redis的系统时间
    */
    public static Long getTime() {
        //    return redisTemplate.getConnectionFactory().getConnection().time();
        //    logger.info("当前系统时间:"+new Date());
        return System.currentTimeMillis();
    }

    /**
     * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     *
     * @return
     */
/*    public static long getTime() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }*/

    /**
     * 获取缓存有效时间，单位：秒
     *
     * @param key KEY
     */
    public static Long getExpire(final String key) {
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serStr = redisTemplate.getStringSerializer();
                return connection.ttl(serStr.serialize(key));
            }
        });
        return result;
    }

    /**
     * 检测Redis是否能联通
     */
    public static boolean ping() {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping().contains("OK");
            }
        });
        return result;
    }

    /*设置消息键设置后，不生效的问题:
    虽然修改配置文件redis.conf中的：notify-keyspace-events Ex，但是根本没有生效
    * */
    public static void notifyKeySpaceConfig() {
        String parameter = "notify-keyspace-events";
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Properties properties = connection.getConfig(parameter);
        if (StringUtils.isEmpty(properties.getProperty(parameter))) {
            connection.setConfig(parameter, "Ex");//过期事件
        }
        logger.info(redisTemplate.getConnectionFactory().getConnection().getConfig(parameter).getProperty(parameter));
    }
}
    

