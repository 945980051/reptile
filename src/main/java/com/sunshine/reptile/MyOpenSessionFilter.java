package com.sunshine.reptile;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 1:39
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyOpenSessionFilter implements Filter {

    private final OpenSessionInViewFilter filter;

    public MyOpenSessionFilter() {
        filter = new OpenSessionInViewFilter();
        filter.setSessionFactoryBeanName("sessionFactory_soc");

    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filter.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        filter.destroy();
    }

}