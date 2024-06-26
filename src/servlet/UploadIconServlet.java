package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.UserDao;

/**
 * Servlet implementation class UploadIconServlet
 */
@MultipartConfig
@WebServlet("/UploadIconServlet")
public class UploadIconServlet extends HttpServlet {
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
		System.out.print("Dopost");
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/C4/LoginServlet");
			return;
		}



		request.setCharacterEncoding("UTF-8");
		//改造
		 Part filePart = request.getPart("icon");

	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";

	        // Creates the images directory if it does not exist
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }

	        // Saves the file on the server
	        try (InputStream inputStream = filePart.getInputStream();
	             FileOutputStream outputStream = new FileOutputStream(uploadPath + File.separator + fileName)) {
	            int read;
	            final byte[] bytes = new byte[1024];
	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	        }

		String employeeId = request.getParameter("id");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
//		String icon = request.getParameter("icon");
		System.out.println("file Name " + fileName);
		 System.out.println("employeeid_phote" + employeeId);
		UserDao uDao = new UserDao();
		if (request.getParameter("submit").equals("変更")) {
			if(uDao.updatePhoto(employeeId,password,username,fileName)) {

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
				dispatcher.forward(request, response);


			}
		}





	}

}
