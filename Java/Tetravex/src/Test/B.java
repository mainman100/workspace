package Test;

public class B extends A{
	int x, z;
	B () { super(); x=3; y=4; z=5; }
	int getx () { return x; }
	int getz () { return z; }
	public static void main(String[]args)
	{
		A o1 = new A();
		A o2 = new B();
		B o3 = new B();
		System.out.println(o1.x); // ------------------------------
		System.out.println(o1.getx()); // ------------------------------
		System.out.println(o1.y); // ------------------------------
		System.out.println(o2.x); // ------------------------------
		System.out.println(o2.getx()); // ------------------------------
		System.out.println(o2.y); // ------------------------------
		System.out.println(o3.x); // ------------------------------
		System.out.println(o3.getx()); // ------------------------------
		System.out.println(o3.y); // ------------------------------
		System.out.println(o3.getz()); // ------------------------------
	}
}

