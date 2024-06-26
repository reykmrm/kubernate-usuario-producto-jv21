package com.repaso.producto.service;
import com.repaso.producto.client.UsuarioClientRest;
import com.repaso.producto.mappers.ProductMapper;
import com.repaso.producto.models.Categoria;
import com.repaso.producto.models.UsuarioDto;
import com.repaso.producto.models.dto.ProductDto;
import com.repaso.producto.models.entity.Product;
import com.repaso.producto.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    @Override
    @Transactional
    public void guardarProducto(ProductDto productDto, Long idUsuario ) {
        Boolean codigo = validarExistenciaCodigoProducto(productDto.getCodigo());
        if(codigo){
            throw new IllegalArgumentException("El codigo de producto: " +productDto.getCodigo()+" YA EXISTE!!");
        }

        //al ser un registro unico si podria usar static para guardar algun valor del usuario que inicia sesion
        Optional<UsuarioDto> o = Optional.ofNullable(usuarioClientRest.porId(idUsuario));
        if(o.isPresent()){
            productDto.setIdUsuario(o.get().getId());
        }

        if (!productDto.getCategoria().toString().equalsIgnoreCase(Categoria.ALIMENTOS.toString())
                && productDto.getFechaVencimiento() == null) {
            productDto.setFechaVencimiento(LocalDate.now());
        }else if(productDto.getFechaVencimiento()==null){
            throw new IllegalArgumentException("Los productos de la categoria alimentos deben llevar fecha");
        }

        if(validarFecha(productDto)){
            productRepository.
                    save(productMapper.
                            pasarDeProductDto(productDto));
        }else{
            throw new IllegalArgumentException("La fecha ingresada debe de ser" +
                    " superior a la fecha actual, Hoy:" + LocalDate.now());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> porId(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> obtenerTodos() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarProducto(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product actualizarProducto(ProductDto productDto) {
        return productRepository.save(productMapper.pasarDeProductDto(productDto));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean validarExistenciaCodigoProducto(String codigo){
        return productRepository.existsByCodigo(codigo);
    }

    /**
     * No se puede poner una fecha antes
     * de la fecha actual, en el modificar y crear.
     * @param productDto
     * @return Boolean
     */
    public Boolean validarFecha(ProductDto productDto){
        LocalDate hoy = LocalDate.now(); ///hoy
        LocalDate fechaProduct = productDto.getFechaVencimiento(); //fecha 'x'
        int dias = (int) ChronoUnit.DAYS.between(hoy,fechaProduct); //dias entre fechas
        System.out.println();
        return dias >= 0;//true
    }
}
