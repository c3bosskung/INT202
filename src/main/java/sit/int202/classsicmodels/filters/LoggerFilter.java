package sit.int202.classsicmodels.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

//@WebFilter(filterName = "LoggerFilter", servletNames = "ProductListServlet")
@WebFilter(filterName = "LoggerFilter", urlPatterns = {"/*"})
public class LoggerFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //before
        long before = System.currentTimeMillis();

        chain.doFilter(request, response); //do the resource

        //after
        HttpServletRequest req = (HttpServletRequest) request;
        long duration = System.currentTimeMillis() - before;
        String message = req.getRequestURI() + " duration: " +  duration + " ms";
        config.getServletContext().log(message);
    }

    public void destroy() {
    }
}

