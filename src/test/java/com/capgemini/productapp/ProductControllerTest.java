package com.capgemini.productapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
		
	@Mock
	public ProductService service;
	
	@InjectMocks
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	Product product;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

	}
	
	@Test
	public void  testaddProduct() throws Exception {
		
		/*product.setProductId(123);
		product.setProductName("iphone");
		product.setProductCategory("phone");
		product.setProductPrice(50000);
*/
		when(service.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "iphone", "phone", 50000));
		
		mockMvc.perform(post("/product").
				contentType(MediaType.APPLICATION_JSON)
				.content("{\"productId\":\"123\",\"productName\":\"iphone\",\"productCategory\":\"phone\",\"productPrice\":\"50000\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists());
						
	}
	
@Test
public void testupdateProduct() throws Exception {
	
}

}


