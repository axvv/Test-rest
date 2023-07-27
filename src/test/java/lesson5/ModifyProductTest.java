package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {
    static ProductService productService;
    int id = (int)(Math.random() * 4) + 1;
    Faker faker = new Faker();

    Product product = new Product()
            .withId(id)
            .withTitle(faker.food().ingredient())
            .withCategoryTitle("Food")
            .withPrice((int) (Math.random()*10000));


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void modifyProductTest() {
        Response<Product> response = productService.modifyProduct(product)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.body().getId(), equalTo(id));
    }
}
