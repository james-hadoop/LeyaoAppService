package com.app_service.service;

import java.util.List;
import java.util.Map;

import com.app_service.entity.hs_user.SUserFeedbackSummary;
import com.app_service.entity.hs_user.TUserSummary;

public interface IHsUserService {
    List<TUserSummary> getTUserSummary(Map<String, Object> paramMap);

    int regist(TUserSummary tUserSummary);

    String login(TUserSummary tUserSummary);

    int logout(TUserSummary tUserSummary);

    int reset(TUserSummary tUserSummary);

    String getVerifyCode(String hUserPhoneNr);

    TUserSummary selectByhUserPhoneNr(Long hUserPhoneNr);

    int addTUserSummary(TUserSummary tUserSummary);

    int editTUserSummary(TUserSummary tUserSummary);

    int deleteTUserSummary(TUserSummary tUserSummary);

    List<SUserFeedbackSummary> getSUserFeedbackSummaryByCondition(Map<String, Object> paramMap);

    int getSUserFeedbackSummaryByConditionCount(Map<String, Object> paramMap);

    int addSUserFeedbackSummary(SUserFeedbackSummary sUserFeedbackSummary);

    String uploadFileBase64(String fileBase64, String fileName, String token, Long hUserPhoneNr, String currentPortraitName);
}
