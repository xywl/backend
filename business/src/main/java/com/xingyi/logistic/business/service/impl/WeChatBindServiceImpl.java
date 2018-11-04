package com.xingyi.logistic.business.service.impl;

import com.xingyi.logistic.authentication.model.LocalAuth;
import com.xingyi.logistic.authentication.model.LocalAuthQuery;
import com.xingyi.logistic.authentication.service.LocalAuthService;
import com.xingyi.logistic.authentication.service.wechat.WeChatService;
import com.xingyi.logistic.authentication.util.DigestUtil;
import com.xingyi.logistic.business.bean.wechat.AppSecretConfig;
import com.xingyi.logistic.business.bean.wechat.AppType;
import com.xingyi.logistic.business.bean.wechat.OpenIdResponse;
import com.xingyi.logistic.business.bean.wechat.UnionIdResponse;
import com.xingyi.logistic.business.model.UserThirdParty;
import com.xingyi.logistic.business.model.UserThirdPartyQuery;
import com.xingyi.logistic.business.service.UserThirdPartyService;
import com.xingyi.logistic.business.service.WeChatBindService;
import com.xingyi.logistic.common.bean.ErrCode;
import com.xingyi.logistic.common.bean.JsonRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by xiaohu on 2018/11/2.
 */
@Service
public class WeChatBindServiceImpl implements WeChatBindService {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private UserThirdPartyService userThirdPartyService;

    @Autowired
    private LocalAuthService localAuthService;

    @Value("#{30L * 24L * 3600L * 1000L}")
    private long tokenExpire;

    @Override
    public JsonRet<Object> checkBind(String code, int appType) {
        if (StringUtils.isEmpty(code)) {
            return JsonRet.getErrRet(ErrCode.WECHAT_APP_TYPE_INVALID);
        }
        //根据appType获取相应的appId, appSecret
        AppSecretConfig appSecretConfig = weChatService.getAppSecretConfig(appType);
        if (appSecretConfig == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_APP_TYPE_INVALID);
        }

        //获取openId
        OpenIdResponse openIdResponse = weChatService.getOpenId(appSecretConfig.getAppId(), appSecretConfig.getAppSecret(), code);
        if (openIdResponse == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_OPENID_ERR);
        }
        if (StringUtils.isEmpty(openIdResponse.getOpenId())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_OPENID_ERR.getCode(), openIdResponse.getErrMsg());
        }

        //获取accessToken
        String accessToken = weChatService.getAccessToken(appSecretConfig.getAppId(), appSecretConfig.getAppSecret());
        if (StringUtils.isEmpty(accessToken)) {
            return JsonRet.getErrRet(ErrCode.WECHAT_ACCESS_TOKEN_EMPTY);
        }

        //获取unionId
        String openId = openIdResponse.getOpenId();
        UnionIdResponse unionIdResponse = weChatService.getUnionId(accessToken, openId);
        if (unionIdResponse == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_UNIONID_ERR);
        }
        if (StringUtils.isEmpty(unionIdResponse.getUnionId())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_UNIONID_ERR.getCode(), unionIdResponse.getErrMsg());
        }

        //根据unionId获取user信息
        UserThirdPartyQuery queryParam = new UserThirdPartyQuery();
        queryParam.setThirdId(unionIdResponse.getUnionId());
        JsonRet<List<UserThirdParty>> userThirdPartyRet = userThirdPartyService.getList(queryParam);
        if (!userThirdPartyRet.isSuccess() || CollectionUtils.isEmpty(userThirdPartyRet.getData())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_NOT_BIND);
        }
        UserThirdParty userThirdParty = userThirdPartyRet.getData().get(0);
        LocalAuth localAuth = localAuthService.queryByUserId(userThirdParty.getUserId());
        if (localAuth == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_BIND_USER_NOT_EXIST);
        }

        //如果是来自小程序的，则执行登录过程，返回token，其余的则返回绑定的userName
        if (appType == AppType.MINI_PROGRAM) {
            long expire = System.currentTimeMillis() + this.tokenExpire;
            String md5 = DigestUtil.md5(String.valueOf(localAuth.getUserId()), localAuth.getLoginName(), localAuth.getPasswd(), String.valueOf(expire));
            String token = localAuth.getUserId() + ":" + md5 + ":" + expire;
            token = Base64Utils.encodeToString(token.getBytes());
            return JsonRet.getSuccessRet(token);
        } else {
            return JsonRet.getSuccessRet(localAuth.getLoginName());
        }
    }

    @Override
    public JsonRet<Object> bindFromMP(String code, String userName, String userPass) {
        // 校验用户名、密码是否正确 从SignController中拷贝
        LocalAuthQuery localAuthQuery = new LocalAuthQuery(userName);
        JsonRet<List<LocalAuth>> localAuthRet = this.localAuthService.getList(localAuthQuery);
        List<LocalAuth> localAuthList = localAuthRet.getData();

        //账号不存在
        if (CollectionUtils.isEmpty(localAuthList)) {
            return JsonRet.getErrRet(ErrCode.AUTHTICATION_NOT_EXIST.getCode(), ErrCode.AUTHTICATION_NOT_EXIST.getMsg());
        }

        LocalAuth localAuth = localAuthList.get(0);
        String localPasswd = localAuth.getPasswd();
        //密码错误
        if (!org.apache.commons.lang3.StringUtils.equals(userPass, localPasswd)) {
            return JsonRet.getErrRet(ErrCode.AUTHTICATION_PASSWD_ERROR.getCode(), ErrCode.AUTHTICATION_PASSWD_ERROR.getMsg());
        }

        // 获取微信用户信息
        JsonRet<UnionIdResponse> unionIdRet = getUnionId(code, AppType.MP);
        if (!unionIdRet.isSuccess()) {
            return JsonRet.getErrRet(unionIdRet.getErrCode(), unionIdRet.getMsg());
        }

        // 校验当前微信用户是否已经绑定
        UnionIdResponse unionIdResponse = unionIdRet.getData();
        UserThirdPartyQuery queryParam = new UserThirdPartyQuery();
        queryParam.setThirdId(unionIdResponse.getUnionId());
        JsonRet<List<UserThirdParty>> userThirdPartyRet = userThirdPartyService.getList(queryParam);
        if (userThirdPartyRet.isSuccess() && !CollectionUtils.isEmpty(userThirdPartyRet.getData())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_ALREADY_BIND);
        }

        // 建立绑定关系
        UserThirdParty userThirdParty = new UserThirdParty();
        userThirdParty.setThirdId(unionIdResponse.getUnionId());
        userThirdParty.setThirdType(0);
        userThirdParty.setThirdName(unionIdResponse.getNickName());
        userThirdParty.setUserId(localAuth.getUserId());
        JsonRet<Long> addRet = userThirdPartyService.add(userThirdParty);
        if (addRet.isSuccess()) {
            return JsonRet.getSuccessRet(true);
        } else {
            return JsonRet.getErrRet(ErrCode.WECHAT_BIND_ERR);
        }
    }

    private JsonRet<UnionIdResponse> getUnionId(String code, int appType) {
        if (StringUtils.isEmpty(code)) {
            return JsonRet.getErrRet(ErrCode.WECHAT_APP_TYPE_INVALID);
        }
        //根据appType获取相应的appId, appSecret
        AppSecretConfig appSecretConfig = weChatService.getAppSecretConfig(appType);
        if (appSecretConfig == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_APP_TYPE_INVALID);
        }

        //获取openId
        OpenIdResponse openIdResponse = weChatService.getOpenId(appSecretConfig.getAppId(), appSecretConfig.getAppSecret(), code);
        if (openIdResponse == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_OPENID_ERR);
        }
        if (StringUtils.isEmpty(openIdResponse.getOpenId())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_OPENID_ERR.getCode(), openIdResponse.getErrMsg());
        }

        //获取accessToken
        String accessToken = weChatService.getAccessToken(appSecretConfig.getAppId(), appSecretConfig.getAppSecret());
        if (StringUtils.isEmpty(accessToken)) {
            return JsonRet.getErrRet(ErrCode.WECHAT_ACCESS_TOKEN_EMPTY);
        }

        //获取unionId
        String openId = openIdResponse.getOpenId();
        UnionIdResponse unionIdResponse = weChatService.getUnionId(accessToken, openId);
        if (unionIdResponse == null) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_UNIONID_ERR);
        }
        if (StringUtils.isEmpty(unionIdResponse.getUnionId())) {
            return JsonRet.getErrRet(ErrCode.WECHAT_GET_UNIONID_ERR.getCode(), unionIdResponse.getErrMsg());
        }
        return JsonRet.getSuccessRet(unionIdResponse);
    }
}
