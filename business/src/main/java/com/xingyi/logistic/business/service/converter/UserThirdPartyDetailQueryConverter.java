package com.xingyi.logistic.business.service.converter;

import com.xingyi.logistic.business.service.base.QueryConditionConverter;
import com.xingyi.logistic.business.db.entity.UserThirdPartyDetailDBQuery;
import com.xingyi.logistic.business.model.UserThirdPartyDetailQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * auto generated by code helper on 2018-11-04.
 */
@Component
public class UserThirdPartyDetailQueryConverter extends QueryConditionConverter<UserThirdPartyDetailQuery, UserThirdPartyDetailDBQuery> {

    @Override
    public UserThirdPartyDetailDBQuery toDOCondition(UserThirdPartyDetailQuery src) {
        UserThirdPartyDetailDBQuery dst = new UserThirdPartyDetailDBQuery();
        if (src != null) {
            paginationConvert(src, dst);
            BeanUtils.copyProperties(src, dst);
        }
        return dst;
    }

}