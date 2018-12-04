package com.xingyi.logistic.business.db.dao;

import com.xingyi.logistic.business.db.dao.base.BaseDAO;
import com.xingyi.logistic.business.db.entity.ShipStaffDBQuery;
import com.xingyi.logistic.business.db.entity.ShipStaffDO;
import com.xingyi.logistic.business.model.ShipStaff;
import com.xxx.boot.jdbc.annotation.Dao;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jadic on 2017/12/31.
 */
@Dao
public interface ShipStaffDAO extends BaseDAO<ShipStaffDO, ShipStaffDBQuery> {
     int judege(@Param("pojo")ShipStaff shipStaff);

     ShipStaffDO getShipOwnerByShipId(@Param("shipId") long shipId);
}
