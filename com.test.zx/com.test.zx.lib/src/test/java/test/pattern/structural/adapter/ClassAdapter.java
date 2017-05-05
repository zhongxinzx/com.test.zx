package test.pattern.structural.adapter;

/**
 * 类适配器
 * (适配器模式的用意是要改变源的接口，以便于目标接口相容)
 * @author zhongxin
 *
 */
public class ClassAdapter extends Adaptee implements Target {

	@Override
	public void request() {
		this.specialRequest();
	}

	/**
	 * 可以通过覆写来扩展SpecificRequest
	 */
	@Override
	public void specialRequest() {
		super.specialRequest();
	}
	
	
}
