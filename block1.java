import java.util.Scanner;

public class block1{

				// 1

	public static int remainder(int a, int b){
		return a % b;
	} 

				// 2

	public static int triArea(int a, int b){
		return a * b / 2;
	}

				// 3

	public static int animals(int a, int b, int c){
		return a * 2 + b * 4 + c * 4;
	}

				// 4

	public static boolean profitableGamble(double prob, double prize, double pay){
		if((prob * prize - pay) > 0)
			return true;
		else
			return false;
	}

				// 5

	public static String operation(int N, int a, int b){
		if(N == a + b)
			return "added";
		else if(N == a - b)
			return "substracted";
		else if(N == a * b)
			return "multiplied";
		else if(N == a / b)
			return "devided";
		else
			return "none";
	}

				// 6

	public static int ctoa(char a){
		return (int)a;
	}

				// 7

	public static int addUpTo(int a){
		int result = 0;
		for(int i = 0; i <= a; i++)
			result += i;
		return result;
	}

				// 8

	public static int nextEdge(int a, int b){
		return a + b - 1;
	}

				// 9

	public static int sumOfCubes(int a[]){
		int result = 0;

		for(int i : a)
			result += i * i * i;
		return result;
	}

				// 10

	public static boolean abcmath(int a, int b, int c){
		for(int i = 0; i < b; i++){
			a *= 2;
		}
		if(a % c == 0)
			return true;
		else
			return false;
	}

    public static void main(String []args) {
		Scanner in = new Scanner(System.in);

		int[] a = [1, 5, 9];
		System.out.println(sumOfCubes(a));
		//System.out.println(operation(24, 15, 9));	
				
		//double a = in.nextDouble();
    }
}
