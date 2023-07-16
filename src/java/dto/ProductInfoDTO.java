package dto;

public class ProductInfoDTO {
    private int soldCount;
    private int remainingCount;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(int soldCount, int remainingCount) {
        this.soldCount = soldCount;
        this.remainingCount = remainingCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }
}
