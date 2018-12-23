package com.xingyi.logistic.business.model;

/**
 * Created by WCL on 2018/1/24.
 */
public class DispatchData
{
    private Long id;
    private Long shipId;
    private String orderNo;
    private Integer dispatchType;
    private Integer shipType;
    private Integer status;
    private Integer settleType;
    private Integer preWeight;
    private Integer preLoad;
    private Integer preArriveTime;
    private Double preSettleAmount;
    private Long startPortId;
    private Long endPortId;
    private String goodsName;
    private String sender;
    private String receiver;
    private String name;
    private String mobile;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Integer dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getPreWeight() {
        return preWeight;
    }

    public void setPreWeight(Integer preWeight) {
        this.preWeight = preWeight;
    }

    public Integer getPreLoad() {
        return preLoad;
    }

    public void setPreLoad(Integer preLoad) {
        this.preLoad = preLoad;
    }

    public Integer getPreArriveTime() {
        return preArriveTime;
    }

    public void setPreArriveTime(Integer preArriveTime) {
        this.preArriveTime = preArriveTime;
    }

    public Double getPreSettleAmount() {
        return preSettleAmount;
    }

    public void setPreSettleAmount(Double preSettleAmount) {
        this.preSettleAmount = preSettleAmount;
    }

    public Long getStartPortId() {
        return startPortId;
    }

    public void setStartPortId(Long startPortId) {
        this.startPortId = startPortId;
    }

    public Long getEndPortId() {
        return endPortId;
    }

    public void setEndPortId(Long endPortId) {
        this.endPortId = endPortId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
