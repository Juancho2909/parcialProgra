package gui;

import java.awt.*;
import javax.swing.*;
import model.Usuario;
import repository.UsuarioRepositorio;
import service.UsuarioServicio;

/**
 * InterfazRegistro es una clase que extiende JFrame y representa la interfaz gráfica
 * para el registro de usuarios en la aplicación Tienda UTP.
 * 
 * Esta clase permite a los usuarios ingresar sus datos personales y registrarse en el sistema.
 * Los datos ingresados se almacenan en un repositorio de usuarios y se utilizan para crear
 * una nueva instancia de Usuario.
 * 
 * La interfaz incluye campos para ingresar nombre, apellido, correo, tipo de documento,
 * documento, dirección, teléfono y contraseña. También incluye botones para aceptar y cancelar
 * el registro.
 * 
 * Al hacer clic en el botón "Aceptar", se crea un nuevo usuario con los datos ingresados y se
 * registra en el sistema. Luego, se muestra la interfaz principal de la aplicación.
 * 
 * Al hacer clic en el botón "Cancelar", se cierra la ventana de registro y se muestra la
 * interfaz principal de la aplicación.
 * 
 * @param usuarioRepositorio El repositorio de usuarios utilizado para almacenar y recuperar
 *                           información de los usuarios.
 */
public class InterfazRegistro extends JFrame {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioServicio usuarioServicio;

    public InterfazRegistro(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioServicio = new UsuarioServicio(usuarioRepositorio); // Inyectamos la dependencia

        setTitle("Tienda UTP");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 20, 20));

        JLabel nombre = new JLabel("Nombre: ");
        JTextField campoNombre = new JTextField(20);
        add(nombre);
        add(campoNombre);

        JLabel apellido = new JLabel("Apellido: ");
        JTextField campoApellido = new JTextField(20);
        add(apellido);
        add(campoApellido);

        JLabel correo = new JLabel("Correo: ");
        JTextField campoCorreo = new JTextField(20);
        add(correo);
        add(campoCorreo);

        JLabel tipoDocumento = new JLabel("Tipo de Documento: ");
        JTextField campoTipoDocumento = new JTextField(20);
        add(tipoDocumento);
        add(campoTipoDocumento);

        JLabel documento = new JLabel("Documento: ");
        JTextField campoDocumento = new JTextField(20);
        add(documento);
        add(campoDocumento);

        JLabel direccion = new JLabel("Dirección: ");
        JTextField campoDireccion = new JTextField(20);
        add(direccion);
        add(campoDireccion);

        JLabel telefono = new JLabel("Teléfono: ");
        JTextField campoTelefono = new JTextField(20);
        add(telefono);
        add(campoTelefono);

        JLabel contrasena = new JLabel("Contraseña: ");
        JPasswordField campoPassword = new JPasswordField(20);
        add(contrasena);
        add(campoPassword);

        JButton botonRegistroAceptar = new JButton("Aceptar");
        add(botonRegistroAceptar);
        JButton botonCancelar = new JButton("Cancelar");
        add(botonCancelar);

        // Acción del botón Aceptar
        botonRegistroAceptar.addActionListener(e -> {
            Usuario usuario = new Usuario(
                usuarioRepositorio.cantidadUsuarios() + 1,
                campoNombre.getText(),
                campoApellido.getText(),
                campoCorreo.getText(),
                campoTipoDocumento.getText(),
                campoDocumento.getText(),
                campoDireccion.getText(),
                campoTelefono.getText(),
                true,
                new String(campoPassword.getPassword()) 
            );

            usuarioServicio.registrarUsuario(usuario);

            // Mostrar usuarios registrados en consola (solo para depuración)
            usuarioRepositorio.obtenerUsuarios().forEach(System.out::println);

            new InterfazPrincipal(usuarioRepositorio).setVisible(true);
            this.dispose(); // Cierra esta ventana de registro
        });

        // Acción del botón Cancelar
        botonCancelar.addActionListener(e -> {
            new InterfazPrincipal(usuarioRepositorio).setVisible(true);
            this.dispose();
        });
    }
}
