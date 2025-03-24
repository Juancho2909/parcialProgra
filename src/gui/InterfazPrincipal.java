package gui;
import java.awt.*;
import javax.swing.*;
import model.Usuario;
import repository.UsuarioRepositorio;
import service.UsuarioServicio;

/**
 * La clase InterfazPrincipal extiende JFrame y representa la ventana principal de la aplicación de la tienda UTP.
 * Permite a los usuarios ingresar sus credenciales para iniciar sesión o registrarse.
 */
public class InterfazPrincipal extends JFrame {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioServicio usuarioServicio;

    /**
     * Constructor de la clase InterfazPrincipal.
     * 
     * @param usuarioRepositorio el repositorio de usuarios utilizado para acceder a los datos de los usuarios.
     */
    public InterfazPrincipal(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioServicio = new UsuarioServicio(usuarioRepositorio);

        // Configuración de la ventana principal
        setTitle("Tienda UTP");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        // Etiqueta de bienvenida
        JLabel bienvenida = new JLabel("Bienvenido a la tienda UTP");
        add(bienvenida);
        add(new JLabel()); // Espacio vacío

        // Campo de correo electrónico
        JLabel correo = new JLabel("Correo: ");
        JTextField campoCorreo = new JTextField(20);
        add(correo);
        add(campoCorreo);

        // Campo de contraseña
        JLabel contrasena = new JLabel("Contraseña: ");
        JPasswordField campoPassword = new JPasswordField(20);
        add(contrasena);
        add(campoPassword);

        // Botón de registro
        JButton botonRegistro = new JButton("Registrarse");
        add(botonRegistro);
        JButton botonIngresar = new JButton("Ingresar");
        add(botonIngresar);
        
        // Acción del botón Registrarse
        botonRegistro.addActionListener(e -> {
            new InterfazRegistro(usuarioRepositorio).setVisible(true);
            this.dispose();
        });

        // Acción del botón Ingresar
        botonIngresar.addActionListener(e -> {
            String email = campoCorreo.getText();
            String password = new String(campoPassword.getPassword());

            Usuario usuario = usuarioRepositorio.obtenerUsuarioPorEmail(email);
            if (usuario == null || !usuario.getPassword().equals(password)) {  
                JOptionPane.showMessageDialog(this, "Usuario no encontrado o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                return;  
            }

            new InterfazIngreso(usuario.getId(),usuarioRepositorio).setVisible(true);
            this.dispose();
        });
    }

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazPrincipal(new UsuarioRepositorio()).setVisible(true));
    }
}
