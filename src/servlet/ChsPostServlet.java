package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChsDao;
import dao.HomeDao;
import model.Chs;
import model.Posts;

@WebServlet("/ChsPostServlet")
public class ChsPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエストを処理するメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("/C4/LoginServlet");
		return;
    }

    // POSTリクエストを処理するメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Chs ch = (Chs)request.getAttribute("ch");
    	int channels_id = ch.getChannelId();
    	String chName = ch.getChName();
    	String chComment = ch.getChComment();
    	ChsDao cDao = new ChsDao();
    	HomeDao hDao = new HomeDao();
    	List<Posts> PostList = new ArrayList<Posts>();
    	PostList = cDao.chPostSelect(channels_id);
    	for (Posts p : PostList) {
			if (p.getComments() != 0) {
				p.setComuser(hDao.comUser(p.getComments()));
			}
		}
    	request.setAttribute("PostList", PostList);
    	request.setAttribute("channelName", chName);
    	request.setAttribute("chId", channels_id);
    	request.setAttribute("chComment", chComment);
    	request.getRequestDispatcher("/WEB-INF/jsp/chpost.jsp").forward(request, response);
    }
}

