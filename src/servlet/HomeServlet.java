package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//自分がインポートしました
import dao.HomeDao;
import model.Posts;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");

		//検索処理を行う
		HomeDao hDao = new HomeDao();
		List<Posts> PostList = hDao.select();
		System.out.print(PostList);

		//検索結果をリクエストスコープに格納する
		request.setAttribute("PostList", PostList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータ
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		//検索処理を行う
		HomeDao hDao = new HomeDao();
		List<Posts> PostList = hDao.select();

		//検索処理を行う
		//HomeDao hDao = new HomeDao();
		//List<Posts> PostList = hDao.search(search);
		//System.out.print(PostList);

		//検索結果をリクエストスコープに格納する
		request.setAttribute("PostList", PostList);

		//結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

	}

}
