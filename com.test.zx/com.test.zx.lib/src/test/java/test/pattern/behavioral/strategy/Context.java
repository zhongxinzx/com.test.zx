package test.pattern.behavioral.strategy;

/**
 * 
 * 
 * 略模式属于对象的行为模式。其用意是针对一组算法，
 * 将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。
 * 策略模式使得算法可以在不影响到客户端的情况下发生变化。
 * @author zhongxin
 *
 */
public class Context {
	
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void useStrategy() {
		strategy.userStrategy();
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	
}
