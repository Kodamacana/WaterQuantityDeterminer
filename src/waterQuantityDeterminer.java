import java.util.Scanner;
import java.util.ArrayList;

public class waterQuantityDeterminer {
    public static void main(String[] args) {
        ArrayList<Integer> ColumnsLength = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Sütun Uzunluğunu Tuşlayınız:");

            for (int i = 0; i < 4; i++) {
                System.out.print("Sütun " + (i + 1) + ": ");
                ColumnsLength.add(scanner.nextInt());
            }
            scanner.close();

            System.out.println("Doldurulabilen Birim Su Miktarı : " + "\u001B[33m" + CreateColumns(ColumnsLength) + "\u001B[0m");

        } catch (Exception e) {
            System.out.println("Bir Hata Oluştu, Hata Mesajı: " + "\u001B[33m" + e + "\u001B[0m");
        }
    }

    static int CreateColumns(ArrayList<Integer> Columns) {
        int water = 0, toplam = 0;
        boolean isFinished = true;

        while (isFinished) {
            boolean value1 = true, value2 = true;

            if(Columns.size() <= 1) break;

            int ReturningValue = FindNumber(Columns.get(Columns.size() - 1), Columns.get(Columns.size() - 2)) ;
            if (ReturningValue != 0) {
                Columns.remove(Columns.size() - 1);
                value1 = false;
            }

            if(Columns.size() <= 1) break;

            ReturningValue = FindNumber(Columns.get(0), Columns.get(1));
            if (ReturningValue != 0) {
                Columns.remove(0);
                value2 = false;
            }

            if (value1 && value2) {
                isFinished = false;
                int minValue = Math.min(Columns.get(0), Columns.get(Columns.size() - 1));

                for (int i = 1; i < Columns.size()-1 ; i++) {
                    if(Columns.get(i) < minValue) toplam += minValue - Columns.get(i);
                }
            }
        }
        water = toplam;
        return water;
    }

    static int FindNumber(int a, int b){
        if (a < b) return b;
        else return 0;
    }
}