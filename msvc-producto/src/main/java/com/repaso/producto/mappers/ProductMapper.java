package com.repaso.producto.mappers;
import com.repaso.producto.models.dto.ProductDto;
import com.repaso.producto.models.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    //esto en el servicio
    public Product pasarDeProductDto (ProductDto productDto){
        Product productE = new Product();
        BeanUtils.copyProperties(productDto,productE);
        return productE;
    }

    //esto en el controlador
    public ProductDto pasarDeProduct(Product productE){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productE,productDto);
        return productDto;
    }


}
