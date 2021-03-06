package com.app_service.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app_service.common.GlobalConstant;
import com.app_service.entity.hs_event.HsEvent;
import com.app_service.entity.hs_event.SEventActive;
import com.app_service.entity.hs_event.SEventBanner;
import com.app_service.entity.hs_event.SEventCategory;
import com.app_service.entity.hs_event.SEventContent;
import com.app_service.entity.hs_event.SEventRecom1;
import com.app_service.entity.hs_event.SEventRecom2;
import com.app_service.entity.hs_event.SEventRecom3;
import com.app_service.entity.hs_event.SEventSubContent1;
import com.app_service.entity.hs_event.SEventSubContent2;
import com.app_service.entity.hs_event.SEventType;
import com.app_service.entity.hs_event.SubContentJsonEntity;
import com.app_service.entity.hs_event.SubContentJsonEntityWithoutUrl;
import com.app_service.entity.hs_event.TEventPage;
import com.app_service.entity.hs_event.TEventSummary;
import com.app_service.entity.hs_event.enums.REventCategoryEnum;
import com.app_service.entity.hs_event.enums.REventTypeEnum;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HsEventUtil {
    public static List<TEventPage> eventSummaryList2EventPageList(List<TEventSummary> tEventSummaryList)
            throws JsonParseException, JsonMappingException, IOException {
        if (null == tEventSummaryList || 0 == tEventSummaryList.size()) {
            return null;
        }

        List<TEventPage> tEventPageList = new ArrayList<TEventPage>();

        for (TEventSummary es : tEventSummaryList) {
            tEventPageList.addAll(eventSummary2EventPageList(es));
        }

        return tEventPageList;
    }

    public static List<TEventPage> eventSummary2EventPageList(TEventSummary es)
            throws JsonParseException, JsonMappingException, IOException {
        if (null == es) {
            return null;
        }

        List<TEventPage> tEventPageList = new ArrayList<TEventPage>();

        List<SEventSubContent1> subContent1s = HsEventUtil.eventSummary2EventSubContent1(es);
        List<String> subContent1Url = new ArrayList<String>();
        if (null != subContent1s) {
            for (SEventSubContent1 subContent1 : subContent1s) {
                subContent1Url.add(subContent1.toString());
            }
        }

        List<SEventSubContent2> subContent2s = HsEventUtil.eventSummary2EventSubContent2(es);
        List<String> subContent2Str = es.getsEventSubContent2StrList();
        if (null != subContent2s) {
            for (SEventSubContent2 subContent2 : subContent2s) {
                subContent2Str.add(subContent2.toString());
            }
        }

        if (null == subContent1Url || 0 == subContent1Url.size()) {
            TEventPage ep = new TEventPage();

            ep.sethEventId(es.gethEventId());
            ep.setsEventCategoryCd(es.getsEventCategoryCd());
            ep.setrEventCategoryDesc(REventCategoryEnum.getValue(es.getsEventCategoryCd()));
            ep.setsEventTypeCd(es.getsEventTypeCd());
            ep.setrEventTypeDesc(REventTypeEnum.getValue(es.getsEventTypeCd()));
            ep.setsEventTitleUrl(es.getsEventTitleUrl());
            ep.setsEventContentUrl(es.getsEventContentUrl());
            ep.setsEventActiveInd(es.getsEventActiveInd());
            ep.setCreateTs(es.getCreateTs());
            ep.setUpdateTs(es.getUpdateTs());
            ep.setsEventSearchContentTxt(es.getsEventSearchContentTxt());

            tEventPageList.add(ep);
        } else {
            for (int i = 0; i < subContent1Url.size(); i++) {
                TEventPage ep = new TEventPage();

                ep.sethEventId(es.gethEventId());
                ep.setsEventCategoryCd(es.getsEventCategoryCd());
                ep.setrEventCategoryDesc(REventCategoryEnum.getValue(es.getsEventCategoryCd()));
                ep.setsEventTypeCd(es.getsEventTypeCd());
                ep.setrEventTypeDesc(REventTypeEnum.getValue(es.getsEventTypeCd()));
                ep.setsEventTitleUrl(es.getsEventTitleUrl());
                ep.setsEventContentUrl(es.getsEventContentUrl());
                ep.setsEventSubContent1Url(subContent1Url.get(i));
                ep.setsEventSubContent2Str(subContent2Str.get(i));
                ep.setsEventActiveInd(es.getsEventActiveInd());
                ep.setCreateTs(es.getCreateTs());
                ep.setUpdateTs(es.getUpdateTs());
                ep.setsEventSearchContentTxt(es.getsEventSearchContentTxt());

                tEventPageList.add(ep);
            }
        }

        return tEventPageList;
    }

    public static List<TEventSummary> eventPageList2EventSummaryList(List<TEventPage> tEventPageList) {
        if (null == tEventPageList || 0 == tEventPageList.size()) {
            return null;
        }

        List<TEventSummary> tEventSummaryList = new ArrayList<TEventSummary>();

        Set<Long> eventIdSet = new HashSet<Long>();

        // java.util.Collections.sort(tEventPageList, null);
        // for (TEventPage ep : tEventPageList) {
        // System.out.print(ep.gethEventId());
        // }

        TEventSummary es = null;
        for (TEventPage ep : tEventPageList) {
            Long eventId = ep.gethEventId();

            if (eventIdSet.contains(eventId)) {
                String sEventSubContent1Url = ep.getsEventSubContent1Url();
                if (null != sEventSubContent1Url && !GlobalConstant.NULL_STRING.equals(sEventSubContent1Url)) {
                    es.getsEventSubContent1UrlList().add(sEventSubContent1Url);
                }

                String sEventSubContent2Str = ep.getsEventSubContent2Str();
                if (null != sEventSubContent2Str && !GlobalConstant.NULL_STRING.equals(sEventSubContent2Str)) {
                    es.getsEventSubContent2StrList().add(sEventSubContent2Str);
                }
            } else {
                eventIdSet.add(eventId);

                if (null != es) {
                    tEventSummaryList.add(es);
                }

                es = new TEventSummary();

                es.sethEventId(ep.gethEventId());
                es.setsEventCategoryCd(ep.getsEventCategoryCd());
                es.setrEventCategoryDesc(ep.getrEventCategoryDesc());
                es.setsEventTypeCd(ep.getsEventTypeCd());
                es.setrEventTypeDesc(ep.getrEventTypeDesc());
                es.setsEventTitleUrl(ep.getsEventTitleUrl());
                es.setsEventContentUrl(ep.getsEventContentUrl());

                String sEventSubContent1Url = ep.getsEventSubContent1Url();
                if (null != sEventSubContent1Url && !GlobalConstant.NULL_STRING.equals(sEventSubContent1Url)) {
                    es.getsEventSubContent1UrlList().add(sEventSubContent1Url);
                }

                String sEventSubContent2Str = ep.getsEventSubContent2Str();
                if (null != sEventSubContent2Str && !GlobalConstant.NULL_STRING.equals(sEventSubContent2Str)) {
                    es.getsEventSubContent2StrList().add(sEventSubContent2Str);
                }

                es.setsEventActiveInd(ep.getsEventActiveInd());
                es.setCreateTs(ep.getCreateTs());
                es.setUpdateTs(ep.getUpdateTs());
                es.setsEventSearchContentTxt(ep.getsEventSearchContentTxt());
            }
        }

        if (null != es) {
            tEventSummaryList.add(es);
        }

        HsEventUtil.makeSubContent(tEventSummaryList);

        return tEventSummaryList;
    }

    public static List<TEventSummary> eventPageList2EventSummaryListWithoutSubEvents(List<TEventPage> tEventPageList) {
        if (null == tEventPageList || 0 == tEventPageList.size()) {
            return null;
        }

        List<TEventSummary> tEventSummaryList = new ArrayList<TEventSummary>();

        for (TEventPage ep : tEventPageList) {
            TEventSummary es = new TEventSummary();

            es.sethEventId(ep.gethEventId());
            es.setsEventCategoryCd(ep.getsEventCategoryCd());
            es.setrEventCategoryDesc(ep.getrEventCategoryDesc());
            es.setsEventTypeCd(ep.getsEventTypeCd());
            es.setrEventTypeDesc(ep.getrEventTypeDesc());
            es.setsEventTitleUrl(ep.getsEventTitleUrl());
            es.setsEventContentUrl(ep.getsEventContentUrl());

            es.setsEventActiveInd(ep.getsEventActiveInd());
            es.setCreateTs(ep.getCreateTs());
            es.setUpdateTs(ep.getUpdateTs());
            es.setsEventSearchContentTxt(ep.getsEventSearchContentTxt());
            
            tEventSummaryList.add(es);
        }

        return tEventSummaryList;
    }

    public static List<TEventSummary> makeSubContent(List<TEventSummary> eventSummaryList) {
        try {
            if (null == eventSummaryList || 0 == eventSummaryList.size()) {
                return null;
            }

            for (TEventSummary event : eventSummaryList) {
                List<String> subContent2StrList = event.getsEventSubContent2StrList();
                List<String> subContent1UrlList = event.getsEventSubContent1UrlList();

                if (null != subContent2StrList && 0 != subContent2StrList.size() && null != subContent1UrlList
                        && 0 != subContent1UrlList.size()) {
                    List<SubContentJsonEntity> subContentJsonEntityList = new ArrayList<SubContentJsonEntity>();

                    for (int i = 0; i < subContent2StrList.size(); i++) {
                        String subContent2Str = subContent2StrList.get(i);
                        String subContent1Url = subContent1UrlList.get(i);

                        SubContentJsonEntityWithoutUrl subContentJsonEntityWithoutUrl = HsEventUtil
                                .JsonString2SubContentJsonEntityWithoutUrl(subContent2Str);
                        SubContentJsonEntity subContentJsonEntity = new SubContentJsonEntity(
                                subContentJsonEntityWithoutUrl);
                        subContentJsonEntity.setUrl(subContent1Url);
                        subContentJsonEntityList.add(subContentJsonEntity);
                    }

                    event.setsEventSubContentString(
                            HsEventUtil.SubContentJsonEntity2JsonString(subContentJsonEntityList));
                    event.setsEventSubContent(subContentJsonEntityList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return eventSummaryList;
        }

        return eventSummaryList;
    }

    public static HsEvent eventSummary2Event(TEventSummary es) {
        HsEvent event = new HsEvent();
        event.sethEventId(es.gethEventId());
        event.setCreateTs(es.getCreateTs());
        event.sethEventId(es.gethEventId());
        event.setUpdateTs(es.getUpdateTs());

        return event;
    }

    public static SEventActive eventSummary2EventActive(TEventSummary es) {
        SEventActive eventActive = new SEventActive();
        eventActive.sethEventId(es.gethEventId());
        eventActive.setCreateTs(es.getCreateTs());
        eventActive.sethEventId(es.gethEventId());
        eventActive.setsEventActiveInd(es.getsEventActiveInd());
        eventActive.setUpdateTs(es.getUpdateTs());

        return eventActive;
    }

    public static SEventBanner eventSummary2EventBanner(TEventSummary es) {
        SEventBanner sEventBanner = new SEventBanner();
        sEventBanner.setCreateTs(es.getCreateTs());
        sEventBanner.sethEventId(es.gethEventId());
        sEventBanner.setsEventBannerActiveInd(es.getsEventActiveInd());
        sEventBanner.setsEventBannerPositionCd(es.getsEventBannerPositionCd());
        sEventBanner.setUpdateTs(es.getUpdateTs());

        return sEventBanner;
    }

    public static SEventCategory eventSummary2EventCategory(TEventSummary es) {
        SEventCategory sEventCategory = new SEventCategory();
        sEventCategory.setCreateTs(es.getCreateTs());
        sEventCategory.sethEventId(es.gethEventId());
        sEventCategory.setsEventCategoryCd(es.getsEventCategoryCd());
        sEventCategory.setUpdateTs(es.getUpdateTs());

        return sEventCategory;
    }

    public static SEventContent eventSummary2EventContent(TEventSummary es) {
        SEventContent sEventContent = new SEventContent();
        sEventContent.setCreateTs(es.getCreateTs());
        sEventContent.sethEventId(es.gethEventId());
        sEventContent.setsEventContentUrl(es.getsEventContentUrl());
        sEventContent.setsEventTitleUrl(es.getsEventTitleUrl());
        sEventContent.setUpdateTs(es.getUpdateTs());

        return sEventContent;
    }

    public static SEventRecom1 eventSummary2EventRecom1(TEventSummary es) {
        if (es.getsEventTypeCd() != REventTypeEnum.Audio.getCode()) {
            return null;
        }

        SEventRecom1 sEventRecom1 = new SEventRecom1();
        sEventRecom1.setCreateTs(es.getCreateTs());
        sEventRecom1.sethEventId(es.gethEventId());
        sEventRecom1.setsEventRecomActiveInd(es.getsEventActiveInd());
        sEventRecom1.setsEventRecomPositionCd(es.getsEventRecomPositionCd(false));
        sEventRecom1.setUpdateTs(es.getUpdateTs());

        return sEventRecom1;
    }

    public static SEventRecom2 eventSummary2EventRecom2(TEventSummary es) {
        if (es.getsEventTypeCd() != REventTypeEnum.Video.getCode()) {
            return null;
        }

        SEventRecom2 sEventRecom2 = new SEventRecom2();
        sEventRecom2.setCreateTs(es.getCreateTs());
        sEventRecom2.sethEventId(es.gethEventId());
        sEventRecom2.setsEventRecomActiveInd(es.getsEventActiveInd());
        sEventRecom2.setsEventRecomPositionCd(es.getsEventRecomPositionCd(false));
        sEventRecom2.setUpdateTs(es.getUpdateTs());

        return sEventRecom2;
    }

    public static SEventRecom3 eventSummary2EventRecom3(TEventSummary es) {
        if (es.getsEventTypeCd() != REventTypeEnum.Image.getCode()) {
            return null;
        }

        SEventRecom3 sEventRecom3 = new SEventRecom3();
        sEventRecom3.setCreateTs(es.getCreateTs());
        sEventRecom3.sethEventId(es.gethEventId());
        sEventRecom3.setsEventRecomActiveInd(es.getsEventActiveInd());
        sEventRecom3.setsEventRecomPositionCd(es.getsEventRecomPositionCd(false));
        sEventRecom3.setUpdateTs(es.getUpdateTs());

        return sEventRecom3;
    }

    public static List<SEventSubContent1> eventSummary2EventSubContent1(TEventSummary es)
            throws JsonParseException, JsonMappingException, IOException {
        if (null == es || null == es.getsEventSubContent() || null == es.getsEventSubContentString()
                || 0 == es.getsEventSubContentString().length()) {
            return null;
        }

        List<SubContentJsonEntity> entities = HsEventUtil
                .JsonString2SubContentJsonEntity(es.getsEventSubContentString());

        List<SEventSubContent1> sEventSubContent1List = new ArrayList<SEventSubContent1>();
        for (SubContentJsonEntity entity : entities) {
            SEventSubContent1 sEventSubContent1 = new SEventSubContent1();
            sEventSubContent1.setCreateTs(es.getCreateTs());
            sEventSubContent1.sethEventId(es.gethEventId());
            sEventSubContent1.setsEventSubContent1Url(entity.getUrl());
            sEventSubContent1.setUpdateTs(es.getUpdateTs());

            sEventSubContent1List.add(sEventSubContent1);
        }

        return sEventSubContent1List;
    }

    public static List<SEventSubContent2> eventSummary2EventSubContent2(TEventSummary es) throws IOException {
        if (null == es || null == es.getsEventSubContent() || null == es.getsEventSubContentString()
                || 0 == es.getsEventSubContentString().length()) {
            return null;
        }

        List<SubContentJsonEntity> entities = HsEventUtil
                .JsonString2SubContentJsonEntity(es.getsEventSubContentString());

        List<SEventSubContent2> sEventSubContent2List = new ArrayList<SEventSubContent2>();
        for (SubContentJsonEntity entity : entities) {
            SEventSubContent2 sEventSubContent2 = new SEventSubContent2();
            sEventSubContent2.setCreateTs(es.getCreateTs());
            sEventSubContent2.sethEventId(es.gethEventId());
            sEventSubContent2.setsEventSubContent2Str(
                    HsEventUtil.SubContentJsonEntityWithoutUrl2JsonString(entity.toSubContentJsonEntityWithoutUrl()));
            sEventSubContent2.setUpdateTs(es.getUpdateTs());

            sEventSubContent2List.add(sEventSubContent2);
        }

        return sEventSubContent2List;
    }

    public static SEventType eventSummary2EventType(TEventSummary es) {
        SEventType sEventType = new SEventType();
        sEventType.setCreateTs(es.getCreateTs());
        sEventType.sethEventId(es.gethEventId());
        sEventType.setsEventTypeCd(es.getsEventTypeCd());
        sEventType.setUpdateTs(es.getUpdateTs());

        return sEventType;
    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static String SubContentJsonEntity2JsonString(List<SubContentJsonEntity> entities)
            throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(entities);

        return jsonString;
    }

    public static List<SubContentJsonEntity> JsonString2SubContentJsonEntity(String json)
            throws JsonParseException, JsonMappingException, IOException {
        JavaType javaType = getCollectionType(ArrayList.class, SubContentJsonEntity.class);

        List<SubContentJsonEntity> entities = mapper.readValue(json, javaType);
        return entities;
    }

    public static String SubContentJsonEntityWithoutUrl2JsonString(SubContentJsonEntityWithoutUrl entity)
            throws JsonProcessingException {
        String convertedJobParam = mapper.writeValueAsString(entity);

        return convertedJobParam;
    }

    public static SubContentJsonEntityWithoutUrl JsonString2SubContentJsonEntityWithoutUrl(String json)
            throws JsonParseException, JsonMappingException, IOException {
        SubContentJsonEntityWithoutUrl entity = mapper.readValue(json,
                (new SubContentJsonEntityWithoutUrl().getClass()));
        return entity;
    }

    public static String makeEventSubContentString(List<SEventSubContent1> eventSubContent1List,
            List<SEventSubContent2> eventSubContent2List) throws IOException {
        String result = null;

        if (null == eventSubContent1List || 0 == eventSubContent1List.size() || null == eventSubContent2List
                || 0 == eventSubContent1List.size()) {
            return null;
        }

        List<SubContentJsonEntity> eventSubContentList = new ArrayList<SubContentJsonEntity>();
        for (int i = 0; i < eventSubContent1List.size(); i++) {
            SEventSubContent2 eventSubContent2 = eventSubContent2List.get(i);
            SubContentJsonEntityWithoutUrl subContentJsonEntityWithoutUrl = HsEventUtil
                    .JsonString2SubContentJsonEntityWithoutUrl(eventSubContent2.getsEventSubContent2Str());

            SubContentJsonEntity subContentJsonEntity = new SubContentJsonEntity(subContentJsonEntityWithoutUrl);
            subContentJsonEntity.setUrl(eventSubContent1List.get(i).getsEventSubContent1Url());

            eventSubContentList.add(subContentJsonEntity);
        }

        result = HsEventUtil.SubContentJsonEntity2JsonString(eventSubContentList);

        return result;
    }

    public static List<SubContentJsonEntity> makeEventSubContent(List<SEventSubContent1> eventSubContent1List,
            List<SEventSubContent2> eventSubContent2List) throws IOException {
        if (null == eventSubContent1List || 0 == eventSubContent1List.size() || null == eventSubContent2List
                || 0 == eventSubContent1List.size()) {
            return null;
        }

        List<SubContentJsonEntity> eventSubContentList = new ArrayList<SubContentJsonEntity>();
        for (int i = 0; i < eventSubContent1List.size(); i++) {
            SEventSubContent2 eventSubContent2 = eventSubContent2List.get(i);
            SubContentJsonEntityWithoutUrl subContentJsonEntityWithoutUrl = HsEventUtil
                    .JsonString2SubContentJsonEntityWithoutUrl(eventSubContent2.getsEventSubContent2Str());

            SubContentJsonEntity subContentJsonEntity = new SubContentJsonEntity(subContentJsonEntityWithoutUrl);
            subContentJsonEntity.setUrl(eventSubContent1List.get(i).getsEventSubContent1Url());

            eventSubContentList.add(subContentJsonEntity);
        }

        return eventSubContentList;
    }

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        // String jsonOrigin =
        // "{\"url\":\"audio/a.mp3\",\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"}";
        String jsonOrigin = "[{\"url\":\"audio/a.mp3\",\"lyricist\":\"peter\",\"composer\":\"tom\",\"singer\":\"linda\"},{\"url\":\"audio/b.mp3\",\"lyricist\":\"fang\",\"composer\":\"jay\",\"singer\":\"jay\"}]";

        List<SubContentJsonEntity> entities = HsEventUtil.JsonString2SubContentJsonEntity(jsonOrigin);
        System.out.println(entities.size());
        for (SubContentJsonEntity entity : entities) {
            System.out.println(entity.getUrl() + " " + entity.getSinger());
        }

        String jsonString = HsEventUtil.SubContentJsonEntity2JsonString(entities);
        System.out.println(jsonString);
    }
}