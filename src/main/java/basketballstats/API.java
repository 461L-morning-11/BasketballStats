package basketballstats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {
	
	static APIProperties instance;
	
	public static APIProperties fetchAPI(int pageNum, String type) {
		instance = new APIProperties();
		JSONParser parse = new JSONParser();
		
		
		if(type == "teams") {
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/teams/" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200)
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				else {

					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject jobj = (JSONObject) parse.parse(inline);

						instance.id = (Long) jobj.get("id");
						System.out.println(instance.id);
						instance.division = (String) jobj.get("division");
						instance.abbreviation = (String) jobj.get("abbreviation");
						instance.city = (String) jobj.get("city");
						instance.long_name = (String) jobj.get("full_name");
						instance.short_name = (String) jobj.get("name");
						instance.conference = (String) jobj.get("conference");
					}

				}
				conn.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return instance;
		}
		
		
		if(type == "players") {
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/players/" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						instance.id = (Long) js.get("id");
						System.out.println(instance.id);
						instance.first_name = (String) js.get("first_name");
						instance.last_name = (String) js.get("last_name");
						instance.short_position = (String) js.get("position");
						instance.position = "";
						char[] position_letters = instance.short_position.toCharArray();
						for (int j = 0; j < instance.short_position.length(); j++) {
							try {
								if (position_letters[j] == 'C') {
									instance.position += "Center";
								} else if (position_letters[j] == 'G') {
									instance.position += "Guard";
								} else if (position_letters[j] == 'F') {
									instance.position += "Forward";
								} else if (position_letters[j] == 'C') {
									instance.position += "Center";
								} else if (position_letters[j] == '-') {
									instance.position += "-";
								}
							} catch (Exception e) {
							}
						}
						JSONObject t = (JSONObject) js.get("team");
						instance.team_id = (Long) t.get("id");
						instance.team_name = (String) t.get("full_name");
						instance.team_conference = (String) t.get("conference");
						instance.height_feet = (long) js.get("height_feet");
						instance.weight_pounds = (long) js.get("weight_pounds");
						instance.height_inches = (long) js.get("height_inches");

					}
				}
				conn.disconnect();

			} catch (Exception e0) {
				e0.printStackTrace();
			}

			// 2019 stats
			parse = new JSONParser();
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2019&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.nine_pts = (Double) g.get("pts");
							instance.nine_ast = (Double) g.get("ast");
							instance.nine_rbs = (Double) g.get("reb");
							instance.nine_ft_pct = (Double) g.get("ft_pct");
						} else {
							instance.nine_pts = -1.0;
							instance.nine_ast = -1.0;
							instance.nine_rbs = -1.0;
							instance.nine_ft_pct = -1.0;
						}

					}
				}
				conn.disconnect();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// 2018 stats
			parse = new JSONParser();
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2018&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.eight_pts = (Double) g.get("pts");
							instance.eight_ast = (Double) g.get("ast");
							instance.eight_rbs = (Double) g.get("reb");
							instance.eight_ft_pct = (Double) g.get("ft_pct");
						} else {
							instance.eight_pts = -1.0;
							instance.eight_ast = -1.0;
							instance.eight_rbs = -1.0;
							instance.eight_ft_pct = -1.0;
						}

					}
				}
				conn.disconnect();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			// 2017 stats
			parse = new JSONParser();
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2017&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.seven_pts = (Double) g.get("pts");
							instance.seven_ast = (Double) g.get("ast");
							instance.seven_rbs = (Double) g.get("reb");
							instance.seven_ft_pct = (Double) g.get("ft_pct");
						} else {
							instance.seven_pts = -1.0;
							instance.seven_ast = -1.0;
							instance.seven_rbs = -1.0;
							instance.seven_ft_pct = -1.0;
						}

					}
				}
				conn.disconnect();

			} catch (Exception e3) {
				e3.printStackTrace();
			}

			// 2016 stats
			parse = new JSONParser();
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/season_averages?season=2016&player_ids[]=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.six_pts = (Double) g.get("pts");
							instance.six_ast = (Double) g.get("ast");
							instance.six_rbs = (Double) g.get("reb");
							instance.six_ft_pct = (Double) g.get("ft_pct");
						} else {
							instance.six_pts = -1.0;
							instance.six_ast = -1.0;
							instance.six_rbs = -1.0;
							instance.six_ft_pct = -1.0;
						}

					}
				}
				conn.disconnect();

			} catch (Exception e4) {
				e4.printStackTrace();
			}
			return instance;
		}
		
		
		if (type == "games") {
			try {
				URL url = new URL("https://www.balldontlie.io/api/v1/games?per_page=100&page=" + pageNum);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responsecode = conn.getResponseCode();
				String inline = "";
				if (responsecode != 200) {
					instance.fill = false;
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.fill = true;
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject jobj = (JSONObject) parse.parse(inline);
						JSONArray jsonarr = (JSONArray) jobj.get("data");

						for (Object o : jsonarr) {
							JSONObject js = (JSONObject) o;
							Long longtemp;
							instance.id = (Long) js.get("id");
							System.out.println(instance.id);
							String dateTemp = js.get("date") + "";
							dateTemp = dateTemp.substring(0, 10);
							instance.date = new SimpleDateFormat("dd-MM-yyyy").parse(dateTemp);
							instance.home_score = Integer.valueOf((js.get("home_team_score") + ""));

							instance.visitor_score = Integer.valueOf((js.get("visitor_team_score") + ""));

							instance.season = Integer.valueOf((js.get("season") + ""));
							longtemp = (long) js.get("period");
							instance.period = longtemp.shortValue();
							instance.status = js.get("status") + "";
							instance.time = js.get("time") + "";
							instance.postseason = (boolean) js.get("postseason");
							JSONObject g = (JSONObject) js.get("home_team");
							longtemp = (long) g.get("id");
							instance.home_name = (String) g.get("name");
							instance.home_id = longtemp.shortValue();
							JSONObject gg = (JSONObject) js.get("visitor_team");
							longtemp = (long) gg.get("id");
							instance.visitor_name = (String) gg.get("name");
							instance.visitor_id = longtemp.shortValue();
						}

					}
				}
				conn.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return instance;
		}
		
		return instance;
	}
}
