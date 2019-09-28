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
 * Time : 2:31 PM
 * Problem Code : WineSelling
 * Platform : NA
 */

public class WineSelling {
    // begin of solution

    // DP solution
    private static int maxProfit2(int[] prices, int n) {
        int[][] dp = new int[n][n];
        int y = n;
        for (int i = 0; i < n; i++) {
            int s = 0, e = i;
            while (e < n) {
                if (s == e)
                    dp[s][e] = prices[s] * y;
                else dp[s][e] = Math.max(prices[s] * y + dp[s + 1][e], prices[e] * y + dp[s][e - 1]);
                s++;
                e++;
            }
            y--;
        }
        return dp[0][n - 1];
    }

    //memoized solution
    private static int[][] memo;

    private static int maxProfit1(int[] prices, int y, int s, int e) {
        if (s > e)
            return 0;
        if (memo[s][e] != -1)
            return memo[s][e];
        int a = prices[s] * y + maxProfit1(prices, y + 1, s + 1, e);
        int b = prices[e] * y + maxProfit1(prices, y + 1, s, e - 1);
        return memo[s][e] = Math.max(a, b);
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        int y = sc.si();
        int[] prices = new int[y];
        for (int i = 0; i < y; i++)
            prices[i] = sc.si();
        memo = new int[y][y];
        for (int i = 0; i < y; i++)
            Arrays.fill(memo[i], -1);
        System.out.println(maxProfit2(prices, y));
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