package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChsDao;
import model.Chs;

@WebServlet("/ChServlet")
public class ChServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータからチャンネル名と説明を取得
    	request.setCharacterEncoding("UTF-8");
        String chName = request.getParameter("channelName");
        String chComment = request.getParameter("channelDescription");

        // チャンネルをデータベースに保存するなどの処理?
        // コンソールに出力
        System.out.println("新しいチャンネルが作成されました！");
        System.out.println("チャンネル名: " + chName);
        System.out.println("説明: " + chComment);

        ChsDao cDao = new ChsDao();
        cDao.regist(chName, chComment);
      //データの準備
        List<Chs> chList = null;
		//データベースからすべてのチャンネルを取得する
        chList = cDao.select();

		request.setAttribute("chList", chList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
		dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
