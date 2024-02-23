package resources;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LeftMenuButton extends JButton implements Serializable {
	private static final long serialVersionUID = 1L;
	private Color pressedColor = new Color(70, 122, 120);
    private Color rolloverColor = new Color(139, 189, 187);
    private Color baseColor = new Color(96, 164, 161);

    public LeftMenuButton () {
        super();
        
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);
        
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);

        setBackground(baseColor);
        setForeground(Color.WHITE);
        setFont((new Font("Segoe UI", Font.BOLD, 15)));

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                } else {
                    setBackground(baseColor);
                }
            }
        });
    }
    
    public LeftMenuButton (String text, boolean currentFrame) {
        this();
        
        /* si el valor pasado por parámetro es true, el botón corresponde al frame en el que nos encontramos,
        por lo que el color base será igual al de presionado para diferenciarlo del resto */
        if (currentFrame) {
        	baseColor = pressedColor;
        }
        
        setBackground(baseColor);
        setText(text);
    }
}
