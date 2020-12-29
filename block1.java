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

    public static void main() {
		System.out.println("1.1");
		System.out.println(remainder(1, 2));
		System.out.println(remainder(3, 4));
		System.out.println(remainder(-9, 45));
		System.out.println(remainder(5, 5));
		//Task 1.2
		System.out.println("1.2");
		System.out.println(triArea(3,2));
		System.out.println(triArea(7,4));
		System.out.println(triArea(10,10));
		//Task 1.3
		System.out.println("1.3");
		System.out.println(animals(2,3,5));
		System.out.println(animals(1,2,3));
		System.out.println(animals(5,2,8));
		//Task 1.4
		System.out.println("1.4");
		System.out.println(profitableGamble(0.2, 50, 9));
		System.out.println(profitableGamble(0.9, 1, 2));
		System.out.println(profitableGamble(0.9, 3, 2));
		//Task 1.5
		System.out.println("1.5");
		System.out.println(operation(24,15,9));
		System.out.println(operation(24, 26, 2));
		System.out.println(operation(15, 11,11 ));
		System.out.println(operation(6, 2, 3));
		System.out.println(operation(2, 14, 7));
		//Task 1.6
		System.out.println("1.6");
		System.out.println(ctoa('A'));
		System.out.println(ctoa('a'));
		System.out.println(ctoa('m'));
		System.out.println(ctoa('['));
		System.out.println(ctoa('\\'));
		System.out.println(ctoa(' '));
		//Task 1.7
		System.out.println("1.7");
		System.out.println(addUpTo(3));
		System.out.println(addUpTo(10));
		System.out.println(addUpTo(7));
		//Task 1.8
		System.out.println("1.8");
		System.out.println(nextEdge(8,10));
		System.out.println(nextEdge(5,7));
		System.out.println(nextEdge(9,2));
		//Task 1.9
		System.out.println("1.9");
		System.out.println(sumOfCubes(new int[] {1,5,9}));
		//Task 1.10
		System.out.println("1.10");
		System.out.println(abcmath(42,5,10));
		System.out.println(abcmath(5,2,1));
		System.out.println(abcmath(1,2,3));

    }
}
