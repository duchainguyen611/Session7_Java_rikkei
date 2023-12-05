package ra.imp;

import ra.entity.Product;

import java.util.Objects;
import java.util.Scanner;

public class ProductImp {

    Product[] arrProduct = new Product[100];

    int currentIndexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductImp productImp = new ProductImp();
        do {
            System.out.println("\n***********************MENU**************************\n" +
                    "1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Tính lợi nhuận các sản phẩm\n" +
                    "4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần\n" +
                    "5. Thống kê các sản phẩm theo giá\n" +
                    "6. Tìm các sản phẩm theo tên sản phẩm\n" +
                    "7. Nhập sản phẩm\n" +
                    "8. Bán sản phẩm\n" +
                    "9. Cập nhật trạng thái sản phẩm\n" +
                    "10. Thoát");
            System.out.print("Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productImp.inputData(scanner);
                    break;
                case 2:
                    productImp.displayData();
                    break;
                case 3:
                    productImp.calProfit();
                    break;
                case 4:
                    productImp.sortProductByProfit();
                    break;
                case 5:
                    productImp.staticsProductByPrice(scanner);
                    break;
                case 6:
                    productImp.lookForProductByName(scanner);
                    break;
                case 7:
                    productImp.addProduct(scanner);
                    break;
                case 8:
                    productImp.sellProduct(scanner);
                    break;
                case 9:
                    productImp.updateStatusProduct(scanner);
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Mời nhập từ 1 đến 10!");
                    break;
            }
        } while (true);
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập số sản phẩm cần điền thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Sản phẩm thứ %d:\n", i + 1);
            arrProduct[currentIndexProduct] = new Product();
            arrProduct[currentIndexProduct].inputData(scanner, arrProduct, currentIndexProduct);
            currentIndexProduct++;
        }
    }

    public void displayData() {
        System.out.println("Hiển thị thông tin các sản phẩm:");
        for (int i = 0; i < currentIndexProduct; i++) {
            arrProduct[i].displayData();
        }
    }

    public void calProfit() {
        System.out.println("Tính lợi nhuận các sản phẩm:");
        for (int i = 0; i < currentIndexProduct; i++) {
            arrProduct[i].setProfit(arrProduct[i].calProfit());
        }
        System.out.println("Đã tính xong lợi nhuận! Ấn 2 để xem kết quả");
    }

    public void sortProductByProfit() {
        System.out.println("Sắp xếp các sản phẩm theo lợi nhuận giảm dần:");
        for (int i = 0; i < currentIndexProduct - 1; i++) {
            for (int j = i + 1; j < currentIndexProduct; j++) {
                if (arrProduct[i].getProfit() < arrProduct[j].getProfit()) {
                    Product temp = arrProduct[i];
                    arrProduct[i] = arrProduct[j];
                    arrProduct[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong! Ấn 2 để xem kết quả");
    }

    public void staticsProductByPrice(Scanner scanner) {
        System.out.println("Thống kê các sản phẩm theo giá:");
        System.out.print("Nhập giá đầu để thống kê:");
        float formPrice = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập giá cuối để thống kê:");
        float toPrice = Float.parseFloat(scanner.nextLine());

        System.out.printf("Các sản phẩm trong khoảng %.0f đến %.0f là:\n", formPrice, toPrice);
        int count = 0;
        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProduct[i].getExportPrice() >= formPrice && arrProduct[i].getExportPrice() <= toPrice) {
                count++;
                arrProduct[i].displayData();
            }
        }
        System.out.printf("Số lượng sản phẩm trong khoảng %.0f đến %.0f là: %d", formPrice, toPrice, count);
    }

    public void lookForProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm muốn tìm kiếm:");
        String name = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProduct[i].getProductName().contains(name)) {
                arrProduct[i].displayData();
            }
        }
    }

    public void addProduct(Scanner scanner) {
        boolean checkProductId = false;
        do {
            System.out.print("Nhập vào mã sản phẩm cần nhập sản phẩm:");
            String importProductId = scanner.nextLine();
            System.out.print("Nhập số lượng sản phẩm cần nhập sản phẩm:");
            int importQuantity = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndexProduct; i++) {
                if (Objects.equals(importProductId, arrProduct[i].getProductId())) {
                    checkProductId = true;
                    arrProduct[i].setQuantity(arrProduct[i].getQuantity() + importQuantity);
                    System.out.print("Nhập sản phẩm thành công! Ấn 2 để kiểm tra!");
                    break;
                }
            }
            if (!checkProductId) {
                System.out.println("Không có mã sản phầm này!");
            }
        } while (!checkProductId);
    }

    public void sellProduct(Scanner scanner) {
        boolean checkProductName = false;
        boolean checkQuantity = false;
        boolean checkStatus = false;
        do {
            System.out.print("Nhập vào tên sản phẩm cần bán sản phẩm:");
            String sellProductName = scanner.nextLine();
            System.out.print("Nhập vào số lượng sản phẩm cần bán sản phẩm:");
            int sellQuantity = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndexProduct; i++) {
                if (Objects.equals(sellProductName, arrProduct[i].getProductName())) {
                    checkProductName = true;
                    if (arrProduct[i].getStatus()) {
                        checkStatus = true;
                        if (arrProduct[i].getQuantity() >= sellQuantity) {
                            checkQuantity = true;
                            arrProduct[i].setQuantity(arrProduct[i].getQuantity() - sellQuantity);
                            System.out.print("Bán sản phẩm thành công! Ấn 2 để kiểm tra!");
                            break;
                        }
                    }
                }
            }
            if (!checkProductName) {
                System.out.println("Không có tên sản phầm này!");
            }else if (!checkStatus) {
                System.out.println("Sản phẩm đang trong trạng thái Không bán!");
            }else if (!checkQuantity) {
                System.out.println("Số lượng sản phẩm cần bán phải bé hơn hoặc bằng số lượng sản phẩm trong kho!");
            }
        } while (!checkProductName || !checkStatus || !checkQuantity);
    }

    public void updateStatusProduct(Scanner scanner) {
        boolean checkProductId = false;
        do {
            System.out.print("Nhập vào mã sản phẩm cần cập nhật trạng thái:");
            String updateProductId = scanner.nextLine();
            for (int i = 0; i < currentIndexProduct; i++) {
                if (Objects.equals(updateProductId, arrProduct[i].getProductId())) {
                    checkProductId = true;
                    if (arrProduct[i].getStatus()) {
                        arrProduct[i].setStatus(false);
                    } else {
                        arrProduct[i].setStatus(true);
                    }
                    System.out.print("Cập nhật thành công! Ấn 2 để kiểm tra!");
                    break;
                }
            }
            if (!checkProductId) {
                System.out.println("Không có mã sản phầm này!");
            }
        } while (!checkProductId);
    }
}
