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

import dao.ChsDao;
import model.Chs;

@WebServlet("/ChServlet")
public class ChServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータからチャンネル名と説明を取得
		request.setCharacterEncoding("UTF-8");
		String chName = request.getParameter("channelName");
		String chComment = request.getParameter("channelDescription");
		ChsDao cDao = new ChsDao();
		if (request.getParameter("submit").equals("削除")) {
			int chId = Integer.parseInt(request.getParameter("channelId"));
			// 削除アクションの処理
			cDao.delete(chId);
			response.sendRedirect("ChServlet");
		} else if (request.getParameter("submit").equals("作成")) {
			// 表示（または更新）アクションの処理
			// チャンネルをデータベースに保存するなどの処理?
			// コンソールに出力
			System.out.println("新しいチャンネルが作成されました！");
			System.out.println("チャンネル名: " + chName);
			System.out.println("説明: " + chComment);

			cDao.regist(chName, chComment);
			//データの準備
			List<Chs> chList = null;
			//データベースからすべてのチャンネルを取得する
			chList = cDao.select();

			request.setAttribute("chList", chList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
			dispatcher.forward(request, response);
		} else if (request.getParameter("submit").equals(chName)) {
			int chId = Integer.parseInt(request.getParameter("channelId"));
			Chs ch = cDao.chOneSelect(chId);
			request.setAttribute("ch", ch);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ChsPostServlet");
			dispatcher.forward(request, response);
		} else if (request.getParameter("submit").equals("検索")) {
			// 検索アクションの処理
			List<Chs> chList = null;
			//データベースから検索したチャンネルを取得する
			chList = cDao.chSelect(chName);

			request.setAttribute("chList", chList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}
		//データの準備
		ChsDao cDao = new ChsDao();
		List<Chs> chList = null;
		//データベースからすべてのチャンネルを取得する
		chList = cDao.select();

		request.setAttribute("chList", chList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
		dispatcher.forward(request, response);
	}
}
