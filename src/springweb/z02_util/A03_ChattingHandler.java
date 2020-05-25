package springweb.z02_util;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;



/*
# chatting Handler 구현
0. clinet와 url pattern 방식으로 접속, 메시지 전송, 메시지 받기, 접속 종료, 예외처리를 해주는 모듈
	chatHandler ==> /chat.do
1. TextWebSocketHandler를 기본 클래스로 상속
	1) overriding 해야할 메서드 정의
		(client의 처리 함수와 연동 처리)
		- 접속시 처리할 내용
		- 채팅시 처리할 내용
		- 접속후 처리할 내용
		- 예외가 발생시 처리할 내용..
*/
@Controller("chatHandler")
public class A03_ChattingHandler extends TextWebSocketHandler{
/*
# 메서드에 넘겨오는 주요 매개 객체들.
1. WebSocketSession
	클라이언트가 접속하거나, 메시지를 보낼때, 클라이언트에 대한 정보를 가져온다.
2. TextMessage
	클라이언트가 전송한 메시지를 처리하는 객체.

# 서버에 등록된 client 정보
1. Map<String, WebSocketSession>
	put()를 통해서, id와 client Session을 추가/삭제 처리한다.
	

*/
	// client의 접속 정보 저장
	// ? 어떤 clinet가 어떤 메시지를 전달하고 전달 받는가(수신/발신)?
	// Map<String, WebSocketSession>
	// 	String : clinet의 식별 id
	//  WebSocketSession : 어떤 메시지를 전달받고, 전달하는지?
	private Map<String, WebSocketSession> users = new ConcurrentHashMap();
	
	// 1. 초기 clinet에서 접속 완료후, 처리할 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		// super.afterConnectionEstablished(session);
		log(session.getId()+"님 접속합니다!!");
		// client 추가 처리.(서버에 접속한 client 정보 누적)
		TextMessage message = new TextMessage("안녕하세요");
		session.sendMessage(message);
		
		users.put(session.getId(), session);
	}
	// 2. 메시지를 보낼 때 처리할 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		// 특정한 클라이언트에서 온 메시지를 접속한 모든 client에게 전달
		// 1) 매개변수
		//	  WebSocketSession session : 누가? client를 통해 전달되어@@@@@
		//    TextMessage message : 특정한 client에서 온 메시지..
		
		log(session.getId()+"에서 온 메시지:"+message.getPayload());
		// 접속한 client에 대한 메시지는 
		// WebSocketSession의 sendMessage()로 전달이 가능하다.
		// 각 client의 WebSocketSession 정보는 users.value()에 배열 형태로 가지고 있다.
		// users.values() : 접속한 client의 WebSocketSession 정보를 가지고 있다.
		
		for(WebSocketSession ws : users.values()) {
			// 1. 각 client에게 메시지 전달.
			//	    매개변수로 넘겨온 message를 모든 client에 전달 처리한다.
			ws.sendMessage(message);
			// 2. 전달하는 메시지 log 처리.
			// 3. 1:1 또는 group chatting을 하는 경우에는 이부분에 조건을 처리하여,
			// 	    특정한 id에 해당하는 client에게만 전달 되게 한다.
			log(ws.getId()+"님에게 전달 메시지 : "+message.getPayload());
		}
	}
	
	// 3. 접속 종료시 처리할 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		// super.afterConnectionClosed(session, status);
		// 접속 종료한 client를 서버에서 제외 처리..(put에서 id를 저장하여 삭제할 때도 id를 이용)
		log(session.getId()+" 접속 종료 합니다.");
		// 매개값으로 접속을 종료할 대산 client WebSocketSession 정보를 통해서
		// 등록된 client에서 삭제를 한다.
		
		users.remove(session.getId());
	}
	
	// 4. 예외가 발생시 처리할 메서드.
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		// super.handleTransportError(session, exception);
		// 에러가 발생한 session의 id값과 에러 내용 로그 출력.
		log(session.getId()+" 에러 발생!! 에러 내용 : "+exception.getMessage());
	}
	private void log(String logMsg) {
		System.out.println(new Date()+" : "+logMsg);
	}
}
