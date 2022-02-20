package com.example.service.impl;

import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.common.FileUtils;
import com.example.dto.ProductDTO;
import com.example.dto.ProductDetail;
import com.example.dto.ProductRequest;
import com.example.entity.Brand;
import com.example.entity.ChatLieuDay;
import com.example.entity.ChatLieuMat;
import com.example.entity.Products;
import com.example.repository.BrandRepository;
import com.example.repository.ChatLieuDayRepository;
import com.example.repository.ChatLieuMatRepository;
import com.example.repository.ProductsRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductsRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ChatLieuMatRepository chatLieuMatRepository;

	@Autowired
	private ChatLieuDayRepository chatLieuDayRepository;
	

	@Value("D://watch/product")
	private String location;

	@Override
	public Optional<ProductDTO> getAllProductDto(Long id) {
		
		Optional<Products> products = productRepository.findById(id);
		
		 if(products.isPresent()) { 
			 Optional<ProductDTO> dtos = Optional.of(new
		 ProductDTO(products.get().getName(), products.get().getPrice(),products.get().getImage(), products.get().getSlug(),
				 products.get().getQuantity(), products.get().getProductType(), products.get().getDescription(),
				 products.get().getGender(), products.get().getDoChiuNuoc(), products.get().getBrands().getId(),
				 products.get().getCLDay().getId(),products.get().getCLMat().getId())); 
			/* System.out.println(dtos.toString()); */
			 return dtos;
		 }else {
			 return null;
		 }
	}
	
	@Override
	@Transactional
	public void createProduct(HttpServletRequest httpServletRequest, ProductRequest request) throws IOException {
		Products product = new Products();
		Optional<Brand> brand = brandRepository.findById(request.getBrands());
		Optional<ChatLieuMat> clmat = chatLieuMatRepository.findById(request.getCLMat());
		Optional<ChatLieuDay> clday = chatLieuDayRepository.findById(request.getCLDay());
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());
		product.setProductType(request.getProductType());
		product.setDescription(request.getDescription());
		product.setDoChiuNuoc(request.getDoChiuNuoc());
		product.setGender(request.getGender());
		product.setSlug(toSlug(request.getName()));
		brand.ifPresent(b -> product.setBrands(b));
		clmat.ifPresent(m -> product.setCLMat(m));
		clday.ifPresent(d -> product.setCLDay(d));
		if (request.getImage() != null) {
			product.setImage(FileUtils.saveImg(request.getImage(), this.location, httpServletRequest));
		}
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void updateProduct(HttpServletRequest httpServletRequest, ProductRequest request) throws IOException {
		Optional<Products> productss = productRepository.findById(request.getId());
		Optional<Brand> brand = brandRepository.findById(request.getBrands());
		Optional<ChatLieuMat> clmat = chatLieuMatRepository.findById(request.getCLMat());
		Optional<ChatLieuDay> clday = chatLieuDayRepository.findById(request.getCLDay());
		productss.ifPresent(product->{
				product.setName(request.getName());
				product.setPrice(request.getPrice());
				product.setQuantity(request.getQuantity());
				product.setProductType(request.getProductType());
				product.setDescription(request.getDescription());
				product.setDoChiuNuoc(request.getDoChiuNuoc());
				product.setGender(request.getGender());
				product.setSlug(toSlug(request.getName()));
				brand.ifPresent(b -> product.setBrands(b));
				clmat.ifPresent(m -> product.setCLMat(m));
				clday.ifPresent(d -> product.setCLDay(d));
				if (request.getImage() != null) {
					try {
						product.setImage(FileUtils.saveImg(request.getImage(), this.location, httpServletRequest));
					} catch (IOException e) {
					}
				}
				productRepository.save(product);
		});

	}
	
	@Override
	public List<ProductDetail> getAllProductDetail() {
		List<Products> products = productRepository.findAll();
	
		return	products.stream().map(prod->ProductDetail.builder().id(prod.getId())
				.name(prod.getName())
				.price(prod.getPrice())
				.quantity(prod.getQuantity())
				.description(prod.getDescription())
				.doChiuNuoc(prod.getDoChiuNuoc())
				.gender(prod.getGender())
				.slug(prod.getSlug())
				.image(prod.getImage())
				.brands(prod.getBrands().getBrandName())
				.cLDay(prod.getCLDay().getLoaiDay())
				.cLMat(prod.getCLMat().getLoaiMatKinh())
				.productType(prod.getProductType())
				.img(prod.getProductImg())
				.build()
				).collect(Collectors.toList());		
	
	}

	@Override
	public void deleteProduct(Long id) {
		if(productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		}			
	}



	@Override
	public String saveFile(MultipartFile img, HttpServletRequest httpServletRequest) throws IOException {
	
		return	FileUtils.saveImg(img, this.location, httpServletRequest);	
	}

	@Override
	public ProductDetail getProdDetail(String slug) {
		System.out.println(slug);
		Products  products =  productRepository.getBySlug(slug);

		return ProductDetail.builder().id(products.getId())
								.name(products.getName())
								.cLDay(products.getCLDay().getLoaiDay())
								.brands(products.getBrands().getBrandName())
								.cLMat(products.getCLMat().getLoaiMatKinh())
								.doChiuNuoc(products.getDoChiuNuoc())
								.description(products.getDescription())
								.gender(products.getGender())
								.img(products.getProductImg())
								.productType(products.getProductType())
								.quantity(products.getQuantity())
								.price(products.getPrice()).build();

	
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
