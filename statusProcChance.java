

import java.util.*;

public class statusProcChance {
  static Scanner user = new Scanner(System.in);
  static ArrayList<Double>damageNumbers = new ArrayList<Double>();
  static ArrayList<String>damageTypes = new ArrayList<String>();

  public static void main(String[] args) {
    getGreeting();
    spacing();
    getWeaponInfo();
    spacing();
    calcAll();
  }

  //This is where 95% of the magic happens. This method is the key to creating a
  //non-Scanner class
  public static void calcAll() {

    System.out.println("MULTISHOT multiplier:");
    double multishot = user.nextDouble();
    if (multishot > 10) {
      multishot = multishot/100.00;
    }
    multishot += 1;
    System.out.println("Pellet count:");
    multishot *= user.nextDouble();
    damageNumbers.set(0, multishotMath(damageNumbers.get(0), multishot));
    System.out.println();
    System.out.println("Your Weapon's Damage:");
    System.out.println("-------------------------");
    for (int i = 1; i < damageTypes.size(); i++) {
      System.out.println("\t" + damageTypes.get(i) + ":    " + "\t" + damageNumbers.get(i));
    }
    System.out.println("\t" + "STATUS: " + "\t" + Math.round(damageNumbers.get(0)*1000)/10.00 + "%   (per pellet)");
    System.out.println("\t" + "PELLETS: " + "\t" + Math.round(multishot*10)/10.0);
    System.out.println("-------------------------");
    System.out.println();

    double weightedTotal = 0.0;
    for (int i = 1; i < damageNumbers.size(); i++) {
      if (i <= 3) {
        weightedTotal += damageNumbers.get(i);
      } else {
        weightedTotal += damageNumbers.get(i);
      }
    }
    for (int j = 1; j < damageNumbers.size(); j++) {
      double proc = damageNumbers.get(j)/weightedTotal;
      double status = proc*damageNumbers.get(0);
      if (j <= 3) {
        System.out.println("Chance of " + damageTypes.get(j) + " proc per bullet: " + "\t" + Math.round(status*4000)/10.0
          + "%" + "\t" + "(" + Math.round(proc*4000)/10.0 + "% liklihood of status proc being " + damageTypes.get(j) + ")");
      } else {
        System.out.println("Chance of " + damageTypes.get(j) + " proc per bullet:   " + "\t" + Math.round(status*1000)/10.0
          + "%" + "\t" + "(" + Math.round(proc*1000)/10.0 + "% liklihood of status proc being " + damageTypes.get(j) + ")");
      }
    }
  }

  //Converts STATUS "probability" into "chance per pellet"
  public static double multishotMath(double status, double multishot) {
    double math = 1 - Math.pow((1 - status), (1.0/multishot));
    return math;
  }

  //Gets the weapon damage numbers and types and completely fills out the ArrayLists
  public static void getWeaponInfo() {
    damageTypes.add("STATUS");
    damageTypes.add("IMPACT");
    damageTypes.add("PUNCTURE");
    damageTypes.add("SLASH");

    System.out.println("Weapon STATUS chance:");
    while (!user.hasNextDouble()) {
      System.out.println("Incorrect input. Try again.");
      String incorrect = user.nextLine();
    }

    damageNumbers.add(user.nextDouble());
    if (damageNumbers.get(0) > 1) {
      damageNumbers.set(0, damageNumbers.get(0)/100.00); //Converts to decimal if entering ##.## format
    }

    spacing();

    for (int i = 1; i < damageTypes.size(); i++) {
      System.out.println("Total " + damageTypes.get(i) + " damage:");
      damageNumbers.add(user.nextDouble());
    }

    spacing();

    System.out.println("# of elemental damage types: ");
    int count = user.nextInt();
    System.out.println();
    for (int j = 1; j <= count; j++) {
      System.out.println("Elemental damage type #" + j + " name:");
      String elementName = user.next();
      damageTypes.add(elementName.toUpperCase());
      System.out.println("Total " + elementName.toUpperCase() + " damage:");
      damageNumbers.add(user.nextDouble());
    }
  }

  //Greets the user
  public static void getGreeting() {
    System.out.println();
    System.out.println("This calculates the % chance your STATUS effect of choice will occur per damage instance.");
  }

  //Simple redundant spacing visual
  public static void spacing() {
    System.out.println();
    System.out.println("...");
    System.out.println();
  }
}

//Some things to note:
/*
-- Program currently cannot handle most user-based errors well
-- Methods should 'return' more often instead of just load-bearing for main
*/
