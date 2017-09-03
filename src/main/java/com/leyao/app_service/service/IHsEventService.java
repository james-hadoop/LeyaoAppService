package com.leyao.app_service.service;

import java.util.List;
import java.util.Map;

import com.leyao.app_service.entity.hs_event.SEventBanner;
import com.leyao.app_service.entity.hs_event.TEventSummary;

public interface IHsEventService {
    String checkUpdate();

    List<TEventSummary> getTEventSummaryList(Map<String, Object> paramMap);
    int getTEventSummaryListCount(Map<String, Object> paramMap);

    void createHsEvent(TEventSummary record);

//    List<SEventBanner> getSEventBannerList(Map<String, Object> paramMap);
//
//    void createSEventBanner(SEventBanner record);
}