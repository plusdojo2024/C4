package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class SelectConvServlet
 */
@WebServlet("/SelectConvServlet")
public class SelectConvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectConvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * アカウントから特定のDMに遷移
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("user_id");

        UserDao userDao = new UserDao();
        int conversationId = userDao.getConversationIdByUserId(userId);

        if (conversationId != -1) {
            request.setAttribute("conversationsId", conversationId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dm.jsp");
            dispatcher.forward(request, response);
        } else {
            // エラーメッセージを設定して、アカウントページに戻す
            request.setAttribute("errorMessage", "会話が見つかりませんでした。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
            dispatcher.forward(request, response);
        }
    }
}