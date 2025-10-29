package x.y;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.core.Ordered;

public class SimpleProfiler implements Ordered {

	private int order;

	// allows us to control the ordering of advice
	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	// this method is the around advice
	public Object profile(ProceedingJoinPoint call) throws Throwable {
		Object returnValue;
		StopWatch clock = new StopWatch(getClass().getName());
		try {
			clock.start(call.toLongString());
			 
			returnValue = call.proceed();
		} finally {
			clock.stop();
			System.out.println(clock.prettyPrint());
		}
		return returnValue;
	}
}