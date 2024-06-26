package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChsDao;

/**
 * Servlet implementation class TestRandomServlet
 */
@WebServlet("/TestRandomServlet")
public class TestRandomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/C4/LoginServlet");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Date nowDate = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 String fnd = sdf.format(nowDate);
		ChsDao cDao = new ChsDao();
		List<String> booking = cDao.booking(fnd);
		if (booking.size() < 5) {
			request.setAttribute("testMess", "5人以下のため生成されませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
			dispatcher.forward(request, response);
		} else {
			;
		}
	}

}
