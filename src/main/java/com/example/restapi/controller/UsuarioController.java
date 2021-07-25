package com.example.restapi.controller;

import com.example.restapi.model.Usuario;
import com.example.restapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Usuario> init(@PathVariable (value = "id") Long id) {

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(ObjectUtils.isEmpty(usuario))
        {
            return new ResponseEntity("Não foi encontrado nenhum usuário com a id" + id,HttpStatus.OK);
        }
        return  new ResponseEntity(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces ="application/json")
    public ResponseEntity<List<Usuario>> init() {

        List<Usuario> usuario = (List<Usuario>) usuarioRepository.findAll();

        if(ObjectUtils.isEmpty(usuario))
        {
            return new ResponseEntity("Não foi encontrado nenhum usuario para montagem da lista",HttpStatus.OK);
        }
        return  new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody  Usuario usuario ){

        for(int pos =0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        Usuario usuarioParaSalvar = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioParaSalvar, HttpStatus.OK);

    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody  Usuario usuario ){

        for(int pos =0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        Usuario usuarioParaSalvar = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioParaSalvar, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}", produces ="application/json")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable (value = "id") Long id) {

        usuarioRepository.deleteById(id);
        return  new ResponseEntity("Registro deletado com sucesso", HttpStatus.OK);
    }
}
