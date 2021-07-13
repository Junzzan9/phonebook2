package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc") // 웹주소 변경 가능
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 액션 파라미터 읽기(업무 종류)
		String action = request.getParameter("action");
		System.out.println(action); // 액션값 확인

		if ("list".equals(action) || action == null) {

			// 리스트 업무
			System.out.println("[리스트폼]");

			// 리스트
			PhoneDao pDao = new PhoneDao(); // 다오 생성 (메소드 써야함)
			List<PersonVo> pList = pDao.getPersonList(); // 리스트 불러와서 메모리에 올리기

			System.out.println("controller =========================");
			System.out.println(pList);

			// 데이터를 가져오는것은 자바(servlet)가 편하나 html 작업은 html(jsp)로 분업

			// 데이터 넣기 servlet->jsp (setAttribute) jsp에서 쓰일 명칭 + 자료형
			request.setAttribute("pList", pList);

			/*
			 * request.setAttribute("age", 29); request.setAttribute("name", "오준식");
			 */

			// forward == servlet이 html작업을 jsp에게 시킨다.
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
		}

		else if ("wform".equals(action)) {
			System.out.println("글쓰기 폼");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		}

		else if ("insert".equals(action)) {
			System.out.println("[저장]");

			// Dao-->저장
			PhoneDao pDao = new PhoneDao();

			// 파라미터 꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String com = request.getParameter("company");

			// Vo 생성자에 값넣고 sql 테이블에 추가.
			PersonVo pVo = new PersonVo(name, hp, com);
			pDao.personInsert(pVo);

			// 리다이렉트.
			response.sendRedirect("/phonebook2/pbc?action=list");

		}

		else if ("uform".equals(action)) {
			System.out.println("[수정폼]"); // 확인용

			int no = Integer.parseInt(request.getParameter("id"));
			
			PhoneDao pDao = new PhoneDao();
			PersonVo pVo = pDao.getPerson(no);
			
			System.out.println(no);
			request.setAttribute("pVo", pVo);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
		}

		else if ("update".equals(action)) {
			// Dao-->저장
			PhoneDao pDao = new PhoneDao();

			// 파라미터 꺼내기
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String com = request.getParameter("company");

			// Vo 생성자에 값넣고 sql 테이블수정.
			PersonVo pVo = new PersonVo(id, name, hp, com);
			pDao.personUpdate(pVo);
			
			//리다이렉트.
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
		else if ("delete".equals(action)) {
			// Dao-->저장
			PhoneDao pDao = new PhoneDao();

			// 파라미터 꺼내기
			int id = Integer.parseInt(request.getParameter("id"));
	

			// 삭제메소드에 받은 id값 인트로 변환 후 넣기
			pDao.personDelete(id);
			
			//리다이렉트.
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
