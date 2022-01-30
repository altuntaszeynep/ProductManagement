public class Product {
    private String productName;
    private String UPCCode;
    private Double weight;
    private boolean oversized;

    public Product(String productName, String UPCCode, Double weight){
        this.productName = productName;
        this.UPCCode = UPCCode;
        this.weight = weight;
        if (weight>=50)
            this.setOversized(true);
        else
            this.setOversized(false);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUPCCode() {
        return UPCCode;
    }

    public void setUPCCode(String UPCCode) {
        this.UPCCode = UPCCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if (weight>=50)
            setOversized(true);
        else
            setOversized(false);

        this.weight = weight;
    }

    public boolean isOversized() {
        return oversized;
    }

    private void setOversized(boolean oversized) {
        this.oversized = oversized;
    }

    public void validateProductName() {
        if (this.productName.isEmpty())
            throw new RuntimeException("Product Name Cannot be null or empty");
    }

    public void validateUPCCode() {
        if (this.UPCCode.isEmpty())
            throw new RuntimeException("UPCCode Cannot be null or empty");
    }

    public void validateWeight() {
        if (this.weight == null) {
            throw new RuntimeException("Weight Cannot be null or empty");
        }
    }

}
