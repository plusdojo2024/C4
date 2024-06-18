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
import model.Result;
import model.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/LinX/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String employeeId = request.getParameter("id");
		String username = request.getParameter("username");
//		String lang = request.getParameter("lang");
//		String birth = request.getParameter("birth");
//		String comment = request.getParameter("comment");
//		String point = request.getParameter("point");
		System.out.println("upadating employeeId" + employeeId);
		System.out.println("upadating name" + username);


			// 更新または削除を行う
			UserDao bDao = new UserDao();
			if (request.getParameter("submit").equals("編集")) {
				if (bDao.update(new User(username,employeeId))) {	// 更新成功
					request.setAttribute("result",
					new Result("更新成功！", "レコードを更新しました。", "/LinX/HomeServlet"));
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！", "レコードを更新できませんでした。", "/LinX/HomeServlet"));
				}
			}



			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);




	}

}
