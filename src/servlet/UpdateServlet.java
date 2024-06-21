package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.Result;
import model.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Servlet doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doPost");
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/LinX/LoginServlet");
			return;
		}

		//改造
//		 Part filePart = request.getPart("icon");
//		 System.out.println("fp" + filePart);
//	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//	        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
//
//	        // Creates the images directory if it does not exist
//	        File uploadDir = new File(uploadPath);
//	        if (!uploadDir.exists()) {
//	            uploadDir.mkdir();
//	        }
//
//	        // Saves the file on the server
//	        try (InputStream inputStream = filePart.getInputStream();
//	             FileOutputStream outputStream = new FileOutputStream(uploadPath + File.separator + fileName)) {
//	            int read;
//	            final byte[] bytes = new byte[1024];
//	            while ((read = inputStream.read(bytes)) != -1) {
//	                outputStream.write(bytes, 0, read);
//	            }
//	        }
//
//	        System.out.println("file" + fileName);
//	        System.out.println("input" + uploadPath);



        //ここまで
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String employeeId = request.getParameter("id");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
//		String icon = fileName;
		String birth = request.getParameter("birth");
		String comment = request.getParameter("comment");
		int point = Integer.parseInt(request.getParameter("point"));
		String[] langList = request.getParameterValues("languages");
//		System.out.println("upadating employeeId" + employeeId);
//		System.out.println("upadating name" + username);
//		System.out.println("upadating birth" + birth);
//		System.out.println("upadating lang" + lang);

//		if (langList == null) {
//			langList = new String[0]; // Initialize to empty array if null
//		}

			// 更新または削除を行う
			UserDao uDao = new UserDao();
			if (request.getParameter("submit").equals("更新")) {
				if (uDao.update(new User(employeeId,password,username,birth,comment,point),langList)) {	// 更新成功
					request.setAttribute("result",
					new Result("更新成功！", "レコードを更新しました。", "/LinX/HomeServlet"));
				}
				else {												// 更新失敗
					request.setAttribute("result",
					new Result("更新失敗！", "レコードを更新できませんでした。", "/LinX/HomeServlet"));
				}
			}



			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);




	}

}
