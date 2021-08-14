package aforo255.ms.test.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "id_invoice")
    private Integer idInvoice;

    private double amount;

    private Instant date;


    public Integer getIdOperation() {
        return idOperation;
    }

    public Operation setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public Operation setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Operation setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Instant getDate() {
        return date;
    }

    public Operation setDate(Instant date) {
        this.date = date;
        return this;
    }
}
