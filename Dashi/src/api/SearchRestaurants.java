package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class SearchRestaurants
 */
@WebServlet("/restaurants")
public class SearchRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchRestaurants() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = Logger.getLogger(SearchRestaurants.class.getName());

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * demo for three types of responses
		 * **/		
		//		//Tells the browser that server is returning a response in a format of JSON 
		//		response.setContentType("application/json");
		//		//Allow all viewers to view this response. 
		//		response.addHeader("Access-Control-Allow-Origin", "*");
		//		String username = "";
		//		//Create a PrintWriter from response such that we can add data to response. 
		//		PrintWriter out = response.getWriter();
		//		if (request.getParameter("username") != null) {
		//			//Get the username sent from the client (user)
		//			username = request.getParameter("username");
		//			//In the output stream, add some magic. 
		//			out.print("Hello " + username);
		//		}
		//		//Flush the output stream and send the data to the client side. 
		//		out.flush();
		//		out.close();   

		//return html files
		//		response.setContentType("text/html");
		//		PrintWriter out = response.getWriter();
		//		out.println("<html><body>");
		//		out.println("<h1>ying is the best !!</h1>");
		//		out.println("</body></html>");
		//		out.flush();
		//		out.close();

		//return json object
		//		response.setContentType("application/json");
		//		response.addHeader("Access-Control-Allow-Origin", "*");
		//
		//		String username = "";
		//		if (request.getParameter("username") != null) {
		//			username = request.getParameter("username");
		//		}
		//		JSONObject obj = new JSONObject();
		//		try {
		//			obj.put("username", username);
		//		} catch (JSONException e) {
		//			e.printStackTrace();
		//		}
		//		PrintWriter out = response.getWriter();
		//		out.print(obj);
		//		out.flush();
		//		out.close();

//				JSONArray array = new JSONArray();
//				try {
//					if (request.getParameterMap().containsKey("user_id")
//							&& request.getParameterMap().containsKey("lat")
//							&& request.getParameterMap().containsKey("lon")) {
//						String userId = request.getParameter("user_id");
//						double lat = Double.parseDouble(request.getParameter("lat"));
//						double lon = Double.parseDouble(request.getParameter("lon"));
//						// return some fake restaurants
//						array.put(new JSONObject().put("name", "Panda Express"));
//						array.put(new JSONObject().put("name", "Hong Kong Express"));
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				RpcParser.writeOutput(response, array);

		//update to version with DBConnection
		JSONArray array = new JSONArray();
		DBConnection connection = new MySQLDBConnection();
		if (request.getParameterMap().containsKey("lat")
				&& request.getParameterMap().containsKey("lon")) {
			// term is null or empty by default
			String term = request.getParameter("term");
//			HttpSession session = request.getSession(true);
			//String userId = (String) session.getAttribute("user");
			String userId = "1111";
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			LOGGER.log(Level.INFO, "lat:" + lat + ",lon:" + lon);
			array = connection.searchRestaurants(userId, lat, lon, term);
		}
		RpcParser.writeOutput(response, array);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
