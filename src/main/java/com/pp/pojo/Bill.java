package com.pp.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <Description> <br>
 *
 * @author Coder_Lin<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2021/03/11 15:18 <br>
 * @see com.coderlin.pojo <br>
 */
public class Bill {
    private Integer id;         //id
    private String billCode;    //订单编号
    private String productName;
    private String productDesc;
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private Integer isPayment;
    private Integer providerId;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;

    private String providerName;


    public Integer getId() {
        return id;
    }

    public String getBillCode() {
        return billCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
