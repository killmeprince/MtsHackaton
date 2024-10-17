import java.util.Scanner;

public class MtsString {

    // none of this is truly working, so tech hack for me is too opp(
// const
    private final static int MOD = 998244353;
    private static long[] factorial;
    private static long[] inverseFactorial;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();
            if (sc.hasNext()) {
                String str = sc.next();
                sc.close();

                //длина строки меньше 3
                if (n < 3) {
                    System.out.println(0);
                    return;
                }

                //счет количества символов m, t и s
                int m = 0, t = 0, s = 0;
                for (char ch : str.toCharArray()) {
                    if (ch == 'm') {
                        m++;
                    } else if (ch == 't') {
                        t++;
                    } else if (ch == 's') {
                        s++;
                    }
                }

                // вввычисление p и q
                long p = (long) m * t * s % MOD;
                precomputeFactorials(n, MOD);
                long q = binomialCoefficient(n, 3, MOD);

                //обратный элемент для q по модулю
                long inversionQ = modInverse(q, MOD);
                long result = p * inversionQ % MOD;

                System.out.println(result);

            } else {
                System.out.println("Ошибка: строка не была предоставлена.");
            }
        } else {
            System.out.println("Ошибка: число не было предоставлено.");
        }
    }
    private static void precomputeFactorials(int n, int mod) {
        factorial = new long[n + 1];
        inverseFactorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % mod;
        }
        inverseFactorial[n] = modInverse(factorial[n], mod);
        for (int i = n - 1; i >= 0; i--) {
            inverseFactorial[i] = inverseFactorial[i + 1] * (i + 1) % mod;
        }
    }
    private static long binomialCoefficient(int n, int k, int mod) {
        if (k > n) return 0;
        return factorial[n] * inverseFactorial[k] % mod * inverseFactorial[n - k] % mod;
    }
// func to calc the opp module elem
    private static long modInverse(long e, int mod) {
        return power(e,mod - 2, mod);
    }
// math pow doesnot work here()()())
    private static long power(long base,long exp, int mod) {
        long result = 1;
        while(exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;

            }
            base = (base * base) % mod;
            exp /= 2;

        }
        return result;
    }
}
