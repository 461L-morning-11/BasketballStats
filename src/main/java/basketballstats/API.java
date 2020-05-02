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

						instance.setId((Long) jobj.get("id"));
						System.out.println(instance.getId());
						instance.setDivision((String) jobj.get("division"));
						instance.setAbbreviation((String) jobj.get("abbreviation"));
						instance.setCity((String) jobj.get("city"));
						instance.setLong_name((String) jobj.get("full_name"));
						instance.setShort_name((String) jobj.get("name")); 
						instance.setConference((String) jobj.get("conference"));
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
					instance.setFill(false);
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						instance.setId((Long) js.get("id"));
						System.out.println(instance.getId());
						instance.setFirst_name((String) js.get("first_name"));
						instance.setLast_name((String) js.get("last_name")); 
						instance.setShort_position((String) js.get("position"));
						instance.setPosition(""); 
						char[] position_letters = instance.getShort_position().toCharArray();
						for (int j = 0; j < instance.getShort_position().length(); j++) {
							try {
								if (position_letters[j] == 'C') {
									instance.setPosition(instance.getPosition() + "Center"); 
								} else if (position_letters[j] == 'G') {
									instance.setPosition(instance.getPosition() + "Guard");
								} else if (position_letters[j] == 'F') {
									instance.setPosition(instance.getPosition() + "Forward");
								} else if (position_letters[j] == 'C') {
									instance.setPosition(instance.getPosition() + "Center");
								} else if (position_letters[j] == '-') {
									instance.setPosition(instance.getPosition() + "-");
								}
							} catch (Exception e) {
							}
						}
						JSONObject t = (JSONObject) js.get("team");
						instance.setTeam_id((Long) t.get("id"));
						instance.setTeam_name((String) t.get("full_name"));
						instance.setTeam_conference((String) t.get("conference"));
						instance.setHeight_feet((long) js.get("height_feet"));
						instance.setWeight_pounds((long) js.get("weight_pounds"));
						instance.setHeight_inches((long) js.get("height_inches"));

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
					instance.setFill(false);
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.setNine_pts((Double) g.get("pts")); 
							instance.setNine_ast((Double) g.get("ast")); 
							instance.setNine_rbs((Double) g.get("reb")); 
							instance.setNine_ft_pct((Double) g.get("ft_pct"));
						} else {
							instance.setNine_pts(-1.0); 
							instance.setNine_ast(-1.0); 
							instance.setNine_rbs(-1.0); 
							instance.setNine_ft_pct(-1.0);
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
					instance.setFill(false); 
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true); 
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.setNine_pts((Double) g.get("pts")); 
							instance.setNine_ast((Double) g.get("ast")); 
							instance.setNine_rbs((Double) g.get("reb")); 
							instance.setNine_ft_pct((Double) g.get("ft_pct"));
						} else {
							instance.setNine_pts(-1.0); 
							instance.setNine_ast(-1.0); 
							instance.setNine_rbs(-1.0); 
							instance.setNine_ft_pct(-1.0);
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
					instance.setFill(false);
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.setNine_pts((Double) g.get("pts")); 
							instance.setNine_ast((Double) g.get("ast")); 
							instance.setNine_rbs((Double) g.get("reb")); 
							instance.setNine_ft_pct((Double) g.get("ft_pct"));
						} else {
							instance.setNine_pts(-1.0); 
							instance.setNine_ast(-1.0); 
							instance.setNine_rbs(-1.0); 
							instance.setNine_ft_pct(-1.0);
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
					instance.setFill(false);
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject js = (JSONObject) parse.parse(inline);

						JSONArray t = (JSONArray) js.get("data");
						if (t.size() != 0) {
							JSONObject g = (JSONObject) t.get(0);

							instance.setNine_pts((Double) g.get("pts")); 
							instance.setNine_ast((Double) g.get("ast")); 
							instance.setNine_rbs((Double) g.get("reb")); 
							instance.setNine_ft_pct((Double) g.get("ft_pct"));
						} else {
							instance.setNine_pts(-1.0); 
							instance.setNine_ast(-1.0); 
							instance.setNine_rbs(-1.0); 
							instance.setNine_ft_pct(-1.0);
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
					instance.setFill(false);
					throw new RuntimeException("HttpResponseCode: " + responsecode);
				} else {
					instance.setFill(true);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					while ((inline = in.readLine()) != null) {
						JSONObject jobj = (JSONObject) parse.parse(inline);
						JSONArray jsonarr = (JSONArray) jobj.get("data");

						for (Object o : jsonarr) {
							JSONObject js = (JSONObject) o;
							Long longtemp;
							instance.setId((Long) js.get("id"));
							System.out.println(instance.getId());
							String dateTemp = js.get("date") + "";
							dateTemp = dateTemp.substring(0, 10);
							instance.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(dateTemp)); 
							instance.setHome_score(Integer.valueOf((js.get("home_team_score") + "")));

							instance.setVisitor_score(Integer.valueOf((js.get("visitor_team_score") + ""))); 

							instance.setSeason(Integer.valueOf((js.get("season") + "")));
							longtemp = (long) js.get("period");
							instance.setPeriod(longtemp.shortValue()); 
							instance.setStatus(js.get("status") + ""); 
							instance.setTime(js.get("time") + ""); 
							instance.setPostseason((boolean) js.get("postseason")); 
							JSONObject g = (JSONObject) js.get("home_team");
							longtemp = (long) g.get("id");
							instance.setHome_name((String) g.get("name"));
							instance.setHome_id(longtemp.shortValue()); 
							JSONObject gg = (JSONObject) js.get("visitor_team");
							longtemp = (long) gg.get("id");
							instance.setVisitor_name((String) gg.get("name")); 
							instance.setVisitor_id(longtemp.shortValue()); 
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
