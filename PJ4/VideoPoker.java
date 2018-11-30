package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;
    private int Value;
    private boolean playing = true;
    private static List<Integer> discardArray;

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
        // implement this method!
    Value=9;
        
               
        if (isRoyalFlush()) Value=8;
        
        else if (isStraightFlush()) Value=7;        
        
        else if (isFourOfAKind()) Value=6;
        
        else if (isFullHouse()) Value=5;
        
        else if (isFlush()) Value=4;
        
        else if (isStraight()) Value=3;    	
        
        else if (isThreeOfAKind()) Value=2;        
                
        else if (isTwoPair()) Value=1;
    	
        else if (isRoyalPair()) Value=0;  
    	
        
    	if (Value < 9)
    	 System.out.println(goodHandTypes[Value] + "!");
        else
         System.out.println("Sorry, you lost !");   
  	
    

    }

    /*************************************************
     *   add additional private methods here ....
     *
     *************************************************/
public static void SortRanks(int[] array) {
    
    
    for (int j = 0; j < array.length ; j++) {
        int a = array[j];
        int i = j - 1;
        while( i >=0 && array[i] > a){
            array[i+1] =  array[i];
            i = i - 1;            
        }
        array[i+1] = a;
    }
    }
        private boolean isRoyalPair(){
		
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        boolean isPair = false;
        
        for (int i=0; i<5; i++){ 
            if (tempRank[i] ==1 ){
             
            for (int j=0; j<5; j++){
                
             if (j != i) {
                if (tempRank[j] == 1  )
                    
                    isPair = true;
             }
            }
           }
                 
        }
        
        for (int i=0; i<5; i++){ 
            if (tempRank[i] ==11 ){
             
            for (int j=0; j<5; j++){
                
             if (j != i) {
                if (tempRank[j] == 11 )
                    
                    isPair = true;
             }
            }
           }   
        }
        
        for (int i=0; i<5; i++){ 
            if (tempRank[i] ==12 ){
             
            for (int j=0; j<5; j++){
                
             if (j != i) {
                if (tempRank[j] == 12 )
                    
                    isPair = true;
             }
            }
           }    
        }
        
        for (int i=0; i<5; i++){ 
            if (tempRank[i] ==13){
             
            for (int j=0; j<5; j++){
                
             if (j != i) {
                if (tempRank[j] == 13 )
                    
                    isPair = true;
             }
            }
           }    
        }
        if (isPair)
            return true;
        else
           return false; 
        }
        
    
    private boolean isTwoPair(){

	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        int totalPairs = 0;
        if (tempRank[0] == tempRank[1] &&
            tempRank[0] != tempRank[2] ) 
            totalPairs += 1;

        if (tempRank[1] == tempRank[2] &&
            tempRank[1] != tempRank[3] ) 
            totalPairs += 1;
                
        if (tempRank[2] == tempRank[3] &&
            tempRank[2] != tempRank[4] ) 
            totalPairs += 1;
        
        if (tempRank[3] == tempRank[4] )
            
            totalPairs += 1;
        
        if (totalPairs == 2)
            return true;
        else
            return false; 
	   
	}
    
    private boolean isThreeOfAKind(){
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        
        if (tempRank[0] == tempRank[1] &&
            tempRank[1] == tempRank[2] && tempRank[3] != tempRank[4])         
        
            return true;
        else
            return false;
	   
	}
    
    private boolean isStraight(){
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        
        
        if (tempRank[1] == (tempRank[0]+1) && 
	    tempRank[2] == (tempRank[1]+1) &&
            tempRank[3] == (tempRank[2]+1) &&
            tempRank[4] == (tempRank[3]+1)) return true;
	    
        else
	 return false;	
        
        
    }
    
    private boolean isFlush(){
		
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
               
        
        for (int i=0; i<4; i++){
            
             if (tempHand[i].getSuit() != tempHand[i+1].getSuit())
                 return false;
             
               
        }
        return true;
        
    }	
    
    private boolean isFullHouse(){
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        
        if (tempRank[0] == tempRank[1] &&
            tempRank[1] == tempRank[2] && tempRank[3] == tempRank[4])         
        
            return true;
        else
            return false;

	}
    
    private boolean isFourOfAKind(){
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        
        if (tempRank[0] == tempRank[1] &&
            tempRank[1] == tempRank[2] &&
            tempRank[2] == tempRank[3] )         
        
            return true;
        else
            return false;
    }
    
    private boolean isStraightFlush(){
		
		
        
        if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank); 
        
        for (int i=0; i<5; i++){
            for (int j=i+1; j<5; j++){
             if (tempHand[i].getSuit() != tempHand[j].getSuit())
                 return false;
             }
               
        }
        
        if (tempRank[1] == (tempRank[0]+1) && 
	    tempRank[2] == (tempRank[1]+1) &&
            tempRank[3] == (tempRank[2]+1) &&
            tempRank[4] == (tempRank[3]+1)) return true;
	    
        else
	 return false;	
                
        
	}
	
    private boolean isRoyalFlush(){
	if ( playerHand.size() != 5 ) return(false);
	Card[] tempHand = new Card[playerHand.size()];
		
	for (int i = 0; i < tempHand.length; i++) {
	    tempHand[i]=(Card)playerHand.get(i);
	} 
        
         int [] tempRank = new int[5];
         for(int i =0; i < 5; i++){
             tempRank[i] = tempHand[i].getRank();
         }
        SortRanks(tempRank);        
        
        for (int i=0; i<5; i++){
            for (int j=i+1; j<5; j++){
             if (tempHand[i].getSuit() != tempHand[j].getSuit())
                 return false;
             }
               
        }
        
        if (tempRank[0] ==1 && tempRank[1]==10 && 
	    tempRank[2]==11 && tempRank[3]==12 && 
	    tempRank[4]==13) return true;
        else
	 return false;	
		
		
    }
           
        List<Card> aHand = new ArrayList<Card>();
	private List<Card> discard(){	
	discardArray  = new ArrayList<Integer>();
	Scanner in = new Scanner(System.in);
	String inStr=null;		    	
	boolean doLoop = true;		    	
	while (doLoop){
	    doLoop = false;
	    System.out.println("Enter positions of cards to keep (e.g. 1 4 5): ");			    	
	    inStr = in.nextLine();			    	
	    if (!inStr.isEmpty()){			    	
	        String splStr[] = inStr.split(" ");
		if (splStr.length>5){ // check if user selected more than 5 cards
		System.out.println("Error, too many cards. Choose up to 5 cards. ");
		doLoop = true;
		}				    	
		for (int i=0; i < splStr.length; i++) { 
		    int k = Integer.parseInt(splStr[i]); 
		    discardArray.add(i, k);
		}
		//Check if each card number is between 1 and 5
		for (int i=0; i < discardArray.size(); i++) { 				    		 
		    if (discardArray.get(i)<1 || discardArray.get(i)>=6){
		        System.out.println("Position number out of range. Choose 1 to 5");
		        doLoop = true;				
		    }
	        }				
	        if(!doLoop){	
	            //remove cards and add them to new array
		    for (int i=0; i<discardArray.size(); i++){
		        int x =discardArray.get(i);
                        aHand.add(playerHand.get(x-1));
	            }			
			System.out.println("Held Cards: "+aHand);
			try { //deal more cards
			    aHand.addAll(oneDeck.deal(5-discardArray.size()));
			} catch (PlayingCardException e) {
			          System.out.println("PlayingCardException e.getMessage()");
			}
	        }
	     discardArray.clear();
	    }
        }
		
	if (inStr.isEmpty()){
	   aHand.clear();
	   System.out.println("No cards in hands.");
	try {
            aHand.addAll(oneDeck.deal(5));
	    } 
	     catch (PlayingCardException e) {
	      System.out.println("PlayingCardException e.getMessage()");
	    }	     	
	}	
     return aHand;
    }  


    public void play() 
    {
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to keep  
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */

        // implement this method!
        showPayoutTable();
    	
    	playerHand = new ArrayList<Card>();
	while (playing) {
	    System.out.println("Balance: $" +getBalance());
	    placeBet(); 
	    oneDeck.shuffle();	    	
	    //Dealing the hand
	    try {
	        playerHand = new ArrayList<Card>(oneDeck.deal(5));
	    } catch (PlayingCardException exc) {
	         System.out.println("PlayingCardException e.getMessage()");
	    }	
	    System.out.println("Hand: "+playerHand);  
            playerHand=discard();    
	    System.out.println("Hand: "+playerHand);
	    checkHands();
            payOut(Value);
	    playerHand.clear();
	    System.out.println("\nYour balance: $"+playerBalance);
	    // play more?
	    if ((playerBalance > 0)&&(PlayAgain())) {;
	        askShowPayoutTable();
	    }	
	    else {
	        System.out.println("Your balance is: $" + getBalance() );
                System.out.println("Bye!");
	        playing=false;
	 	}
	    }
    }
    
    private void payOut(int payOut){
    	if (payOut==9){    	  
    	    playerBalance = playerBalance-playerBet; 
    	}    	
    	else if (payOut!=9){
    	    playerBalance=playerBalance+(playerBet*multipliers[payOut]); 
    	}
    }
    
    private int getBalance(){
    	return this.playerBalance;
    }
    
    private int getBet(){
    	return playerBet;
    }
    
    private void placeBet(){
    	Scanner in = new Scanner(System.in);
    	boolean hasFunds = true;
    	do {
            System.out.print("Enter bet: ");
            playerBet=in.nextInt();
            if (playerBet > playerBalance){
             hasFunds = false;  
    	     System.out.println("Not enough funds. Enter a smaller amount.");
            }
            else
             hasFunds = true;
    	}while(!hasFunds);
    } 
    
    
    public static boolean PlayAgain(){
    	
    	boolean askAgain=true; 
    	boolean playAgain=true;
    	Scanner in = new Scanner(System.in);    	
    	while (askAgain){
        	System.out.println("Play Again? (y or n)");
        	String continuePlaying=in.next();
        	if (continuePlaying.equalsIgnoreCase("y")){
      		  playAgain=true;
      		  askAgain=false;
      		  }      		  
      		else if (continuePlaying.equalsIgnoreCase("n")){ 
      		  playAgain=false;
      		  askAgain=false;
      		}
      		else
      	     System.out.println("Please answer y/n");
    		}
    	 return playAgain;   	 
    	}
    
    // 
     private void askShowPayoutTable()
    { 
    	
    	boolean askAgain=true; 
    	boolean playAgain=true;
    	Scanner in = new Scanner(System.in);
    	
    	while (askAgain){
        	System.out.println("Show payout table? (y or n)");
        	String continuePlaying=in.next();
        	if (continuePlaying.equalsIgnoreCase("y")){
      		  playAgain=true;
      		  askAgain=false;
      		  }      		  
      		else if (continuePlaying.equalsIgnoreCase("n")){ 
      		  playAgain=false;
      		  askAgain=false;
      		}
      		else
      	     System.out.println("Please answer y/n");
    		}
    	 if (playAgain) showPayoutTable();
    
    }



    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(3,1));
		playerHand.add(new Card(3,10));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(3,11));
		playerHand.add(new Card(3,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(3,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(1,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(3,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(3,8));
		playerHand.add(new Card(0,8));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(2,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(3,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(1,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(1,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(1,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(3,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}
