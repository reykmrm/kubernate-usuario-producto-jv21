package com.repaso.producto.controller;
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

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Product product, BindingResult result){
        if(result.hasErrors()){
            return validarErrores(result);
        }
        //falta validacion FECHA
        productServiceImp.crearP(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
    public ResponseEntity<?> actualizar(@RequestBody @Valid Product product, BindingResult result){

        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Product> o = productServiceImp.porId(product.getCodigo());
        if(o.isPresent()){
            Product p = o.get();
            p.setNombre(product.getNombre());
            p.setDescripcion(product.getDescripcion());
            p.setCategoria(product.getCategoria());
            p.setMarca(product.getMarca());
            p.setPeso(product.getPeso());
            p.setFechaVencimiento(product.getFechaVencimiento());
            productServiceImp.crearP(p);
            return ResponseEntity.
                    status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public void eliminar(int code){
        productServiceImp.eliminarP(code);
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
