//RecursiveD
//Recursive Descent Parser that takes in +,-,*, and /


//<expr> :== <term> + <expr> | <term> - <expr> | <term>
//<term> :== <exponent> * <term> | <exponent> / <term> | <exponent>
//<exponent> :== <factor> ^ <factor> | <factor>
//<factor> :== (<expr>) | [0-9] {[0-9]}

public class RecursiveD
{
	
	private static int m_next = 0;
	
	public static void main(String args[])
	{
		System.out.println(RecursiveD.expr("2^2.1"));
	}
	
	public static double expr(String input)
	{
		
		//Immediately call term
		double num = term(input);
		
		while(m_next < input.length() && (input.charAt(m_next) == '+' || input.charAt(m_next) == '-'))
		{			
			if(input.charAt(m_next) == '+')
			{	
				m_next++;
				num += term(input);
			}
			
			else if(input.charAt(m_next) == '-')
			{	
				m_next++;
				num -= term(input);
			}
		}
		return num;
	}
	
	public static double term(String input)
	{
		//Immediately call factor
		double num = exponent(input);
		
		while(m_next < input.length() && (input.charAt(m_next) == '*' || input.charAt(m_next) == '/'))
		{
			
			if(input.charAt(m_next) == '*')
			{	
				m_next++;
				num *= exponent(input);
			}
			
			else if(input.charAt(m_next) == '/')
			{	
				m_next++;
				num /= exponent(input);
			}
		}
		
		return num;
}
	
	public static double exponent(String input)
	{
		double num = factor(input);
		
		while(m_next < input.length() && input.charAt(m_next) == '^')
		{
			m_next++;
			num = Math.pow(num, factor(input));
		}
		
		return num;
	}
	
	public static double factor(String input)
	{
		try {
			
		String number = "";
		double returnNum = 0;
		
		while(m_next < input.length() && (Character.isDigit(input.charAt(m_next)) || input.charAt(m_next) == '.'))
		{
			number += Character.toString(input.charAt(m_next));
			m_next++;
		}
		
		if(!(number.equals("")))
		{
			return Double.parseDouble(number);
		}
		
		while(m_next < input.length() && input.charAt(m_next) == '(')
		{
			m_next++;
			returnNum = expr(input);
		}
		
		if(input.charAt(m_next) != ')')
		{
			System.err.println("SyntaxError");
			return 0;
		}
		
		else
		{
			m_next++;
			return returnNum;
		}
		}
		catch(StringIndexOutOfBoundsException err)
		{
			System.err.println("SyntaxError");
			return 0;
		}
		
	}
	
}