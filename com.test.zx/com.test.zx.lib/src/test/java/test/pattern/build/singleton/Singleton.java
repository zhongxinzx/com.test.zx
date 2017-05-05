package test.pattern.build.singleton;

/**
 * 单例好处
 * 	1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 *	2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 *	3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。
 * （比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。
 * @author zhongxin
 *
 */
public class Singleton {
	
	/** 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
	private static Singleton instance = null;
	
	/** 私有构造方法，防止被实例化 */
	private Singleton() {
		
	}
	/** 静态工程方法，创建实例 */
	public static Singleton getInstance() {
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}
	/***********************************************************/
	/**
	 * 单例模式使用内部类来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
	 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，
	 * 并且会保证把赋值给instance的内存初始化完毕，这样我们就不用担心上面的问题。
	 * 同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
	 * 这样我们暂时总结一个完美的单例模式：
	 * @author zhongxin
	 *
	 */
	private static class SingletonFactory {
		private static Singleton instance = new Singleton();
		
	}
	/** 静态工程方法，创建实例 */
	public static Singleton getInstanceByInnerClass() {
		return SingletonFactory.instance;
	}
	
	
	
	/** 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return instance;
	}
}
