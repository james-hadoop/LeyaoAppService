package com.app_service.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app_service.common.Response;
import com.app_service.dao.mapper.hs_user.TUserPageMapper;
import com.app_service.dao.mapper.ls_user_event.LUserEventMapper;
import com.app_service.dao.mapper.ls_user_event.SUserEventActiveMapper;
import com.app_service.dao.mapper.ls_user_event.SUserEventLikeMapper;
import com.app_service.dao.mapper.ls_user_event.SUserEventReadMapper;
import com.app_service.entity.hs_user.TUserSummary;
import com.app_service.entity.ls_user_event.LUserEvent;
import com.app_service.entity.ls_user_event.SUserEventActive;
import com.app_service.entity.ls_user_event.SUserEventLike;
import com.app_service.entity.ls_user_event.SUserEventRead;
import com.app_service.entity.ls_user_event.TUserEventSummary;
import com.app_service.entity.ls_user_event.enums.RUserEventCategoryEnum;
import com.app_service.service.ILsUserEventService;
import com.app_service.util.LsUserEventUtil;

@Service
public class LsUserEventServiceImpl implements ILsUserEventService {
    @Autowired
    LUserEventMapper lUserEventMapper;

    @Autowired
    SUserEventActiveMapper sUserEventActiveMapper;

    @Autowired
    SUserEventLikeMapper sUserEventLikeMapper;

    @Autowired
    SUserEventReadMapper sUserEventReadMapper;

    @Autowired
    TUserPageMapper tUserPageMapper;

    @Override
    @Transactional
    public int feedbackTUserEventSummary(TUserEventSummary tUserEventSummary) {
        Date timestamp = new Date();
        tUserEventSummary.setCreateTs(timestamp);
        tUserEventSummary.setUpdateTs(timestamp);

        // LUserEvent
        TUserSummary user = tUserPageMapper.selectByhUserPhoneNr(tUserEventSummary.gethUserPhoneNr());
        tUserEventSummary.sethUserId(user.gethUserId());
        tUserEventSummary.sethEventId(tUserEventSummary.gethEventId());

        LUserEvent lUserEvent = LsUserEventUtil.userEventSummary2UserEvent(tUserEventSummary);
        lUserEventMapper.insertSelective(lUserEvent);
        long lUserEventId = lUserEventMapper.getMaxLUserEventId();

        tUserEventSummary.setlUserEventId(lUserEventId);

        // SUserEventActive
        SUserEventActive sUserEventActive = LsUserEventUtil.userEventSummary2UserEventActive(tUserEventSummary);
        sUserEventActiveMapper.insertSelective(sUserEventActive);

        // SUserEventLike
        if (null != tUserEventSummary.getsUserEventLikeInd()) {
            SUserEventLike sUserEventLike = LsUserEventUtil.userEventSummary2UserEventLike(tUserEventSummary);
            sUserEventLikeMapper.insertSelective(sUserEventLike);
        }

        // SUserEventRead
        if (null != tUserEventSummary.getsUserEventReadLogTxt()) {
            SUserEventRead sUserEventRead = LsUserEventUtil.userEventSummary2UserEventRead(tUserEventSummary);
            sUserEventReadMapper.insertSelective(sUserEventRead);
        }

        return Response.SUCCESS;
    }
}