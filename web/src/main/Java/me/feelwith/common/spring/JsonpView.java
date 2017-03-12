package me.feelwith.common.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by L on 2016/11/26.
 */
public class JsonpView extends MappingJackson2JsonView{
	private static final String DEFAULT_CONTENT_TYPE ="application/javascript";

	@Override
	public String getContentType(){
		return DEFAULT_CONTENT_TYPE;
	}
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if("get".equalsIgnoreCase(request.getMethod())){
        String callbakc = "call";
			if(request.getParameterMap().containsKey(callbakc)){
				StringBuilder sb = new StringBuilder(request.getParameter(callbakc)).append("(");
				response.getOutputStream().write(sb.toString().getBytes());
				super.render(model, request, response);
				response.getOutputStream().write(")".getBytes());
				return;
			}
//		}
        super.render(model, request, response);
//		super.render(model, request, response);
	}

}
