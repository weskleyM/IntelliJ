package com.ufc.main.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String roleUsuario;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public String getRoleUsuario() {
        return roleUsuario;
    }

    public void setRoleUsuario(String roleUsuario) {
        this.roleUsuario = roleUsuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String getAuthority() {
        return this.roleUsuario;
    }
}
