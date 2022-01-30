import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductManagementTest {
    private ProductManagement productManagement;

    @BeforeEach
    public void initialize() {
        System.out.println("Instantiating Contact Manager");
        productManagement = new ProductManagement();
    }

    @Test
    @DisplayName("Should Create One Product")
    public void shouldCreateOneProduct() {
        productManagement.createProduct ("Netgear", "123456789012", 49.9);
        assertFalse(productManagement.getAllProducts().isEmpty());
        assertEquals(1, productManagement.getAllProducts().size());
    }

    @Test
    @DisplayName("Should Create Two Product")
    public void shouldCreateTwoProduct() {
        productManagement.createProduct ("Netgear", "123456789012", 49.9);
        productManagement.createProduct ("Cisco", "123456789011", 50.0);
        assertFalse(productManagement.getAllProducts().isEmpty());
        assertEquals(2, productManagement.getAllProducts().size());
    }

    @Test
    @DisplayName("Should Not Create Product When Product Name is Null")
    public void shouldNotCreateProductWhenProductNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            productManagement.createProduct(null, "123456789012", 49.9);
        });
    }

}
