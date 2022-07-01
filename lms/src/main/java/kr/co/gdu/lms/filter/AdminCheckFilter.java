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
@WebFilter("/loginCheck/adminCheck/*")
public class AdminCheckFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			int level = (int)session.getAttribute("sessionLv");
			log.debug(CF.LCH + "AdminCheckFilter level : " + level + CF.RS);
			
			if(level != 4) {
				if(response instanceof HttpServletResponse) {
					log.debug(CF.LCH + "총 관리자 제외 접근 불가" + CF.RS);
					((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/loginCheck/main");
					return;
				}
			}
		} else {
			log.debug(CF.LCH+" AdminCheckFilter 브라우저가 아닌 접속"+CF.RS);
		}
		chain.doFilter(request, response);
	}


}
