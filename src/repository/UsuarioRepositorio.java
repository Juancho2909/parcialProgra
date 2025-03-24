package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import model.Usuario;

/**
 * Repositorio para gestionar usuarios.
 */
public class UsuarioRepositorio {
    private final List<Usuario> usuarios;

    /**
     * Constructor que inicializa el repositorio con un usuario por defecto.
     */
    public UsuarioRepositorio() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Juan", "Morales", "juan@example.com", "CC", 
                                 "1089599293", "MZ31CS33", "3166170699", false, "1234"));
    }

    /**
     * Agrega un nuevo usuario al repositorio.
     *
     * @param usuario El usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Modifica un usuario existente en el repositorio.
     *
     * @param id El ID del usuario a modificar.
     * @param usuarioModificado El usuario con los datos modificados.
     */
    public void modificarUsuario(int id, Usuario usuarioModificado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarioModificado.setId(id);  // Mantener el mismo ID
                usuarios.set(i, usuarioModificado);
                return;
            }
        }
    }

    /**
     * Obtiene la cantidad de usuarios en el repositorio.
     *
     * @return La cantidad de usuarios.
     */
    public int cantidadUsuarios() {
        return usuarios.size();
    }

    /**
     * Obtiene un usuario por su email.
     *
     * @param email El email del usuario a buscar.
     * @return El usuario encontrado o null si no se encuentra.
     */
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> Objects.equals(usuario.getEmail(), email)) // Evita NullPointerException
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return El usuario encontrado o null si no se encuentra.
     */
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene una lista inmodificable de todos los usuarios.
     *
     * @return La lista de usuarios.
     */
    public List<Usuario> obtenerUsuarios() {
        return Collections.unmodifiableList(usuarios); // Evita modificaciones externas
    }

    /**
     * Activa un usuario por su ID.
     *
     * @param id El ID del usuario a activar.
     * @return true si el usuario fue activado, false si no se encontró el usuario.
     */
    public boolean activarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setActivo(true);
                return true; // Retorna si se activó correctamente
            }
        }
        return false; // No se encontró el usuario
    }
}
