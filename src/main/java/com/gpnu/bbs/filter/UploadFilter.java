package com.gpnu.bbs.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Component
public class UploadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        //System.out.println(uri);
        System.out.println("filter method");
        String fileDir = "D:/Temp";
        String filePath = fileDir + uri;
        System.out.println(filePath);
        File f = new File(filePath);
        if (f.exists()){
            servletResponse.getOutputStream().write(Files.readAllBytes(f.toPath()));
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
