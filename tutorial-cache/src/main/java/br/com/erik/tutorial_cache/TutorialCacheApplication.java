package br.com.erik.tutorial_cache;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class TutorialCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialCacheApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(ProductService productService) {
		return args -> {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Id: 1: " + productService.getProductById(1L));
			System.out.println("Id: 2: " + productService.getProductById(2L));
			System.out.println("Id: 1: " + productService.getProductById(1L));
			System.out.println("Id: 1: " + productService.getProductById(1L));
			System.out.println("Id: 1: " + productService.getProductById(1L));
			System.out.println("Id: 5: " + productService.getProductById(5L));
		};
	}
}

record Product(Long id, String name, String description) implements Serializable {
}

@Service
class ProductService {
	Map<Long, Product> products = new HashMap<>() {
		{
			put(1L, new Product(1L, "Notebook", "Macbook Pro"));
			put(2L, new Product(2L, "Notebook", "XSP"));
			put(3L, new Product(3L, "Notebook", "Alienware"));
			put(4L, new Product(4L, "Notebook", "Thinkpad"));
			put(5L, new Product(5L, "Notebook", "Zenbook"));
		}
	};

	// Using Redis
	// Faz o cache em memory
	@Cacheable("product")
	public Product getProductById(Long id) {
		System.out.println("Searching product...");
		simulateLatency();
		return products.get(id);
	}

	private void simulateLatency() {
		try {
			long time = 1000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
