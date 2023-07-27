package lesson5;


import lesson5.api.ProductService;

import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;


public class GetProductTest {
    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void getProductByIdPositiveTest() {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }
}
