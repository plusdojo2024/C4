package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDao;
import model.messages;




@WebServlet("/MessagesServlet")
public class MessagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
    	/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/C4/LoginServlet");
			return;
		}*/
    	
		int conversationsId = 1;

		MessagesDao mDao = new MessagesDao();
		List<messages> messagesList = mDao.select(conversationsId);

		String id = "0001";//(String)session.getAttribute("id");

		request.setAttribute("id", id);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", messagesList);
		request.setAttribute("conversationsId", conversationsId);




				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dm.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/simpleBC/LoginServlet");
			return;
		}*/
		MessagesDao mDao = new MessagesDao();
		request.setCharacterEncoding("UTF-8");
		int conversationsId = Integer.parseInt(request.getParameter("conversationsId"));
		request.setAttribute("conversationsId", conversationsId);
		String message = request.getParameter("message");

		mDao.regist(conversationsId,message);
		// 検索処理を行う
		List<messages> messagesList = mDao.select(conversationsId);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", messagesList);

		String id = "0001";//(String)session.getAttribute("id");

		request.setAttribute("id", id);

		/*// メッセージを相手メッセージと自分のメッセージに分ける
				List<messages> myMessages = new ArrayList<messages>();
				List<messages> opponentMessages = new ArrayList<messages>();

				for (messages msg : myMessages) {
				    if (msg.getSenderId() =="1" ) {
				        // 自分のメッセージ
				        myMessages.add(msg);
				    } else {
				        // 相手のメッセージ
				        opponentMessages.add(msg);
				    }
				}
				// リクエストスコープに格納する
			    request.setAttribute("myMessages", myMessages);
			    request.setAttribute("opponentMessages", opponentMessages);*/


		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dm.jsp");
		dispatcher.forward(request, response);
	}
}
