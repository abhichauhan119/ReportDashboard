package services;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import com.google.gson.Gson;

import database.DBManager;

/**
 * Servlet implementation class GetCurrentTestCases
 */
@WebServlet("/GetCurrentTestCases")
public class GetCurrentTestCases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentTestCases() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map <String,Integer> map=null;
		JSONObject jsonObject=new JSONObject();
		String json=null;
		
		//Adding Values for PieChart
		map=DBManager.getPieChartData();
		jsonObject.put("pieChart", map);
		
		//Adding values for barChart
		Map <String,Integer[]> map1=null;
		map1=DBManager.getBarChartData();
		jsonObject.put("barChart", map1);
		
		//Creating Json object to send back to Ajax call
		json=new Gson().toJson(jsonObject);
		
		//Set content type for response
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
