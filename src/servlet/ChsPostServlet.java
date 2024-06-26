package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChsDao;
import model.Chs;
import model.Posts;

@WebServlet("/ChsPostServlet")
public class ChsPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエストを処理するメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			//ログインしてなかったら下記のサーブレットに遷移する
			response.sendRedirect("/C4/LoginServlet");
			return;
		}
    	// リクエストパラメータからチャンネル名を取得
        //String channelName = request.getParameter("channelName");
    	// テスト用
    	String channelName = "任意のチャンネル名";

        // チャンネル名がnullでなく、空でない場合
        if (channelName != null && !channelName.isEmpty()) {
            // チャンネル名をリクエストに設定
        	// ChsDaoのpostsテーブルから取得したデータをリクエストに設定
        	// リクエストパラメータを取得する
        	/*request.setCharacterEncoding("UTF-8");
        	int channels_id = Integer.parseInt(request.getParameter("channels_id"));
        	int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        	String content = request.getParameter("content");
        	int comments_id = Integer.parseInt(request.getParameter("comments_id"));
        	int reaction_id = Integer.parseInt(request.getParameter("reaction_id"));
        	int file_id = Integer.parseInt(request.getParameter("file_id"));
        	String created_at = request.getParameter("created_at");*/
        	// テスト用
        	int channels_id = 1;




        	ChsDao cDao = new ChsDao();
        	List<Posts> cardList = cDao.chPostSelect(channels_id);

        	request.setAttribute("cardList", cardList);
            request.setAttribute("channelName", channelName);
            // chPost.jspにフォワード
            request.getRequestDispatcher("/WEB-INF/jsp/chpost.jsp").forward(request, response);
        } else {
            // チャンネル名が無効な場合、ch.jspにリダイレクト
            //response.sendRedirect(request.getContextPath() + "/WEB-INF/jsp/ch.jsp");
        	// チャンネル名が無効な場合、ch.jspにフォワード
            request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp").forward(request, response);
        }
    }

    // POSTリクエストを処理するメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Chs ch = (Chs)request.getAttribute("ch");
    	int channels_id = ch.getChannelId();
    	String chName = ch.getChName();
    	String chComment = ch.getChComment();
    	ChsDao cDao = new ChsDao();
    	List<Posts> PostList = new ArrayList<Posts>();
    	PostList = cDao.chPostSelect(channels_id);
    	request.setAttribute("PostList", PostList);
    	request.setAttribute("channelName", chName);
    	request.setAttribute("chId", channels_id);
    	request.setAttribute("chComment", chComment);
    	request.getRequestDispatcher("/WEB-INF/jsp/chpost.jsp").forward(request, response);
    }
}

