package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.LoginUser;
import model.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}
		//改造
		LoginUser userId = (LoginUser)session.getAttribute("id");
		String employee_id = userId.getId();
		// 検索処理を行う
		UserDao bDao = new UserDao();
		User user = bDao.select(employee_id);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//改造
		HttpSession session = request.getSession();
		LoginUser userId = (LoginUser)session.getAttribute("id");
		String employee_id = userId.getId();
		// 検索処理を行う
		UserDao bDao = new UserDao();
		User user = bDao.select(employee_id);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

}
