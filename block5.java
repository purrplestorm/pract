import java.util.Scanner;
import java.util.Vector;
import java.util.Stack;
import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class block5
{
				// 1.1

	public static String decrypt(int[] a){
		int lastCode = a[0];
		String result = Character.toString((char)a[0]);
		for(int i = 1; i < a.length; i++){
			int code = a[i] + lastCode;		// Код нужного символа
			lastCode = code;
			result = result.concat(Character.toString((char)code));
		}
		return result;
	}

				// 1.2

	public static int[] encrypt(String a){
		int lastCode = (int)a.charAt(0);
		int[] result = new int[a.length()];
		result[0] = lastCode;
		for(int i = 1; i < a.length(); i++){
			result[i] = (int)a.charAt(i) - lastCode;
			lastCode = (int)a.charAt(i);
		}
		return result;
	}

				// 2

	public static boolean canMove(String figure, String a, String b){
		figure = figure.toLowerCase();
		a = a.toLowerCase();
		b = b.toLowerCase();

		switch(figure){
			case("pawn"):
				if( a.charAt(0) == b.charAt(0) &&
					b.charAt(1) == (a.charAt(1) + 1))
					return true;
				else
					return false;
			case("rook"):
				if( a.charAt(0) == b.charAt(0) || 
					a.charAt(1) == b.charAt(1))
					return true;
				else
					return false;
			case("bishop"):
				if( Math.abs(a.charAt(0) - b.charAt(0)) ==
					Math.abs(a.charAt(1) - b.charAt(1)))
					return true;
				else
					return false;
			case("knight"):
				if( (Math.abs(a.charAt(0) - b.charAt(0)) == 2 &&
					Math.abs(a.charAt(1) - b.charAt(1)) == 1) ||
					(Math.abs(a.charAt(0) - b.charAt(0)) == 1 &&
					Math.abs(a.charAt(1) - b.charAt(1)) == 2))
					return true;
				else
					return false;
			case("queen"):
				if( Math.abs(a.charAt(0) - b.charAt(0)) ==
					Math.abs(a.charAt(1) - b.charAt(1)) ||
					(a.charAt(0) == b.charAt(0) || 
					a.charAt(1) == b.charAt(1)))
					return true;
				else
					return false;
			case("king"):
				if( (Math.abs(a.charAt(0) - b.charAt(0)) <= 1) &&
					Math.abs(a.charAt(1) - b.charAt(1)) <= 1)
					return true;
				else
					return false;
			default:
				return false;
		}
	}

				// 3

	public static boolean canComplete(String a, String b){
		Vector<Integer> vec = new Vector<>();

		// Записываем порядок вхождения символов в вектор
		for(char i : a.toCharArray()){
			if(b.indexOf(i) == -1) // Если такого символа нет, сразу отбрасываем
				return false;
			else
				vec.addElement(b.indexOf(i));
		}

		// Если массив отсортирован, и повторений нет, то всё хорошо
		for(int i = 0; i < vec.size() - 1; i++){
			if(vec.elementAt(i) >= vec.elementAt(i + 1))
				return false;
		}
		return true;
	}

				// 4

	public static int sumDigProd(int[] a){
		
		int sum = 0;

		for(int i = 0; i < a.length; i++){
			sum += a[i];
		}

		while(sum > 9){
			int buf = sum;
			sum = 1;
			// После выполнение цикла while в переменной "sum" будет произведение цифр
			while(buf > 1){	
				sum *= buf % 10;
				buf /= 10;
			}
		}

		return sum;
	}

				// 5

	public static Vector<String> sameVowelGroup(String[] a){
		Vector<String> retVal = new Vector<String>();
		retVal.addElement(a[0]);

		String[] b = new String[a.length];

		for(int i = 0; i < a.length; i++){
			String uniqueVowels = "";
			for(char j : a[i].toCharArray()){
				if ( j == 'a' || j == 'e' || j == 'o' || j == 'i' || j == 'u'){
					if(uniqueVowels.indexOf(j) == -1)
						uniqueVowels = uniqueVowels.concat(Character.toString(j));
				}
			}
			b[i] = uniqueVowels;	// Получаем массив уникальных гласных для каждого слова
		}

		for(int i = 1; i < b.length; i++)
		{
			boolean flag = true;
			for(char j : b[i].toCharArray()){
				// Проверяем наличие гласных слова с целевым
				if(b[0].indexOf(Character.toString(j)) == -1){
					flag = false;
					break;
				}
			}
			if(flag)
				retVal.addElement(a[i]);
		}

		return retVal;
	}

				// 6

	public static boolean validateCard(String a){
		String s = a;

		// Step 1

		s = s.substring(0, s.length() - 1);
		
		// Step 2
		
		Stack<Character> stack = new Stack<>();
		for(char i : s.toCharArray())
			stack.add(i);

		s = "";
		while(!stack.empty())
			s += stack.pop();
		
		// Step 3, 4

		int sum = 0;
		for(int i = 0; i < s.length(); i++){
			int buf = Character.getNumericValue(s.charAt(i));
			if(i % 2 == 0)
			{
				buf *= 2;
				if(buf > 9)
					buf = buf % 10 + buf / 10;
			}
			sum += buf;
		}

		// Step 5

		int checkSum = Character.getNumericValue(a.charAt(a.length() - 1));
		if((10 - (sum % 10)) == checkSum)
			return true;
		else
			return false;
	}

				// 7.1

	public static String numToEng(int a){

		if(a == 0)
			return "zero";

		String s = Integer.toString(a);
		int b = a;
		String result = "";

		String[] dictZeroToNineteen = {
			"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", 
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", 
			"sixteen", "seventeen", "eighteen", "nineteen" 
		};
		String[] dictTens = {
			"",
			"ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty",
			"ninety"
		};

		// Если есть сотни
		if(s.length() > 2){	 
			result += dictZeroToNineteen[Character.getNumericValue(s.charAt(0))] + " hundred ";
			b = b % 100;
		}

		// Если остаток больше 19
		if(b > 19)
		{
			result += dictTens[b / 10] + " ";
			if(b % 10 != 0)	// Чтобы не было one hundred twenty zero
				result += dictZeroToNineteen[b % 10];
		}
		// Остаток меньше 20
		else if(b > 0) 
			result += dictZeroToNineteen[b];

		return result;
	}

				// 7.2

	public static String numToRus(int a){
				if(a == 0)
			return "ноль";

		String s = Integer.toString(a);
		int b = a;
		String result = "";

		String[] dictZeroToNineteen = {
			"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", 
			"девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", 
			"шестнадцать", "семнадцать", "восемнадцать", "девятнадцать" 
		};
		String[] dictTens = {
			"",
			"десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят",
			"девяносто"
		};

		String[] dictHundreds = {
			"", "сто", "двести", "триста", "четыреста", "пятьсот", 
			"шестьсот", "семьсот", "восемьсот", "девятьсот"
		};

		// Если есть сотни
		if(s.length() > 2){	 
			result += dictHundreds[Character.getNumericValue(s.charAt(0))] + " ";
			b = b % 100;
		}

		// Если остаток больше 19
		if(b > 19)
		{
			result += dictTens[b / 10] + " ";
			if(b % 10 != 0)	
				result += dictZeroToNineteen[b % 10] + " ";
		}
		// Остаток меньше 20
		else if(b > 0) 
			result += dictZeroToNineteen[b] + " ";

		return result;
	}

				// 8

	private static String getSha256Hash(String a) throws NoSuchAlgorithmException{
		String result = "";
		MessageDigest digest;
		
		digest = MessageDigest.getInstance("SHA-256");	
		byte[] encodedHash = digest.digest(a.getBytes(StandardCharsets.UTF_8));

		StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
		for(int i = 0; i < encodedHash.length; i++){
			String hex = Integer.toHexString(0xff & encodedHash[i]);
			if(hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

				// 9

	public static String correctTitle(String a){
		a = a.toLowerCase();
		String result = "";
		Vector<String> arr = new Vector<>();
		int lastWord = 0;

		for(int i = 0; i < a.length(); i++){
			if( a.charAt(i) == ' ' || a.charAt(i) == '-'){
				i++;
				String word = a.substring(lastWord, i);
				arr.addElement(word);
				lastWord = i;
			}
		}

		arr.addElement(a.substring(lastWord, a.length()));

		for(String i : arr){
			if( i.equals("and ") || i.equals("the ") || 
				i.equals("of ") || i.equals("in "))
				result += i;
			else{
				String capital = Character.toString(i.charAt(0)).toUpperCase();
				result += capital + i.substring(1, i.length());
			}
		}

		return result;
	}

				// 10

	public static String hexLattice(int a){
		// Условие таково, что число должно быть суммой таблицы умножения шестерок
		int b = a - 1;
		for(int i = 1; b > 0; i++){
			b -= i * 6;
		}
		if(b < 0)
			return "Invalid";
		
		int mainHorizontal = 1;
		int numOfLines = 0;
		if(((a - 1) / 6) % 2 == 0){
			mainHorizontal = (a - 1) / 6 + 1;	// Четное частное
			numOfLines = (mainHorizontal - 1) / 2;
		}
		else{
			mainHorizontal = (a - 1) / 6 + 2;	// Нечетное
			numOfLines = (mainHorizontal - 1) / 2;
		}

		String upperHalf = "";
		String bottomHalf = "";
		String middlePart = "";

		int summ = a - mainHorizontal;

		// Средняя линия

		for(int i = 0; i < mainHorizontal; i++)
			middlePart += "o ";
		middlePart += "\n";

		// Нижние линии

		int increment = mainHorizontal - 1;
		for(int i = 1; i <= numOfLines; i++){
			for(int j = 0; j < i; j++)
				bottomHalf += " ";
			for(int j = 0; j < increment; j++)
				bottomHalf += "o ";
			increment--;
			bottomHalf += "\n";
		}

		// Верхние линии

		increment = mainHorizontal - numOfLines;
		for(int i = numOfLines; i > 0; i--){
			for(int j = 0; j < i; j++)
				upperHalf += " ";
			for(int j = 0; j < increment; j++)
				upperHalf += "o ";
			increment++;
			upperHalf += "\n";
		}
		return (upperHalf + middlePart + bottomHalf);
	}

	public static void main(String[] args)
	{
						// 1
		System.out.print("5.1.1: ");
		for(int i : encrypt("Hello"))
			System.out.print(i + ", ");
		System.out.print("\n5.1.2: ");
		int[] encryptedArr = {72, 29, 7, 0, 3};
		System.out.println(decrypt(encryptedArr));
						// 2
		System.out.print("5.2: ");
		System.out.println(canMove("knight", "a1", "b3"));
						// 3
		System.out.print("5.3: ");
		System.out.println(canComplete("bbutl", "beautiful"));
						// 4
		System.out.print("5.4: ");
		int[] sumDigProdArr = {1, 2, 3, 4, 5, 6};
		System.out.println(sumDigProd(sumDigProdArr));
						// 5
		System.out.print("5.5: ");
		String[] vowelsArr = {"hoops", "chuff", "bot", "bottom"};
		Vector<String> vowelsRetVal = sameVowelGroup(vowelsArr);
		for(String i : vowelsRetVal)
			System.out.print(i + " ");
		System.out.println();
						// 6
		System.out.print("5.6: ");
		System.out.println(validateCard("1234567890123452"));
						// 7
		System.out.print("5.7.1: ");
		System.out.println(numToEng(571));
		System.out.print("5.7.2: ");
		//chcp 65001 (utf8)
		System.out.println(numToRus(572));
						// 8
		System.out.print("5.8: ");
		try{
			System.out.println(getSha256Hash("password123"));
		}	
		catch(NoSuchAlgorithmException e){
			System.err.println(e.toString());
		}
						// 9
		System.out.print("5.9: ");
		System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
						// 10
		System.out.println("5.10: ");
		System.out.print(hexLattice(61));
	}
}