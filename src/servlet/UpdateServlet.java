package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UpdateServlet extends HttpServlet {
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
        //ここまで
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String employeeId = request.getParameter("id");
		String username = request.getParameter("username");
//		String icon = fileName;
		String birth = request.getParameter("birth");
		String comment = request.getParameter("comment");
		int point = Integer.parseInt(request.getParameter("point"));
		String[] langList = request.getParameterValues("languages");

//		if (langList == null) {
//			langList = new String[0]; // Initialize to empty array if null
//		}

			// 更新または削除を行う
			UserDao uDao = new UserDao();
			if (request.getParameter("submit").equals("更新")) {
				if (uDao.update(employeeId,username,birth,comment,point,langList)) {	// 更新成功

					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfileServlet");
					dispatcher.forward(request, response);

				}

			}



	}

}
