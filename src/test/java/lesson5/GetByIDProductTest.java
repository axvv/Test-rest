package lesson5;

import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetByIDProductTest {
    static ProductService productService;
    int id = (int)(Math.random() * 4) + 1;
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    @SneakyThrows
    @Test
    void createProductInFoodCategoryTest()  {
        Response<Product> response = productService.getProductById(id)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.body().getPrice(), greaterThan(1));
        assertThat(response.body().getCategoryTitle(),notNullValue());
    }
}
