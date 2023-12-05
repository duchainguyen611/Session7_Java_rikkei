package ra.entity;

import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private Boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, Boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int currentIndexProduct) {

        //Nhập mã sản phẩm
        boolean checkProductId = false;
        do {
            System.out.print("Nhập mã sản phẩm:");
            this.productId = scanner.nextLine();
            if (this.productId.length() != 4) {
                System.out.println("Mã sản phẩm phải gồm 4 ký tự!");
            } else {
                if (this.productId.charAt(0) != 'P') {
                    System.out.println("Mã sản phẩm bắt đầu là ký tự P");
                } else {
                    boolean checkDuplicateProductId = false;
                    for (int i = 0; i < currentIndexProduct; i++) {
                        if (Objects.equals(this.productId, arrProduct[i].getProductId())) {
                            checkDuplicateProductId = true;
                            System.out.println("Mã sản phẩm này bị trùng!");
                            break;
                        }
                    }
                    if (!checkDuplicateProductId) {
                        checkProductId = true;
                    }
                }
            }

        } while (!checkProductId);

        //Nhập tên sản phẩm
        boolean checkProductName = false;
        do {
            System.out.print("Nhập tên sản phẩm:");
            this.productName = scanner.nextLine();
            if (this.productName.length() < 6 || this.productName.length() > 50) {
                System.out.println("Tên sản phẩm phải có từ 6-50 ký tự");
            } else {
                boolean checkDuplicateProductName = false;
                for (int i = 0; i < currentIndexProduct; i++) {
                    if (Objects.equals(this.productName, arrProduct[i].getProductName())) {
                        checkDuplicateProductName = true;
                        System.out.println("Mã sản phẩm này bị trùng!");
                        break;
                    }
                }
                if (!checkDuplicateProductName) {
                    checkProductName = true;
                }
            }

        } while (!checkProductName);

        //Nhập giá nhập
        do {
            System.out.print("Nhập giá nhập:");
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice <= 0) {
                System.out.println("Giá nhập phải có giá trị > 0!");
            }
        } while (this.importPrice <= 0);

        //Nhập giá xuất
        do {
            System.out.print("Nhập giá xuất:");
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice < this.importPrice + this.importPrice * 0.2) {
                System.out.println(" Giá xuất phải có giá trị lơn hơn ít nhất 20% so với giá nhập!");
            }
        } while (this.exportPrice < this.importPrice + this.importPrice * 0.2);

        //Nhập số lượng sản phẩm
        do {
            System.out.print("Nhập số lượng sản phẩm:");
            this.quantity = Integer.parseInt(scanner.nextLine());
            if (this.quantity <= 0) {
                System.out.println("Số lượng sản phẩm phải có giá trị lớn hơn 0!");
            }
        } while (this.quantity <= 0);

        //Nhập mô tả sản phẩm
        System.out.print("Nhập mô tả sản phẩm:");
        this.descriptions = scanner.nextLine();

        //Nhập trạng thái sản phẩm
        do {
            System.out.print("Nhập trạng thái sản phẩm:");
            String status = scanner.nextLine();
            if (Objects.equals(status, "true") || Objects.equals(status, "false")) {
                this.status = Boolean.parseBoolean(status);
                break;
            } else {
                System.out.println("Trạng thái sản phẩm chỉ nhận 2 giá trị true | false!");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá nhập: %.0f - Giá xuất: %.0f\n" +
                        "Lợi nhuận: %.0f - Số lượng sản phẩm: %d - Mô tả sản phẩm: %s - Trạng thái: %s\n"
                , this.productId, this.productName, this.importPrice, this.exportPrice, this.profit, this.quantity
                , this.descriptions, this.status ? "Đang bán" : "Không bán");
    }

    public float calProfit(){
        this.profit = this.exportPrice - this.importPrice;
        return this.profit;
    }
}
