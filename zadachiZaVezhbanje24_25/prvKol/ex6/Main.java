package zadachiZaVezhbanje24_25.prvKol.ex6;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        System.out.println(shufflesToTop(n));
    }

    private static int shufflesToTop(int pos) {
        int count = 0;
        while (pos != 1) {
            if (pos > 7) {
                pos = pos - 7;
            } else {
                pos = 51 - (pos - 1);
            }
            count++;
        }
        return count;
    }
}
