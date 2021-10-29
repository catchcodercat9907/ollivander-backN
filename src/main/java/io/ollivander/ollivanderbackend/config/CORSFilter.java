package io.ollivander.ollivanderbackend.config;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter extends GenericFilterBean {

    private String allowedOrigin;

    public CORSFilter(String allowedOrigin) {
        super();
        this.allowedOrigin = allowedOrigin.trim();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        if (ObjectUtils.isEmpty(response.getHeader("Access-Control-Allow-Origin"))) {
            response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("X-Frame-Options", "SameOrigin");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Cache-Control, X-Requested-With, Content-Type, Authorization, Accept-Language, X-Auth-DeviceId");
        response.setHeader("Access-Control-Expose-Headers", "Content-Type, Accept, Server-Timestamp, Content-Disposition, Authorization, Accept-Language");

        if (!"OPTIONS".equals(request.getMethod())) {
            chain.doFilter(req, res);
        }
    }
}
