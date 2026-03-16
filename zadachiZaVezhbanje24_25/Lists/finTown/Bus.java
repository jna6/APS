package zadachiZaVezhbanje24_25.Lists.finTown;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka

        int minCost;
        int maxCost;
        if(N == 0 || M<=N){
            minCost = N * 100;
        }else {
            minCost = N * 100 + (M - N) * 100;
        }

        if(M == 0){
            maxCost = ((N + M) * 100);
        }else
            maxCost = ((N + M) * 100)-100;


        System.out.println(minCost);
        System.out.println(maxCost);
    }

}

