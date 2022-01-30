import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductManagement {

    public List<Product> validProductList = new ArrayList<>();

    public void createProduct(String productName, String UPCCode, double weight){
        Product product = new Product(productName, UPCCode, weight);
        validateProduct(product);
        addProductToList(product);
    }

    public void addProductToList(Product product){

        boolean result = UPCValidation(product);
        if (result)
            validProductList.add(product);
    }

    private void validateProduct(Product product) {
        product.validateProductName();
        product.validateUPCCode();
        product.validateWeight();
    }

    public List<Product> getAllProducts() {
        return validProductList.stream().toList();
    }


    private boolean UPCValidation(Product product){
        String UPCCode = product.getUPCCode();
        if(UPCCode.length()!=12){//UPC Code should have only 12 digit characters long
            return false;
        }

        if(!isNumeric(UPCCode)){//UPC Code contains only numeric characters
            return false;
        }
        return true;
    }

    public boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
