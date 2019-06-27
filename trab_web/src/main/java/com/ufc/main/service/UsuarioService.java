package com.ufc.main.service;

import com.ufc.main.entities.Role;
import com.ufc.main.entities.Usuario;
import com.ufc.main.repositories.RoleRepository;
import com.ufc.main.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void save(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Role role = roleRepository.findByRoleUsuario("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }

    public void remove(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.getOne(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
