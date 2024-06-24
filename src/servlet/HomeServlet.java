package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		List<Posts> PostList = new ArrayList<Posts>();

		//検索処理を行う
		HomeDao hDao = new HomeDao();
		PostList = hDao.select();

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
		String post = request.getParameter("post");
		List<Posts> PostList = new ArrayList<Posts>();
		HomeDao hDao = new HomeDao();

		if (request.getParameter("submit").equals("検索")) {
			String search = request.getParameter("search");
			//検索処理を行う
			PostList = hDao.search(search);
		} else if (request.getParameter("submit").equals("投稿")) {
			int employee_id = Integer.parseInt(request.getParameter("employee_id"));
			String content = request.getParameter("content");
			int comments = Integer.parseInt(request.getParameter("comments"));
			int file_id = Integer.parseInt(request.getParameter("file_id"));
			String created_at = null;

			hDao.insert(new Posts(0, 0, employee_id, content, comments, 0, file_id, created_at));
			PostList = hDao.select();
		}else {
			//検索処理を行う
			PostList = hDao.select();
		}

		//検索結果をリクエストスコープに格納する
		request.setAttribute("PostList", PostList);

		//結果をページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

	}

}
