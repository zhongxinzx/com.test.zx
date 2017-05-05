package test.commcon;

public class Test {
	private String name = "name";
	private static String staticName = "staticName";
	static {
		System.out.println(staticName);
	}
	public Test(String name) {
		this.name = name;
		System.out.println(name);
	}
	public Test() {
		System.out.println(name);
	}
	public static void main(String[] args) {
		new Test("inputName");
		// new Test();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
