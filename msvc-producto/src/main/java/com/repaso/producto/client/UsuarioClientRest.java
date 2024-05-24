package com.repaso.producto.client;
import com.repaso.producto.models.UsuarioDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuario",url = "localhost:8001")
public interface UsuarioClientRest {


    @GetMapping("/{id}")
    UsuarioDto porId(@PathVariable Long id);
}
