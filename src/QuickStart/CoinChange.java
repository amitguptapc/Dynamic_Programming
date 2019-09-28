package QuickStart;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Author : AMIT KUMAR GUPTA
 * e-mail : amitguptapc@gmail.com
 * Date : 28/09/19
 * Time : 2:08 PM
 * Problem Code : CoinChange
 * Platform : NA
 */

public class CoinChange {

    private static long MOD = 1000000007;

    // begin of solution

    // DP solution
    private static int find2(int n, int p, int[] coins) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < p; j++) {
                if (i - coins[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[n];
    }

    // memoized approach
    private static int[] memo;

    private static int find1(int n, int p, int[] coins) {
        if (n == 0)
            return 0;
        if (memo[n] != -1)
            return memo[n];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < p; i++) {
            if (n - coins[i] >= 0)
                ans = Math.min(ans, find1(n - coins[i], p, coins) + 1);
        }
        return memo[n] = ans;
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        int n = sc.si();
        int p = sc.si();
        int[] coins = new int[p];
        for (int i = 0; i < p; i++)
            coins[i] = sc.si();
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println(find2(n, p, coins));
        pr.close();
    }
    // end of solution

    static class AmitScan {
        private byte[] buf = new byte[1024]; // Buffer of Bytes
        private int index;
        private InputStream in;
        private int total;

        AmitScan() {
            in = System.in;
        }

        private int scan() throws IOException // Scan method used to scan buf
        {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        String s() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        int si() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) // Removing starting whitespaces
                n = scan();
            int neg = 1;
            if (n == '-') // If Negative Sign encounters
            {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        long sl() throws IOException {
            long integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        private boolean isWhiteSpace(int n) {
            return n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1;
        }
    }

    static class AmitPrint {
        private final BufferedWriter bw;

        AmitPrint() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        private void p(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        void pl(Object object) throws IOException {
            p(object);
            bw.append("\n");
        }

        void close() throws IOException {
            bw.close();
        }

        void f() throws IOException {
            bw.flush();
        }
    }
}