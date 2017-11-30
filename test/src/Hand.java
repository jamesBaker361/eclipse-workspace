
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   public boolean isBaby=false; //if this hand is a Baby Straight
   public boolean isBroadway=false; //if this hand is a Broadway Striaght
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   public Hand(Hand og) {
	   hand=new Card[5];
	   count=0;
	   for(int k=0;k<og.getCardCount();k++) {
		   addCard(new Card(og.getCard(k).getValue(),og.getCard(k).getSuit()));
	   }
   }
   
   public Hand(Hand og, boolean noJokers) {
	   hand=new Card[5];
	   count=0;
	   if(noJokers) {
		   for(int k=0;k<og.getCardCount();k++) {
			   if(og.getCard(k).getSuit()!=Card.JOKER) {
				   addCard(new Card(og.getCard(k).getValue(),og.getCard(k).getSuit()));
			   }
		   }
	   }else {
	   for(int k=0;k<og.getCardCount();k++) {
		   addCard(new Card(og.getCard(k).getValue(),og.getCard(k).getSuit()));
	   }
	   }
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }
	  sortByValue();
	  
   }
   
   public Hand jokerLessClone() {
	   Hand out=new Hand();
	   for(int u=0;u<count;u++) {
		   if(hand[u].getSuit()!=Card.JOKER) {
			   out.addCard(new Card(hand[u].getValue(),hand[u].getSuit()));
		   }
	   }
	   return(out);
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }
   
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand[position] = null;
   }
   
   public void removeCardBetter(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	      hand[position] = null;
	      count--;
	      for(int y=position;y<count;y++) {
	    	  	hand[y]=new Card(hand[y+1].getValue(),hand[y+1].getSuit());
	      }
	      sortByValue();
	   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   
   public static void main(String[] args) {
	   Hand h=new Hand();
	   
	   h.addCard(new Card(3,Card.CLUBS));
	   h.addCard(new Card(3,Card.HEARTS));
	   h.addCard(new Card(3,Card.HEARTS));
	   h.addCard(new Card(10,Card.DIAMONDS));
	   h.addCard(new Card(10,Card.SPADES));
	   
	   Hand l=new Hand();
	   l.addCard(new Card(5,Card.CLUBS));
	   l.addCard(new Card(6,Card.CLUBS));
	   l.addCard(new Card(10,Card.HEARTS));
	   l.addCard(new Card(1,Card.CLUBS));
	   l.addCard(new Card(4,Card.CLUBS));
	   //h.addCard(new Card(11,Card.SPADES));
	   //IO.outputBooleanAnswer(h.hasFourOfAKind());
	   //h.sortByValue();
	   //Card c=new Card(1,1);
	   //Card d=new Card(c.getValue(),c.getSuit());
	   
	   //System.out.println(l.highestDuplicate().toString());
	   //Hand x=h.reduceToRepeat(6);
	   //x.printHand();
	   //IO.outputIntAnswer(h.repeatIndex(4));
	  //h.sortByValue();
	   //IO.outputIntAnswer(h.getScore());
	  // IO.outputIntAnswer(l.getScore());
	   //IO.outputIntAnswer(h.compareTo(l));
	   //h.sortByValue();
	   //h.printHand();
	   //h.removeCardBetter(2);
	   //h.addCard(new Card(3,Card.SPADES));
	   //IO.outputBooleanAnswer(h.hasFullHouse());
	   //System.out.println("suck me dick");
	   IO.outputIntAnswer(h.compareTo(l));
	   System.out.println(l.highestDuplicate());
	   //System.out.println(h.getCard(h.repeatIndex(4)));
	   //IO.outputStringAnswer(h.highestDuplicate().toString());
	   //IO.outputIntAnswer(f.getCardCount());
   }
   
   //returns how many jokers are in the hand
   public int jokerCount() {
	   int jokerCount=0;
	   for(int u=0;u<count;u++) {
		   if(hand[u].getSuit()==Card.JOKER) {
			   jokerCount++;
		   }
	   }
	   return(jokerCount);
   }
   
 //Returns true if this hand has 4 cards that are of the same value
   /*public boolean hasFourOfAKind() {
	   sortByValue();
	   System.out.println(jokerCount());
	   if(count<4) {
		   return(false);
	   }
	   
	   if(jokerCount()==1) {
		   if(hand[0].getValue()==hand[1].getValue()&&hand[1].getValue()==hand[2].getValue()) {
			   return(true);
		   }
		   if(hand[1].getValue()==hand[2].getValue()&&hand[2].getValue()==hand[3].getValue()) {
			   return(true);
		   }
		   if(count==5) {
			   if(hand[2].getValue()==hand[3].getValue()&&hand[3].getValue()==hand[4].getValue()) {
				   return(true);
			   }
		   }
	   }else if(jokerCount()==2) {
		   for(int z=0;z<count-1;z++) {
			   if(hand[z].getValue()==hand[z+1].getValue()&&hand[z].getSuit()!=Card.JOKER&&hand[z+1].getSuit()!=Card.JOKER) {
				   return(true);
			   }
		   }
	   }else if(jokerCount()==0) {
		   if(hand[0].getValue()==hand[1].getValue()&&hand[1].getValue()==hand[2].getValue()&&hand[2].getValue()==hand[3].getValue()) {
			   return(true);
		   }
		   if(count==5) {
			   if(hand[1].getValue()==hand[2].getValue()&&hand[2].getValue()==hand[3].getValue()&&hand[4].getValue()==hand[3].getValue()) {
				   return(true);
			   }
		   }
	   }
	   return(false);
   }*/
   
 //Returns true if this hand has 3 cards that are of the same value
  /* public boolean hasTriplet() {
	   if(count<3) {
		   return(false);
	   }
	   Hand noJoke=jokerLessClone();
	   if(jokerCount()==0) {
		   if(hand[0].getValue()==hand[1].getValue()&&hand[1].getValue()==hand[2].getValue()) {
			   return(true);
		   }else if(count>=4&&hand[1].getValue()==hand[2].getValue()&&hand[2].getValue()==hand[3].getValue()) {
			   return(true);
		   }else if(count==5&&hand[2].getValue()==hand[3].getValue()&&hand[3].getValue()==hand[4].getValue()) {
			   return(true);
		   }
	   }else if(jokerCount()==1) {
		   if(hand[0].getValue()==hand[1].getValue()&&hand[1].getValue()==hand[2].getValue()) {
			   
		   }else if(noJoke.getCardCount()==4&&hand[1].getValue()==hand[2].getValue()&&hand[2].getValue()==hand[3].getValue()) {
			   return(true);
		   }
		   
	   }else if(jokerCount()==2) {
		   
	   }
   }
   */
   
   //this takes a hand and sees if this hand has any reps-of-a-kind, but excludes jokers
   /*
    * it tells you if there are @reps or more things with the same value
    */
   public boolean hasRepeats(int reps) {
	   //sortByValue();
	   Hand noJoke=jokerLessClone();
	   if(noJoke.getCardCount()<reps) {
		   //System.out.println("noJoke.getCardCount()<reps");
		   return(false);
	   }
	   //noJoke.sortByValue();
	   //noJoke.printHand();
	   for(int r=0;r<1+noJoke.getCardCount()-reps;r++) {
		   //System.out.println("r is "+r);
		   boolean repeat=true;
		   for(int s=0;s<reps-1;s++) {
			   //System.out.println("s is "+s);
			   if(noJoke.getCard(s+r).getValue()==noJoke.getCard(r+s+1).getValue()) {
				   
			   }else {
				   //System.out.println(noJoke.getCard(s).toString()+"!="+noJoke.getCard(s+1).toString());
				   repeat=false;
				   break;
			   }
		   }
		   if(repeat) {
			   return(true);
		   }
	   }
	   return(false);
   }
   
   public boolean hasFiveOfAKind() {
	   return(hasRepeats(5));
   }
   
   //returns the index of the first sequence that is reps long, or -1 if there isnt one
   //assumes the deck has been ordered already
   //treats Jokers like any other card
   public int repeatIndex(int reps) {
	   //sortByValue();
	   if(!hasRepeats(reps)) {
		   return(-1);
	   }
	   if(getCardCount()<reps) {
		   //System.out.println("noJoke.getCardCount()<reps");
		   return(-1);
	   }
	   //noJoke.sortByValue();
	   //noJoke.printHand();
	   for(int r=0;r<1+getCardCount()-reps;r++) {
		   //System.out.println("r is "+r);
		   boolean repeat=true;
		   for(int s=0;s<reps-1;s++) {
			   //System.out.println("s is "+s);
			   if(getCard(s+r).getValue()==getCard(r+s+1).getValue()) {
				   
			   }else {
				   //System.out.println(getCard(s).toString()+"!="+getCard(s+1).toString());
				   repeat=false;
				   break;
			   }
		   }
		   if(repeat) {
			   return(r);
		   }
	   }
	   return(-1);
   }
   /*
    * this produces a new Hand that only consists of the pair, 
    * or four of a kind or triplet that we gotta worry about
    * reps is the length of the repeating sequence
    * and the count of the outputted hand
    * so if reps=3, and hasTriplet==true
    * well get a Hand with the three cards that are the same
    * 
    * for a fullHouse, reduceToRepeat(2) returns a pair of whichever 
    * value is lowest (so if your hand is 3 queens and 2 kings 
    * reduceToRepeat(2) returns a pair of queens
    */
   public Hand reduceToRepeat(int reps) {
	   Hand out=new Hand();
	   if(repeatIndex(reps)==-1) {
		   //this would be an error
		   System.out.println("there are not "+reps+" cards of the same value in this hand");
		   return(this);
	   }
	   for(int y=repeatIndex(reps);y<repeatIndex(reps)+reps;y++) {
		   out.addCard(getCard(y));
	   }
	   //out.sortByValue();
	   return(out);
   }
   
   public boolean hasTriplet() {
	   if(hasFourOfAKind()) {
		   return(true);
	   }
	   if(hasRepeats(3)) {
		   return(true);
	   }else if(hasRepeats(2)&&jokerCount()>=1) {
		   return(true);
	   }else if(jokerCount()==2) {
		   return(true);
	   }else {
		   return(false);
	   }
   }
   
   public boolean hasFourOfAKind() {
	   if(hasRepeats(4)) {
		   return(true);
	   }else if(hasRepeats(3)&&jokerCount()>=1) {
		   return(true);
	   }else if(hasRepeats(2)&&jokerCount()>=2) {
		   return(true);
	   }else if(jokerCount()>=3){
		   return(true);
	   }else {
	   }
		   return(false);
	   }
   
   
 //Returns the number of pairs this hand contains
   /*
    * if there is a 3 of a kinf or four of a kind, that will not be counted
    * a pair only is counted if there are exactly two of that value of card
    * 
    */
   public int numPairsUseful() {
	   int pairs=0;
	   if(count<2) {
		   return(0);
	   }else if(hasFiveOfAKind()){
		   return(10);
	   }else if(hasFourOfAKind()) {
		   return(6);
	   }else if(hasTriplet()) {
		   pairs=3;
		   if(count==3||count==4) {
			   return(pairs);
		   }
		   Hand temp=new Hand(this);
		   int tripIndex=repeatIndex(3);
		   for(int s=0;s<3;s++) {
			   temp.removeCardBetter(tripIndex);
			   /*
			    * 
			    * so if there is a joker and a pair, hasTriplet return true
			    * but repeatIndex() returns -1 b/c ttheres no actual series of 3 things yerd
			    * so we gotta figure out which pair becomes the triple
			    */
		   }
		   if(temp.getCardCount()==2&&temp.getCard(0).getValue()==temp.getCard(1).getValue()) {
			   pairs++;
		   }
		   return(pairs);
	   }
	   if(jokerCount()==2) {
		   return(2);
	   }
	   if(jokerCount()==1) {
		   pairs++;
	   }
	   //sortByValue();
	   //Hand temp=jokerLessClone();
	   for(int y=0;y<getCardCount()-1;y++) {
		   if(getCard(y).getValue()==getCard(y+1).getValue()) {
			   pairs++;
			   y++;
		   }
	   }
	   return(pairs);
   }
   
   public int numPairs() {
	   if(count<5) {
		   IO.reportBadInput();
		   return(0);
	   }
	   int pairs=0;
	   if(count<2) {
		   return(0);
	   }else if(hasFiveOfAKind()){
		   return(2);
	   }else if(hasFourOfAKind()) {
		   return(2);
	   }else if(hasFullHouse()) {
		   return(2);
	   }else if(hasTriplet()) {
		   pairs=1;
	   }
	   //sortByValue();
	   //Hand temp=jokerLessClone();
	   for(int y=0;y<getCardCount()-1;y++) {
		   if(getCard(y).getValue()==getCard(y+1).getValue()) {
			   pairs++;
			   y++;
		   }
	   }
	   return(pairs);
   }
   
   public boolean hasFullHouse() {
	   if(count<5) {
		   IO.reportBadInput();
		   return(false);
	   }
	   return((!hasFourOfAKind())&&(hasTriplet())&&(numPairsUseful()>3));
   }
   
   public boolean hasFlush() {
	   if(count<5) {
		   IO.reportBadInput();
		   return(false);
	   }
	   for(int u=0;u<count-1;u++) {
		   if(hand[u].getSuit()!=hand[u+1].getSuit()) {
			   return(false);
		   }
	   }
	   return(true);
   }
   
   public boolean hasStraight() {
	   //sortByValue();
	   if(count<5) {
		   IO.reportBadInput();
		   return(false);
	   }
	   for(int u=0;u<count-1;u++) {
		   //
		   //System.out.println(u);
		   if(hand[u].getValue()!=hand[u+1].getValue()-1){
			   if(u!=0) {
				   return(false);
			   }else {
				   if(hand[u].getValue()!=Card.ACE||hand[4].getValue()!=Card.KING) {
					   return(false);
				   }
			   }
		   }
	   }
	   if(getCard(1).getValue()==10) {
		   //if its a straight and the second card is a 10, then it  ust be a broadway flush
		   isBroadway=true;
	   }else if(getCard(1).getValue()==2) {
		   //its a bby straight, the second card would be a 2
		   isBaby=true;
	   }
	   return(true);
   }
   
   
 
   
 //Returns the highest valued Card of any pair or triplet found, -1 if // none. When values are equal, you may return either
   public Card highestDuplicate() {
	   //sortByValue();
	   //return(getCard(0));
	   if(hasFiveOfAKind()){
		   
		   //return(hand[0]);
		   int maxSuit=getCard(0).getSuit();
		   int indexMS=0;
		   for(int k=1;k<5;k++) {
			   if(getCard(k).getSuit()<maxSuit) {
				   //its "less than" on purpose. For some reason Card.SADES=0, and Card.CLUBS=3 
				   maxSuit=getCard(k).getSuit();
				   indexMS=k;
			   }
		   }
		   //System.out.println(getCard(indexMS).toString());
		   return(getCard(indexMS));
	   }else if(hasFourOfAKind()) {
		   //System.out.println(repeatIndex(4));
		   int maxSuit=getCard(repeatIndex(4)).getSuit();
		   int indexMS=repeatIndex(4);
		   for(int k=repeatIndex(4)+1;k<repeatIndex(4)+3;k++) {
			   if(hand[k].getSuit()<maxSuit) {
				   //its "less than" on purpose. For some reason Card.SADES=0, and Card.CLUBS=3 
				   maxSuit=hand[k].getSuit();
				   indexMS=k;
			   }
		   }
		   return(getCard(indexMS));
		   
	   }else if(hasFullHouse()) {
		  int pairValue=0;
		  int pairIndex=0;
		  int tripleValue=getCard(repeatIndex(3)).getValue();
		  //its only possible for repeatIndex(3) to be 0 or 2 b/c the array is sorted
		  if(repeatIndex(3)==0) {
			  pairIndex=3;
		  }else if(repeatIndex(3)==3) {
			  pairIndex=0;
		  }
		  pairValue=getCard(pairIndex).getValue();
		  if(pairValue==1) {
			  return(getCard(pairIndex));
		  }else if(tripleValue>=pairValue||tripleValue==1) {
			  //System.out.println("i just might make 100k");
			  //is triple value is >= pairValue or triplevalue is an ace
			  return(getCard(repeatIndex(3)));
		  }else if(pairValue>tripleValue||pairValue==1) {
			  //System.out.println("i just might make 100k line 564");
			  return(getCard(pairIndex));
		  }
	   }else if(hasTriplet()) {
		   int maxSuit=getCard(repeatIndex(3)).getSuit();
		   int indexMS=repeatIndex(3);
		   for(int k=repeatIndex(3)+1;k<repeatIndex(4)+2;k++) {
			   if(getCard(k).getSuit()<maxSuit) {
				   //its "less than" on purpose. For some reason Card.SADES=0, and Card.CLUBS=3 
				   maxSuit=hand[k].getSuit();
				   indexMS=k;
			   }
		   }
		   return(getCard(indexMS));
	   }else if(numPairsUseful()==2){
		   //return(getCard(0));
		   //Hand temp=new Hand(this);
		   Card[] pairOne=new Card[2];
		   boolean pOneFull=false;
		   Card[] pairTwo=new Card[2];
		   for(int y=0;y<getCardCount()-1;y++) {
			   if(getCard(y).getValue()==getCard(y+1).getValue()) {
				   if(!pOneFull) {
					   pOneFull=true;
					   pairOne[0]=getCard(y);
					   pairOne[1]=getCard(y+1);
					   pOneFull=true;
					   //y++;
				   }else {
					   pairTwo[0]=getCard(y);
					   pairTwo[1]=getCard(y+1);
					   break;
				   }
			   }
		   }
		   /*
		   System.out.println(pairTwo[0]);
		   System.out.println(pairTwo[1]);
		   System.out.println(pairOne[0]);
		   System.out.println(pairOne[1]);
		   */
		   if(pairTwo[0].getValue()==1) {
			   if(pairTwo[0].getSuit()<pairTwo[1].getSuit()) {
				   return(pairTwo[0]);
			   }else {
				   return(pairTwo[1]);
			   }
		   }else if(pairOne[0].getValue()>pairTwo[0].getValue()||pairOne[0].getValue()==1) {
			   if(pairOne[0].getSuit()<pairOne[1].getSuit()) {
				   return(pairOne[0]);
			   }else {
				   return(pairOne[1]);
			   }
		   }else {
			   if(pairTwo[0].getSuit()<pairTwo[1].getSuit()) {
				   return(pairTwo[0]);
			   }else {
				   return(pairTwo[1]);
			   }
		   }
	   }else if(numPairsUseful()==1) {
		   if(getCard(repeatIndex(2)).getSuit()<getCard(repeatIndex(2)+1).getValue()) {
			   System.out.println(repeatIndex(2));
			   return(getCard(repeatIndex(2)));
		   }else {
			   System.out.println(getCard(repeatIndex(2)+1));
			   return(getCard(repeatIndex(2)+1));
		   }
	   }
	   return(null);
   }
   
 //Returns the card with the highest value in the hand. When there is
   //more than one highest value card, you may return any one of them
   public Card highestValue() {
	   int highIndex=0;
	   for(int y=1;y<getCardCount();y++) {
		   if(getCard(highIndex).getValue()<getCard(y).getValue()&&getCard(highIndex).getValue()!=1) {
			   highIndex=y;
		   }else if(getCard(highIndex).getValue()==getCard(y).getValue()) {
			   if(getCard(highIndex).getSuit()>getCard(y).getSuit()) {
				   highIndex=y;
			   }
		   }else if(getCard(highIndex).getValue()!=1&&getCard(y).getValue()==1){
			   highIndex=y;
		   }
	   }
	   //System.o
	   System.out.println(getCard(highIndex));
	   return(getCard(highIndex));
   }
   
   /*
    * returns a score, the higher the score, the more powerful the hand
    * so a 0 is like having nothing
    * and like a big ass number is a royal flush
    */
   public int getScore() {
	   if(count<5) {
		   IO.reportBadInput();
		   return(-1);
	   }
	   if(hasFiveOfAKind()) {
		   return(10);
	   }else if(hasStraight()&&hasFlush()) {
		   return(9);
	   }else if(hasFourOfAKind()) {
		   return(8);
	   }else if(hasFullHouse()) {
		   return(7);
	   }else if(hasFlush()){
		   return(6);
	   }else if(hasStraight()) {
		   return(5);
	   }else if(hasTriplet()) {
		   return(3);
	   }else if(numPairsUseful()==2) {
		   return(2);
	   }else if(numPairsUseful()==1) {
		   return(1);
	   }else {
		   return(0);
	   }
   }
   
   //Returns -1 if the instance hand loses to the parameter hand, 0 if 
   //the hands are equal, and +1 if the instance hand wins over the 
   //parameter hand. Hint: you might want to use some of the methods 
   //above
   public int compareTo(Hand h) {
	   if(getScore()>h.getScore()) {
		   //System.out.println(""+this.toString()+" has a bigger score than "+h.toString());
		   return(1);
	   }else if(getScore()<h.getScore()) {
		   return(-1);
	   }else {
		   //this means they must be equal in score so whomstever wins 
		   //would be like the high card
		   if(hasFiveOfAKind()) {
			   if(highestDuplicate().getValue()==h.highestDuplicate().getValue()) {
				   if(highestValue().getSuit()<h.highestValue().getSuit()) {
					   return(1);
				   }else if(highestValue().getSuit()>h.highestValue().getSuit()) {
					   return(-1);
				   }else if(highestValue().getSuit()==h.highestValue().getSuit()) {
					   return(0);
				   }
			   }else if(highestDuplicate().getValue()==1) {
				   //aces win even tho they're technically lower value
				   return(1);
			   }else if(h.highestDuplicate().getValue()==1) {
				   return(-1);
			   }else if(highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   return(-1);
			   }else if(highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   return(1);
			   }
		   }else if(hasFourOfAKind()){
			   Hand thisHand=reduceToRepeat(4);
			   Hand otherHand=h.reduceToRepeat(4);
			   if(thisHand.highestDuplicate().getValue()==otherHand.highestDuplicate().getValue()) {
				   if(thisHand.highestValue().getSuit()<otherHand.highestValue().getSuit()) {
					   return(1);
				   }else if(thisHand.highestValue().getSuit()>otherHand.highestValue().getSuit()) {
					   return(-1);
				   }else if(thisHand.highestValue().getSuit()==otherHand.highestValue().getSuit()) {
					   return(0);
				   }
			   }else if(thisHand.highestDuplicate().getValue()==1) {
				   //aces win even tho they're technically lower value
				   return(1);
			   }else if(otherHand.highestDuplicate().getValue()==1) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()<otherHand.highestDuplicate().getValue()) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()>otherHand.highestDuplicate().getValue()) {
				   return(1);
			   }
		   }else if(hasFullHouse()) {
			   //System.out.println("ful hous");
			   Hand thisPair=new Hand();
			   Hand thisTriple=new Hand();
			   Hand otherPair=new Hand();
			   Hand otherTriple=new Hand();
			   for(int u=0;u<5;u++) {
				   if(getCard(u).getValue()==getCard(repeatIndex(3)).getValue()) {
					   thisTriple.addCard(getCard(u));
				   }else {
					   thisPair.addCard(getCard(u));
				   }
				   if(h.getCard(u).getValue()==h.getCard(h.repeatIndex(3)).getValue()) {
					   otherTriple.addCard(h.getCard(u));
				   }else {
					   otherPair.addCard(h.getCard(u));
				   }
			   }
			   if(thisTriple.highestValue().getValue()==otherTriple.highestValue().getValue()) {
				   System.out.println("equality");
				   if(thisPair.highestValue().getValue()==otherPair.highestValue().getValue()) {
					   return(0);
				   }else if(thisPair.highestValue().getValue()==1) {
					   return(1);
				   }else if(otherPair.highestValue().getValue()==1) {
					   return(-1);
				   }else if(thisPair.highestValue().getValue()>otherPair.highestValue().getValue()) {
					   return(1);
				   }else if(thisPair.highestValue().getValue()<otherPair.highestValue().getValue()) {
					   return(-1);
				   }
			   }else if(thisTriple.highestValue().getValue()==1) {
				   return(1);
			   }else if(otherTriple.highestValue().getValue()==1) {
				   return(-1);
			   }else if(thisTriple.highestValue().getValue()>thisTriple.highestValue().getValue()) {
				   return(1);
			   }else if(thisTriple.highestValue().getValue()<thisTriple.highestValue().getValue()) {
				   return(-1);
			   }
		   }else if(hasTriplet()){
			   Hand thisHand=reduceToRepeat(3);
			   Hand otherHand=h.reduceToRepeat(3);
			   if(thisHand.highestDuplicate().getValue()==otherHand.highestDuplicate().getValue()) {
				   if(thisHand.highestValue().getSuit()<otherHand.highestValue().getSuit()) {
					   return(1);
				   }else if(thisHand.highestValue().getSuit()>otherHand.highestValue().getSuit()) {
					   return(-1);
				   }else if(thisHand.highestValue().getSuit()==otherHand.highestValue().getSuit()) {
					   return(0);
				   }
			   }else if(thisHand.highestDuplicate().getValue()==1) {
				   //aces win even tho they're technically lower value
				   return(1);
			   }else if(otherHand.highestDuplicate().getValue()==1) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()<otherHand.highestDuplicate().getValue()) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()>otherHand.highestDuplicate().getValue()) {
				   return(1);
			   }
		   }else if(numPairsUseful()==2) {
			   //System.out.println("numPairsUseful = 2");
			   if(highestDuplicate().getValue()==h.highestDuplicate().getValue()) {
				   if(highestValue().getSuit()<h.highestValue().getSuit()) {
					   return(1);
				   }else if(highestValue().getSuit()>h.highestValue().getSuit()) {
					   return(-1);
				   }else if(highestValue().getSuit()==h.highestValue().getSuit()) {
					   return(0);
				   }
			   }else if(highestDuplicate().getValue()==1) {
				   //aces win even tho they're technically lower value
				   return(1);
			   }else if(h.highestDuplicate().getValue()==1) {
				   return(-1);
			   }else if(highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   return(-1);
			   }else if(highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   return(1);
			   }
		   }else if(numPairsUseful()==1) {
			   Hand thisHand=reduceToRepeat(2);
			   Hand otherHand=h.reduceToRepeat(2);
			   if(thisHand.highestDuplicate().getValue()==otherHand.highestDuplicate().getValue()) {
				   if(thisHand.highestValue().getSuit()<otherHand.highestValue().getSuit()) {
					   return(1);
				   }else if(thisHand.highestValue().getSuit()>otherHand.highestValue().getSuit()) {
					   return(-1);
				   }else if(thisHand.highestValue().getSuit()==otherHand.highestValue().getSuit()) {
					   return(0);
				   }
			   }else if(thisHand.highestDuplicate().getValue()==1) {
				   //aces win even tho they're technically lower value
				   return(1);
			   }else if(otherHand.highestDuplicate().getValue()==1) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()<otherHand.highestDuplicate().getValue()) {
				   return(-1);
			   }else if(thisHand.highestDuplicate().getValue()>otherHand.highestDuplicate().getValue()) {
				   return(1);
			   }
		   }else if(hasStraight()) {
			   if(highestValue().getValue()==h.highestValue().getValue()) {
				   if(h.isBaby&&isBroadway) {
					   return(1);
				   }else if(isBaby&&h.isBroadway) {
					   return(-1);
				   }else {
					   return(0);
				   }
			   }else if(highestValue().getValue()==1) {
				   return(1);
			   }else if(h.highestValue().getValue()==1) {
				   return(-1);
			   }else if(highestValue().getValue()>h.highestValue().getValue()) {
				   return(1);
			   }else if(highestValue().getValue()<h.highestValue().getValue()) {
				   return(-1);
			   }
		   }else if(hasFlush()) {
			   if(highestValue().getValue()==h.highestValue().getValue()) {
				   return(0);
			   }else if(highestValue().getValue()==1) {
				   return(1);
			   }else if(h.highestValue().getValue()==1) {
				   return(-1);
			   }else if(highestValue().getValue()>h.highestValue().getValue()) {
				   return(1);
			   }else if(highestValue().getValue()<h.highestValue().getValue()) {
				   return(-1);
			   }
		   }
		   return(69);
	   }
   }

   /******************************** Implement your methods here ****************************************
 //Returns the number of pairs this hand contains
   public int numPairs() {
	   DONE
   }
   //Returns true if this hand has 3 cards that are of the same value
   public boolean hasTriplet() {
	   DONE
   }
    
   //Returns true if this hand has all cards that are of the same suit
   public boolean hasFlush() {
	   DONE
   }
   //Returns true if this hand has 5 consecutive cards of any suit
   public boolean hasStraight() {
	   DONE
   }
    
   //Returns true if this hand has a triplet and a pair of different //values
   public boolean hasFullHouse() {
	   DONE
   }
    
   //Returns true if this hand has 4 cards that are of the same value
   public boolean hasFourOfAKind() {
	   DONE
   }
    
   //Returns the card with the highest value in the hand. When there is
   //more than one highest value card, you may return any one of them
   public Card highestValue() {
	   DONE
   }
    
   //Returns the highest valued Card of any pair or triplet found, -1 if // none. When values are equal, you may return either
   public Card highestDuplicate() {
	   DONE
   }
    
   //Returns -1 if the instance hand loses to the parameter hand, 0 if //the hands are equal, and +1 if the instance hand wins over the //parameter hand. Hint: you might want to use some of the methods //above
   public int compareTo(Hand h) {
	   DONE
   }*/

}