package resources;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 * Clase que corresponde al JButton personalizado utilizado a lo largo de la aplicación.
 */
public class MainButton extends JButton {
	private Color pressedColor = new Color(70, 122, 120);
    private Color baseColor = new Color(96, 164, 161);
    private Color disabledColor = new Color(98, 105, 104);

    /**
     * Constructor de la clase MainButton.
     * Inicializa y configura el JButton personalizado.
     * @param text texto que muestra el botón
     */
    public MainButton (String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setContentAreaFilled(false);
        setOpaque(true);
        
        setHorizontalTextPosition(SwingConstants.CENTER);

        setBackground(baseColor);
        setForeground(Color.WHITE);
        setFont((new Font("Segoe UI", Font.BOLD, 20)));
        setText(text);

        // cambia el color de fondo en función de si está siendo presionado o no
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else {
                    setBackground(baseColor);
                }
            }
        });
        
        // cambia el color del texto del botón cuando este está desactivado
        setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return disabledColor;
            }
        });
    }
}
