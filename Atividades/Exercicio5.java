public class Exercicio5 {
    public static void main(String[] args) {
        System.out.println("Calculando fatoriais com int:");
        for (int i = 1; i <= 20; i++) {
            int fatorialInt = 1;
            for (int j = 1; j <= i; j++) {
                fatorialInt *= j;
            }
            System.out.println("O fatorial de " + i + " (int) é: " + fatorialInt);
        }

        System.out.println("\nCalculando fatoriais com long:");
        for (int i = 1; i <= 40; i++) {
            long fatorialLong = 1L;
            for (int j = 1; j <= i; j++) {
                fatorialLong *= j;
            }
            System.out.println("O fatorial de " + i + " (long) é: " + fatorialLong);
        }

        System.out.println("\nExplicação:");
        System.out.println("O tipo 'int' em Java tem um limite máximo (Integer.MAX_VALUE, que é 2^31 - 1).");
        System.out.println("Quando o resultado de um fatorial excede esse limite, ocorre um 'overflow'.");
        System.out.println("Isso significa que o valor 'dá a volta' e começa a partir do limite negativo, resultando em números incorretos ou negativos.");
        System.out.println("O tipo 'long' tem um limite muito maior (Long.MAX_VALUE, que é 2^63 - 1), permitindo calcular fatoriais de números maiores sem overflow imediato.");
        System.out.println("No entanto, mesmo 'long' tem um limite, e para fatoriais de números ainda maiores (como 40!), o resultado pode eventualmente exceder Long.MAX_VALUE, também causando overflow, embora isso demore mais para acontecer do que com 'int'.");
    }
}