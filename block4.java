import java.util.Scanner;
import java.util.Vector;

public class block4
{
						// 1

	public static String textEditor(int n, int k, String s){
		int lastId = 0;
		Vector<String> words = new Vector<>();
		// В этом цикле беребираем строку и вычленяем слова в вектор
		for(int i = 0; i < n - 1; i++){
			String buffer = s.substring(lastId, s.length());
			lastId += buffer.indexOf(' ') + 1;
			words.addElement(buffer.substring(0, buffer.indexOf(' ')));
		}
		words.addElement(s.substring(lastId, s.length()));

		String result = "";
		int currentLine = 0;
		for(String i : words){
			if(currentLine + i.length() > k){ // Место в строке кончилось
				result = result.concat("\n");
				currentLine = 0;
			}
			result = result.concat(i + " ");
			currentLine += i.length();
		}
		return result;
	}

						// 2

	public static String split(String s){
		int openedBrackets = 0;
		int left = 0;
		Vector<String> vec = new Vector<>();
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '(')
				openedBrackets++;
			else if(s.charAt(i) == ')')
				openedBrackets--;
			if(openedBrackets == 0){ // Число символов "(" и ")" равно, получаем кластер
				vec.addElement(s.substring(left, i + 1));
				left = i + 1;
			}
		}

		// Форматируем результат

		String result = "[ ";

		for(String i : vec)
			result = result.concat("\"" + i + "\", ");
		result = result.substring(0, result.length() - 2);
		result = result.concat(" ]");
		return result;
	}

						// 3.1

	public static String toCamelCase(String s){
		int left = 0;
		String result = "";
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '_'){
				// Находим символ "_", добавляем всё что было до него, послдний символ делаем заглавным
				result = result.concat(Character.toString(s.charAt(left)).toUpperCase() + s.substring(left + 1, i));
				i++;
				left = i;
			}
		}
		// Обрабатываем последний символ "_":
		result = result.concat(Character.toString(s.charAt(s.lastIndexOf('_') + 1)).toUpperCase() + s.substring(s.lastIndexOf('_') + 2, s.length()));
		// Не помню зачем это, но и без этого работает
		//result = Character.toString(s.charAt(0)).toLowerCase().concat(result.substring(1, result.length()));

		return result;
	}

						// 3.2

	public static String toSnakeCase(String a){
		String result = "";
		int left = 0;
		for(int i = 0; i < a.length(); i++){
			if(Character.isUpperCase(a.charAt(i)))
			{
				// Находим заглавную - добавляем всё что было до неё, символ "_" и уменьшаем закглавную
				result = result.concat(a.substring(left, i) + "_" +  Character.toString(a.charAt(i)).toLowerCase());
				i++;
				left = i;
			}
		}
		// Добавляем оставшиеся символы
		result = result.concat(a.substring(left, a.length()));
		return result;
	}

						// 4

	public static double overTime(double[] a){
		double base = a[1] - a[0];	// Обычные часы
		if(a[1] > 17){
			base -= a[1] - 17;	// Вычитаем из обычных сверхурочные
			base = base * a[2] + (a[1] - 17) * a[2] * a[3]; // Считаем
		}
		else
			base *= a[2];	// Сверхурочных нет
		return base;
	}

						// 5

	public static String BMI(String a, String b){
		double weight, height;
		if(a.indexOf("pounds") != -1){	// Переводим фунты
			weight = Double.parseDouble(a.substring(0, a.indexOf("pounds")));
			weight *= 0.453592;
		}
		else 	// Расчет в килограммах
			weight = Double.parseDouble(a.substring(0, a.indexOf("kilos")));
		if(b.indexOf("inches") != -1){	// Переводим дюймы
			height = Double.parseDouble(b.substring(0, b.indexOf("inches")));
			height *= 0.0254;
		}
		else 	// Расчет в метрах
			height = Double.parseDouble(b.substring(0, b.indexOf("meters")));

		double bmi = weight / (height * height);
		String text = "";
		if(bmi < 18.5)
			text = " Underweight";
		else if(bmi < 24.9)
			text = " Normal weight";
		else
			text = " Overweight";

		return String.format("%.1f", bmi) + text;
	}

						// 6

	public static int bugger(int a){
		int result = 0;
		for( ; a > 9; result++){
			int b = a;
			a = 1;
			while(b > 1){
				// "a" здесь это произведение каждой из цифр числа
				a *= b % 10;		// Уммножаем на последнюю цифру
				b /= 10;			// Убираем последнюю цифру
				// По окончанию циклов while используем новую "а" в цикле for
			}
		}
		return result;
	}

						// 7

	public static String toStarShorthand(String a){
		a = a.concat("!");

		char lastChar = a.charAt(0);	// Крайний уникальный символ
		int repeats = 0;				// Повторения символа
		String retVal = "";				// Результат

		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == lastChar)
				repeats++;
			else
			{
				// Вхождение нового символа
				retVal = retVal.concat(Character.toString(lastChar));
				if(repeats > 1)
					retVal = retVal.concat("*" + Integer.toString(repeats));
				lastChar = a.charAt(i);
				repeats = 1;
			}
		}
		return retVal;
	}

						// 8

	public static boolean doesRhyme(String a, String b){

		String lastA = a.substring(a.lastIndexOf(" ") + 1, a.length() - 1);
		String lastB = b.substring(b.lastIndexOf(" ") + 1, b.length() - 1);
		
		String vowelsA, vowelsB;
		vowelsA = vowelsB = "";

		for(char i : lastA.toLowerCase().toCharArray()){
			if ( i == 'a' || i == 'e' || i == 'o' || i == 'i' || i == 'u')
				vowelsA = vowelsA.concat(Character.toString(i));
		}

		for(char i : lastB.toLowerCase().toCharArray()){
			if ( i == 'a' || i == 'e' || i == 'o' || i == 'i' || i == 'u')
				vowelsB = vowelsB.concat(Character.toString(i));
		}

		if(vowelsA.equals(vowelsB))
			return true;
		else return false;
	}

						// 9

	public static boolean trouble(Integer a, Integer b){
		String a1 = Integer.toString(a);
		String b1 = Integer.toString(b);

		for(int i = 0; i <= 9; i++){
			// Проверяем все 9 сочетаний по три цифры
			String aim = Integer.toString(i) + 
				Integer.toString(i) + 
				Integer.toString(i);
			if(a1.indexOf(aim) != -1)
				if(b1.indexOf(Integer.toString(i) + Integer.toString(i)) != -1)
					return true;
		}
		return false;
	}

						// 10

	public static int countUniqueBooks(String a, char b){
		
		Vector<Integer> vec = new Vector<>();
		// Составляем вектор индексов символа "b" в строке "а"

		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == b)
				vec.addElement(i);
		}

		int count = 0;

		for(int i = 0; i < vec.size() - 1; i+=2){
			// Проходим по вектору с шагом 2
			String unique = "";
			// Берем два индекса и ищем уникальные символы в этом интервале
			for(int j = vec.elementAt(i) + 1; j < vec.elementAt(i + 1); j++){
				if(unique.indexOf(a.charAt(j)) == -1){
					unique = unique.concat(Character.toString(a.charAt(j)));
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args){
		System.out.println("test: " + 3%10);
						// 1
		System.out.println("4.1: ");
		System.out.println(textEditor(10, 7, "hello my name is Bessie and this is my essay"));
						// 2
		System.out.print("4.2: ");
		System.out.println(split("()()()"));
						// 3
		System.out.println("4.3.1: " + toCamelCase("hi_how_is_it_going"));
		System.out.println("4.3.2: " + toSnakeCase("hiHowIsItGoing"));
						// 4
		double[] arr1 = {13.25, 15, 30, 1.5};
		System.out.println("4.4: $" + overTime(arr1));
						// 5
		System.out.println("4.5: " + BMI("154 pounds", "2 meters"));
						// 6
		System.out.println("4.6: " + bugger(999));
						// 7
		System.out.println("4.7: " + toStarShorthand("77777geff"));
						// 8
		System.out.println("4.8: " + doesRhyme("Sam I am!", "Green eggs and HAM."));
						// 9
		System.out.println("4.9: " + trouble(451222777, 411227));
						// 10
		System.out.println("4.10: " + countUniqueBooks("AZYWABBCATTTA", 'A'));
	}
}