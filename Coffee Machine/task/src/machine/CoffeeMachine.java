
package machine;

import java.security.KeyStore.TrustedCertificateEntry;
import java.util.Scanner;

public class CoffeeMachine {

    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int money = 550;

    public static void main(String[] args) {
        boolean ok = true;
        Scanner scanner = new Scanner(System.in);

        while (ok) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "remaining":
                    printResources();
                    break;
                case "buy":
                    sell(scanner);
                    break;
                case "fill":
                    fill(scanner);
                    break;
                case "take":
                    getMoney();
                    break;
                case "exit":
                    ok = false;
                    break;
                default:
                    break;
            }
        }

    }

    private static void printResources() {
        System.out.println(String.format(
                "The coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "$%d of money\n", water, milk, beans, cups, money));
    }

    private static void sell(Scanner scanner) {
        System.out.println(
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String want = scanner.nextLine();

        switch (want) {
            case "1":
                // espresso
                if (canMake(250, 0 , 16)) {
                    make(250, 0, 16, 1, 4);
                } else {
                    sorry(getNotEnoughResource(250, 0, 16));
                }
                break;
            case "2":
                // latte
                if (canMake(350, 75 , 20)) {
                    make(350, 75 , 20, 1, 7);
                } else {
                    sorry(getNotEnoughResource(350, 75, 20));
                }
                break;
            case "3":
                // cappuccino
                if (canMake(200, 100, 12)) {
                    make(200, 100, 12, 1, 6);
                } else {
                    sorry(getNotEnoughResource(200, 100, 12));
                }
                break;
            default:
                break;
        }
    }

    private static void fill(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }

    private static boolean canMake(int w, int m, int b) {
        return water >= w && milk >= m && beans >= b && cups > 0;
    }

    private static void make(int w, int m, int b, int c, int mm) {
        System.out.println("I have enough resources, making you a coffee!");
        water -= w;
        milk -= m;
        beans -= b;
        cups -= c;
        money += mm;
    }

    private static void sorry(String resource) {
        System.out.println(String.format("Sorry, not enough %s!", resource));
    }

    private static String getNotEnoughResource(int w, int m, int b) {
        if (water < w) {
            return "water";
        }
        if (milk < m) {
            return "milk";
        }
        if (beans < b) {
            return "coffee beans";
        }
        if (cups <= 0) {
            return "disposable cups";
        }
        return "money";
    }

    private static void getMoney() {
        System.out.println(String.format("I gave you $%d", money));
        money = 0;
    }
}







