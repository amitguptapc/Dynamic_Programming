package DigitDP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

/**
 * Author : AMIT KUMAR GUPTA
 * e-mail : amitguptapc@gmail.com
 * Date : 20/09/19
 * Time : 8:00 PM
 * Problem Code : ClassyNumbers
 * Platform : Codeforces
 */

public class ClassyNumbers {

    private static long MOD = 1000000007;

    // begin of solution
    private static long[][][] dp;
    private static String s;

    private static void reset() {
        for (int i = -0; i < 20; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = -1;
    }

    private static long solve(int pos, int count, int tight) {
        if (pos == s.length())
            return 1;
        if (dp[pos][count][tight] != -1)
            return dp[pos][count][tight];
        int end = (tight == 1) ? s.charAt(pos) - '0' : 9;
        long ans = 0;
        for (int i = 0; i <= end; i++) {
            int cnt = count + (i > 0 ? 1 : 0);
            if (cnt <= 3)
                ans += solve(pos + 1, cnt, tight & (i == end ? 1 : 0));
        }
        return dp[pos][count][tight] = ans;
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        int t = sc.si();
        while (t-- > 0) {
            long l = sc.sl();
            long r = sc.sl();
            dp = new long[20][4][2];
            l -= 1;
            s = r + "";
            reset();
            long ans = solve(0, 0, 1);
            s = l + "";
            reset();
            ans -= solve(0, 0, 1);
            pr.pl(ans);
        }
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