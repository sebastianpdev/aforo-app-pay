package aforo255.ms.test.pay.service;

public interface CRUD<E> {

    public E findById(Long id);

    public E save(E record);

}
