package aforo255.ms.test.pay.service;

import aforo255.ms.test.pay.dao.OperationRedisRepo;
import aforo255.ms.test.pay.dao.OperationRepository;
import aforo255.ms.test.pay.domain.Operation;
import aforo255.ms.test.pay.domain.OperationRedis;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class PaymentService implements CRUD<Operation> {

    private final OperationRepository operationRepository;
    private final OperationRedisRepo operationRedisRepo;

    public PaymentService(OperationRepository operationRepository, OperationRedisRepo operationRedisRepo) {
        this.operationRepository = operationRepository;
        this.operationRedisRepo = operationRedisRepo;
    }

    @Override
    public Operation findById(Long id) {
        return null;
    }

    public OperationRedis findByIdRedis(String id){
        return operationRedisRepo.findById(id);
    }

    public void save(OperationRedis operationRedis){
        operationRedisRepo.save(operationRedis);
    }

    @Override
    public Operation save(Operation record) {
        if (record.getDate()==null){
            record.setDate(getCurrentInstant());
        }
        return operationRepository.save(record);
    }

    public Map<String, OperationRedis> findAll(){
        return operationRedisRepo.findAll();
    }

    public static Instant getCurrentInstant() {
        return Instant.now();
    }

    public static Instant getCurrentInstantBogota() {
        return getCurrentInstant().minus(5L, ChronoUnit.HOURS);
    }
}
