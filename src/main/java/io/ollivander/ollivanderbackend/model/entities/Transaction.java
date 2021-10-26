package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

    @Column(name = "code")
    private String code;

    @Column(name = "type", columnDefinition = "SMALLINT")
    private Integer type;   // the type of order transaction can be either Credit or Debit

    @Column(name = "mode", columnDefinition = "SMALLINT")
    private Integer mode;   // the mode of the order transaction: OFFLINE, CASH ON DELIVERY, CHEQUE, DRAFT, WIRED, ONLINE

    @Column(name = "status", columnDefinition = "SMALLINT")
    private Integer status; // the status of the order transaction: NEW, CANCELLED, FAILED, PENDING, DECLINED, REJECTED, SUCCESS

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content")
    @Lob
    private String content;

    public Transaction() {
    }

    public Transaction(Integer id, Account account, Orders orders, String code, Integer type, Integer mode, Integer status, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.account = account;
        this.orders = orders;
        this.code = code;
        this.type = type;
        this.mode = mode;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
