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
 * Time : 8:35 PM
 * Problem Code : MaxProduct
 * Platform : Codeforces
 */

public class MaxProduct {

    private static long MOD = 1000000007;

    // begin of solution
    static class Pair {
        long prod;
        String str;

        Pair(long a, String b) {
            prod = a;
            str = b;
        }
    }

    private static String a, b;
    private static Pair[][][][] dp;

    private static Pair solve(int pos, int ta, int tb, int z) {
        if (pos == a.length())
            return new Pair(1, "");
        if (dp[pos][ta][tb][z].prod != -1)
            return dp[pos][ta][tb][z];
        int lb = ta == 1 ? a.charAt(pos) - '0' : 0;
        int ub = tb == 1 ? b.charAt(pos) - '0' : 9;
        Pair ans = new Pair(0, "");
        for (int i = lb; i <= ub; i++) {
            int p;
            if (z == 0 && i == 0)
                p = 1;
            else p = i;
            Pair temp = solve(pos + 1, ta & (i == lb ? 1 : 0), tb & (i == ub ? 1 : 0), z | (i > 0 ? 1 : 0));
            ans = (ans.prod > temp.prod * p) ? ans : new Pair(temp.prod * p, i + temp.str);
        }
        return dp[pos][ta][tb][z] = ans;
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        a = sc.s();
        b = sc.s();
        while (a.length() < b.length())
            a = '0' + a;
        dp = new Pair[20][2][2][2];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        dp[i][j][k][l] = new Pair(-1, "");
        pr.pl(Long.parseLong(solve(0, 1, 1, 0).str));
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