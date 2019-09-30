package OneDimensional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

/**
 * Author : AMIT KUMAR GUPTA
 * e-mail : amitguptapc@gmail.com
 * Date : 30/09/19
 * Time : 7:12 PM
 * Problem Code : MaxSubarraySum
 * Platform : NA
 */

public class MaxSubarraySum {

    private static long MOD = 1000000007;

    // begin of solution

    // Space Optimized DP solution
    // Kadane's Algorithm
    private static int maxSum2(int[] a, int n) {
        int maxEndingHere = 0, maxSoFar = 0;
        for (int i = 0; i < n; i++) {
            maxEndingHere += a[i];
            if (maxEndingHere < 0)
                maxEndingHere = 0;
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // DP solution
    private static int maxSum1(int[] a, int n) {
        int[] dp = new int[n];
        dp[0] = Math.max(a[0], 0);
        int max = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + a[i];
            if (dp[i] < 0)
                dp[i] = 0;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        int n = sc.si();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.si();
        pr.pl(maxSum2(a, n));
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