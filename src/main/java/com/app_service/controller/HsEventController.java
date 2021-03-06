package com.app_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app_service.common.Response;
import com.app_service.entity.GridContent;
import com.app_service.entity.ResponseContent;
import com.app_service.entity.enums.ResponseResultEnum;
import com.app_service.entity.hs_event.TEventSummary;
import com.app_service.service.IHsEventService;

@RestController
@RequestMapping("/v1/service/event")
public class HsEventController {
    private static final Logger logger = LoggerFactory.getLogger(HsEventController.class);

    @Autowired
    IHsEventService hsEventService;

    /**
     * @apiGroup Event
     * 
     * @apiName getTEventSummaryByType
     * 
     * @api {get} /v1/service/event/getTEventSummaryByType 根据事件类型获取事件(sEventTypeCd=1:听我/sEventTypeCd=2:看我/sEventTypeCd=3:读我)
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} sEventTypeCd Event type.
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * {
     *     "rows": [
     *         {
     *             "hEventId": 7,
     *             "sEventCategoryCd": 0,
     *             "rEventCategoryDesc": "d1",
     *             "sEventTypeCd": 1,
     *             "rEventTypeDesc": "video",
     *             "sEventTitleUrl": "video/1.mp4",
     *             "sEventContentUrl": "video/1.mp4",
     *             "sEventActiveInd": 0,
     *             "createTs": 946627200000,
     *             "updateTs": 946627200000,
     *             "sEventSearchContentTxt": "7",
     *             "sEventSubContent1UrlList": [],
     *             "sEventSubContent2StrList": [],
     *             "sEventBannerPositionCd": null,
     *             "sEventRecomPositionCd": null
     *         }
     *     ],
     *     "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getTEventSummaryByType", method = RequestMethod.GET)
    public GridContent getTEventSummaryByType(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sEventTypeCd", defaultValue = "-1") Integer sEventTypeCd,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        logger.info(
                "/v1/service/event/getTEventSummaryByType() called: sessionCode={}, sEventTypeCd={}, page={}, rows={}",
                sessionCode, sEventTypeCd, page, rows);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sEventTypeCd", sEventTypeCd);
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByType(paramMap);
            int count = 6;
            // int count = hsEventService.getTEventSummaryByTypeCount(paramMap);

            // List<TEventSummary> tEventSummaryList = new
            // ArrayList<TEventSummary>();
            // int count = 0;

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryByType()", e);
            return gridContent;
        }

        return gridContent;
    }

    @RequestMapping(value = "/postTEventSummaryByType", method = RequestMethod.POST)
    public GridContent postTEventSummaryByType(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sEventTypeCd", defaultValue = "-1") Integer sEventTypeCd,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        logger.info(
                "/v1/service/event/postTEventSummaryByType() called: sessionCode={}, sEventTypeCd={}, page={}, rows={}",
                sessionCode, sEventTypeCd, page, rows);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sEventTypeCd", sEventTypeCd);
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByType(paramMap);
            int count = hsEventService.getTEventSummaryByTypeCount(paramMap);

            // List<TEventSummary> tEventSummaryList = new
            // ArrayList<TEventSummary>();
            // int count = 0;

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/postTEventSummaryByType()", e);
            return gridContent;
        }

        return gridContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName getTEventSummaryByCategory
     * 
     * @api {get} /v1/service/event/getTEventSummaryByCategory
     *      根据事件类目获取事件(sEventCategoryCd=1:盒声活动/sEventCategoryCd=2:音乐教学/sEventCategoryCd=3:艺人分享/sEventCategoryCd=4:音乐专辑)
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} sEventCategoryCd Event category
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * {
     *     "rows": [
     *         {
     *             "hEventId": 7,
     *             "sEventCategoryCd": 0,
     *             "rEventCategoryDesc": "d1",
     *             "sEventTypeCd": 1,
     *             "rEventTypeDesc": "video",
     *             "sEventTitleUrl": "video/1.mp4",
     *             "sEventContentUrl": "video/1.mp4",
     *             "sEventActiveInd": 0,
     *             "createTs": 946627200000,
     *             "updateTs": 946627200000,
     *             "sEventSearchContentTxt": "7",
     *             "sEventSubContent1UrlList": [],
     *             "sEventSubContent2StrList": [],
     *             "sEventBannerPositionCd": null,
     *             "sEventRecomPositionCd": null
     *         }
     *     ],
     *     "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getTEventSummaryByCategory", method = RequestMethod.GET)
    public GridContent getTEventSummaryByCategory(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sEventCategoryCd", defaultValue = "-1") Integer sEventCategoryCd,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        logger.info(
                "/v1/service/event/getTEventSummaryByCategory() called: sessionCode={}, sEventCategoryCd={}, page={}, rows={}",
                sessionCode, sEventCategoryCd, page, rows);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sEventCategoryCd", sEventCategoryCd);
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByCategory(paramMap);
            int count = hsEventService.getTEventSummaryByCategoryCount(paramMap);

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryByCategory()", e);
            return gridContent;
        }

        return gridContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName getTEventSummaryForBanner
     * 
     * @api {get} /v1/service/event/getTEventSummaryForBanner 获取Banner事件
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * {
     *     "rows": [
     *         {
     *             "hEventId": 7,
     *             "sEventCategoryCd": 0,
     *             "rEventCategoryDesc": "d1",
     *             "sEventTypeCd": 1,
     *             "rEventTypeDesc": "video",
     *             "sEventTitleUrl": "video/1.mp4",
     *             "sEventContentUrl": "video/1.mp4",
     *             "sEventActiveInd": 0,
     *             "createTs": 946627200000,
     *             "updateTs": 946627200000,
     *             "sEventSearchContentTxt": "7",
     *             "sEventSubContent1UrlList": [],
     *             "sEventSubContent2StrList": [],
     *             "sEventBannerPositionCd": null,
     *             "sEventRecomPositionCd": null
     *         }
     *     ],
     *     "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getTEventSummaryForBanner", method = RequestMethod.GET)
    public GridContent getTEventSummaryForBanner(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "6") Integer rows,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        logger.info("/v1/service/event/getTEventSummaryForBanner() called: sessionCode={}, page={}, rows={}",
                sessionCode, page, rows);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventPageListForBanner(paramMap);
            int count = hsEventService.getTEventPageListForBannerCount(paramMap);

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryForBanner()", e);
            return gridContent;
        }

        return gridContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName checkUpdate
     * 
     * @api {get} /v1/service/event/checkUpdate 获取事件最新版本
     * 
     * @apiParam {String} eventVersion Event version.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * 1505223163561
     * 
     * @apiSuccessExample {json} Error-Response: 
     * 1505223163561
     */
    @RequestMapping(value = "/checkUpdate", method = RequestMethod.GET)
    public String checkUpdate(@RequestParam(value = "eventVersion") String eventVersion) {
        return hsEventService.checkUpdate(eventVersion);
    }

    /**
     * @apiGroup Event
     * 
     * @apiName getTEventSummaryByConditionAndUser
     * 
     * @api {get} /v1/service/event/getTEventSummaryByConditionAndUser 获取我的历史/我的关注
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} hUserPhoneNr User phone number.
     * @apiParam {Number} [sUserEventLikeInd] Event like code sUserEventLikeInd=1.
     * 
     * @apiSuccessExample {json} Success-Response: 
          * {
     *     "rows": [
     *         {
     *             "hEventId": 7,
     *             "sEventCategoryCd": 0,
     *             "rEventCategoryDesc": "d1",
     *             "sEventTypeCd": 1,
     *             "rEventTypeDesc": "video",
     *             "sEventTitleUrl": "video/1.mp4",
     *             "sEventContentUrl": "video/1.mp4",
     *             "sEventActiveInd": 0,
     *             "createTs": 946627200000,
     *             "updateTs": 946627200000,
     *             "sEventSearchContentTxt": "7",
     *             "sEventSubContent1UrlList": [],
     *             "sEventSubContent2StrList": [],
     *             "sEventBannerPositionCd": null,
     *             "sEventRecomPositionCd": null
     *         }
     *     ],
     *     "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getTEventSummaryByConditionAndUser", method = RequestMethod.GET)
    public GridContent getTEventSummaryByConditionAndUser(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sessionCode", required = true) String sessionCode,
            @RequestParam(value = "hUserPhoneNr", required = false) Long hUserPhoneNr,
            @RequestParam(value = "sEventCategoryCd", required = false) Integer sEventCategoryCd,
            @RequestParam(value = "sEventTypeCd", required = false) Integer sEventTypeCd,
            @RequestParam(value = "sUserEventLikeInd", required = false) Integer sUserEventLikeInd,
            @RequestParam(value = "sUserEventReadLogTxt", required = false) String sUserEventReadLogTxt,
            @RequestParam(value = "sEventSearchContentTxt", required = false) String sEventSearchContentTxt) {
        logger.info(
                "/v1/service/event/getTEventSummaryByConditionAndUser() called: sessionCode={}, page={}, rows={},hUserPhoneNr={},sEventCategoryCd={},sEventTypeCd={},sUserEventLikeInd={},sUserEventReadLogTxt={},sEventSearchContentTxt={}",
                sessionCode, page, rows, hUserPhoneNr, sEventCategoryCd, sEventTypeCd, sUserEventLikeInd,
                sUserEventReadLogTxt, sEventSearchContentTxt);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            // paramMap.put("start", start);
            // paramMap.put("end", end);
            paramMap.put("hUserPhoneNr", hUserPhoneNr);
            paramMap.put("sEventCategoryCd", sEventCategoryCd);
            paramMap.put("sEventTypeCd", sEventTypeCd);
            paramMap.put("sUserEventLikeInd", sUserEventLikeInd);
            if (null != sUserEventReadLogTxt) {
                paramMap.put("sUserEventReadLogTxt", sUserEventReadLogTxt.toLowerCase());
            }
            if (null != sEventSearchContentTxt) {
                paramMap.put("sEventSearchContentTxt", sEventSearchContentTxt.toLowerCase());
            }

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByConditionAndUser(paramMap);
            int count = hsEventService.getTEventSummaryByConditionAndUserCount(paramMap);

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryByCondition()", e);
            return gridContent;
        }

        return gridContent;
    }

    @RequestMapping(value = "/getTEventSummaryByCondition", method = RequestMethod.GET)
    public GridContent getTEventSummaryByCondition(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sessionCode", required = true) String sessionCode,
            @RequestParam(value = "hUserPhoneNr", required = false) Long hUserPhoneNr,
            @RequestParam(value = "sEventCategoryCd", required = false) Integer sEventCategoryCd,
            @RequestParam(value = "sEventTypeCd", required = false) Integer sEventTypeCd,
            @RequestParam(value = "sUserEventLikeInd", required = false) Integer sUserEventLikeInd,
            @RequestParam(value = "sUserEventReadLogTxt", required = false) String sUserEventReadLogTxt,
            @RequestParam(value = "sEventSearchContentTxt", required = false) String sEventSearchContentTxt) {
        logger.info(
                "/v1/service/event/getTEventSummaryByCondition() called: sessionCode={}, page={}, rows={},hUserPhoneNr={},sEventCategoryCd={},sEventTypeCd={},sUserEventLikeInd={},sUserEventReadLogTxt={},sEventSearchContentTxt={}",
                sessionCode, page, rows, hUserPhoneNr, sEventCategoryCd, sEventTypeCd, sUserEventLikeInd,
                sUserEventReadLogTxt, sEventSearchContentTxt);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("start", start);
            paramMap.put("end", end);
            paramMap.put("hUserPhoneNr", hUserPhoneNr);
            paramMap.put("sEventCategoryCd", sEventCategoryCd);
            paramMap.put("sEventTypeCd", sEventTypeCd);
            paramMap.put("sUserEventLikeInd", sUserEventLikeInd);
            if (null != sUserEventReadLogTxt) {
                paramMap.put("sUserEventReadLogTxt", sUserEventReadLogTxt.toLowerCase());
            }
            if (null != sEventSearchContentTxt) {
                paramMap.put("sEventSearchContentTxt", sEventSearchContentTxt.toLowerCase());
            }

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByCondition(paramMap);
            int count = hsEventService.getTEventSummaryByConditionCount(paramMap);

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryByCondition()", e);
            return gridContent;
        }

        return gridContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName getTEventSummaryByConditionGlobal
     * 
     * @api {get} /v1/service/event/getTEventSummaryByConditionGlobal 搜索全局事件
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * @apiParam {Number} [hUserPhoneNr] User phone number.
     * @apiParam {Number} [sEventCategoryCd] Event category code.
     * @apiParam {Number} [sEventTypeCd] Event type code.
     * @apiParam {Number} [sUserEventLikeInd] Event like code.
     * @apiParam {String} [sUserEventReadLogTxt] Event read log.
     * @apiParam {String} [sEventSearchContentTxt] User search text.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * 
     * {
     *     "rows": [
     *         {
     *             "hEventId": 7,
     *             "sEventCategoryCd": 0,
     *             "rEventCategoryDesc": "d1",
     *             "sEventTypeCd": 1,
     *             "rEventTypeDesc": "video",
     *             "sEventTitleUrl": "video/1.mp4",
     *             "sEventContentUrl": "video/1.mp4",
     *             "sEventActiveInd": 0,
     *             "createTs": 946627200000,
     *             "updateTs": 946627200000,
     *             "sEventSearchContentTxt": "7",
     *             "sEventSubContent1UrlList": [],
     *             "sEventSubContent2StrList": [],
     *             "sEventBannerPositionCd": null,
     *             "sEventRecomPositionCd": null
     *         }
     *     ],
     *     "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getTEventSummaryByConditionGlobal", method = RequestMethod.GET)
    public GridContent getTEventSummaryByConditionGlobal(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "100") Integer rows,
            @RequestParam(value = "sessionCode", required = true) String sessionCode,
            @RequestParam(value = "hUserPhoneNr", required = false) Long hUserPhoneNr,
            @RequestParam(value = "sEventCategoryCd", required = false) Integer sEventCategoryCd,
            @RequestParam(value = "sEventTypeCd", required = false) Integer sEventTypeCd,
            @RequestParam(value = "sEventActiveInd", required = false) Integer sEventActiveInd,
            @RequestParam(value = "sUserEventLikeInd", required = false) Integer sUserEventLikeInd,
            @RequestParam(value = "sUserEventReadLogTxt", required = false) String sUserEventReadLogTxt,
            @RequestParam(value = "sEventSearchContentTxt", required = false) String sEventSearchContentTxt,
            @RequestParam(value = "urlString", required = false) String urlString) {
        logger.info(
                "/v1/service/event/getTEventSummaryByConditionGlobal() called: sessionCode={}, page={}, rows={},hUserPhoneNr={},sEventCategoryCd={},sEventTypeCd={},sUserEventLikeInd={},sUserEventReadLogTxt={},sEventSearchContentTxt={}",
                sessionCode, page, rows, hUserPhoneNr, sEventCategoryCd, sEventTypeCd, sUserEventLikeInd,
                sUserEventReadLogTxt, sEventSearchContentTxt);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("start", start);
            paramMap.put("end", end);
            paramMap.put("hUserPhoneNr", hUserPhoneNr);
            paramMap.put("sEventCategoryCd", sEventCategoryCd);
            paramMap.put("sEventTypeCd", sEventTypeCd);
            paramMap.put("sEventActiveInd", sEventActiveInd);
            paramMap.put("sUserEventLikeInd", sUserEventLikeInd);
            paramMap.put("sUserEventReadLogTxt", sUserEventReadLogTxt);
            paramMap.put("sEventSearchContentTxt", sEventSearchContentTxt);
            paramMap.put("urlString", urlString);

            List<TEventSummary> tEventSummaryList = hsEventService.getTEventSummaryByConditionGlobal(paramMap);
            int count = hsEventService.getTEventSummaryByConditionGlobalCount(paramMap);

            gridContent.setRows(tEventSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/event/getTEventSummaryByConditionGlobal()", e);
            return gridContent;
        }

        return gridContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName addTEventSummary
     * 
     * @api {post} /v1/service/event/addTEventSummary 添加事件
     * 
     * @apiParam {Number} sEventCategoryCd Event category code.
     * @apiParam {String} rEventCategoryDesc Event category description.
     * @apiParam {Number} sEventTypeCd Event type code.
     * @apiParam {String} rEventTypeDesc Event type description.
     * @apiParam {String} sEventTitleUrl Event title url.
     * @apiParam {String} sEventContentUrl Event content url.
     * @apiParam {Number} sEventBannerPositionCd Event banner position.
     * @apiParam {Number} sEventRecomPositionCd Event recommend position.
     *
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Add success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Add fail" }
     */
    @RequestMapping(value = "/addTEventSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent addTEventSummary(@RequestBody TEventSummary tEventSummary) {
        logger.info("/v1/service/event/addTEventSummary() called: sEventTitleUrl={}",
                tEventSummary.getsEventTitleUrl());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsEventService.addTEventSummary(tEventSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Add fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Add success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/event/addTEventSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName editTEventSummary
     * 
     * @api {post} /v1/service/event/editTEventSummary 编辑事件
     * 
     * @apiParam {Number} sEventCategoryCd Event category code.
     * @apiParam {String} rEventCategoryDesc Event category description.
     * @apiParam {Number} sEventTypeCd Event type code.
     * @apiParam {String} rEventTypeDesc Event type description.
     * @apiParam {String} sEventTitleUrl Event title url.
     * @apiParam {String} sEventContentUrl Event content url.
     * @apiParam {Number} sEventBannerPositionCd Event banner position.
     * @apiParam {Number} sEventRecomPositionCd Event recommend position.
     *
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Edit success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Edit fail" }
     */
    @RequestMapping(value = "/editTEventSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent editTEventSummary(@RequestBody TEventSummary tEventSummary) {
        logger.info("/v1/service/event/editTEventSummary() called: sEventTitleUrl={}",
                tEventSummary.getsEventTitleUrl());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsEventService.editTEventSummary(tEventSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Edit fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Edit success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/event/editTEventSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup Event
     * 
     * @apiName deleteTEventSummary
     * 
     * @api {post} /v1/service/event/deleteTEventSummary 删除事件
     * 
     * @apiParam {Number} sEventCategoryCd Event category code.
     * @apiParam {String} rEventCategoryDesc Event category description.
     * @apiParam {Number} sEventTypeCd Event type code.
     * @apiParam {String} rEventTypeDesc Event type description.
     * @apiParam {String} sEventTitleUrl Event title url.
     * @apiParam {String} sEventContentUrl Event content url.
     * @apiParam {Number} sEventBannerPositionCd Event banner position.
     * @apiParam {Number} sEventRecomPositionCd Event recommend position.
     *
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Delete success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Delete fail" }
     */
    @RequestMapping(value = "/deleteTEventSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent deleteTEventSummary(@RequestBody TEventSummary tEventSummary) {
        logger.info("/v1/service/event/deleteTEventSummary() called: hEventId={}", tEventSummary.gethEventId());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsEventService.deleteTEventSummary(tEventSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Delete fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Delete success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/event/deleteTEventSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }
}