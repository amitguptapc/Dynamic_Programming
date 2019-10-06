package DigitDP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

/**
 * Author : AMIT KUMAR GUPTA
 * e-mail : amitguptapc@gmail.com
 * Date : 23/09/19
 * Time : 11:08 PM
 * Problem Code : dMagicNo
 * Platform : Codeforces
 */

public class dMagicNo {

    private static long MOD = 1000000007;

    // begin of solution
    private static long[][][][][] dp;
    private static String s;
    private static int m, d, size;

    private static void reset(int a, int z) {
        for (int i = 0; i < a; i++)
            for (int j = 0; j < z; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        for (int m = 0; m < 2; m++)
                            dp[i][j][k][l][m] = -1;
    }

    private static long solve(int pos, int mod, int tight, int st, int even) {
        if (pos == size)
            return mod == 0 ? 1 : 0;
        if (dp[pos][mod][tight][st][even] != -1)
            return dp[pos][mod][tight][st][even];
        long ans = 0;
        int end = tight == 1 ? s.charAt(pos) - '0' : 9;
        if (st == 0) {
            ans = (ans + solve(pos + 1, mod, tight & (s.charAt(pos) == '0' ? 1 : 0), st, 0)) % MOD;
            for (int i = 1; i <= end; i++)
                if (i != d)
                    ans = (ans + solve(pos + 1, (mod * 10 + i) % m, tight & (i == end ? 1 : 0), 1, 1)) % MOD;
        } else {
            for (int i = 0; i <= end; i++) {
                if (even == 1 && i == d)
                    ans = (ans + solve(pos + 1, (mod * 10 + i) % m, tight & (i == end ? 1 : 0), 1, 0)) % MOD;
                else if (even == 0 && i != d)
                    ans = (ans + solve(pos + 1, (mod * 10 + i) % m, tight & (i == end ? 1 : 0), 1, 1)) % MOD;
            }
        }
        return dp[pos][mod][tight][st][even] = ans;
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        m = sc.si();
        d = sc.si();
        String a = sc.s();
        String b = sc.s();
        int as = a.length();
        int bs = b.length();
        dp = new long[bs][m][2][2][2];
        s = b;
        size = bs;
        reset(bs, m);
        long ans = solve(0, 0, 1, 0, 0);
        s = a;
        size = as;
        dp = new long[as][m][2][2][2];
        reset(as, m);
        ans = (ans - solve(0, 0, 1, 0, 0) + MOD) % MOD;
        long ab = 0;
        boolean flag = false;
        for (int i = 0; i < as; i++) {
            if (i % 2 == 1 && (a.charAt(i) - '0' != d))
                flag = true;
            if (i % 2 == 0 && (a.charAt(i) - '0' == d))
                flag = true;
            ab = (ab * 10 + (a.charAt(i) - '0')) % m;
        }
        if (ab == 0 && !flag)
            ans = (ans + 1) % MOD;
        pr.pl(ans);
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