package util.readexcel;


public class NewTest {
	public static void main(String[] args) {
		String a = "xpath=abcde";
		String [] b = a.split("xpath=");
		System.out.println(b.length);
		for(String x : b){
			System.out.println(x);
		}
	}

}
