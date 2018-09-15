package com.xingyi.logistic.controller;

import com.xingyi.logistic.aop.annotation.Biz;
import com.xingyi.logistic.business.model.DangerZone;
import com.xingyi.logistic.business.model.DangerZoneQuery;
import com.xingyi.logistic.business.service.BaseService;
import com.xingyi.logistic.business.service.DangerZoneService;
import com.xingyi.logistic.common.bean.JsonRet;
import com.xingyi.logistic.config.JsonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 危险区域
 */
@Biz("危险区域")
@RestController
@RequestMapping("/dangerZone")
public class DangerZoneController extends BaseCRUDController<DangerZone, DangerZoneQuery> {

    @Autowired
    private DangerZoneService dangerZoneService;

    @Override
    protected BaseService<DangerZone, DangerZoneQuery> getBaseService() {
        return dangerZoneService;
    }

    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public JsonRet<Object> getPage(@JsonParam DangerZoneQuery dangerZoneQuery) {
        return super.getList(dangerZoneQuery);
    }
}
