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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	        // リクエストパラメータを取得
	        request.setCharacterEncoding("UTF-8");
	        String conversations_id = request.getParameter("conversations_id");
	        //System.out.print(conversations_id);

	        //conversations_idを取得
	        ConvDao cDao = new ConvDao();

	        List<> SelectConv = cDao.selectByconversations_id(conversations_id);
	        System.out.print(SelectConv);

	        // 結果をリクエストスコープに格納
	        request.setAttribute("SelectConv",SelectConv );

	        // JSPにフォワード
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/DM.jsp");
	        dispatcher.forward(request, response);
	    }

	}

}
