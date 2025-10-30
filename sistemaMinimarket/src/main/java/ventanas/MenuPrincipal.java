package ventanas;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar al iniciar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo gris claro
        getContentPane().setBackground(new Color(240, 240, 240));

        // Obtener dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 🔷 Barra superior (mensaje de atención)
        JLabel barraSuperior = new JLabel("ATENCIÓN: EL STOCK DE PRODUCTO X ES DE N", SwingConstants.CENTER);
        barraSuperior.setOpaque(true);
        barraSuperior.setBackground(new Color(220, 220, 220));
        barraSuperior.setBounds(50, 20, screenWidth - 100, 40);
        add(barraSuperior);

        // CALCULAR DIMENSIONES CON MEJORES MÁRGENES
        int marginLeft = 50;
        int marginTop = 80;
        int marginBottom = 150; // Aumentado para subir el panel inferior
        int gapBetweenPanels = 30; // Espacio entre paneles
        
        int panelWidth = (screenWidth - 2 * marginLeft - gapBetweenPanels);
        int leftPanelWidth = (int)(panelWidth * 0.62); // Ajustado
        int rightPanelWidth = (int)(panelWidth * 0.35); // Ajustado
        int panelHeight = screenHeight - marginTop - marginBottom;

        // Panel de productos (izquierda)
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(null);
        panelProductos.setBounds(marginLeft, marginTop, leftPanelWidth, panelHeight-50);
        panelProductos.setBackground(Color.WHITE);
        panelProductos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(panelProductos);

        // Campo de búsqueda
        JTextField txtBuscar = new JTextField();
        txtBuscar.setBounds(20, 20, panelProductos.getWidth() - 110, 35);
        panelProductos.add(txtBuscar);

        // Botón filtro convertido en menú de opciones
        JButton btnFiltro = new JButton("⚙️");
        btnFiltro.setBounds(panelProductos.getWidth() - 80, 20, 70, 35);
        panelProductos.add(btnFiltro);

        // Crear el menú emergente
        JPopupMenu menuOpciones = new JPopupMenu();

        JMenuItem itemVentas = new JMenuItem("Registro de Ventas");
        JMenuItem itemInventario = new JMenuItem("Hoja de Inventario");
        JMenuItem itemPedidos = new JMenuItem("Registro de Pedidos");

        menuOpciones.add(itemVentas);
        menuOpciones.add(itemInventario);
        menuOpciones.add(itemPedidos);

        // Mostrar el menú al hacer clic en el botón
        btnFiltro.addActionListener(e -> {
            menuOpciones.show(btnFiltro, 0, btnFiltro.getHeight());
        });

        // Acción al seleccionar "Registro de Ventas"
        itemVentas.addActionListener(e -> {
            new RegistroVentas().setVisible(true);
        });

        // Acción al seleccionar "Hoja de Inventario"
        itemInventario.addActionListener(e -> {
          //  new HojaInventario().setVisible(true);
        });

        // Acción al seleccionar "Registro de Pedidos"
        itemPedidos.addActionListener(e -> {
            new RegistroPedidos().setVisible(true);
        });

        // Panel de íconos de productos
        JPanel panelIconos = new JPanel();
        panelIconos.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelIconos.setBackground(new Color(250, 250, 250));
        JScrollPane scroll = new JScrollPane(panelIconos);
        scroll.setBounds(15, 70, panelProductos.getWidth() - 30, panelProductos.getHeight() - 90);
        panelProductos.add(scroll);

        // Añadimos algunos productos simulados (más productos para llenar el espacio)
        for (int i = 1; i <= 20; i++) {
            JButton producto = new JButton("📦 Producto " + i);
            producto.setPreferredSize(new Dimension(120, 100));
            panelIconos.add(producto);
        }

        // 🧾 Panel de lista de compras (derecha)
        JPanel panelLista = new JPanel();
        panelLista.setLayout(null);
        panelLista.setBounds(marginLeft + leftPanelWidth + gapBetweenPanels, marginTop, rightPanelWidth, panelHeight-50);
        panelLista.setBackground(Color.WHITE);
        panelLista.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(panelLista);

        // Ejemplo de líneas de texto con precios
        JTextArea listaTextos = new JTextArea();
        listaTextos.setText(generarListaProductos());
        listaTextos.setEditable(false);
        listaTextos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollLista = new JScrollPane(listaTextos);
        scrollLista.setBounds(15, 20, panelLista.getWidth() - 30, panelLista.getHeight() - 150); // Más espacio para botones
        panelLista.add(scrollLista);

        // Línea de total
        JLabel lblTotal = new JLabel("Total: " + ".".repeat(50) + " 155.75");
        lblTotal.setFont(new Font("Monospaced", Font.BOLD, 14));
        lblTotal.setBounds(15, panelLista.getHeight() - 120, panelLista.getWidth() - 30, 25);
        panelLista.add(lblTotal);

        // Botones X y ✓ con mejor posicionamiento
        JButton btnCancelar = new JButton("✖ Cancelar");
        btnCancelar.setBounds(20, panelLista.getHeight() - 80, 120, 40);
        panelLista.add(btnCancelar);

        JButton btnAceptar = new JButton("✔ Aceptar");
        btnAceptar.setBounds(panelLista.getWidth() - 140, panelLista.getHeight() - 80, 120, 40);
        panelLista.add(btnAceptar);

        // ⚙️ Panel inferior (3 botones circulares) - SUBIDO SIGNIFICATIVAMENTE
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 15)); // Centrado
        panelInferior.setBounds(marginLeft, screenHeight - 185, screenWidth - 3 * marginLeft, 100); // Subido a -110 (antes -90)
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(panelInferior);

        // 3 botones simulando iconos de acciones (más grandes)
        String[] iconos = {"Ventas", "Pedidos", "Inventario"};
        JButton[] botonesInferiores = new JButton[3];
        
        for (int i = 0; i < iconos.length; i++) {
            JButton boton = new JButton(iconos[i]);
            boton.setPreferredSize(new Dimension(160, 65)); // Mejor proporción
            boton.setFont(new Font("Arial", Font.BOLD, 14));
            panelInferior.add(boton);
            botonesInferiores[i] = boton;
        }

        // CONECTAR BOTONES INFERIORES CON SUS FUNCIONALIDADES
        // Botón Ventas - Muestra el registro de ventas
        botonesInferiores[0].addActionListener(e -> {
            new RegistroVentas().setVisible(true);
        });

        // Botón Pedidos - Muestra el registro de pedidos
        botonesInferiores[1].addActionListener(e -> {
            new RegistroPedidos().setVisible(true);
        });

        // Botón Inventario - Muestra la hoja de inventario
        botonesInferiores[2].addActionListener(e -> {
            // new HojaInventario().setVisible(true);
            JOptionPane.showMessageDialog(this, "Funcionalidad de Inventario en desarrollo", "Inventario", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // Método para generar una lista más larga de productos
    private String generarListaProductos() {
        StringBuilder sb = new StringBuilder();
        String[] productos = {
            "Producto A", "Producto B Premium", "Producto C Plus", 
            "Producto D Deluxe", "Producto E Standard", "Producto F Professional",
            "Producto G Ultimate", "Producto H Basic", "Producto I Advanced",
            "Producto J Enterprise", "Producto K Special", "Producto L Ultimate"
        };
        double[] precios = {12.50, 25.75, 18.00, 45.25, 8.50, 32.00, 60.00, 5.25, 28.50, 75.00, 42.00, 38.50};
        
        for (int i = 0; i < productos.length; i++) {
            sb.append(String.format("%2d. %-25s %7.2f%n", 
                i + 1, productos[i], precios[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}