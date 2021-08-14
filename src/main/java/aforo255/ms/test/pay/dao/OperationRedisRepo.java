package aforo255.ms.test.pay.dao;

import aforo255.ms.test.pay.domain.OperationRedis;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class OperationRedisRepo implements IOperationRedis{

    private final RedisTemplate<String, OperationRedis> redisTemplate;
    private HashOperations hashOperations;
    private String key = "TRANSACTION";

    public OperationRedisRepo(RedisTemplate<String, OperationRedis> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(OperationRedis operation) {
        hashOperations.put(key, operation.getIdOperation(), operation);
    }

    @Override
    public Map<String, OperationRedis> findAll() {
        return hashOperations.entries(key);
    }

    @Override
    public OperationRedis findById(String id) {
        return (OperationRedis) hashOperations.get(key, id);
    }

    @Override
    public void update(OperationRedis operation) {
        save(operation);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(key, id);
    }
}
