package com.nstepa.wc.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nstepa.wc.admin.consts.MPBaseConstant;
import com.nstepa.wc.admin.services.UserService;
import com.nstepa.wc.admin.wx.AES;
import com.nstepa.wc.admin.wx.WxSessionBean;
import com.nstepa.wc.beans.User;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.commons.utils.HttpKit;
import com.nstepa.wc.commons.utils.JSONUtil;
import com.nstepa.wc.commons.utils.JsonUtils;
import com.nstepa.wc.redis.utils.RedisTemplateUtil;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.nstepa.wc.springboot.support.exceptions.NepaException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "", description = "小程序登录认证")
@RestController
@RequestMapping(value = "login")
public class LoginController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Value("${weixin.config.appId}")
	private String appId;

	@Value("${weixin.config.secret}")
	private String secret;

	@Value("${weixin.config.sessionId-expire}")
	private String sessionIdExpire;

	@Value("${weixin.code2session.url}")
	private String code2sessionUrl;

	@Autowired
	private RestTemplate restTemplate;
	@Resource
	private RedisUtils redisUtils;
	@Autowired
	private UserService userService;
	// 返回类型ResultDTO<String>
	private final ParameterizedTypeReference<String> responseType =
			new ParameterizedTypeReference<String>() {
			};
	//获取凭证校检接口
	@ApiOperation(value="系统登录验证", notes="验证用户登录信息")
	@PostMapping(value = "/wx/getSession")
	public Response<Map<String,String>> doLogin(
			@ApiParam(value = "用户名",required = true) @NotBlank(message = "code不能为空") @RequestParam(required = false) String code){

//		logger.info("code------>"+rawData);
//		JSONObject rawDataJson = JSON.parseObject(rawData);

		WxSessionBean sessionBean = getSessionKeyOrOpenId(code);
		logger.info("请求获取的SessionAndopenId="+sessionBean);

		String openId = sessionBean.getOpenid();
		String sessionKey = sessionBean.getSession_key();
		logger.info("openId="+openId+",session_key="+sessionKey);

		User user = userService.selectOne(new EntityWrapper<User>().eq("user_key", openId));
		//uuid生成唯一key
		String skey = UUID.randomUUID().toString();
		if(user==null){
			//存入数据库
//			String nickName = rawDataJson.getString("nickName");
//			String avatarUrl = rawDataJson.getString("avatarUrl");//用户头像
//			String gender  = rawDataJson.getString("gender");//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
//			String city = rawDataJson.getString("city");
//			String country = rawDataJson.getString("country");
//			String province = rawDataJson.getString("province");

			user = new User();
			user.setUserKey(openId);
			user.setCreateTime(new Date());
//			user.setAddress(country+" "+province+" "+city);
//			user.setUserName(nickName);

			userService.insert(user);
		}else {
			//已存在
			logger.info("用户openId已存在,不需要插入");
		}
		//根据openId查询skey是否存在
//		String skey_redis = (String) redisUtils.get(openId);
		String skey_redis = RedisTemplateUtil.getStr(openId);
		if(StringUtils.isNotBlank(skey_redis)){
			//存在则删除skey ，重新生成skey 将skey返回
			RedisTemplateUtil.del2(skey_redis);
			RedisTemplateUtil.del2(openId);
//			redisUtils.del(skey_redis);
		}
		//  缓存一份新的
		JSONObject sessionObj = new JSONObject();
		sessionObj.put("openId",openId);
		sessionObj.put("sessionKey",sessionKey);

//		redisUtils.set(skey,sessionObj.toJSONString());
//		redisUtils.set(openId,skey);
		RedisTemplateUtil.setStr(skey,sessionObj.toJSONString(),Long.parseLong(sessionIdExpire));
		RedisTemplateUtil.setStr(openId,skey,Long.parseLong(sessionIdExpire));

		Map<String,String> sessionMap = new HashMap<>();
		//把新的sessionKey和oppenid返回给小程序
		sessionMap.put("3rd_session",skey);
		return success(sessionMap);
	}


	/**
	 * 获取用户openId和unionId数据(如果没绑定微信开放平台，解密数据中不包含unionId)
	 *
	 * @param encryptedData 加密数据
	 * @param iv            加密算法的初始向量
	 * @return
	 */
//	@ApiOperation(value="系统登录验证", notes="获取个人信息")
//	@PostMapping(value = "/wx/decodeUserInfo", produces = "application/json")
//	public Map<String, Object> decodeUserInfo(@RequestParam(required = true, value = "encryptedData") String encryptedData,
//											  @RequestParam(required = true, defaultValue = "iv") String iv) {
//		HttpServletRequest request = HttpKit.getRequest();
//		String sessionId = request.getHeader(MPBaseConstant.THIRD_SESSION_ID);
//		String wxSessionStr = RedisTemplateUtil.getMapField(MPBaseConstant.SESSION_ID_MAP, sessionId, String.class);
//		String sessionKey = wxSessionStr.split("#")[0];
//
//		try {
//			AES aes = new AES();
//			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
//			if (null != resultByte && resultByte.length > 0) {
//				String userInfo = new String(resultByte, "UTF-8");
//				MpSysUser mpSysUser = JSONUtil.toBean(userInfo, MpSysUser.class);
//				mpSysUser.setNickName(EmojiParser.removeAllEmojis(mpSysUser.getNickName()));
//				Wrapper<MpSysUser> wrapper = new EntityWrapper<MpSysUser>();
//				wrapper = wrapper.eq("wx_open_id", mpSysUser.getOpenId());
//				mpSysUser.setUpdateTime(getNow());
//				iMpSysUserService.update(mpSysUser, wrapper);
//				return rtnParam(0, userInfo);
//			}
//		} catch (InvalidAlgorithmParameterException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return rtnParam(50021, null);
//	}
	/**
	 * 根据code获取session_key和openId
	 * @param code
	 * @return
	 */
	public WxSessionBean getSessionKeyOrOpenId(String code){
		ResponseEntity<String> resultEntity;
		try {
			resultEntity = this.restTemplate.exchange(
					this.code2sessionUrl,
					HttpMethod.GET,
					null,
					this.responseType,
					appId, secret, code);
		} catch (final RestClientException e) {
			logger.error(ResponseCode.FAIL_REQUEST_WX_SERVER.getMsg(), e);
			throw new NepaException(ResponseCode.FAIL_REQUEST_WX_SERVER);
		}
		WxSessionBean sessionBean = JsonUtils.toBean(resultEntity.getBody(), WxSessionBean.class);
		if (sessionBean != null && sessionBean.getErrcode() != null) {
			logger.error(ResponseCode.ERROR_RESPONSE_WX_SERVER.getMsg(), sessionBean.getErrcode());
			throw new NepaException(sessionBean.getErrcode(),sessionBean.getErrmsg());
		}
		return sessionBean;
	}
}

