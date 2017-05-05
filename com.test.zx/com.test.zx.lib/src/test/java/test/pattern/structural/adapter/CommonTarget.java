package test.pattern.structural.adapter;

/**
 * 正常实现类
 * @author zhongxin
 *
 */
public class CommonTarget implements Target {

	@Override
	public void request() {
		System.out.println("This is a common request!");
	}

}
