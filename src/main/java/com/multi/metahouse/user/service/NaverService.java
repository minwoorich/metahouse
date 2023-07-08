package com.multi.metahouse.user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class NaverService {
	
	/* 인증 요청문을 구성하는 파라미터 */
	//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
	//response_type: 인증 과정에 대한 구분값. code로 값이 고정돼 있습니다.
	//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
	//state: 애플리케이션이 생성한 상태 토큰
	private final static String CLIENT_ID = "nvU8irdTypPGRr1XQ35g";
	private final static String CLIENT_SECRET = "JHGPQWc1At";
	private final static String REDIRECT_URI = "http://101.101.210.168:8088/metahaus/signnaver"; // local에서 확인시, 101.101.210.168 -> localhost로 변경
	
	/* 프로필 조회 API URL */
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	//결과값 JSON 형태로 변경
	public HashMap<String, String> getUserInfo(String jsonUserInfo){
		HashMap<String, String> userInfo = new HashMap<>();
		
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonUserInfo);
		
		JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
		
		String id = response.getAsJsonObject().get("id").getAsString();
		String gender = response.getAsJsonObject().get("gender").getAsString();
		if(gender.equals("M")) {
			gender = "male";
		}else {
			gender = "female";
		}
		
		String email = response.getAsJsonObject().get("email").getAsString();
		String mobile = response.getAsJsonObject().get("mobile").getAsString();
		String nickname = response.getAsJsonObject().get("name").getAsString();
		nickname = StringEscapeUtils.unescapeJava(nickname);
		
		String birth = response.getAsJsonObject().get("birthyear").getAsString() + "-" + response.getAsJsonObject().get("birthday").getAsString();
		
		//System.out.println("id: " + id);
		userInfo.put("id", id);
		//System.out.println("gender: " + gender);
		userInfo.put("gender", gender);
		//System.out.println("email: " + email);
		userInfo.put("email", email);
		//System.out.println("mobile: " + mobile);
		userInfo.put("mobile", mobile);
		//System.out.println("nickname: " + nickname);
		userInfo.put("nickname", nickname);
		//System.out.println("birth: " + birth);
		userInfo.put("birth", birth);
		
		return userInfo;
	}
	

	/* 네이버아이디로 Callback 처리 및 AccessToken 획득 Method */
	public OAuth2AccessToken getAccessToken(String code, String state) throws IOException {

		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).state(state).build(NaverLoginApi.instance());
			
		/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			
		return accessToken;
	}


	/* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).build(NaverLoginApi.instance());
		
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		
		return response.getBody();
	}
	
	public void unlink(String access_Token) {
		String reqURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&access_token=" + access_Token + "&service_provider=NAVER";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}