/************************************************************************************
 *
 *  		CSC220 Programming Project#2
 *  
 * Specification: 
 *
 * Taken from Project 7, Chapter 5, Page 178
 * I have modified specification and requirements of this project
 *
 * Ref: http://www.gigamonkeys.com/book/        (see chap. 10)
 *
 * In the language Lisp, each of the four basic arithmetic operators appears 
 * before an arbitrary number of operands, which are separated by spaces. 
 * The resulting expression is enclosed in parentheses. The operators behave 
 * as follows:
 *
 * (+ a b c ...) returns the sum of all the operands, and (+ a) returns a.
 *
 * (- a b c ...) returns a - b - c - ..., and (- a) returns -a. 
 *
 * (* a b c ...) returns the product of all the operands, and (* a) returns a.
 *
 * (/ a b c ...) returns a / b / c / ..., and (/ a) returns 1/a. 
 *
 * Note: + * - / must have at least one operand
 *
 * You can form larger arithmetic expressions by combining these basic 
 * expressions using a fully parenthesized prefix notation. 
 * For example, the following is a valid Lisp expression:
 *
 * 	(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ 1))
 *
 * This expression is evaluated successively as follows:
 *
 *	(+ (- 6) (* 2 3 4) (/ 3 1 -2) (+ 1))
 *	(+ -6 24 -1.5 1.0)
 *	17.5
 *
 * Requirements:
 *
 * - Design and implement an algorithm that uses SimpleLinkedStack class to evaluate a 
 *   valid Lisp expression composed of the four basic operators and integer values. 
 * - Valid tokens in an expression are '(',')','+','-','*','/',and positive integers (>=0)
 * - Display result as floting point number with at 2 decimal places
 * - Negative number is not a valid "input" operand, e.g. (+ -2 3) 
 *   However, you may create a negative number using parentheses, e.g. (+ (-2)3)
 * - There may be any number of blank spaces, >= 0, in between tokens
 *   Thus, the following expressions are valid:
 *   	(+   (-6)3)
 *   	(/(+20 30))
 *
 * - Must use StackInterface and SimpleLinkedStack in this project. 
     (*** DO NOT USE Java API Stack class ***)
 * - Must throw LispExpressionException to indicate errors
 * - Must not add new or modify existing data fields
 * - Must implement methods in SimpleLinkedStack class. 
 * - Must implement these methods in LispExpressionEvaluator class: 
 *
 *   	public LispExpressionEvaluator()
 *   	public LispExpressionEvaluator(String inputExpression) 
 *      public void reset(String inputExpression) 
 *      public double evaluate()
 *      private void solveCurrentParenthesisOperation()
 *
 * - You may add new private methods
 *
 *************************************************************************************/

package PJ2;
import java.util.*;

public class LispExpressionEvaluator
{
    // Current input Lisp expression
    private String currentExpression;

    // Main expression stack, see algorithm in evaluate()
    private StackInterface<Object> allTokensStack;
    private StackInterface<Double> currentOperandsStack;

    // default constructor
    // set currentExpression to "" 
    // create stack objects
    public LispExpressionEvaluator()
    {
	// add statements
         currentExpression = "";
        allTokensStack = new SimpleLinkedStack<Object>();
        currentOperandsStack = new SimpleLinkedStack<Double>();
    }

    // constructor with an input expression 
    // set currentExpression to inputExpression 
    // create stack objects
    public LispExpressionEvaluator(String inputExpression) 
    {
	// add statements
                currentExpression = inputExpression;
            allTokensStack = new SimpleLinkedStack<Object>();
    	currentOperandsStack = new SimpleLinkedStack<Double>();
    }

    // set currentExpression to inputExpression 
    
    // clear stack objects
    public void reset(String inputExpression) 
    {
	// add statements
         if(inputExpression == null)
         {
             throw new LispExpressionException();
         }
                 currentExpression = inputExpression;
                 allTokensStack.clear();
                 currentOperandsStack.clear();
    }

    // This function evaluates current operator with its operands
    // See complete algorithm in evaluate()
    //
    // Main Steps:
    // 		Pop operands from allTokensStack and push them onto 
    // 			currentOperandsStack until you find an operator
    //  	Apply the operator to the operands on currentOperandsStack
    //          Push the result into allTokensStack
    //
    private void solveCurrentParenthesisOperation()
    {
        // add statements
        double result=0;
        if(allTokensStack.empty() ){
          throw new LispExpressionException("Empty Stack");
        }
       Object operands = allTokensStack.pop();
      
        while ( operands instanceof String ) {
          double value = Double.parseDouble(String.valueOf(operands));
        
          currentOperandsStack.push(value);

          if(allTokensStack.empty() ){
            throw new LispExpressionException("LispExpressionException");
          }
          else{
              operands = allTokensStack.pop();
          }
        }
      
        try{
           String allTockensStack = operands.toString() ;
           char item = allTockensStack.charAt(0);
            switch (item) {                              

                case '/':
                  if(currentOperandsStack==null || currentOperandsStack.empty()){
                      throw new LispExpressionException("LispExpressionException: /");
                  }
                  result = currentOperandsStack.pop();

                  if (currentOperandsStack.empty()) {
                    result = 1/result;
                    allTokensStack.push(String.valueOf(result));
                  }
                  else {
                    while(!currentOperandsStack.empty()) {
                      result /= currentOperandsStack.pop();
                    }
                      allTokensStack.push(String.valueOf(result));
                  }
                  break;
                  
                case '*':  
                    if(currentOperandsStack==null || currentOperandsStack.empty()){
                        throw new LispExpressionException("LispExpressionException: *");
                    }
                    result = 1;
                    while ( !currentOperandsStack.empty() ) {
                      result *= currentOperandsStack.pop();
                    }
                    allTokensStack.push(String.valueOf(result));

                    break;
                    
                case '-':
                    if(currentOperandsStack==null || currentOperandsStack.empty()){
                        throw new LispExpressionException("LispExpressionException: -");
                    }
                result = currentOperandsStack.pop();
                if (currentOperandsStack.empty()) {
                  result = -result;
                  allTokensStack.push(String.valueOf(result));
                }
                else {
                  while(!currentOperandsStack.empty()) {
                    result -= currentOperandsStack.pop();
                  }
                    allTokensStack.push(String.valueOf(result));
                }
                break;
                
              case '+':
                  if(currentOperandsStack==null || currentOperandsStack.empty()){
                      throw new LispExpressionException("LispExpressionException: +");
                  }
                  while (!currentOperandsStack.empty() ) {
                    result += currentOperandsStack.pop();
                  }
                  allTokensStack.push(String.valueOf(result));
                  break;

                case '(':
                default:
                  throw new LispExpressionException(item + " is not a valid operator");

            }
        }
        catch ( LispExpressionException e){
            throw new LispExpressionException( e.getMessage());
          }
    }

    /**
     * This funtion evaluates current Lisp expression in currentExpression
     * It return result of the expression 
     *
     * The algorithm:  
     *
     * Step 1   Scan the tokens in the string.
     * Step 2		If you see an operand, push operand object onto the allTokensStack
     * Step 3  	    	If you see "(", next token should be an operator
     * Step 4  		If you see an operator, push operator object onto the allTokensStack
     * Step 5		If you see ")"  // steps in solveCurrentParenthesisOperation() :
     * Step 6			Pop operands and push them onto currentOperandsStack 
     * 					until you find an operator
     * Step 7			Apply the operator to the operands on currentOperandsStack
     * Step 8			Push the result into allTokensStack
     * Step 9    If you run out of tokens, the value on the top of allTokensStack is
     *           is the result of the expression.
     */
    public double evaluate()
    {
       // only outline is given...
        // you need to add statements/local variables
        // you may delete or modify any statements in this method

        // use scanner to tokenize currentExpression
        Scanner currentExpressionScanner = new Scanner(currentExpression);
        
        // Use zero or more white space as delimiter,
        // which breaks the string into single character tokens
        currentExpressionScanner = currentExpressionScanner.useDelimiter("\\s*");

        // Step 1: Scan the tokens in the string.
        while (currentExpressionScanner.hasNext())
        {
		
     	    // Step 2: If you see an operand, push operand object onto the allTokensStack
            if (currentExpressionScanner.hasNextInt())
            {
                // This force scanner to grab all of the digits
                // Otherwise, it will just get one char
                String dataString = currentExpressionScanner.findInLine("\\d+");
                allTokensStack.push(dataString);
            

   		// more ...
            }
            else{
            try{
                // Get next token, only one char in string token
                String aToken = currentExpressionScanner.next();
                //System.out.println("Other: " + aToken);
                char item = aToken.charAt(0);
                
                switch (item)
                {
                case '(':
                    aToken = currentExpressionScanner.next();
                    item = aToken.charAt(0);
                    switch (item){
                    
                    case '-':
                        allTokensStack.push(item);
                        break;
                    case '+':
                        allTokensStack.push(item);
                        break;
                        
                    case '/':
                          allTokensStack.push(item);
                          break;
                          
                    case '*':
                        allTokensStack.push(item);
                        break;
                        
                    default:
                      throw new LispExpressionException(item + " is not a legal expression operator");
                  }
                  break;
                    
                
        

                    	// Step 5: If you see ")" do steps 6,7,8 in
                    	// evaluateCurrentOperation() :

                  
                    case ')':
                    	
                    	solveCurrentParenthesisOperation();
                    	
                      break;
     		    // Step 3: If you see "(", next token shoube an operator
     		    // Step 4: If you see an operator, push operator object onto the allTokensStack
     		    // Step 5: If you see ")"  // steps in solveCurrentParenthesisOperation() :
                    default:  // error
                        throw new LispExpressionException(item + " is not a legal expression operator");
                }
                }// end switch
                catch ( LispExpressionException e){
                    throw new LispExpressionException( e.getMessage());    
            } // end else
        } // end while
        }
            double result= Double.parseDouble(String.valueOf(allTokensStack.pop()));
            if (!allTokensStack.empty()){
               throw new LispExpressionException ("This stack still has values, but there is no operand");  
            }
        
        // Step 9: If you run out of tokens, the value on the top of allTokensStack is
        //         is the result of the expression.
        //
        //         return result
       
	return result; // return the correct result
    }
    
    

    //=====================================================================
    // DO NOT MODIFY ANY STATEMENTS BELOW
    //=====================================================================

    // This static method is used by main() only
    private static void evaluateExprTest(String s, LispExpressionEvaluator expr, String expect)
    {
        Double result;
        System.out.println("Expression " + s);
        System.out.printf("Expected result : %s\n", expect);
	expr.reset(s);
        try {
           result = expr.evaluate();
           System.out.printf("Evaluated result : %.2f\n", result);
        }
        catch (LispExpressionException e) {
            System.out.println("Evaluated result :"+e);
        }
        
        System.out.println("-----------------------------");
    }

    // define few test cases, exception may happen
    public static void main (String args[])
    {
        LispExpressionEvaluator expr= new LispExpressionEvaluator();
        //expr.setDebug();
        String test1 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ 1))";
        String test2 = "(+ (- 632) (* 21 3 4) (/ (+ 32) (* 1) (- 21 3 1)) (+ 0))";
        String test3 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 1) (- 2 1 ))(* 1))";
        String test4 = "(+ (/2)(+ 1))";
        String test5 = "(+ (/2 3 0))";
        String test6 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))";
        String test7 = "(+ (*))";
        String test8 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+ ))";
	evaluateExprTest(test1, expr, "17.50");
	evaluateExprTest(test2, expr, "-378.12");
	evaluateExprTest(test3, expr, "4.50");
	evaluateExprTest(test4, expr, "1.5");
	evaluateExprTest(test5, expr, "Infinity or LispExpressionException");
	evaluateExprTest(test6, expr, "LispExpressionException");
	evaluateExprTest(test7, expr, "LispExpressionException");
	evaluateExprTest(test8, expr, "LispExpressionException");
    }
}
