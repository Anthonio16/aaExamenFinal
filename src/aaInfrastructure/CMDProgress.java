package aaInfrastructure;
//Refactorización

public class CMDProgress {
    public static void show(String title, int steps, int msPerStep) {
        System.out.print(title + " ");
        for (int i = 0; i < steps; i++) {
            System.out.print("█");
            try { Thread.sleep(msPerStep); } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }
}