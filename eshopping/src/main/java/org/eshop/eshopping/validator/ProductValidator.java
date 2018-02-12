package org.eshop.eshopping.validator;

import org.eshop.shoppingBackEnd.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	private static final Logger log = LoggerFactory.getLogger(ProductValidator.class);
	@Override
	public boolean supports(Class<?> productClass) {
		return Product.class.equals(productClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product)target;
		
		//if file has been selected
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file",null, "Image file is missing");
			return;
		}
		
		log.info("File type infor: " + product.getFile().getContentType());
		if(!(product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif"))) {
			
			errors.rejectValue("file", null,"Image file not supported");
			return;
		}
	}

}
