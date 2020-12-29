import java.util.Scanner;
import java.util.Vector;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class block6
{
			// 1
	// Ряд белла 1, 1, 2, 5, 15, 52, 203 ...
	// Считаем через формулу стирлинга
	// https://ru.wikipedia.org/wiki/Число_Белла
	//https://ru.wikipedia.org/wiki/Числа_Стирлинга_второго_рода

	public static int bell(int[] a){
		if(a.length == 0 || a.length == 1){
			return 1;
		}

		int result = 0;
		
		for(int m = 0; m <= a.length; m++){
			result += stirling(a.length, m);
		}

		return result;
	}

	public static int stirling(int n, int k){
		if(n == k)
			return 1;
		if(n <= 0 || k <= 0 || n < k)
			return 0;
		return stirling(n - 1, k - 1) + k * stirling(n - 1, k);
	}

				// 2

	public static boolean isVowel(Character a){
		a = Character.toLowerCase(a);
		if(a == 'a' || a == 'e' || a == 'i' || a == 'o' ||
			a == 'u' || a == 'y')
			return true;
		else
			return false;
	}

	public static String translateWord(String a){

		if(a.length() == 0)
			return "";

		String result = "";

		// Гласная
		if(isVowel(a.charAt(0))){
			result = a + "yay";
		}
		// Согласная
		else
		{
			int rightIndex = 0;	// Число согласных до первой гласной
			for(Character i : a.toCharArray()){
				if(isVowel(i))
					break;
				else
					rightIndex++;
			}
			result = a.substring(rightIndex, a.length()) + 
				a.substring(0, rightIndex) +
				"ay";
		}
		return result;
	}

	public static String translateSentence(String a){
		String result = "";
		int lastWord = 0;

		Vector<String> arr = new Vector<>();

		for(int i = 0; i < a.length(); i++){
			if( a.charAt(i) == ' ' || a.charAt(i) == '-'){
				String word = a.substring(lastWord, i);
				arr.addElement(word);
				lastWord = ++i;
			}
		}

		arr.addElement(a.substring(lastWord, a.length() - 1));

		for(String i : arr){
			result += translateWord(i) + " ";
		}

		return result;
	}

				// 3

	public static boolean validColor(String s){

		String a = s.substring(s.indexOf('(') + 1, s.length());
		Vector<String> numbers = new Vector<>();

		int lastId = 0;
		
		// Записываем три числа в вектор

		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == ',' || a.charAt(i) == ')'){
				String buff = a.substring(lastId, i);
				numbers.addElement(buff);
				lastId = i + 1;
			}
		}

		// Если указан rgba, но число аргументов не равно 4

		if(s.indexOf("rgba") != -1 && numbers.size() != 4)
			return false;

		for(String i : numbers){
			try{
				double val = Double.parseDouble(i);
				if(val < 0 || val > 255)
					return false;
			}
			catch(Exception e){
				return false;
			}
		}

		return true;
	}

				// 4

	public static String stripUrlParams(String s){
		
		if(s.indexOf("?") == -1)
			return s;

		String result = "";
		String a = s.substring(s.indexOf("?"), s.length());
		Map<String, Integer> params = new HashMap<String, Integer>();

		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == '=')
			{	
				// Map автоматически заменяет значения с одинаковыми ключами
				// на новую версию
				params.put(Character.toString(a.charAt(i - 1)), 
					Integer.parseInt(Character.toString(a.charAt(i + 1))));
				i++;
			}
		}

		result = s.substring(0, s.indexOf("?") + 1);

		for(Map.Entry<String, Integer> i : params.entrySet()){
			result += i.getKey() + "=" + i.getValue();
		}

		return result;
	}

	public static String stripUrlParams(String s, String[] b){
		
		if(s.indexOf("?") == -1)
			return s;

		String result = "";
		String a = s.substring(s.indexOf("?"), s.length());
		Map<String, Integer> params = new HashMap<String, Integer>();

		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == '=')
			{
				params.put(Character.toString(a.charAt(i - 1)), 
					Integer.parseInt(Character.toString(a.charAt(i + 1))));
				i++;
			}
		}

		result = s.substring(0, s.indexOf("?") + 1);
		
		for(int i = 0; i < b.length; i++){
			if(params.containsKey(b[i]))
				params.remove(b[i]);
		}

		for(Map.Entry<String, Integer> i : params.entrySet()){
			result += i.getKey() + "=" + i.getValue();
		}

		return result;
	}

				// 5

	public static Vector<String> getHashTags(String a){
		Vector<String> result = new Vector<>();
		Vector<String> words = new Vector<>();

		int lastWord = 0;

		// Составляем вектор слов

		for(int i = 0; i < a.length(); i++){
			if( a.charAt(i) == ' '){
				String word = a.substring(lastWord, i);
				words.addElement(word);
				lastWord = ++i;
			}
		}

		words.addElement(a.substring(lastWord, a.length()));

		// Число слов
		int numOfHastags = 3;
		if(words.size() < 3)
			numOfHastags = words.size();

		// Реверсим, чтобы искать максимум с конца, чтобы удовлетворить условию:
		// "Если несколько слов одинаковой длины, найдите слово, которое встречается первым"
		Collections.reverse(words);

		// Находим максимум numOfHastags раз 
		for(int i = 0; i < numOfHastags; i++){
			int maxSize = -1;
			String maxWord = "";

			for(String j : words){
				if(j.length() >= maxSize){
					maxWord = j;
					maxSize = j.length();
				}
			}

			result.addElement("#" + maxWord.toLowerCase());
			words.remove(maxWord);
		}

		return result;
	}

				// 6

	public static int ulam(int index){
		Vector<Integer> sequence = new Vector<>();
		
		sequence.addElement(1);
		sequence.addElement(2);

		for(int it = 3; it <= index; it++){
			Map<Integer, Boolean> sums = new HashMap<>();
			for(int i = 0; i < sequence.size() - 1; i++){
				for(int j = i + 1; j < sequence.size(); j++){
					if(sequence.elementAt(i) != sequence.elementAt(j)){
						int newSum = sequence.elementAt(i) + sequence.elementAt(j);
						if(sums.containsKey(newSum))
							sums.put(newSum, false);
						else
							sums.put(newSum, true);
					}
				}
			}
			sequence.addElement(findMin(sums, sequence.elementAt(sequence.size() - 1)));
		}
		return sequence.elementAt(sequence.size() - 1);
	}

	public static int findMin(Map<Integer, Boolean> a, int b){
		for(int i = b + 1; i < b * 2; i++){
			if(a.containsKey(i) && a.get(i) == true)
				return i;
		}
		return -1;
	}

				// 7

	public static String longestNonrepeatingSubstring(String a){
		// Храним виденные ранее символы в хэшмэп
		Map<Character, Integer> chars = new HashMap<>();
		String result = "";

		int firstIndex, lastIndex;
		firstIndex = lastIndex = 0;
		for( ; lastIndex < a.length(); lastIndex++){
			if(chars.containsKey(a.charAt(lastIndex))){
				// Если такой символ уже попал в текущий сабстринг, начинаем заново
				if(chars.get(a.charAt(lastIndex)) + 1 > firstIndex)
					firstIndex = chars.get(a.charAt(lastIndex)) + 1;
			}
			// Если текущий сабстринг длиннее предыдущего, перезаписываем
			if(lastIndex - firstIndex + 1 > result.length())
				result = a.substring(firstIndex, lastIndex + 1);
			chars.put(a.charAt(lastIndex), lastIndex);
		}

		return result;
	}

				// 8

	public static String convertToRoman(int a){
		String result = "";

		// I = 1; V = 5; X = 10; L = 50; C = 100; D = 500; M = 1000.

		Map<Integer, String> numbers = new HashMap<>();
		numbers.put(1, "I");
		numbers.put(4, "IV");
		numbers.put(5, "V");
		numbers.put(9, "IX");
		numbers.put(10, "X");
		numbers.put(40, "XL");
		numbers.put(50, "L");
		numbers.put(90, "XC");
		numbers.put(100, "C");
		numbers.put(400, "CD");
		numbers.put(500, "D");
		numbers.put(900, "CM");
		numbers.put(1000, "M");

		// Отдельно храним ключи отсортированные по убыванию для удобства
		ArrayList<Integer> keys = new ArrayList<Integer>(numbers.keySet());
		Collections.sort(keys);
		Collections.reverse(keys);

		while(a > 0){
			// Берем ближайшее число не больше текущего
			int closestValue = getClosestValue(keys, a);
			// Записываем справа
			result += numbers.get(closestValue);
			// Вычитаем найденное число
			a -= closestValue;
		}

		return result;
	}

	public static int getClosestValue(ArrayList<Integer> a, int b){
		
		for(Integer i : a){
			if(i <= b)
				return i;
		}
		return 1000;
	}

				// 9

	public static boolean formula(String s){
		String a = s + "=";
		// Удаляем пробелы
		a = a.replaceAll("\\s+", "");
		Vector<String> parts = new Vector<>();
		Vector<Integer> results = new Vector<>();

		for(int i = 0; i < s.length(); i++){

		} 

		// Получаем вектор всех частей уравнения
		
		while(a.indexOf("=") != -1){
			int id = a.indexOf("=");
			// Часть уравнения
			String i = a.substring(0, id);

			String operator = getOperator(i);
			int currResult = 0;

			if(operator != ""){
				int operatorId = i.indexOf(operator);
				int firstNum = Integer.parseInt(i.substring(0, operatorId));
				int secondNum = Integer.parseInt(i.substring(operatorId + 1, i.length()));
				switch (operator){
					case ("*"):
						currResult = firstNum * secondNum;
						break;
					case ("/"):
						currResult = firstNum / secondNum;
						break;
					case ("+"):
						currResult = firstNum + secondNum;
						break;
					case ("-"):
						currResult = firstNum - secondNum;
						break;
				}
			}
			// Если в этой части уравнения нет оператора, например "= 4"
			else{
				currResult = Integer.parseInt(i);
			}
			// Составляем массив результатов вычислений
			results.addElement(currResult);

			// Отрезаем пройденную часть
			a = a.substring(id + 1, a.length());
		}

		for(Integer i : results)
			if(i != results.elementAt(0))
				return false;

		return true;
	}

	public static String getOperator(String a){
		String operator = "";
		if(a.indexOf("*") != -1)
			operator = "*";	
		else if(a.indexOf("/") != -1)
			operator = "/";			
		else if(a.indexOf("+") != -1)
			operator = "+";
		else if(a.indexOf("-") != -1)
			operator = "-";
		return operator;
	}

				// 10

	public static boolean palindromeDescendant(int a){
		if(a < 10)
			return false;
		String s = Integer.toString(a);
		String left = s.substring(0, s.length()/2);
		String right = s.substring(s.length()/2, s.length());
		
		if(left.equals(right))
			return true;
		else
			return palindromeDescendant(descend(a));
	}

	public static int descend(int a){
		String s = "";
		while(a > 0){
			int right = a % 10;
			int left = a % 100 / 10;
			System.out.println(left + ", " + right);
			a /= 100;
			s = Integer.toString(right + left) + s;
		}
		return Integer.parseInt(s);
	}

	public static void main(String[] args)
	{
		System.out.print("6.1: ");
		int[] a = {1, 2, 3, 4};
		System.out.println(bell(a));
		System.out.print("6.2.1: ");	
		System.out.println(translateWord("Button"));
		System.out.print("6.2.2: ");
		System.out.println(translateSentence("I like to eat honey waffles."));
		System.out.print("6.3: ");
		System.out.println(validColor("rgb(0,0,0.123456789)"));
		System.out.print("6.4: ");
		String[] a1 = {"b"};
		System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", a1));
		System.out.print("6.5: ");
		Vector<String> task65 = getHashTags("Visualizing Science");
		for(String i : task65)
			System.out.print("\"" + i + "\", ");
		System.out.print("\n6.6: ");
		System.out.println(ulam(9));
		System.out.print("6.7: ");
		System.out.println(longestNonrepeatingSubstring("abcabcbb"));
		System.out.print("6.8: ");
		System.out.println(convertToRoman(16));
		System.out.print("6.9: ");
		System.out.println(formula("1 + 3 = 4 = 12 / 3 = 2 * 2"));
		System.out.print("6.10: ");
		System.out.print(palindromeDescendant(11211230));
	}

}

