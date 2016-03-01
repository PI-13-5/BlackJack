package ua.nure.bj.auth;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public class XSSFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {
		XSSWrapper wrapper = new XSSWrapper((HttpServletRequest) req);
		arg2.doFilter(wrapper, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}