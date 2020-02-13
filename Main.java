import java.util.*;
import java.lang.Math;

class Main {
  static Scanner s = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Blackjack");
    System.out.println("Start with 1000 chips, Dealer always stands on 17+");
    System.out.println("  ");
    System.out.println("Please enter 1 to Start");
    int start = s.nextInt();
    if (start == 1) {
      int chips = 1000;
      System.out.print("\033[H\033[2J");
      System.out.flush();
      betsend(chips);
    } else {
      return;
    }
  }
  public static void betsend(int chips) {
    int bet = 0;
    System.out.println("  ");
    System.out.println("How much would you like to bet?");
    bet = s.nextInt();
    chips = chips - bet;
    System.out.print("\033[H\033[2J");
    System.out.flush();
    startGame(chips, bet);
  }
  public static void startGame(int chips, int bet) {
      System.out.println("  ");
      System.out.println("Chips: " + chips + "   " + "Current Bet: " + bet);
      System.out.println("  ");
      boolean userWins = playBlackjack();
      if (userWins == true) {
        chips += (2 * bet);
        System.out.println("You win!");
        System.out.println("Chips: " + chips);
        betsend(chips);
      }
      else if (userWins == false) {
        System.out.println("  ");
        System.out.println("You lose!");
        System.out.println("Chips: " + chips);
        betsend(chips);
      }
  }

  public static boolean playBlackjack() {
    ArrayList<String> deck = new ArrayList<String>();
    ArrayList<String> dealer = new ArrayList<String>();
    ArrayList<String> player = new ArrayList<String>();
    ArrayList<Integer> decknum = new ArrayList<Integer>();
    ArrayList<Integer> dealernum = new ArrayList<Integer>();
    ArrayList<Integer> playernum = new ArrayList<Integer>();
    deck.add("2");
    deck.add("3");
    deck.add("4");
    deck.add("5");
    deck.add("6");
    deck.add("7");
    deck.add("8");
    deck.add("9");
    deck.add("10");
    deck.add("J");
    deck.add("Q");
    deck.add("K");
    deck.add("A");
    decknum.add(2);
    decknum.add(3);
    decknum.add(4);
    decknum.add(5);
    decknum.add(6);
    decknum.add(7);
    decknum.add(8);
    decknum.add(9);
    decknum.add(10);
    decknum.add(10);
    decknum.add(10);
    decknum.add(10);
    decknum.add(11);
    int a;
    int b;
    int dealtotal = 0;
    int playtotal = 0;
    for (int i = 0; i < 2; i++) {
      a = (int) Math.round(Math.random()*12);
      dealer.add(i, deck.get(a));
      dealernum.add(i, decknum.get(a));
    }
    for (int j = 0; j < 2; j++) {
      b = (int) Math.round(Math.random()*12);
      player.add(j, deck.get(b));
      playernum.add(j, decknum.get(b));
    }
    System.out.print("Dealer: ");
    for (int z = 0; z < 2; z++) {
      if (z == 0) {
        System.out.println(dealer.get(z));
      }
      else {
        System.out.println("X");
      }
      dealtotal += dealernum.get(z);
    }
    System.out.println("  ");
    System.out.print("Player: ");
    for (int d = 0; d < 2; d++) {
      System.out.println(player.get(d));
      playtotal += playernum.get(d);
    }
    boolean betting = true;
    boolean winner = true;
    int k = 2;
    int c = 2;
    while (betting == true) {
      if (dealtotal >= 17 && dealtotal < 21) {
        System.out.println("Dealer Stands");
        System.out.println("Would you like to hit or stand? (h/s)");
        String dummy = s.nextLine();
        String select = s.nextLine();
        if (select.equals("h")) {
          int d = (int) Math.round(Math.random()*12);
          player.add(k, deck.get(d));
          playernum.add(k, decknum.get(d));
          playtotal += playernum.get(k);
          System.out.println("Player Draws: " + player.get(k));
          k++;
          break;
        }
        else if (select.equals("s")) {
          betting = false;
        }
      }
      else if (dealtotal < 17) {
        System.out.println("Dealer Hits");
        while (dealtotal < 17) {
          int x = (int) Math.round(Math.random()*12);
          dealer.add(c, deck.get(x));
          dealernum.add(c, decknum.get(x));
          System.out.println("Dealer Draws: " + dealer.get(c));
          dealtotal += dealernum.get(c);
          x++;
          break;
        }
        System.out.println("Would you like to hit or stand? (h/s)");
        String dummy = s.nextLine();
        String select = s.nextLine();
        if (select.equals("h")) {
          int d = (int) Math.round(Math.random()*12);
          player.add(k, deck.get(d));
          playernum.add(k, decknum.get(d));
          playtotal += playernum.get(k);
          System.out.println("Player Draws: " + player.get(k));
          k++;
          break;
        }
        else if (select.equals("s")) {
          betting = false;
        }
      }
    }
    winner = checkWhoWon(playtotal, dealtotal, dealer.get(1));
    return winner;
  }
  public static boolean checkWhoWon(int a, int b, String c) {
    System.out.println("Dealer X Card: " + c);
    boolean win = false;
    if (a == b) {
      win = true;
    }
    else if (a > b && a <= 21 && b <= 21) {
      win = true;
    }
    else if (a < b && a <= 21 && b <= 21) {
      win = false;
    }
    else if (a > 21) {
      win = false;
    }
    else if (b > 21) {
      win = true;
    }
    return win;
  }
  public static void gameOver() {
    return;
  }
}
