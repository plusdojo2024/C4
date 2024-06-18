package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDao;




@WebServlet("/MessagesServlet")
public class MessagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dm.jsp");
		dispatcher.forward(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 // フォームから送信されたデータを取得
        int conversationsId = Integer.parseInt(request.getParameter("conversations_id"));
        String senderId = request.getParameter("sender_id");
        String receiverId = request.getParameter("receiver_id");
        String messageContent = request.getParameter("message_content");


          MessagesDao messageDAO = new MessagesDao();
          messageDAO.insertMessage(conversationsId, senderId, receiverId, messageContent);


          // メッセージ送信後の処理
          response.sendRedirect("dm.jsp");


}
}
