package aforo255.ms.test.pay.domain;

import java.io.Serializable;
import java.util.Date;

public class OperationRedis implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idOperation;
    private String idInvoice;
    private double amount;
    private Date date;

    public String getIdOperation() {
        return idOperation;
    }

    public OperationRedis setIdOperation(String idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public OperationRedis setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public OperationRedis setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public OperationRedis setDate(Date date) {
        this.date = date;
        return this;
    }
}
