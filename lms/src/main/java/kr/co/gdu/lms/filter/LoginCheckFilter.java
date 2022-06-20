package kr.co.gdu.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.gdu.lms.log.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/loginCheck/*")
public class LoginCheckFilter implements Filter {
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			log.debug(CF.LCH+"브라우저로 접속"+CF.RS);
			HttpSession session = ((HttpServletRequest)request).getSession();
			if(session.getAttribute("sessionId") == null) {
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
				return;
			}
		} else {
			log.debug(CF.LCH+"브라우저가 아닌 접속"+CF.RS);
		}
		chain.doFilter(request, response);
	}
}
