package com.hl.auth.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.hl.auth.jwt.JWTGenerator;
import com.hl.base.util.model.Result;

/**
 * 基于HMAC（ 散列消息认证码）的无状态认证过滤器
 * @author liheyu
 * @date 2016年6月24日 下午2:55:15
 */
public class TokenFilter extends AccessControlFilter {
	
	@Value("${secret}")
	private String secret;
	/**
	 * 是否放行
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Assert.notNull(secret, "请配置token的secret");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = getSign(req);
		Result<String> result = new Result<>();
        //jwt的验证方式
        switch (JWTGenerator.getState(token, secret)) {
            case VALID:
                return true;
            case EXPIRED:
            	result.setMessage("token过期");
            	result.setCode(404);
                writeObj2Res(res, result);
                return false;
            case INVALID:
            	result.setMessage("token不可用");
            	result.setCode(406);
            	writeObj2Res(res, result);
                return false;
            default:
            	break;
        }
		return false;
	}
	
	/**
	 * 拒绝处理
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}
	
	/**
     * 往response中写入对象
     *
     * @param res
     * @param ajaxResult
     * @throws IOException
     */
    private void writeObj2Res(HttpServletResponse res, Result<String> result) throws IOException {
        res.setContentType(ContentType.APPLICATION_JSON.toString());
        res.setCharacterEncoding(Consts.UTF_8.toString());
        res.getOutputStream().write(JSON.toJSONString(result).getBytes());
    }
    
    /**
     * 把http header里的字段转换为对象
     * @param req http request
     * @return
     */
    private String getSign(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }
}
