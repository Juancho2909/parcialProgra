package gui;

import java.awt.*;
import javax.swing.*;
import model.Usuario;
import repository.UsuarioRepositorio;

/**
 * La clase InterfazIngreso representa una interfaz gráfica de usuario (GUI) para dar la bienvenida a un usuario
 * y permitirle cerrar sesión en una aplicación de tienda.
 * 
 * Extiende la clase JFrame para crear una ventana con componentes Swing.
 */
public class InterfazIngreso extends JFrame {
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Constructor de la clase InterfazIngreso.
     * 
     * @param idUsuario El ID del usuario que ha iniciado sesión.
     * @param usuarioRepositorio El repositorio de usuarios para obtener información del usuario.
     */
    public InterfazIngreso(int idUsuario, UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        
        // Configuración de la ventana
        setTitle("Tienda UTP");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 5, 5)); // 3 filas, 1 columna con espaciado

        // Obtención del usuario por ID
        Usuario usuario = usuarioRepositorio.obtenerUsuarioPorId(idUsuario);

        // Creación del mensaje de bienvenida
        JLabel bienvenida = new JLabel(usuario != null 
                ? "¡Bienvenido! " + usuario.getNombre() + " " + usuario.getApellido()
                : "¡Bienvenido! Usuario desconocido");
        
        bienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        add(bienvenida);

        // Creación del botón de cerrar sesión
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.addActionListener(e -> {
            new InterfazPrincipal(usuarioRepositorio).setVisible(true);
            this.setVisible(false);
        });

        // Panel para el botón de cerrar sesión
        JPanel panelBoton = new JPanel();
        panelBoton.add(botonCerrarSesion);
        add(panelBoton);
    }
}
