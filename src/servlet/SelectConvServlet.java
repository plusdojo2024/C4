package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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


//	employee_id 使用 conversations_id

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String employee_Id = request.getParameter("employee_Id");
//
//        UserDao convDao = new UserDao();
//        int conversationId = convDao.getConversationIdByEmployeeId(employee_Id);
//
//        if (conversationId != -1) {
//            request.setAttribute("conversationsId", conversationId);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dm.jsp");
//            dispatcher.forward(request, response);
//        } else {
//            // エラーメッセージを設定して、アカウントページに戻す
//            request.setAttribute("errorMessage", "アカウントが見つかりませんでした。");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
//            dispatcher.forward(request, response);
//        }
//    }
}