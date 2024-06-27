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

import dao.ChsDao;
//自分がインポートしました
import dao.HomeDao;
import dao.UserDao;
import model.Chs;
import model.LoginUser;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}
//ログアウト処理
        if ("invalidate".equals(request.getParameter("action"))) {
            session.invalidate();
            response.sendRedirect("/C4/LoginServlet");
            return;
        }		request.setCharacterEncoding("UTF-8");
		List<Posts> PostList = new ArrayList<Posts>();
		LoginUser user = (LoginUser)session.getAttribute("id");
		String employee_id = user.getId();

		//検索処理を行う
		HomeDao hDao = new HomeDao();
		PostList = hDao.select();
		UserDao uDao = new UserDao();
		String name = uDao.getName(employee_id);

		//検索結果をリクエストスコープに格納する
		request.setAttribute("PostList", PostList);
		request.setAttribute("username", name);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータ
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		int chId = 0;
		String post = request.getParameter("post");
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("id");
		String employee_id = user.getId();
		List<Posts> PostList = new ArrayList<Posts>();
		HomeDao hDao = new HomeDao();


		if (date != null) {
			UserDao uDao = new UserDao();
			uDao.booking(date, employee_id);
			PostList = hDao.select();
		} else if (request.getParameter("submit").equals("検索")) {
			String search = request.getParameter("search");
			//検索処理を行う
			PostList = hDao.search(search);
		} else if (request.getParameter("submit").equals("投稿")) {
			chId = Integer.parseInt(request.getParameter("chId"));
			int comments = 0;
			int file_id = 0;
			String created_at = null;

			hDao.insert(new Posts(0, chId, employee_id, post, comments, 0, file_id, created_at));
			PostList = hDao.select();
		}else {
			//検索処理を行う
			PostList = hDao.select();
			UserDao uDao = new UserDao();
			String name = uDao.getName(employee_id);
			request.setAttribute("username", name);
		}

		//検索結果をリクエストスコープに格納する
		request.setAttribute("PostList", PostList);


		if (chId != 0) {
			ChsDao cDao = new ChsDao();
			Chs ch = cDao.chOneSelect(chId);
			request.setAttribute("ch", ch);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ChsPostServlet");
			dispatcher.forward(request, response);
		} else {
			//結果をページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			dispatcher.forward(request, response);
		}
	}

}
