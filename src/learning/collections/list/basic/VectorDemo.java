package learning.collections.list.basic;

import java.util.Vector;

public class VectorDemo {

    public static void main(String[] args) {

        // Tạo danh sách kiểu Vector để lưu trữ các số nguyên có kiểu là Integer
        Vector<Integer> numbers = new Vector<>();

        // Thêm các phần tử số nguyên vào danh sách
        numbers.add(9);
        numbers.add(3);
        numbers.add(1);
        numbers.add(6);

        // Duyệt danh sách và xuất ra màn hình giá trị của các phần tử số nguyên
        // có trong danh sách
        int itemIndex = 0;
        for (Integer value: numbers) {
            System.out.println("[" + itemIndex + "]: " + value);
            itemIndex++;
        }

        // Xuất ra màn hình số phần tử có trong danh sách
        System.out.println("Number of items: " + numbers.size());
    }
}
