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

import dao.UserDao;
import model.User;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * アクセス時にaccount.jspを表示
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}

    	UserDao userDao = new UserDao();
        List<User> userList = userDao.selectAll();
        System.out.print("User List ..."+ userList);

        // 全ユーザー情報をリクエストスコープに格納
        request.setAttribute("userList", userList);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 検索キーワードでユーザーを検索、結果を表示する
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータを取得
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        System.out.print(username);

        UserDao userDao = new UserDao();
        // 検索キーワードに基づいてユーザーを検索
        List<User> searchResults = userDao.selectByUsername(username);
        System.out.print(searchResults);

        // 検索結果をリクエストスコープに格納
        request.setAttribute("searchResults", searchResults);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
        dispatcher.forward(request, response);
    }


}
