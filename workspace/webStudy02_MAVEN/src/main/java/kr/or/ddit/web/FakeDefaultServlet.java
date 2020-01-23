package kr.or.ddit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet({"/","/*","*.jsp"}) -- 톰캣이 받던 요청을 이 서블릿에서 처리 -> 하지 말 것
public class FakeDefaultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		resp.getWriter().println("메롱메롱");
	}
}
