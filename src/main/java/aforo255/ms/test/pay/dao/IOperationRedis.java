package aforo255.ms.test.pay.dao;

import aforo255.ms.test.pay.domain.OperationRedis;

import java.util.Map;

public interface IOperationRedis {
    void save (OperationRedis operation);
    Map<String, OperationRedis> findAll();
    OperationRedis findById(String id );
    void update (OperationRedis operation);
    void delete (String id );
}
