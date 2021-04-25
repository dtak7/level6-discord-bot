package org.jointheleague.modules;

import java.lang.reflect.Array;
import java.util.Random;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import net.aksingh.owmjapis.api.APIException;

public class Poker extends CustomMessageCreateListener {

	public Poker(String channelName) {
		// TODO Auto-generated constructor stub
		super(channelName);
	}

	private static final String command = "!gamble";
	int balance = 50;
	int wager;
	int totalBet;
	Random randCards = new Random();
	String[] cards = new String[52];
	String[] botAndMiddleCards = new String[7];
	String[] playerAndMiddleCards=new String[7];
	int[] usedCards = new int[9];
	int playerCard1;
	int playerCard2;
	int middleCard1;
	int middleCard2;
	int middleCard3;
	int middleCard4;
	int middleCard5;
	int botCard1;
	int botCard2;
	int numberSameCards;
	int playerScore;
	int botScore;
	int numberPairs;
	boolean userCooporates = false;
	boolean isUsed = false;
	boolean flop = true;
	boolean turn = true;
	boolean river=true;
	boolean gameOver = true;
	boolean playerWon;
	boolean rewardGiven;
	boolean tie=false;
	boolean straight;
	int highestCallChance = 0;
	int botCallChance = 0;
	boolean ace;
	boolean two;
	boolean three;
	boolean four;
	boolean five;
	boolean six;
	boolean seven;
	boolean eight;
	boolean nine;
	boolean ten;
	boolean jack;
	boolean queen;
	boolean king;

	@Override
	public void handle(MessageCreateEvent event) throws APIException {
		// TODO Auto-generated method stub
		cards[0] = "ace of spades";
		cards[1] = "2 of spades";
		cards[2] = "3 of spades";
		cards[3] = "4 of spades";
		cards[4] = "5 of spades";
		cards[5] = "6 of spades";
		cards[6] = "7 of spades";
		cards[7] = "8 of spades";
		cards[8] = "9 of spades";
		cards[9] = "10 of spades";
		cards[10] = "jack of spades";
		cards[11] = "queen of spades";
		cards[12] = "king of spades";
		cards[13] = "ace of hearts";
		cards[14] = "2 of hearts";
		cards[15] = "3 of hearts";
		cards[16] = "4 of hearts";
		cards[17] = "5 of hearts";
		cards[18] = "6 of hearts";
		cards[19] = "7 of hearts";
		cards[20] = "8 of hearts";
		cards[21] = "9 of hearts";
		cards[22] = "10 of hearts";
		cards[23] = "jack of hearts";
		cards[24] = "queen of hearts";
		cards[25] = "king of hearts";
		cards[26] = "ace of diamonds";
		cards[27] = "2 of diamonds";
		cards[28] = "3 of diamonds";
		cards[29] = "4 of diamonds";
		cards[30] = "5 of diamonds";
		cards[31] = "6 of diamonds";
		cards[32] = "7 of diamonds";
		cards[33] = "8 of diamonds";
		cards[34] = "9 of diamonds";
		cards[35] = "10 of diamonds";
		cards[36] = "jack of diamonds";
		cards[37] = "queen of diamonds";
		cards[38] = "king of diamonds";
		cards[39] = "ace of clubs";
		cards[40] = "2 of clubs";
		cards[41] = "3 of clubs";
		cards[42] = "4 of clubs";
		cards[43] = "5 of clubs";
		cards[44] = "6 of clubs";
		cards[45] = "7 of clubs";
		cards[46] = "8 of clubs";
		cards[47] = "9 of clubs";
		cards[48] = "10 of clubs";
		cards[49] = "jack of clubs";
		cards[50] = "queen of clubs";
		cards[51] = "king of clubs";
		if (event.getMessageContent().contains(command)) {
			String content = event.getMessageContent().replaceAll(" ", "").replace("!gamble", "");
			if (content.contains("poker")) {
				if (gameOver == true) {
					gameOver = false;
					flop=true;
					turn=true;
					river=true;
					rewardGiven=false;
					totalBet = 0;
					highestCallChance=0;
					botCallChance=0;
					numberPairs=0;
					usedCards[0]=-1;
					usedCards[1]=-1;
					usedCards[2]=-1;
					usedCards[3]=-1;
					usedCards[4]=-1;
					usedCards[5]=-1;
					usedCards[6]=-1;
					usedCards[7]=-1;
					usedCards[8]=-1;
					botAndMiddleCards[0]="";
					botAndMiddleCards[1]="";
					botAndMiddleCards[2]="";
					botAndMiddleCards[3]="";
					botAndMiddleCards[4]="";
					botAndMiddleCards[5]="";
					botAndMiddleCards[6]="";
					playerAndMiddleCards[0]="";
					playerAndMiddleCards[1]="";
					playerAndMiddleCards[2]="";
					playerAndMiddleCards[3]="";
					playerAndMiddleCards[4]="";
					playerAndMiddleCards[5]="";
					playerAndMiddleCards[6]="";
					
					content = content.replace("poker", "");
					try {
						wager = Integer.parseInt(content);
					} catch (Exception e) {
						event.getChannel().sendMessage("Choose a number after the command to gamble");
					}
					if (balance < 0) {
						event.getChannel().sendMessage("You don't have enough money to wager that much.");
					} else if (wager<0) {
						event.getChannel().sendMessage("Choose a positive number");
						gameOver=true;
					}
					else {
						balance -= wager;
						totalBet += wager;
						event.getChannel().sendMessage("Your balance is " + balance);
						// send photos of cards in the middle, its only text currently
						middleCard1 = randCards.nextInt(52);
						usedCards[0] = middleCard1;
						middleCard2 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == middleCard2) {
								isUsed = true;

							}
							while (isUsed == true) {
								middleCard2 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[1] = middleCard2;
						middleCard3 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == middleCard3) {
								isUsed = true;

							}
							while (isUsed == true) {
								middleCard3 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[2] = middleCard3;
						event.getChannel().sendMessage("The middle cards are the " + cards[middleCard1] + ", the "
								+ cards[middleCard2] + " and the " + cards[middleCard3]);

						// send photo of player cards, its only text currently
						playerCard1 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == playerCard1) {
								isUsed = true;

							}
							while (isUsed == true) {
								playerCard1 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[3] = playerCard1;
						playerCard2 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == playerCard2) {
								isUsed = true;

							}
							while (isUsed == true) {
								playerCard2 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[4] = playerCard2;
						event.getChannel().sendMessage(
								"Your cards are the " + cards[playerCard1] + " and the " + cards[playerCard2]);

						botCard1 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == botCard1) {
								isUsed = true;

							}
							while (isUsed == true) {
								botCard1 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[5] = botCard1;
						botCard2 = randCards.nextInt(52);
						for (int i = 0; i < usedCards.length; i++) {
							if (usedCards[i] == botCard2) {
								isUsed = true;

							}
							while (isUsed == true) {
								botCard2 = randCards.nextInt(52);
								isUsed = false;
							}
						}
						usedCards[6] = botCard2;

						// shows bot's cards
						System.out.println("Temporary: Bot's cards are the " + cards[botCard1] + " and the " + cards[botCard2]);

						if (cards[middleCard1].contains("ace")) {
							botAndMiddleCards[0] = "ace";
						} else if (cards[middleCard1].contains("2")) {
							botAndMiddleCards[0] = "2";
						} else if (cards[middleCard1].contains("3")) {
							botAndMiddleCards[0] = "3";
						} else if (cards[middleCard1].contains("4")) {
							botAndMiddleCards[0] = "4";
						} else if (cards[middleCard1].contains("5")) {
							botAndMiddleCards[0] = "5";
						} else if (cards[middleCard1].contains("6")) {
							botAndMiddleCards[0] = "6";
						} else if (cards[middleCard1].contains("7")) {
							botAndMiddleCards[0] = "7";
						} else if (cards[middleCard1].contains("8")) {
							botAndMiddleCards[0] = "8";
						} else if (cards[middleCard1].contains("9")) {
							botAndMiddleCards[0] = "9";
						} else if (cards[middleCard1].contains("10")) {
							botAndMiddleCards[0] = "10";
						} else if (cards[middleCard1].contains("jack")) {
							botAndMiddleCards[0] = "jack";
						} else if (cards[middleCard1].contains("queen")) {
							botAndMiddleCards[0] = "queen";
						} else if (cards[middleCard1].contains("king")) {
							botAndMiddleCards[0] = "king";
						}

						if (cards[middleCard2].contains("ace")) {
							botAndMiddleCards[1] = "ace";
						} else if (cards[middleCard2].contains("2")) {
							botAndMiddleCards[1] = "2";
						} else if (cards[middleCard2].contains("3")) {
							botAndMiddleCards[1] = "3";
						} else if (cards[middleCard2].contains("4")) {
							botAndMiddleCards[1] = "4";
						} else if (cards[middleCard2].contains("5")) {
							botAndMiddleCards[1] = "5";
						} else if (cards[middleCard2].contains("6")) {
							botAndMiddleCards[1] = "6";
						} else if (cards[middleCard2].contains("7")) {
							botAndMiddleCards[1] = "7";
						} else if (cards[middleCard2].contains("8")) {
							botAndMiddleCards[1] = "8";
						} else if (cards[middleCard2].contains("9")) {
							botAndMiddleCards[1] = "9";
						} else if (cards[middleCard2].contains("10")) {
							botAndMiddleCards[1] = "10";
						} else if (cards[middleCard2].contains("jack")) {
							botAndMiddleCards[1] = "jack";
						} else if (cards[middleCard2].contains("queen")) {
							botAndMiddleCards[1] = "queen";
						} else if (cards[middleCard2].contains("king")) {
							botAndMiddleCards[1] = "king";
						}
						if (cards[middleCard3].contains("ace")) {
							botAndMiddleCards[2] = "ace";
						} else if (cards[middleCard3].contains("2")) {
							botAndMiddleCards[2] = "2";
						} else if (cards[middleCard3].contains("3")) {
							botAndMiddleCards[2] = "3";
						} else if (cards[middleCard3].contains("4")) {
							botAndMiddleCards[2] = "4";
						} else if (cards[middleCard3].contains("5")) {
							botAndMiddleCards[2] = "5";
						} else if (cards[middleCard3].contains("6")) {
							botAndMiddleCards[2] = "6";
						} else if (cards[middleCard3].contains("7")) {
							botAndMiddleCards[2] = "7";
						} else if (cards[middleCard3].contains("8")) {
							botAndMiddleCards[2] = "8";
						} else if (cards[middleCard3].contains("9")) {
							botAndMiddleCards[2] = "9";
						} else if (cards[middleCard3].contains("10")) {
							botAndMiddleCards[2] = "10";
						} else if (cards[middleCard3].contains("jack")) {
							botAndMiddleCards[2] = "jack";
						} else if (cards[middleCard3].contains("queen")) {
							botAndMiddleCards[2] = "queen";
						} else if (cards[middleCard3].contains("king")) {
							botAndMiddleCards[2] = "king";
						}

						if (cards[botCard1].contains("ace")) {
							botAndMiddleCards[3] = "ace";
						} else if (cards[botCard1].contains("2")) {
							botAndMiddleCards[3] = "2";
						} else if (cards[botCard1].contains("3")) {
							botAndMiddleCards[3] = "3";
						} else if (cards[botCard1].contains("4")) {
							botAndMiddleCards[3] = "4";
						} else if (cards[botCard1].contains("5")) {
							botAndMiddleCards[3] = "5";
						} else if (cards[botCard1].contains("6")) {
							botAndMiddleCards[3] = "6";
						} else if (cards[botCard1].contains("7")) {
							botAndMiddleCards[3] = "7";
						} else if (cards[botCard1].contains("8")) {
							botAndMiddleCards[3] = "8";
						} else if (cards[botCard1].contains("9")) {
							botAndMiddleCards[3] = "9";
						} else if (cards[botCard1].contains("10")) {
							botAndMiddleCards[3] = "10";
						} else if (cards[botCard1].contains("jack")) {
							botAndMiddleCards[3] = "jack";
						} else if (cards[botCard1].contains("queen")) {
							botAndMiddleCards[3] = "queen";
						} else if (cards[botCard1].contains("king")) {
							botAndMiddleCards[3] = "king";
						}

						if (cards[botCard2].contains("ace")) {
							botAndMiddleCards[4] = "ace";
						} else if (cards[botCard2].contains("2")) {
							botAndMiddleCards[4] = "2";
						} else if (cards[botCard2].contains("3")) {
							botAndMiddleCards[4] = "3";
						} else if (cards[botCard2].contains("4")) {
							botAndMiddleCards[4] = "4";
						} else if (cards[botCard2].contains("5")) {
							botAndMiddleCards[4] = "5";
						} else if (cards[botCard2].contains("6")) {
							botAndMiddleCards[4] = "6";
						} else if (cards[botCard2].contains("7")) {
							botAndMiddleCards[4] = "7";
						} else if (cards[botCard2].contains("8")) {
							botAndMiddleCards[4] = "8";
						} else if (cards[botCard2].contains("9")) {
							botAndMiddleCards[4] = "9";
						} else if (cards[botCard2].contains("10")) {
							botAndMiddleCards[4] = "10";
						} else if (cards[botCard2].contains("jack")) {
							botAndMiddleCards[4] = "jack";
						} else if (cards[botCard2].contains("queen")) {
							botAndMiddleCards[4] = "queen";
						} else if (cards[botCard2].contains("king")) {
							botAndMiddleCards[4] = "king";
						}
						System.out.println("The bot has " + botAndMiddleCards[0]+", "+botAndMiddleCards[1]+", "
								+botAndMiddleCards[2]+", "+botAndMiddleCards[3]+", and "+botAndMiddleCards[4]);
						event.getChannel().sendMessage("Your hand is now the " +cards[playerCard1]+", "+cards[playerCard2]+", "+cards[middleCard1]+", "+cards[middleCard2]+", and "+cards[middleCard3]);
						event.getChannel().sendMessage("How much would you like to bet");
					}
				} 
			else {
					event.getChannel().sendMessage("You already have a game running.");
			}
			}
			else if(content.contains("fold") && !gameOver) {
				event.getChannel().sendMessage("You folded. Your balance is now "+balance);
				gameOver=true;
			}
				else if (content.contains("bet") && flop && !gameOver) {
					content = content.replace("bet", "");
					try {
						wager = Integer.parseInt(content);
					} catch (Exception e) {
						event.getChannel().sendMessage("Choose a number after the command to gamble");
					}
					System.out.println(totalBet);
					if (balance < 0) {
						event.getChannel().sendMessage("You don't have enough money to wager that much.");
					} else if(wager<0) {
						event.getChannel().sendMessage("Choose a positive number");
					}
					else {
						flop = false;
						balance -= wager;
						totalBet += wager;
						event.getChannel().sendMessage("Your balance is " + balance);

						highestCallChance = botAlgorithm(botAndMiddleCards);


						if (500 > highestCallChance) {
							highestCallChance = 500;
						}
						botCallChance = highestCallChance;
						Random callRandom = new Random();
						int i = callRandom.nextInt(1000);
						if (botCallChance >= i) {
							// bot calls
							System.out.println(
									"Bot calls. Bot chance of calling was " + botCallChance + ". The random was " + i);
							middleCard4 = randCards.nextInt(52);
							event.getChannel().sendMessage("Bot calls.");
							for (int k = 0; k < usedCards.length; k++) {
								if (usedCards[k] == middleCard4) {
									isUsed = true;

								}
								while (isUsed == true) {
									middleCard4 = randCards.nextInt(52);
									isUsed = false;
								}
							}
							usedCards[7] = middleCard4;
							event.getChannel()
									.sendMessage("The middle cards are now the " + cards[middleCard1] + ", the "
											+ cards[middleCard2] + ", the " + cards[middleCard3] + " and the "
											+ cards[middleCard4]);
							event.getChannel().sendMessage("Your hand is now the " +cards[playerCard1]+", "+cards[playerCard2]+", "+cards[middleCard1]+", "+cards[middleCard2]+", "+cards[middleCard3]+", and "+cards[middleCard4]);

							if (cards[middleCard4].contains("ace")) {
								botAndMiddleCards[5] = "ace";
							} else if (cards[middleCard4].contains("2")) {
								botAndMiddleCards[5] = "2";
							} else if (cards[middleCard4].contains("3")) {
								botAndMiddleCards[5] = "3";
							} else if (cards[middleCard4].contains("4")) {
								botAndMiddleCards[5] = "4";
							} else if (cards[middleCard4].contains("5")) {
								botAndMiddleCards[5] = "5";
							} else if (cards[middleCard4].contains("6")) {
								botAndMiddleCards[5] = "6";
							} else if (cards[middleCard4].contains("7")) {
								botAndMiddleCards[5] = "7";
							} else if (cards[middleCard4].contains("8")) {
								botAndMiddleCards[5] = "8";
							} else if (cards[middleCard4].contains("9")) {
								botAndMiddleCards[5] = "9";
							} else if (cards[middleCard4].contains("10")) {
								botAndMiddleCards[5] = "10";
							} else if (cards[middleCard4].contains("jack")) {
								botAndMiddleCards[5] = "jack";
							} else if (cards[middleCard4].contains("queen")) {
								botAndMiddleCards[5] = "queen";
							} else if (cards[middleCard4].contains("king")) {
								botAndMiddleCards[5] = "king";
							}
							System.out.println("The bot has "+botAndMiddleCards[0]+", "+botAndMiddleCards[1]
											+", "+botAndMiddleCards[2]+", "+botAndMiddleCards[3]+", "+botAndMiddleCards[4]
											+" and "+botAndMiddleCards[5]);
							event.getChannel().sendMessage("How much would you like to bet");
						} else {
							balance += totalBet * 2;
							event.getChannel().sendMessage(
									"The bot folded, you win " + ((totalBet * 2)-wager) + ". Your balance is now " + balance);
							gameOver = true;
							rewardGiven=true;
						}
					}
				} else if (content.contains("bet") && turn && !gameOver) {
					content = content.replace("bet", "");
					try {
						wager = Integer.parseInt(content);
					} catch (Exception e) {
						event.getChannel().sendMessage("Choose a number after the command to gamble");
					}
					System.out.println(totalBet);
					if (balance < 0) {
						event.getChannel().sendMessage("You don't have enough money to wager that much.");
					} else if(wager<0) {
						event.getChannel().sendMessage("Choose a positive number.");
					}
					else {
						turn = false;
						balance -= wager;
						totalBet += wager;
						event.getChannel().sendMessage("Your balance is " + balance);

						highestCallChance = botAlgorithm(botAndMiddleCards);

						// add more ways of having good hand here, in method

						if (500 > highestCallChance) {
							highestCallChance = 500;
						}
						botCallChance = highestCallChance;
						Random callRandom = new Random();
						int i = callRandom.nextInt(1000);
						if (botCallChance >= i) {
							// bot calls
							System.out.println("Bot calls. Bot chance of calling was " + botCallChance + ". The random was " + i);
							event.getChannel().sendMessage("Bot calls.");
							middleCard5 = randCards.nextInt(52);
							for (int k = 0; k < usedCards.length; k++) {
								if (usedCards[k] == middleCard5) {
									isUsed = true;

								}
								while (isUsed == true) {
									middleCard5 = randCards.nextInt(52);
									isUsed = false;
								}
							}
							usedCards[8] = middleCard5;
							event.getChannel()
									.sendMessage("The middle cards are now the " + cards[middleCard1] + ", the "
											+ cards[middleCard2] + ", the " + cards[middleCard3] + " , the "
											+ cards[middleCard4] + " and the " + cards[middleCard5]);

							if (cards[middleCard5].contains("ace")) {
								botAndMiddleCards[6] = "ace";
							} else if (cards[middleCard5].contains("2")) {
								botAndMiddleCards[6] = "2";
							} else if (cards[middleCard5].contains("3")) {
								botAndMiddleCards[6] = "3";
							} else if (cards[middleCard5].contains("4")) {
								botAndMiddleCards[6] = "4";
							} else if (cards[middleCard5].contains("5")) {
								botAndMiddleCards[6] = "5";
							} else if (cards[middleCard5].contains("6")) {
								botAndMiddleCards[6] = "6";
							} else if (cards[middleCard5].contains("7")) {
								botAndMiddleCards[6] = "7";
							} else if (cards[middleCard5].contains("8")) {
								botAndMiddleCards[6] = "8";
							} else if (cards[middleCard5].contains("9")) {
								botAndMiddleCards[6] = "9";
							} else if (cards[middleCard5].contains("10")) {
								botAndMiddleCards[6] = "10";
							} else if (cards[middleCard5].contains("jack")) {
								botAndMiddleCards[6] = "jack";
							} else if (cards[middleCard5].contains("queen")) {
								botAndMiddleCards[6] = "queen";
							} else if (cards[middleCard5].contains("king")) {
								botAndMiddleCards[6] = "king";
							}
							System.out.println("The bot has "+botAndMiddleCards[0]+", "+botAndMiddleCards[1]
											+", "+botAndMiddleCards[2]+", "+botAndMiddleCards[3]+", "+botAndMiddleCards[4]
											+", "+botAndMiddleCards[5]+" and "+botAndMiddleCards[6]);
							event.getChannel().sendMessage("Your hand is now the " +cards[playerCard1]+", "+cards[playerCard2]+", "+cards[middleCard1]+", "+cards[middleCard2]+", "+cards[middleCard3]+", "+cards[middleCard4]+", and "+cards[middleCard5]);
							event.getChannel().sendMessage("How much would you like to bet?");
						} else {
							balance += totalBet * 2;
							event.getChannel().sendMessage(
									"The bot folded, you win " + ((totalBet * 2)-wager) + ". Your balance is now " + balance);
							gameOver = true;
							rewardGiven=true;
						}
						}
					}else if (content.contains("bet") && river && !gameOver) {
							content = content.replace("bet", "");
							try {
								wager = Integer.parseInt(content);
							} catch (Exception e) {
								event.getChannel().sendMessage("Choose a number after the command to gamble");
							}
							System.out.println(totalBet);
							if (balance < 0) {
								event.getChannel().sendMessage("You don't have enough money to wager that much.");
							} else if(wager<0) {
								event.getChannel().sendMessage("Choose a positive number.");
							}
							else {
								river = false;
								balance -= wager;
								totalBet += wager;
								event.getChannel().sendMessage("Your balance is " + balance);
								
						if (cards[middleCard1].contains("ace")) {
							playerAndMiddleCards[0] = "ace";
						} else if (cards[middleCard1].contains("2")) {
							playerAndMiddleCards[0] = "2";
						} else if (cards[middleCard1].contains("3")) {
							playerAndMiddleCards[0] = "3";
						} else if (cards[middleCard1].contains("4")) {
							playerAndMiddleCards[0] = "4";
						} else if (cards[middleCard1].contains("5")) {
							playerAndMiddleCards[0] = "5";
						} else if (cards[middleCard1].contains("6")) {
							playerAndMiddleCards[0] = "6";
						} else if (cards[middleCard1].contains("7")) {
							playerAndMiddleCards[0] = "7";
						} else if (cards[middleCard1].contains("8")) {
							playerAndMiddleCards[0] = "8";
						} else if (cards[middleCard1].contains("9")) {
							playerAndMiddleCards[0] = "9";
						} else if (cards[middleCard1].contains("10")) {
							playerAndMiddleCards[0] = "10";
						} else if (cards[middleCard1].contains("jack")) {
							playerAndMiddleCards[0] = "jack";
						} else if (cards[middleCard1].contains("queen")) {
							playerAndMiddleCards[0] = "queen";
						} else if (cards[middleCard1].contains("king")) {
							playerAndMiddleCards[0] = "king";
						}
						if (cards[middleCard2].contains("ace")) {
							playerAndMiddleCards[1] = "ace";
						} else if (cards[middleCard2].contains("2")) {
							playerAndMiddleCards[1] = "2";
						} else if (cards[middleCard2].contains("3")) {
							playerAndMiddleCards[1] = "3";
						} else if (cards[middleCard2].contains("4")) {
							playerAndMiddleCards[1] = "4";
						} else if (cards[middleCard2].contains("5")) {
							playerAndMiddleCards[1] = "5";
						} else if (cards[middleCard2].contains("6")) {
							playerAndMiddleCards[1] = "6";
						} else if (cards[middleCard2].contains("7")) {
							playerAndMiddleCards[1] = "7";
						} else if (cards[middleCard2].contains("8")) {
							playerAndMiddleCards[1] = "8";
						} else if (cards[middleCard2].contains("9")) {
							playerAndMiddleCards[1] = "9";
						} else if (cards[middleCard2].contains("10")) {
							playerAndMiddleCards[1] = "10";
						} else if (cards[middleCard2].contains("jack")) {
							playerAndMiddleCards[1] = "jack";
						} else if (cards[middleCard2].contains("queen")) {
							playerAndMiddleCards[1] = "queen";
						} else if (cards[middleCard2].contains("king")) {
							playerAndMiddleCards[1] = "king";
						}
						if (cards[middleCard3].contains("ace")) {
							playerAndMiddleCards[2] = "ace";
						} else if (cards[middleCard3].contains("2")) {
							playerAndMiddleCards[2] = "2";
						} else if (cards[middleCard3].contains("3")) {
							playerAndMiddleCards[2] = "3";
						} else if (cards[middleCard3].contains("4")) {
							playerAndMiddleCards[2] = "4";
						} else if (cards[middleCard3].contains("5")) {
							playerAndMiddleCards[2] = "5";
						} else if (cards[middleCard3].contains("6")) {
							playerAndMiddleCards[2] = "6";
						} else if (cards[middleCard3].contains("7")) {
							playerAndMiddleCards[2] = "7";
						} else if (cards[middleCard3].contains("8")) {
							playerAndMiddleCards[2] = "8";
						} else if (cards[middleCard3].contains("9")) {
							playerAndMiddleCards[2] = "9";
						} else if (cards[middleCard3].contains("10")) {
							playerAndMiddleCards[2] = "10";
						} else if (cards[middleCard3].contains("jack")) {
							playerAndMiddleCards[2] = "jack";
						} else if (cards[middleCard3].contains("queen")) {
							playerAndMiddleCards[2] = "queen";
						} else if (cards[middleCard3].contains("king")) {
							playerAndMiddleCards[2] = "king";
						}
						if (cards[playerCard1].contains("ace")) {
							playerAndMiddleCards[3] = "ace";
						} else if (cards[playerCard1].contains("2")) {
							playerAndMiddleCards[3] = "2";
						} else if (cards[playerCard1].contains("3")) {
							playerAndMiddleCards[3] = "3";
						} else if (cards[playerCard1].contains("4")) {
							playerAndMiddleCards[3] = "4";
						} else if (cards[playerCard1].contains("5")) {
							playerAndMiddleCards[3] = "5";
						} else if (cards[playerCard1].contains("6")) {
							playerAndMiddleCards[3] = "6";
						} else if (cards[playerCard1].contains("7")) {
							playerAndMiddleCards[3] = "7";
						} else if (cards[playerCard1].contains("8")) {
							playerAndMiddleCards[3] = "8";
						} else if (cards[playerCard1].contains("9")) {
							playerAndMiddleCards[3] = "9";
						} else if (cards[playerCard1].contains("10")) {
							playerAndMiddleCards[3] = "10";
						} else if (cards[playerCard1].contains("jack")) {
							playerAndMiddleCards[3] = "jack";
						} else if (cards[playerCard1].contains("queen")) {
							playerAndMiddleCards[3] = "queen";
						} else if (cards[playerCard1].contains("king")) {
							playerAndMiddleCards[3] = "king";
						}
						if (cards[playerCard2].contains("ace")) {
							playerAndMiddleCards[4] = "ace";
						} else if (cards[playerCard2].contains("2")) {
							playerAndMiddleCards[4] = "2";
						} else if (cards[playerCard2].contains("3")) {
							playerAndMiddleCards[4] = "3";
						} else if (cards[playerCard2].contains("4")) {
							playerAndMiddleCards[4] = "4";
						} else if (cards[playerCard2].contains("5")) {
							playerAndMiddleCards[4] = "5";
						} else if (cards[playerCard2].contains("6")) {
							playerAndMiddleCards[4] = "6";
						} else if (cards[playerCard2].contains("7")) {
							playerAndMiddleCards[4] = "7";
						} else if (cards[playerCard2].contains("8")) {
							playerAndMiddleCards[4] = "8";
						} else if (cards[playerCard2].contains("9")) {
							playerAndMiddleCards[4] = "9";
						} else if (cards[playerCard2].contains("10")) {
							playerAndMiddleCards[4] = "10";
						} else if (cards[playerCard2].contains("jack")) {
							playerAndMiddleCards[4] = "jack";
						} else if (cards[playerCard2].contains("queen")) {
							playerAndMiddleCards[4] = "queen";
						} else if (cards[playerCard2].contains("king")) {
							playerAndMiddleCards[4] = "king";
						}
						if (cards[middleCard4].contains("ace")) {
							playerAndMiddleCards[5] = "ace";
						} else if (cards[middleCard4].contains("2")) {
							playerAndMiddleCards[5] = "2";
						} else if (cards[middleCard4].contains("3")) {
							playerAndMiddleCards[5] = "3";
						} else if (cards[middleCard4].contains("4")) {
							playerAndMiddleCards[5] = "4";
						} else if (cards[middleCard4].contains("5")) {
							playerAndMiddleCards[5] = "5";
						} else if (cards[middleCard4].contains("6")) {
							playerAndMiddleCards[5] = "6";
						} else if (cards[middleCard4].contains("7")) {
							playerAndMiddleCards[5] = "7";
						} else if (cards[middleCard4].contains("8")) {
							playerAndMiddleCards[5] = "8";
						} else if (cards[middleCard4].contains("9")) {
							playerAndMiddleCards[5] = "9";
						} else if (cards[middleCard4].contains("10")) {
							playerAndMiddleCards[5] = "10";
						} else if (cards[middleCard4].contains("jack")) {
							playerAndMiddleCards[5] = "jack";
						} else if (cards[middleCard4].contains("queen")) {
							playerAndMiddleCards[5] = "queen";
						} else if (cards[middleCard4].contains("king")) {
							playerAndMiddleCards[5] = "king";
						}
						if (cards[middleCard5].contains("ace")) {
							playerAndMiddleCards[6] = "ace";
						} else if (cards[middleCard5].contains("2")) {
							playerAndMiddleCards[6] = "2";
						} else if (cards[middleCard5].contains("3")) {
							playerAndMiddleCards[6] = "3";
						} else if (cards[middleCard5].contains("4")) {
							playerAndMiddleCards[6] = "4";
						} else if (cards[middleCard5].contains("5")) {
							playerAndMiddleCards[6] = "5";
						} else if (cards[middleCard5].contains("6")) {
							playerAndMiddleCards[6] = "6";
						} else if (cards[middleCard5].contains("7")) {
							playerAndMiddleCards[6] = "7";
						} else if (cards[middleCard5].contains("8")) {
							playerAndMiddleCards[6] = "8";
						} else if (cards[middleCard5].contains("9")) {
							playerAndMiddleCards[6] = "9";
						} else if (cards[middleCard5].contains("10")) {
							playerAndMiddleCards[6] = "10";
						} else if (cards[middleCard5].contains("jack")) {
							playerAndMiddleCards[6] = "jack";
						} else if (cards[middleCard5].contains("queen")) {
							playerAndMiddleCards[6] = "queen";
						} else if (cards[middleCard5].contains("king")) {
							playerAndMiddleCards[6] = "king";
						}
						
						if(!rewardGiven) {
							event.getChannel().sendMessage("Your hand is now the " +cards[playerCard1]+", "+cards[playerCard2]+", "+cards[middleCard1]+", "+cards[middleCard2]+", "+cards[middleCard3]+", "+cards[middleCard4]+", and "+cards[middleCard5]);
							event.getChannel().sendMessage("The bot's final hand has the "+cards[botCard1]+", "+cards[botCard2]+", "+cards[middleCard1]+", "+cards[middleCard2]+", "+cards[middleCard3]+", "+cards[middleCard4]+", and "+cards[middleCard5]);
						playerWon=playerWins(playerAndMiddleCards, botAndMiddleCards);
						if(playerWon) {
							balance += totalBet * 2;
							event.getChannel().sendMessage(
									"You have the better hand. You won " + ((totalBet * 2)) + ". Your balance is now " + balance);
							gameOver = true;
						}
						else if(!playerWon) {
							if(tie) {
								balance+=totalBet;
								event.getChannel().sendMessage("It is a tie. You get back "+totalBet+". Your balance is now "+balance);
							}
							else {
							event.getChannel().sendMessage("The bot has the better hand. You win nothing. Your balance is now "+balance);
						}
						}
						}
						else {
							System.out.println("error");
						}
						}
					}
				}
				}
	

	public int botAlgorithm(String[] botAndMiddleCards) {
		highestCallChance = 0;
		numberSameCards = 0;
		numberPairs=0;
		straight=false;
		ace=false;
		two=false;
		three=false;
		four=false;
		five=false;
		six=false;
		seven=false;
		eight=false;
		nine=false;
		ten=false;
		jack=false;
		queen=false;
		king=false;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if(botAndMiddleCards[j]=="ace") {
				ace=true;
			}
			else if(botAndMiddleCards[j]=="2") {
				two=true;
			}
			else if(botAndMiddleCards[j]=="3") {
				three=true;
			}
			else if(botAndMiddleCards[j]=="4") {
				four=true;
			}
			else if(botAndMiddleCards[j]=="5") {
				five=true;
			}
			else if(botAndMiddleCards[j]=="6") {
				six=true;
			}
			else if(botAndMiddleCards[j]=="7") {
				seven=true;
			}
			else if(botAndMiddleCards[j]=="8") {
				eight=true;
			}
			else if(botAndMiddleCards[j]=="9") {
				nine=true;
			}
			else if(botAndMiddleCards[j]=="10") {
				ten=true;
			}
			else if(botAndMiddleCards[j]=="jack") {
				jack=true;
			}
			else if(botAndMiddleCards[j]=="queen") {
				queen=true;
			}
			else if(botAndMiddleCards[j]=="king") {
				king=true;
			}
			if(ace) {
				if(two) {
					if(three) {
						if(four) {
							if(five) {
								straight=true;
							}
						}
					}
				}
			}
			else if(two) {
				if(three) {
					if(four) {
						if(five) {
							if(six) {
								straight=true;
							}
						}
					}
				}
			}
			else if(three) {
				if(four) {
					if(five) {
						if(six) {
							if(seven) {
								straight=true;
							}
						}
					}
				}
			}
			else if(four) {
				if(five) {
					if(six) {
						if(seven) {
							if(eight) {
								straight=true;
							}
						}
					}
				}
			}
			else if(five) {
				if(six) {
					if(seven) {
						if(eight) {
							if(nine) {
								straight=true;
							}
						}
					}
				}
			}
			else if(six) {
				if(seven) {
					if(eight) {
						if(nine) {
							if(ten) {
								straight=true;
							}
						}
					}
				}
			}
			else if(seven) {
				if(eight) {
					if(nine) {
						if(ten) {
							if(jack) {
								straight=true;
							}
						}
					}
				}
			}
			else if(eight) {
				if(nine) {
					if(ten) {
						if(jack) {
							if(queen) {
								straight=true;
							}
						}
					}
				}
			}
			else if(nine) {
				if(ten) {
					if(jack) {
						if(queen) {
							if(king) {
								straight=true;
							}
						}
					}
				}
			}
			else if(ten) {
				if(jack) {
					if(queen) {
						if(king) {
							if(ace) {
								straight=true;
							}
						}
					}
				}
			}
		}
		
		
		
		
		
		
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "ace") {
				numberSameCards += 1;
				if(513>highestCallChance) {
				highestCallChance=513;
				}
			}
		}
		if (numberSameCards == 2) {
			if (949 > highestCallChance) {
				highestCallChance = 949;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 963;
		}
		else if(numberSameCards == 4) {
			highestCallChance=1000;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "2") {
				numberSameCards += 1;
				if(501>highestCallChance) {
				highestCallChance=501;
				}
			}
		}
		if (numberSameCards == 2) {
			if (700 > highestCallChance) {
				highestCallChance = 700;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 951;
		}
		else if(numberSameCards == 4) {
			highestCallChance=971;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "3") {
				numberSameCards += 1;
				if(502>highestCallChance) {
				highestCallChance=502;
				}
			}
		}
		if (numberSameCards == 2) {
			if (750 > highestCallChance) {
				highestCallChance = 750;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 952;
		}
		else if(numberSameCards == 4) {
			highestCallChance=972;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "4") {
				numberSameCards += 1;
				if(503>highestCallChance) {
				highestCallChance=503;
				}
			}
		}
		if (numberSameCards == 2) {
			if (751 > highestCallChance) {
				highestCallChance = 751;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 953;
		}
		else if(numberSameCards == 4) {
			highestCallChance=973;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "5") {
				numberSameCards += 1;
				if(504>highestCallChance) {
				highestCallChance=504;
				}
			}
		}
		if (numberSameCards == 2) {
			if (800 > highestCallChance) {
				highestCallChance = 800;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 954;
		}
		else if(numberSameCards == 4) {
			highestCallChance=974;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "6") {
				numberSameCards += 1;
				if(505>highestCallChance) {
				highestCallChance=505;
				}
			}
		}
		if (numberSameCards == 2) {
			if (801 > highestCallChance) {
				highestCallChance = 801;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 955;
		}
		else if(numberSameCards == 4) {
			highestCallChance=975;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "7") {
				numberSameCards += 1;
				if(506>highestCallChance) {
				highestCallChance=506;
				}
			}
		}
		if (numberSameCards == 2) {
			if (850 > highestCallChance) {
				highestCallChance = 850;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 956;
		}
		else if(numberSameCards == 4) {
			highestCallChance=976;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "8") {
				numberSameCards += 1;
				if(507>highestCallChance) {
				highestCallChance=507;
				}
			}
		}
		if (numberSameCards == 2) {
			if (851 > highestCallChance) {
				highestCallChance = 851;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 957;
		}
		else if(numberSameCards == 4) {
			highestCallChance=977;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "9") {
				numberSameCards += 1;
				if(508>highestCallChance) {
				highestCallChance=508;
				}
			}
		}
		if (numberSameCards == 2) {
			if (852 > highestCallChance) {
				highestCallChance = 852;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 958;
		}
		else if(numberSameCards == 4) {
			highestCallChance=978;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "10") {
				numberSameCards += 1;
				if(509>highestCallChance) {
				highestCallChance=509;
				}
			}
		}
		if (numberSameCards == 2) {
			if (900 > highestCallChance) {
				highestCallChance = 900;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 959;
		}
		else if(numberSameCards == 4) {
			highestCallChance=979;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "jack") {
				numberSameCards += 1;
				if(510>highestCallChance) {
				highestCallChance=510;
				}
			}
		}
		if (numberSameCards == 2) {
			if (910 > highestCallChance) {
				highestCallChance = 910;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 960;
		}
		else if(numberSameCards == 4) {
			highestCallChance=980;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "queen") {
				numberSameCards += 1;
				if(511>highestCallChance) {
				highestCallChance=511;
				}
			}
		}
		if (numberSameCards == 2) {
			if (920 > highestCallChance) {
				highestCallChance = 920;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 961;
		}
		else if(numberSameCards == 4) {
			highestCallChance=981;
		}

		numberSameCards = 0;
		for (int j = 0; j < botAndMiddleCards.length; j++) {
			if (botAndMiddleCards[j] == "king") {
				numberSameCards += 1;
				if(512>highestCallChance) {
				highestCallChance=512;
				}
			}
		}
		if (numberSameCards == 2) {
			if (930 > highestCallChance) {
				highestCallChance = 930;
				numberPairs+=1;
			}
		} else if (numberSameCards == 3) {
			highestCallChance = 962;
		}
		else if(numberSameCards == 4) {
			highestCallChance=982;
		}
		if(numberPairs==2) {
			highestCallChance=950;
		}
		return highestCallChance;
	}
	public boolean playerWins(String [] playerAndMiddleCards, String [] botAndMiddleCards) {
		playerScore=botAlgorithm(playerAndMiddleCards);
		botScore=botAlgorithm(botAndMiddleCards);
		gameOver=true;
		tie=false;
		if(playerScore>botScore) {
			return true;
		}
		else if(playerScore==botScore) {
			tie=true;
			return false;
		}
		else {
		return false;
		}
	}
}