package springweb.z02_util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class A02_ConnObj {
/*
# java로 서버에 접근하여 데이터(문자열) 가져오기
1. 통신환경에 서버에 접근하려면, 접속의 상태를 나누어 여러 multi thread로 처리하여야 한다.
	(Thread 환경에서 특정 서버에 접속)
2. 서버에 접속 처리하는 객체 HttpURLConnection
	1) network상에서 tcp/ip 프로토콜 기반하에 데이터를 가져오려면
		통신 객체가 필요로 한다.
3. 서버에서 가져오는 데이터는 Stream형식으로 가져와야한다.
	1) network, local에 있는 file 등의 내용을 가져올 때는,
		java의 프로그래밍으로는 Stream형식으로 변환하여 가져오고,
		그것을 파일이나, 문자열로 처리하여야 한다.
*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. 기반 환경 Thread 환경을 만든다.
		//	  Thread객체로 run()메서드를 통해서 수행한다.
		/*
		Runnable 인터페이스를 상속받은 클래스 정의
		1) Runnable 인터페이스를 상속받아서 수행..
			class Prog implements Runnable{
				public void run(){
					// threading으로 특정프로그램을 수행한다.
					// ex) 여러사람이 동시접속시, 동시 처리되는것 같이 보인다. threaing이 없다면 한 사람 작업 끝나고 다음 사람 작업시작.
				}
			}
			Prog p1 = new Prog();
			Thread t1 = new Thread(p1);
			t1.start(); // run()의 선언된 내용을 수행..
			==> 익명 클래스로 선언과 수행을 간편화 처리..
			new Thread(new Runnable(){
				public void run(){
				
				}
			}).start();
			
			
		2) Thread 클래스를 바로 사용..
		class Prog2 extends Thread{
			public void run(){
			}
		}
		Prog2 p1 = new Prog2();
		
		*/
		// 1. Thread 환경에서 통신 처리하기..
		/*
		ThredEnron te1 = new ThredEnron("웹 사용자1");
		Thread t1 = new Thread(te1);
		t1.start();
		ThredEnron te2 = new ThredEnron("웹 사용자2");
		Thread t2 = new Thread(te2);
		t2.start();
		*/
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 서버와 통신 모듈 호출. 처리..
				String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20200421";
				request(url);
			}
		}).start();
	}
	// 서버 통신 처리하는 기능 메서드 정의
	public static void request(String urlS) {
		// 1. 통신 처리 모듈..
		// 	1) 주소 처리 객체 URL
		try {
			URL url = new URL(urlS);
		//	2) HttpURLConnection 객체를 통해서 서버에 stream을 통해서 가져오기.
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn != null) {
		//		- connectTimeout : 1/1000초 단위로 접속후, Timeout시간 지정.
				conn.setConnectTimeout(10000);
		//		- 요청형식 선언 get/post
				conn.setRequestMethod("GET");
		// 		- 입력형식 여부..
				conn.setDoInput(true);
				int resCode = conn.getResponseCode();
				System.out.println("반응코드값: "+resCode); // 정상 범위일때 처리 (200)
				if(resCode==200) {
		// 2. 통신에 의해 받은 스트림 데이터를 출력.
		//		1) Stream으로 일단 서버에서 준 데이터 받기
					System.out.println("# 서버에서 온 stream으로 온 객체 #");
					System.out.println(conn.getInputStream());
					// HttpInputStream ==> InputStreamReader(2byte 문자열을 가져오는 객체)
					// ==> BufferedReader (줄바꿈으로 line별로 문자열을 가져오는 처리)
					BufferedReader reader = new BufferedReader(
												new InputStreamReader(
														conn.getInputStream()));
		//		2) line별로 문자열 내용 확인..
					String line = null;
					while( (line = reader.readLine() ) != null) {
						System.out.println(line);
						// {"boxOfficeResult":{"boxofficeType":"일별 박스오피스","showRange":"20200421~20200421","dailyBoxOfficeList":[{"rnum":"1","rank":"1","rankInten":"0","rankOldAndNew":"OLD","movieCd":"20167904","movieNm":"라라랜드","openDt":"2016-12-07","salesAmt":"13274620","salesShare":"9.4","salesInten":"-349060","salesChange":"-2.6","salesAcc":"30472887368","audiCnt":"2601","audiInten":"-25","audiChange":"-1","audiAcc":"3667647","scrnCnt":"264","showCnt":"453"},{"rnum":"2","rank":"2","rankInten":"0","rankOldAndNew":"OLD","movieCd":"20192399","movieNm":"1917","openDt":"2020-02-19","salesAmt":"17875860","salesShare":"12.7","salesInten":"1096700","salesChange":"6.5","salesAcc":"7171258780","audiCnt":"2186","audiInten":"149","audiChange":"7.3","audiAcc":"789265","scrnCnt":"272","showCnt":"461"},{"rnum":"3","rank":"3","rankInten":"0","rankOldAndNew":"OLD","movieCd":"20206190","movieNm":"서치 아웃","openDt":"2020-04-15","salesAmt":"13780860","salesShare":"9.8","salesInten":"-2030720","salesChange":"-12.8","salesAcc":"213696540","audiCnt":"1704","audiInten":"-279","audiChange":"-14.1","audiAcc":"24314","scrnCnt":"280","showCnt":"550"},{"rnum":"4","rank":"4","rankInten":"0","rankOldAndNew":"NEW","movieCd":"20205402","movieNm":"트롤: 월드 투어","openDt":"2020-04-29","salesAmt":"6130000","salesShare":"4.3","salesInten":"6130000","salesChange":"100","salesAcc":"6274000","audiCnt":"1226","audiInten":"1226","audiChange":"100","audiAcc":"1242","scrnCnt":"2","showCnt":"4"},{"rnum":"5","rank":"5","rankInten":"-1","rankOldAndNew":"OLD","movieCd":"20206021","movieNm":"건즈 아킴보","openDt":"2020-04-15","salesAmt":"9402500","salesShare":"6.7","salesInten":"-1824000","salesChange":"-16.2","salesAcc":"192883000","audiCnt":"1153","audiInten":"-225","audiChange":"-16.3","audiAcc":"21899","scrnCnt":"147","showCnt":"329"},{"rnum":"6","rank":"6","rankInten":"-1","rankOldAndNew":"OLD","movieCd":"20204741","movieNm":"비밀정보원: 인 더 프리즌 ","openDt":"2020-04-15","salesAmt":"8245120","salesShare":"5.8","salesInten":"-216320","salesChange":"-2.6","salesAcc":"109971060","audiCnt":"1046","audiInten":"-13","audiChange":"-1.2","audiAcc":"12771","scrnCnt":"165","showCnt":"282"},{"rnum":"7","rank":"7","rankInten":"0","rankOldAndNew":"OLD","movieCd":"20206402","movieNm":"오픈 더 도어 ","openDt":"2020-04-08","salesAmt":"6666400","salesShare":"4.7","salesInten":"-81000","salesChange":"-1.2","salesAcc":"232703900","audiCnt":"788","audiInten":"-20","audiChange":"-2.5","audiAcc":"25620","scrnCnt":"115","showCnt":"220"},{"rnum":"8","rank":"8","rankInten":"0","rankOldAndNew":"OLD","movieCd":"20205262","movieNm":"엽문4: 더 파이널","openDt":"2020-04-01","salesAmt":"5891500","salesShare":"4.2","salesInten":"254000","salesChange":"4.5","salesAcc":"583934600","audiCnt":"728","audiInten":"46","audiChange":"6.7","audiAcc":"67244","scrnCnt":"113","showCnt":"224"},{"rnum":"9","rank":"9","rankInten":"-3","rankOldAndNew":"OLD","movieCd":"20196716","movieNm":"유령선","openDt":"2020-04-15","salesAmt":"5710060","salesShare":"4.0","salesInten":"-2147420","salesChange":"-27.3","salesAcc":"87239340","audiCnt":"699","audiInten":"-279","audiChange":"-28.5","audiAcc":"9837","scrnCnt":"140","showCnt":"195"},{"rnum":"10","rank":"10","rankInten":"-1","rankOldAndNew":"OLD","movieCd":"20206447","movieNm":"라라걸","openDt":"2020-04-15","salesAmt":"5380980","salesShare":"3.8","salesInten":"384320","salesChange":"7.7","salesAcc":"67502580","audiCnt":"685","audiInten":"71","audiChange":"11.6","audiAcc":"7729","scrnCnt":"146","showCnt":"191"}]}}

					}
					
					
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("통신 및 stream 에러 발생: "+e.getMessage());
		}
		
	}
	
}

class ThredEnron implements Runnable{
	private String name;
	
	public ThredEnron() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThredEnron(String name) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 통신 처리할 내용 구현..
		for(int cnt=0; cnt<100; cnt++) {
			System.out.println(name+"수행 "+cnt+"번 수행 내용");
		}
	}
}