package com.example;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Products;
import com.example.repository.ProductsRepository;

@SpringBootApplication
public class WebDongHoApplication implements CommandLineRunner{
	
	@Autowired
	private ProductsRepository productsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebDongHoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		productsRepository.findAll().forEach(e->{
//			String nameSlug = this.toSlug(e.getName());
//			productsRepository.setSlug(nameSlug, e.getId());
//
//		});
		
	}
	  public static String toSlug(String input) {
		  Pattern NONLATIN = Pattern.compile("[^\\w-]");
		  Pattern WHITESPACE = Pattern.compile("[\\s]");
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	  }

}
