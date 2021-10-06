package io.ollivander.ollivanderbackend.model.dto;

import java.util.Date;

public class CartResponse {
    private Integer id;
    private Integer accountId;
    private String sessionId;
    private String token;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    public CartResponse() {
    }

    public CartResponse(Integer id, Integer accountId, String sessionId, String token, Integer status, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.accountId = accountId;
        this.sessionId = sessionId;
        this.token = token;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
