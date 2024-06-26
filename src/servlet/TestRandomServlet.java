package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChsDao;
import model.Chs;

/**
 * Servlet implementation class TestRandomServlet
 */
@WebServlet("/TestRandomServlet")
public class TestRandomServlet extends HttpServlet {
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
		int i = 5;
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fnd = sdf.format(nowDate);
		//test
		fnd = "2024/06/30";
		ChsDao cDao = new ChsDao();
		List<String> booking = cDao.booking(fnd);
		if (booking.size() < i) {
			request.setAttribute("testMess", "5人未満のため生成されませんでした");
		} else {
			Collections.shuffle(booking);
			booking = booking.subList(0, i);
			for (String e : booking) {
				System.out.print(e);
			}
			cDao.regist("異文化交流", "今日のテーマ");

		}
		//データの準備
		List<Chs> chList = null;
		//データベースからすべてのチャンネルを取得する
		chList = cDao.select();
		request.setAttribute("chList", chList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ch.jsp");
		dispatcher.forward(request, response);
	}

}
