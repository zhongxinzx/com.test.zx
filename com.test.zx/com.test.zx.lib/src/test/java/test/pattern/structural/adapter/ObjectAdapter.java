package test.pattern.structural.adapter;

/**
 * 对象适配器
 * (适配器模式的用意是要改变源的接口，以便于目标接口相容)
 * 
 * 相对类适配来说，更加灵活
 * @author zhongxin
 *
 */
public class ObjectAdapter implements Target {
	
	private Adaptee adaptee;
	
	public ObjectAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void request() {
		adaptee.specialRequest();
	}

	public Adaptee getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	
}
