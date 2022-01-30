import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagementTest {
    private ProductManagement productManagement;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Should Print Before All Tests");}

    @BeforeEach
    public void initialize() {
        System.out.println("Instantiating Product Management");
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

    @Test
    @DisplayName("Should Not Create Product When UPCCode is Null")
    public void shouldNotCreateProductWhenUPCCodeIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            productManagement.createProduct("Netgear", null, 49.9);
        });
    }

    @Test
    @DisplayName("Should Not Create Product When Weight is Null")
    public void shouldNotCreateProductWhenWeightIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            productManagement.createProduct("Netgear", "123456789012", null);
        });
    }

    @Test
    @DisplayName("Should Create Product on MAC O.S.")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Should Run only on MAC")
    public void shouldCreateProductOnMAC() {
        productManagement.createProduct("Netgear", "123456789012", 49.9);
        assertFalse(productManagement.getAllProducts().isEmpty());
        assertEquals(1, productManagement.getAllProducts().size());
    }

    @Test
    @DisplayName("Test Product Creation on Developer Machine")
    public void shouldTestProductCreationOnDEV() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        productManagement.createProduct("Netgear", "123456789012", 49.9);
        assertFalse(productManagement.getAllProducts().isEmpty());
        assertEquals(1, productManagement.getAllProducts().size());
    }

    @Nested
    class RepeatedTests {
        @DisplayName("Repeat Product Creation Test 5 Times")
        @RepeatedTest(value = 5,
                name = "Repeating Product Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestProductCreationRepeatedly() {
            productManagement.createProduct("Netgear", "123456789012", 49.9);
            assertFalse(productManagement.getAllProducts().isEmpty());
            assertEquals(1, productManagement.getAllProducts().size());
        }
    }

    @Nested
    class ParameterizedTests {
        @DisplayName("Value Source Case - UPCCode should match the required Format")
        @ParameterizedTest
        @ValueSource(strings = {"123456789012", "123456789034", "123456789056"})
        public void shouldTestUPCCodeFormatUsingValueSource(String UPCCode) {
            productManagement.createProduct("Netgear", UPCCode, 49.9);
            assertFalse(productManagement.getAllProducts().isEmpty());
            assertEquals(1, productManagement.getAllProducts().size());
        }

        @DisplayName("CSV Source Case - UPCCode should match the required Format")
        @ParameterizedTest
        @CsvSource({"123456789012", "123456789034", "123456789056"})
        public void shouldTestUPCCodeFormatUsingCSVSource(String UPCCode) {
            productManagement.createProduct("Netgear", UPCCode, 49.9);
            assertFalse(productManagement.getAllProducts().isEmpty());
            assertEquals(1, productManagement.getAllProducts().size());
        }

        @DisplayName("CSV File Source Case - UPCCode should match the required Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestUPCCodeFormatUsingCSVFileSource(String UPCCode) {
            productManagement.createProduct("Netgear", UPCCode, 49.9);
            assertFalse(productManagement.getAllProducts().isEmpty());
            assertEquals(1, productManagement.getAllProducts().size());
        }
    }

    @DisplayName("Method Source Case - UPCCode should match the required Format")
    @ParameterizedTest
    @MethodSource("UPCCodeList")
    public void shouldTestUPCCodeFormatUsingMethodSource(String UPCCode) {
        productManagement.createProduct("Netgear", UPCCode, 49.9);
        assertFalse(productManagement.getAllProducts().isEmpty());
        assertEquals(1, productManagement.getAllProducts().size());
    }

    private static List<String> UPCCodeList() {
        return Arrays.asList("123456789012", "123456789034", "123456789056");
    }

    @Test
    @DisplayName("Should Create Oversize Product")
    public void shouldCreateOversizeProduct() {
        productManagement.createProduct ("Netgear", "123456789012", 60.9);
        assertTrue(productManagement.validProductList.get(0).isOversized());
    }

    @Test
    @DisplayName("Should Not Create Product with Wrong UPCCode Format-Length")
    public void shouldNotCreateWrongUPCCodeWithWrongLength() {
        productManagement.createProduct ("Netgear", "1234567890", 60.9);
        assertEquals(0, productManagement.getAllProducts().size());
    }

    @Test
    @DisplayName("Should Not Create Product with Wrong UPCCode Format-Alpha Character")
    public void shouldNotCreateWrongUPCCodeWithAlphaCharacter() {
        productManagement.createProduct ("Netgear", "1234567890AB", 60.9);
        assertEquals(0, productManagement.getAllProducts().size());
    }
}
