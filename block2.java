import java.util.Scanner;

public class block2{

				// 1

	public static void repeat(String a, int b){
		for(int i = 0; i < a.length(); i++)
			for(int j = 0; j < b; j++)
				System.out.print(a.charAt(i));
		System.out.println();
	}	

				// 2

	public static int differenceMaxMin(int[] a){
		int min, max; 
		max = min = a[0];
		for(int i : a){
			if(i < min)
				min = i;
			if(i > max)
				max = i;
		}
		return max - min;
	}	

				// 3

	public static boolean isAvgWhole(int[] a){
		double summ = 0;
		for(int i : a)
			summ += i;
		double avg = summ / a.length;
		if(avg % 1 == 0)
			return true;
		else
			return false;
	}

				// 4

	public static int[] comulativeSum(int[] a){
		int summ = 0;
		for(int i = 0; i < a.length; i++){
			summ += a[i];
			a[i] = summ;
		}
		return a;
	}

				// 5

	public static int getDecimalPlaces(String a){
		int id = a.indexOf('.');
		int len = a.length() - 1;
		if(id == -1)
			return 0;
		else
			return len - id;
	}

				// 6

	public static int Fibonacci(int a){
		// 1 2 3 5 8 13 21...
		if(a == 1)
			return 1;
		if(a == 2)
			return 2;

		int[] arr = new int[a];
		arr[0] = 1;
		arr[1] = 2;
		for(int i = 2; i < a; i++){
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[a - 1];
	}

				// 7

	public static boolean isValid(String a){
		if(a.length() != 5)
			return false;
		for(int i = 0; i < a.length(); i++)
			if((int)a.charAt(i) < 48 || (int)a.charAt(i) > 57)
				return false;
		return true;
	}

				// 8

	public static boolean isStrangePair(String a, String b){
		if(a.length() == 0 && b.length() == 0)
			return true;
		if(a.length() == 0 || b.length() == 0)
			return false;
		if(a.charAt(0) == b.charAt(b.length() - 1) &&
			b.charAt(0) == a.charAt(a.length() - 1))
			return true;
		else return false;
	}

				// 9

	public static boolean isPrefix(String word, String prefix){
		if(prefix.length() >= word.length())
			return false;
		for(int i = 0; i < prefix.length() - 1; i++){
			if(word.charAt(i) != prefix.charAt(i))
				return false;
		}
		return true;
	}

	public static boolean isSuffix(String word, String suffix){
		if(suffix.length() >= word.length())
			return false;
		for(int i = 1; i < suffix.length(); i++){
			if(suffix.charAt(suffix.length() - i) != word.charAt(word.length() - i))
				return false;
		}
		return true;
	}

				// 10

	public static int boxSeg(int a){
		// value: 0 3 2 5 4 7 6 9 8...
		// index: 0 1 2 3 4 5 6 7 8...
		if(a % 2 == 0)
			return a;
		else
			return a + 2;
	}

	public static void main(String []args){
		
				// 1
		System.out.print("2.1: ");
		repeat("mice", 5);
				// 2
		int[] a = {0, 4, 1 ,4, -10, -50, 32, 21};
		System.out.println("2.2: " + differenceMaxMin(a));
				// 3
		int[] b = {1, 5, 6};
		System.out.println("2.3: " + isAvgWhole(b));
				// 4
		int[] c = {3, 3, -2, 408, 3, 3};
		System.out.print("2.4: ");
		c = comulativeSum(c);
		for(int i : c)
			System.out.print(i + " ");
		System.out.println();
				// 5
		System.out.println("2.5: " + getDecimalPlaces("3.1"));
				// 6
		System.out.println("2.6: " + Fibonacci(7));
				// 7
		System.out.println("2.7: " + isValid("59001"));
				// 8
		System.out.println("2.8: " + isStrangePair("ratio", "orator"));
				// 9
		System.out.println("2.9.1: " + isPrefix("automation", "auto-"));
		System.out.println("2.9.2: " + isSuffix("arachnophobia", "-phobia"));
				// 10
		System.out.println("2.10: " + boxSeg(7));
	}
}