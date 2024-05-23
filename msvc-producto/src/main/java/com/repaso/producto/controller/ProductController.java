package com.repaso.producto.controller;
import com.repaso.producto.client.UsuarioClientRest;
import com.repaso.producto.models.dto.ProductDto;
import com.repaso.producto.models.entity.Product;
import com.repaso.producto.service.ProductServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    //tabla producto registro = empleado que lo registro
    /*
    En la tabla producto vamos a registrar quien es el usuario que registra el nuevo producto

    el defecto de esto seria que si cambian el id de la url,
     estarian ingresando a otra persona, toca hacer una verificacion,
     ¿esa verificacion se haria en otra parte, no en productos?
     */

    @PostMapping("/{id}")
    public ResponseEntity<?> guardarProducto(@RequestBody @Valid ProductDto productDto,
                                             BindingResult result,@RequestParam Long id){
        usuarioClientRest.porId(id);
        if(result.hasErrors()){
            return validarErrores(result);
        }
        try{
            productServiceImp.guardarProducto(productDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable int id){
        Optional<Product> o = productServiceImp.porId(id);
        if(o.isPresent()){
            Product p = o.get();
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Product> obtenerTodos(){
        return productServiceImp.obtenerTodos();
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody @Valid ProductDto productDto, BindingResult result){

        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Product> o = productServiceImp.porId(productDto.getId());
        if(o.isPresent()){
            Product p = o.get();
            p.setNombre(productDto.getNombre());
            p.setDescripcion(productDto.getDescripcion());
            p.setCategoria(productDto.getCategoria());
            p.setMarca(productDto.getMarca());
            p.setPeso(productDto.getPeso());
            p.setFechaVencimiento(productDto.getFechaVencimiento());
            productServiceImp.actualizarProducto(productDto);
            return ResponseEntity.
                    status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public void eliminar(int code){
        productServiceImp.eliminarProducto(code);
    }

    /**
     * Valida los errores de los JSON y retorna una respuesta
     * con el valor incorrecto
     * @param result
     * @return
     */
    private static ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errores.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }


}
