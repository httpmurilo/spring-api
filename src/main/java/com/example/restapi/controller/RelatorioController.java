package com.example.restapi.controller;

import com.example.restapi.model.Usuario;
import com.example.restapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/{id}/relatorio", produces ="application/pdf")
    public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(ObjectUtils.isEmpty(usuario))
        {
            return new ResponseEntity("Não foi encontrado nenhum relatorio com a id" + id, HttpStatus.OK);
        }
        return  new ResponseEntity(usuario.get(), HttpStatus.OK);
    }

}
