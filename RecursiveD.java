//RecursiveD
//Recursive Descent Parser that takes in +,-,*, and /

public class RecursiveD
{
	
	private static int m_next = 0;
	
	public static void main(String args[])
	{
		System.out.println(RecursiveD.expr("(4*(7*(1+2)))"));
	}
	
	public static int expr(String input)
	{
		
		//Immediately call term
		int num = term(input);
		
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
	
	public static int term(String input)
	{
		//Immediately call factor
		int num = factor(input);
		
		while(m_next < input.length() && (input.charAt(m_next) == '*' || input.charAt(m_next) == '/'))
		{
			
			if(input.charAt(m_next) == '*')
			{	
				m_next++;
				num *= factor(input);
			}
			
			else if(input.charAt(m_next) == '/')
			{	
				m_next++;
				num /= factor(input);
			}
		}
		
		return num;
	}
	
	public static int factor(String input)
	{
		String number = "";
		
		while(m_next < input.length() && Character.isDigit(input.charAt(m_next)))
		{
			number += Character.toString(input.charAt(m_next));
			m_next++;
		}
		
		if(!(number.equals("")))
		{
			return Integer.parseInt(number);
		}
		
		while(m_next < input.length() && input.charAt(m_next) == '(')
		{
			m_next++;
			return expr(input);
		}
		
		System.out.println("ERR");
		return 0;
		
	}
	
}