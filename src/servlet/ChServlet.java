package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChServlet")
public class ChServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータからチャンネル名と説明を取得
        String chName = request.getParameter("channelName");
        String chComment = request.getParameter("channelDescription");

        // チャンネルをデータベースに保存するなどの処理?
        // コンソールに出力
        System.out.println("新しいチャンネルが作成されました！");
        System.out.println("チャンネル名: " + chName);
        System.out.println("説明: " + chComment);

        // レスポンスを設定
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>チャンネル作成完了</h1>");
        out.println("<p>チャンネル名: " + chName + "</p>");
        out.println("<p>説明: " + chComment + "</p>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/" + "ch" + ".jsp");
		dispatcher.forward(request, response);
    }
}
