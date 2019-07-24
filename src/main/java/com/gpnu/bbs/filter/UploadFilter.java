package com.gpnu.bbs.filter;



import com.gpnu.bbs.util.FileUtil;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        //System.out.println(uri);
        //System.out.println("filter method");
        String filePath = FileUtil.BASE_PATH + uri;
        File f = new File(filePath);
        if (f.exists()){
            servletResponse.getOutputStream().write(FileUtils.readFileToByteArray(f));
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
