package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BotaoRedondo extends JButton {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotaoRedondo(ImageIcon icon) {
        super(icon);
        // Remove as bordas padrão do botão
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Habilita o antialiasing para bordas suaves
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o botão redondo
        if (getModel().isArmed()) {
            g2.setColor(Color.LIGHT_GRAY); // Cor quando o botão é pressionado
        } else {
            g2.setColor(getBackground()); // Cor normal do botão
        }
        g2.fillOval(0, 0, getWidth(), getHeight());

        // Desenha o ícone
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        // Verifica se o clique está dentro do círculo do botão
        Ellipse2D circle = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return circle.contains(x, y);
    }

    public static void main(String[] args) {
        // Carregar a imagem do ícone
        ImageIcon icon = new ImageIcon("Images/seta-para-a-esquerda.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        // Criar a janela
        JFrame frame = new JFrame("Botão Redondo");
        frame.setLayout(null);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criar o botão redondo
        BotaoRedondo botaoRedondo = new BotaoRedondo(icon);
        botaoRedondo.setBackground(Color.WHITE);
        botaoRedondo.setBounds(150, 150, 100, 100); // Define o tamanho e a posição do botão

        // Adicionar o botão à janela
        frame.add(botaoRedondo);
        frame.setVisible(true);
    }
}
