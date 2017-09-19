package com.leyao.app_service.controller;

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

import com.leyao.app_service.common.Response;
import com.leyao.app_service.entity.GridContent;
import com.leyao.app_service.entity.ResponseContent;
import com.leyao.app_service.entity.enums.ResponseResultEnum;
import com.leyao.app_service.entity.hs_message.TMessageSummary;
import com.leyao.app_service.service.IHsMessageService;

public class HsMessageController {
    private static final Logger logger = LoggerFactory.getLogger(HsMessageController.class);

    @Autowired
    IHsMessageService hsMessageService;

    @RequestMapping(value = "/addTMessageSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent addTMessageSummary(@RequestBody TMessageSummary tMessageSummary) {
        logger.info("/v1/service/message/addTMessageSummary() called: hMessageId={}", tMessageSummary.gethMessageId());
        ResponseContent responseContent = new ResponseContent();

        try {
            int resutl = hsMessageService.addTMessageSummary(tMessageSummary);
            if (Response.ERROR == resutl) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Add fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Add success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/message/addTMessageSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup Message
     * 
     * @apiName getTMessageSummary
     * 
     * @api {get} /v1/service/message/getTMessageSummary 获取我的消息
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * @apiParam {Number} [hUserPhoneNr] User phone number.
     * @apiParam {Number} [sMessageCategoryCd] Message category code.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * {
     *  "rows": [ 
     *      {
     *          "hMessageId": 7,
     *          "sMessageActiveInd": 0,
     *          "sMessageCategoryCd": 1,
     *          "sMessageContentStr": "great",
     *          "createTs": 946627200000,
     *          "updateTs": 946627200000
     *      }
     *  ],
     *  "total": 1
     * }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * {
     *  "rows":[],
     *  "total":0
     * }
     */
    @RequestMapping(value = "/getTMessageSummary", method = RequestMethod.GET)
    public GridContent getTMessageSummary( @RequestParam(value = "sessionCode", required = true) String sessionCode, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                    @RequestParam(value = "hUserPhoneNr", required = false) Long hUserPhoneNr, @RequestParam(value = "sMessageCategoryCd", required = false) Integer sMessageCategoryCd) {
        logger.info("/v1/service/message/getTMessageSummary() called: hUserPhoneNr={},sMessageCategoryCd={}", hUserPhoneNr,sMessageCategoryCd);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("hUserPhoneNr", hUserPhoneNr);
            paramMap.put("sMessageCategoryCd", sMessageCategoryCd);
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<TMessageSummary> tMessageSummaryList = hsMessageService.getTMessageSummaryListByCondition(paramMap);
            int count = hsMessageService.getTMessageSummaryListByConditionCount(paramMap);

            gridContent.setRows(tMessageSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/message/getTMessageSummary()", e);
            return gridContent;
        }
        return gridContent;
    }
}