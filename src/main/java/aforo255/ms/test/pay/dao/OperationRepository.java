package aforo255.ms.test.pay.dao;

import aforo255.ms.test.pay.domain.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
