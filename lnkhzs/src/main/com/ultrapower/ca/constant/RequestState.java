package com.ultrapower.ca.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** * @ClassName: RequestState 
* @Description: TODO 
* @author zhangzongbao 
* @date 2013-9-18 下午04:14:42 
* @version   *  
*/ 
public class RequestState implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private String name;
	private int code;
    public static final Map<Integer,RequestState> map=new HashMap();
	
	private RequestState(int code, String name) {
		this.code = code;
		this.name = name;
		map.put(code, this);
	}

	/** 正常 */
	public static final RequestState NORMAL = new RequestState(0, "正常");
	/** 客户端ID非法 */
	public static final RequestState CLIENTIDERROR = new RequestState(1, "客户端ID非法");
	/** 请求处理异常 */
	public static final RequestState SERVERERROR = new RequestState(2, "请求处理异常");
	/** 未知异常 */
	public static final RequestState UNKNOWERROR = new RequestState(3, "未知异常");
 	/**认证失败*/
	public static final RequestState AUTHENTICATIONERROR = new RequestState(4, "账号或密码错误，认证失败");
 	/**连接超时*/
	public static final RequestState CONNECTEXCEPTION = new RequestState(5, "服务异常，账号认证连接超时");
	/**OpenCA服务连接失败*/
	public static final RequestState OPENCACONNECTEXCEPTION = new RequestState(6, "OpenCA服务异常，请求连接超时");
	/**OpenCA SSL证书异常*/
	public static final RequestState SSLPEERUNVERIFIEDEXCEPTION = new RequestState(7, "服务器异常，没有一个有效的SSL证书");
	/**客户端异常*/
	public static final RequestState CLIENTEXCEPTON = new RequestState(8, "客户端异常");
	/**密码信息解密失败*/
	public static final RequestState PASSWORDDECRYPTEXCEPTION = new RequestState(9, "密码信息解密失败");
	/**非法根票据*/
	public static final RequestState ILLEGALBOOTTOKENEXCEPTION = new RequestState(10, "非法根票据");
	/**验证码错误*/
    public static final RequestState CHECKNUMBEREXCEPTION = new RequestState(11, "验证码错误");
	/**用户首次登录*/
	public static final RequestState FIRSTLOGINEXCEPTION = new RequestState(15, "用户首次登录");
	/**用户被锁定*/
	public static final RequestState ACCOUNTLOCKEDEXCEPTION = new RequestState(16, "用户被锁定");
	/**验证码失效*/
	public static final RequestState CHECKNUMBEREXPIREEXCEPTION = new RequestState(17, "验证码失效");
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	public static RequestState getRequestState(Integer code){
		return map.get(code);
	}
	
}
