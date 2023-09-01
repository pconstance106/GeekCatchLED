import java.util.Scanner;

public class LEDBar {

    private boolean isOn;
    private String displayedText;
    private boolean isFlashing;
    private boolean isPulsating;

    public LEDBar() {
        isOn = false;
        displayedText = "";
        isFlashing = false;
        isPulsating = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("LED bar is now ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("LED bar is now OFF.");
    }

    public void displayTextEffect(String text, String effect, int duration) {
        if (isOn) {
            displayedText = text + " (" + effect + ")";
            System.out.println("Displaying text on LED bar: " + displayedText);
            if (isFlashing) {
                flashLED(); // Simulate flashing effect
            } else if (isPulsating) {
                pulsateLED(); // Simulate pulsating effect
            }
            try {
                Thread.sleep(duration); // Delay for the specified duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearDisplay(); // Clear the display after the specified duration
        } else {
            System.out.println("LED bar is turned off. Cannot display text.");
        }
    }

    public void clearDisplay() {
        displayedText = "";
        isFlashing = false;
        isPulsating = false;
        System.out.println("LED bar display cleared.");
    }

    public void flashLED() {
        System.out.println("LED bar is flashing.");
    }

    public void pulsateLED() {
        System.out.println("LED bar is pulsating.");
    }

    public static void main(String[] args) {
        LEDBar ledBar = new LEDBar();
        ledBar.turnOn();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the chatter's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the donation amount: ");
        double amount = scanner.nextDouble();

        scanner.nextLine(); // Consume newline

        System.out.print("Enter the donation message: ");
        String message = scanner.nextLine();

        String effectDescription = "";

        // Adjust the effect based on the donated amount range
        if (amount >= 10 && amount < 50) {
            effectDescription = "Red Text, Flashing";
            ledBar.isFlashing = true;
            ledBar.displayTextEffect(message, effectDescription, 20000); // 20 seconds for amounts between $10 and $49.99
        } else if (amount >= 50) {
            effectDescription = "Green Text, Pulsating";
            ledBar.isPulsating = true;
            ledBar.displayTextEffect(message, effectDescription, 30000); // 30 seconds for amounts of $50 or more
        } else {
            effectDescription = "Blue Text, Steady";
            ledBar.displayTextEffect(message, effectDescription, 10000); // Default 10 seconds for smaller amounts
        }

        ledBar.turnOff();
    }
}
