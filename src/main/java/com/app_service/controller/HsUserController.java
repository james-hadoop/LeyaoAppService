package com.app_service.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app_service.common.Response;
import com.app_service.dao.configuration.ResourceConfig;
import com.app_service.entity.GridContent;
import com.app_service.entity.ProfileFile;
import com.app_service.entity.ResponseContent;
import com.app_service.entity.enums.ResponseResultEnum;
import com.app_service.entity.hs_user.SUserFeedbackSummary;
import com.app_service.entity.hs_user.TUserSummary;
import com.app_service.service.IHsUserService;

@RestController
@RequestMapping("/v1/service/user")
@EnableConfigurationProperties({ ResourceConfig.class })
public class HsUserController {
    private static final Logger logger = LoggerFactory.getLogger(HsUserController.class);

    @Autowired
    private ResourceConfig resourceConfig;

    @Autowired
    private IHsUserService hsUserService;

    @RequestMapping(value = "/getTUserSummary", method = RequestMethod.GET)
    public GridContent getTUserSummary(@RequestParam(value = "sUserNameStr", required = false) String sUserNameStr,
            @RequestParam(value = "sUserEmailStr", required = false) String sUserEmailStr,
            @RequestParam(value = "hUserPhoneNr", required = false) String hUserPhoneNr,
            @RequestParam(value = "sessionCode", required = true) String sessionCode,
            @RequestParam(value = "searchCondition", required = false) String searchCondition) {
        logger.info(
                "/v1/service/user/getTUserSummary() called: sessionCode={}, sUserNameStr={}, sUserEmailStr={}, hUserPhoneNr={}",
                sessionCode, sUserNameStr, sUserNameStr, hUserPhoneNr);
        GridContent gridContent = new GridContent();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sUserNameStr", sUserNameStr);
        paramMap.put("sUserEmailStr", sUserEmailStr);
        paramMap.put("hUserPhoneNr", hUserPhoneNr);
        paramMap.put("searchCondition", searchCondition);

        List<TUserSummary> tUserSummaryList = hsUserService.getTUserSummary(paramMap);
        gridContent.setRows(tUserSummaryList);
        gridContent.setTotal(null == tUserSummaryList ? 0 : tUserSummaryList.size());

        return gridContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName getVerifyCode
     * 
     * @api {post} /v1/service/user/getVerifyCode 获取注册验证码
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * 1234
     * 
     * @apiSuccessExample {json} Error-Response: 
     * -1
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public String getVerifyCode(@RequestParam(value = "hUserPhoneNr", required = true) String hUserPhoneNr) {
        logger.info("/v1/service/user/getVerifyCode() called: hUserPhoneNr={}", hUserPhoneNr);

        String verifyCode = String.valueOf(Response.ERROR);

        try {
            verifyCode = hsUserService.getVerifyCode(hUserPhoneNr);
        } catch (Exception e) {
            logger.error("/v1/service/user/getVerifyCode()", e);
            return verifyCode;
        }

        return verifyCode;
    }

    /**
     * @apiGroup User
     * 
     * @apiName regist
     * 
     * @api {post} /v1/service/user/regist 注册
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {String} sUserPasswordStr User password.
     * @apiParam {Number} verifyCode Verify code.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Regist success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Regist fail" }
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent regist(@RequestBody TUserSummary tUserSummary) {
        logger.info("/v1/service/user/regist() called: hUserPhoneNr={}, verifyCode={}", tUserSummary.gethUserPhoneNr(),
                tUserSummary.getVerifyCode());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.regist(tUserSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Regist fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Regist success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/user/regist()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName login
     * 
     * @api {post} /v1/service/user/login 登录
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {String} sUserPasswordStr User password.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg":"SNb5412b7c-7fdf-4d6e-add4-04ba9f7821932017-09-14
     *                    10:20:01" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Login fail" }
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent login(@RequestBody TUserSummary tUserSummary) {
        logger.info("/v1/service/user/login() called: hUserPhoneNr={}", tUserSummary.gethUserPhoneNr());
        ResponseContent responseContent = new ResponseContent();

        try {
            String sessionCode = hsUserService.login(tUserSummary);
            if (null == sessionCode) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Login fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg(sessionCode);
            }
        } catch (Exception e) {
            logger.error("/v1/service/user/login()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName reset
     * 
     * @api {post} /v1/service/user/reset 重置
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {String} sUserPasswordStr User password.
     * @apiParam {Number} verifyCode Verify code.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Reset success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Reset fail" }
     */
    @RequestMapping(value = "reset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent reset(@RequestBody TUserSummary tUserSummary) {
        logger.info("/v1/service/user/reset() called: hUserPhoneNr={}, verifyCode={}", tUserSummary.gethUserPhoneNr(),
                tUserSummary.getVerifyCode());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.reset(tUserSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("reset fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("reset success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/user/reset()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName addTUserSummary
     * 
     * @api {post} /v1/service/user/addTUserSummary 添加用户详情
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {String} sUserPasswordStr User password.
     * @apiParam {Number} [sUserGenderCd] User gender.
     * @apiParam {String} [sUserNameStr] User nick name.
     * @apiParam {Number} verifyCode Verify code.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Add success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Add fail" }
     */
    @RequestMapping(value = "/addTUserSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent addTUserSummary(@RequestBody TUserSummary tUserSummary) {
        logger.info(
                "/v1/service/user/addTUserSummary() called: hUserPhoneNr={},sUserEmailStr={},sUserGenderDesc={},sUserNameStr={}",
                tUserSummary.gethUserPhoneNr(), tUserSummary.getsUserEmailStr(), tUserSummary.getsUserGenderDesc(),
                tUserSummary.getsUserNameStr());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.addTUserSummary(tUserSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("addTUserSummary fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("addTUserSummary success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/user/addTUserSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName editTUserSummary
     * 
     * @api {post} /v1/service/user/editTUserSummary 编辑用户详情
     * 
     * @apiParam {Number} verifyCode Verify code.
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {String} [sUserPasswordStr] User password.
     * @apiParam {Number} [sUserGenderCd] User gender.
     * @apiParam {String} [sUserNameStr] User nick name.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Edit success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Edit fail" }
     */
    @RequestMapping(value = "/editTUserSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent editTUserSummary(@RequestBody TUserSummary tUserSummary) {
        logger.info(
                "/v1/service/user/editTUserSummary() called: hUserPhoneNr={},sUserEmailStr={},sUserGenderCd={},sUserGenderDesc={},sUserNameStr={}",
                tUserSummary.gethUserPhoneNr(), tUserSummary.getsUserEmailStr(), tUserSummary.getsUserGenderCd(),
                tUserSummary.getsUserGenderDesc(), tUserSummary.getsUserNameStr());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.editTUserSummary(tUserSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("editTUserSummary fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("editTUserSummary success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/user/editTUserSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    @RequestMapping(value = "/deleteTUserSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent deleteTUserSummary(@RequestBody TUserSummary tUserSummary) {
        logger.info("/v1/service/event/deleteTUserSummary() called: hUserId={}", tUserSummary.gethUserId());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.deleteTUserSummary(tUserSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("Delete fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("Delete success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/event/deleteTUserSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName uploadPortrait
     * 
     * @api {post} /v1/service/user/uploadPortrait 上传用户头像
     * 
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {MultipartFile} file Uploaded file multipart.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Upload success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Upload fail" }
     */
    @RequestMapping(value = "/uploadPortrait", method = RequestMethod.POST)
    public ResponseContent uploadPortrait(@RequestParam("file") MultipartFile file,
            @RequestParam("hUserPhoneNr") Long hUserPhoneNr) {
        ResponseContent responseContent = new ResponseContent();

        try {
            System.out.println("uploadPortrait()");

            byte[] bytes;

            if (!file.isEmpty()) {
                bytes = file.getBytes();
            }

            String destPath = resourceConfig.getPrefix() + resourceConfig.getPortrait() + hUserPhoneNr + ".jpg";
            System.out.println("destPath=" + destPath);

            file.transferTo(new File(destPath));

            System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), hUserPhoneNr));

            responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
            responseContent.setResponseResultMsg("Upload success");
        } catch (Exception e) {
            logger.error("/v1/service/user/uploadPortrait()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName uploadProfile
     * 
     * @api {post} /v1/service/user/uploadProfile 上传用户头像
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {String} fileName File name.
     * @apiParam {String} currentPortraitName Current PortraitName.
     * @apiParam {Long} hUserPhoneNr User phone number.
     * @apiParam {String} base64 Uploaded file BASE64 code
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Upload success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Upload fail" }
     */
    @RequestMapping(value = "/uploadProfile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent uploadProfile(@RequestBody ProfileFile profileFile) {
        logger.info("/v1/service/user/uploadProfile() called: hUserPhoneNr={},fileName={},currentPortraitName={}}",
                profileFile.gethUserPhoneNr(), profileFile.getFileName(), profileFile.getCurrentPortraitName());
        ResponseContent responseContent = new ResponseContent();

        try {
            String result = hsUserService.uploadFileBase64(profileFile.getBase64(), profileFile.getFileName(),
                    profileFile.getSessionCode(), profileFile.gethUserPhoneNr(), profileFile.getCurrentPortraitName());

            responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
            responseContent.setResponseResultMsg(result);
        } catch (Exception e) {
            logger.error("/v1/service/user/uploadProfile()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    @RequestMapping(value = "/uploadResource")
    public void uploadResource(@RequestParam("file") MultipartFile file, @RequestParam("targetDir") String targetDir)
            throws IOException {
        System.out.println("uploadResource(): " + targetDir);

        byte[] bytes;

        if (!file.isEmpty()) {
            bytes = file.getBytes();
            // store file in storage
        }

        System.out.println(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());

        String path = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();

        file.transferTo(new File(path));

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), targetDir));

    }

    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    public ResponseContent getProfile(@RequestParam(value = "hUserPhoneNr", required = true) Long hUserPhoneNr,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        ResponseContent responseContent = new ResponseContent();

        try {
            if (null == hUserPhoneNr) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("getProfile error");
                return responseContent;
            }
            responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
            responseContent.setResponseResultMsg(resourceConfig.getPortrait() + hUserPhoneNr + ".jpg");
        } catch (Exception e) {
            logger.error("/v1/service/user/getProfile()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName getPortrait
     * 
     * @api {get} /v1/service/user/getPortrait 获取用户头像
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {String} hUserPhoneNr User phone number.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "portrait/13333333333.jpg" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "NULL" }
     */
    @RequestMapping(value = "/getPortrait", method = RequestMethod.GET)
    public ResponseContent getPortrait(@RequestParam(value = "hUserPhoneNr", required = true) Long hUserPhoneNr,
            @RequestParam(value = "sessionCode", required = true) String sessionCode) {
        ResponseContent responseContent = new ResponseContent();

        try {
            if (null == hUserPhoneNr) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("getPortrait error");
                return responseContent;
            }
            responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
            responseContent.setResponseResultMsg(resourceConfig.getPortrait() + hUserPhoneNr + ".jpg");
        } catch (Exception e) {
            logger.error("/v1/service/user/getPortrait()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName getSUserFeedbackSummaryByCondition
     * 
     * @api {get} /v1/service/user/getSUserFeedbackSummaryByCondition 根据手机号码获取用户反馈
     * 
     * @apiParam {String} sessionCode Logined user session code.
     * @apiParam {String} hUserPhoneNr User phone number.
     * @apiParam {Number} [page] Paging - page number.
     * @apiParam {Number} [rows] Paging - rows in one page.
     * 
     * @apiSuccessExample {json} Success-Response: 
     * { "rows": [ { "sUserFeedbackId":
     *                    1, "hUserId": 1, "sUserFeedbackStr": "Leyao app is
     *                    great!!!", "createTs": 946656000000, "updateTs":
     *                    1507642420000, "hUserPhoneNr": 13333333333 } ], "total": 1
     *                    }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "rows":[], "total":0 }
     */
    @RequestMapping(value = "/getSUserFeedbackSummaryByCondition", method = RequestMethod.GET)
    public GridContent getSUserFeedbackSummaryByCondition(
            @RequestParam(value = "sessionCode", required = true) String sessionCode,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "hUserPhoneNr", required = false) Long hUserPhoneNr) {
        logger.info("/v1/service/user/getSUserFeedbackSummaryByCondition() called: hUserPhoneNr={}", hUserPhoneNr);
        GridContent gridContent = new GridContent();

        try {
            int start = (page - 1) * rows;
            int end = rows;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("hUserPhoneNr", hUserPhoneNr);
            paramMap.put("start", start);
            paramMap.put("end", end);

            List<SUserFeedbackSummary> sUserFeedbackSummaryList = hsUserService
                    .getSUserFeedbackSummaryByCondition(paramMap);
            int count = hsUserService.getSUserFeedbackSummaryByConditionCount(paramMap);

            gridContent.setRows(sUserFeedbackSummaryList);
            gridContent.setTotal(count);
        } catch (Exception e) {
            logger.error("/v1/service/user/getSUserFeedbackSummaryByCondition()", e);
            return gridContent;
        }
        return gridContent;
    }

    /**
     * @apiGroup User
     * 
     * @apiName addSUserFeedbackSummary
     * 
     * @api {post} /v1/service/user/addSUserFeedbackSummary 添加用户反馈
     * 
     * @apiParam {String} sUserFeedbackStr User feedback content.
     * @apiParam {String} hUserPhoneNr User phone number.
     *
     * @apiSuccessExample {json} Success-Response: 
     * { "responseResult": "SUCCESS",
     *                    "responseResultMsg": "Add success" }
     * 
     * @apiSuccessExample {json} Error-Response: 
     * { "responseResult": "ERROR",
     *                    "responseResultMsg": "Add fail" }
     */
    @RequestMapping(value = "/addSUserFeedbackSummary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent addSUserFeedbackSummary(@RequestBody SUserFeedbackSummary sUserFeedbackSummary) {
        logger.info("/v1/service/event/addSUserFeedbackSummary() called: hUserPhoneNr={},sUserFeedbackStr={}",
                sUserFeedbackSummary.gethUserPhoneNr(), sUserFeedbackSummary.getsUserFeedbackStr());
        ResponseContent responseContent = new ResponseContent();

        try {
            int result = hsUserService.addSUserFeedbackSummary(sUserFeedbackSummary);
            if (Response.ERROR == result) {
                responseContent.setResponseResult(ResponseResultEnum.ERROR);
                responseContent.setResponseResultMsg("addSUserFeedbackSummary fail");
            } else {
                responseContent.setResponseResult(ResponseResultEnum.SUCCESS);
                responseContent.setResponseResultMsg("addSUserFeedbackSummary success");
            }
        } catch (Exception e) {
            logger.error("/v1/service/event/addSUserFeedbackSummary()", e);
            responseContent.setResponseResult(ResponseResultEnum.ERROR);
            responseContent.setResponseResultMsg("Server internal error");
            return responseContent;
        }
        return responseContent;
    }
}