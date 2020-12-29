import java.util.Scanner;
import java.util.Vector;

public class block3{

						// 1

	public static int solutions(int a, int b, int c){
		int d = b * b - 4 * a * c;
		if(d > 0)
			return 2;
		else if(d == 0)
			return 1;
		else
			return 0;
	}

						// 2

	public static int findZip(String a){
		int id1 = a.indexOf("zip");
		int id2 = a.lastIndexOf("zip");

		if(id1 != -1 && id2 != -1 && id1 != id2)
			return id2;
		else
			return -1;
	}

						// 3

	public static boolean checkPrefect(int a){
		Vector<Integer> arr = new Vector<>();
		for(int i = 1; i <= a/2 + 1; i++){
			if(a % i == 0)
				arr.addElement(i);
		}

		int summ = 0;
		for(int i : arr)
			summ += i;

		if(summ == a)
			return true;
		else
			return false;
	}	

						// 4

	public static String flipEndChars(String a){
		int len = a.length();
		if(len < 2)
			return "Incompatible.";
		if(a.charAt(0) == a.charAt(len - 1))
			return "Two's a pair";
		String b = a.charAt(len - 1) + a.substring(1, len - 1) + a.charAt(0);
		return b;
	}

						// 5

	public static boolean isValidHexCode(String a){
		int len = a.length();
		if(len < 7 || a.charAt(0) != '#')
			return false;
		for(int i = 1; i < len; i++){
			int code = (int)a.charAt(i);
			if(code < 48 || 
				code > 57 && code < 65 ||
				code > 70 && code < 97 ||
				code > 102)
					return false;
		}
		return true;
	}

						// 6

	public static boolean same(int[] a, int[] b){
		int aUnique, bUnique;
		aUnique = bUnique = 1;

		for(int i = 1; i < a.length; i++){
			if(a[i] != a[i - 1])
				aUnique++;
		}

		for(int i = 1; i < b.length; i++){
			if(b[i] != b[i - 1])
				bUnique++;
		}

		if(aUnique == bUnique)
			return true;
		else
			return false;
	}

						// 7

	public static boolean isKaprekar(Integer a){
		if(a == 0 || a == 1)
			return true;

		Integer square = a * a;
		String s = String.valueOf(square);
		int len = s.length();

		if(len <= 1)
			return false;

		Integer left = Integer.valueOf(s.substring(0, len/2));
		Integer right = Integer.valueOf(s.substring(len/2, len));

		if(left + right == a)
			return true;
		else
			return false;
	}

						// 8

	public static String longestZero(String a){
		int numOfZeros = 0;
		int longestNum = 0;
		String retval = "";
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == '0')
				numOfZeros++;
			else
			{
				if(numOfZeros > longestNum)
					longestNum = numOfZeros;
				numOfZeros = 0;
			}
		}

		for(int i = 0; i < longestNum; i++)
			retval = retval.concat("0");

		return retval;
	}

						// 9

	public static boolean isPrime(int a){
		for(int i = 2; i <= a / 2; i++)
			if(a % i == 0)
				return false;
		return true;
	}

	public static int nextPrime(int a){
		for(int i = a; true; i++){
			if(isPrime(i))
				return i;
		}
	}

						// 10

	public static boolean rightTriangle(int a, int b, int c){
		if(a < b){
			int buf = a;
			a = b;
			b = buf;
		}
		if(a < c){
			int buf = a;
			a = c;
			c = buf;
		}
		if((a * a) == (b * b + c * c))
			return true;
		else
			return false;
	}

	public static void main(String []args){
						// 1
		System.out.println("3.1: " + solutions(1, 0, -1));
						// 2
		System.out.println("3.2: " + findZip("all zip files are ziped"));
						// 3
		System.out.println("3.3: " + checkPrefect(6));
						// 4
		System.out.println("3.4: " + flipEndChars("Cat, dog, and mouse."));
						// 5
		System.out.println("3.5: " + isValidHexCode("#CD5C5C"));
						// 6
		int[] a = {1, 3, 4, 4, 4};
		int[] b = {2, 5, 7};
		System.out.println("3.6: " + same(a, b));
						// 7
		System.out.println("3.7: " + isKaprekar(45));
						// 8
		System.out.println("3.8: " + longestZero("01100001011000"));
						// 9
		System.out.println("3.9: " + nextPrime(11));
						// 10
		System.out.println("3.10: " + rightTriangle(3, 4, 5));
	}
}
