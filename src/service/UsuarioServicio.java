package service;

import model.Usuario;
import repository.UsuarioRepositorio;

/**
 * Clase UsuarioServicio que proporciona servicios relacionados con la gestión de usuarios.
 */
public class UsuarioServicio {
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Constructor que inicializa el servicio con un repositorio de usuarios.
     *
     * @param usuarioRepositorio El repositorio de usuarios que se utilizará para las operaciones.
     */
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Valida si un usuario existe en el repositorio y si la contraseña proporcionada es correcta.
     *
     * @param usuario El usuario a validar.
     * @return true si el usuario existe y la contraseña es correcta, false en caso contrario.
     */
    public Boolean validarUsuario(Usuario usuario) {
        if (usuario == null) {  
            return false;  // Evita NullPointerException
        }
        
        Usuario usuarioEncontrado = usuarioRepositorio.obtenerUsuarioPorEmail(usuario.getEmail());
        return usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(usuario.getPassword());
    }

    /**
     * Registra un nuevo usuario en el repositorio.
     *
     * @param usuario El usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario) {
        if (usuario != null) {  
            usuarioRepositorio.agregarUsuario(usuario);
        }
    }

    // Puedes agregar otros métodos que necesiten el repositorio
}
