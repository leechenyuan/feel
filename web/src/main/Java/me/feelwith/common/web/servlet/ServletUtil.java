package me.feelwith.common.web.servlet;

import me.feelwith.business.data.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by L on 2017/2/11.
 */
public class ServletUtil {
    public static String getIpAddress(HttpServletRequest request){
       String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip == null) {
			return "";
		}

		// 多层代理时 IP 是用逗号隔开的
		int pos = ip.indexOf(",");
		if (pos > 0) {
			String[] ipArray = ip.split(",");
			int i = 0;
			for (i = 0; i < ipArray.length; i++) {
				String ipSub = ipArray[i].trim();
				if (!"unknown".equalsIgnoreCase(ipSub)) {
					ip = ipSub;
					break;
				}
			}
			if (i >= ipArray.length) {
				ip = ipArray[i - 1].trim();
			}
		}

		if (ip.length() <= 16) {
			return ip;
		} else {
			return ip.substring(0, 16);
		}
    }

   public static long getUserId(ServletRequest request) {
       return getUserId(request,0);
   }
    public static long getUserId(ServletRequest request,long defaultValue){
        User user = (User)request.getAttribute("user");
        if(user == null){
            return defaultValue;
        }
        return user.getUserId();
    }
}
