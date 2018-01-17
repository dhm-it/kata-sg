package com.dhm.bank.filters;

import com.dhm.bank.envConfig.AppPropperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED, "token not found in request");
            return;
        }
        String token = header.substring(7);
        //TODO
        //add test if a request contains a token or not



        filterChain.doFilter(request, response);

    }

}
