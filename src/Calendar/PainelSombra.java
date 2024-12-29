package Calendar;

import java.awt.*;

import javax.swing.JPanel;

public class PainelSombra extends JPanel {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        int shadowOffset = 5; // Deslocamento da sombra
	        Color shadowColor = new Color(0, 0, 0, 100); // Cor da sombra com transparência

	        // Dimensões do painel
	        int width = getWidth();
	        int height = getHeight();

	        // Desenha a sombra
	        g2d.setColor(shadowColor);
	        g2d.fillRoundRect(shadowOffset, shadowOffset, width - shadowOffset, height - shadowOffset, 15, 15);

	        // Desenha o painel principal
	        g2d.setColor(getBackground());
	        g2d.fillRoundRect(0, 0, width - shadowOffset, height - shadowOffset, 15, 15);

	        // Desenha a borda (opcional)
	        g2d.setColor(Color.GRAY);
	        g2d.drawRoundRect(0, 0, width - shadowOffset, height - shadowOffset, 15, 15);
	    }
}

