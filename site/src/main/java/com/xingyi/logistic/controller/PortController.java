package com.xingyi.logistic.controller;

import com.xingyi.logistic.business.model.Port;
import com.xingyi.logistic.business.model.PortQuery;
import com.xingyi.logistic.business.service.BaseService;
import com.xingyi.logistic.business.service.PortService;
import com.xingyi.logistic.common.bean.JsonRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jadic on 2017/12/31.
 */
@RestController
@RequestMapping("/port")
public class PortController extends BaseCRUDController<Port, PortQuery> {

    @Autowired
    private PortService portService;

    @Override
    public JsonRet<Integer> add(Port port) {
        return super.add(port);
    }

    @Override
    public JsonRet<Boolean> modify(Port port) {
        return super.modify(port);
    }

    @Override
    public JsonRet<Boolean> del(Long id) {
        return super.del(id);
    }

    @Override
    public JsonRet<Port> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public JsonRet<Object> getList(PortQuery portQuery) {
        return super.getList(portQuery);
    }

    @Override
    protected BaseService<Port, PortQuery> getBaseService() {
        return portService;
    }
}
