package kr.or.ddit.exception;
/**
 * Throwable : 모든 비정상 실행 상황의 추상 
 *  - Error : 일반적으로 개발자가 직접 처리하지 않는 비정상 상황
 *  - Exception : 주로 직접 처리하는 비정상 상황
 *  	- checked (Exception) : 반드시 처리해주어야 하는 예외
 *      - unchecked (RunTimeException) : 처리하지 않아도 JVM에게 자동 throws되는 비정상
 * => 내가 원하는 예외에 맞춰서  
 * @author pc04
 * throws(호출자에게 예외 처리를 위임)
 * try~catch~finally(현재의 코드블럭 내에서 예외 처리)
 * throw(생성된 객체를 예외의 형태로 만들 때 사용)
 * 
 */
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
