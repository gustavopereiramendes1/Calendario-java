package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class app extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Calendario dataAtual;
	Calendario data;
	JLabel labelTitulo;
	JButton botaoTitulo;
	JLabel labelSemana;
	JLabel label[];
	JPanel painelDaJanela;
	JanelaAnoMes janelaAnoMes;
    public app(Calendario data) {
    	super("Calendário");
    	setResizable(false);
    	this.data = data;
    	this.dataAtual = data;
    	
    	
    	painelDaJanela = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public Image imageFundo = new ImageIcon(this.getClass().getResource("/fundo.png")).getImage();
    		
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			g.drawImage(imageFundo, 0, 0, getWidth(), getHeight(), this);

    		}
    	};
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	ImageIcon iconeDaJanela = new ImageIcon(this.getClass().getResource("/calendario.png"));
    	setIconImage(iconeDaJanela.getImage());
    	setContentPane(painelDaJanela);
    	painelDaJanela.setLayout(null);
    	
    	painelDaJanela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fecharJanelaAnoMes(e);
            }
        });
    	
    	
    	
    	
    	botaoTitulo = new JButton(String.format("%s %d" , data.nomeMes, data.anoNumero));
    	botaoTitulo.setBounds(350, 20, 100, 30);
    	botaoTitulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	getContentPane().add(botaoTitulo);
    	
    	botaoTitulo.addActionListener(this::abrirJanelaAnoMes);
    	
    	
    	//Tabela de Dias da Semana
    	
    	int startX = 50;  // posição X inicial
        int startY = 75;  // posição Y inicial
        int dayWidth = 75; // largura de cada "dia"
        int dayHeight = 30; // altura de cada "dia"
        int margin = 30; // margem entre os dias
    	
        for (int i = 0; i < 7; i++) {
        	labelSemana = new JLabel(String.format("%s", data.diasSemana[i]), SwingConstants.CENTER);
        	labelSemana.setBackground(Color.LIGHT_GRAY);
        	labelSemana.setOpaque(true);
            getContentPane().add(labelSemana);

            int x = startX + i * (dayWidth + margin); // nova coluna a cada dia
            labelSemana.setBounds(x, startY, dayWidth, dayHeight);
            labelSemana.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // borda ao redor dos dias da semana
        }
    	
    	
    	//Tabela de Dias do Mês
    	layoutDias(data);
    	
        //int dia = 1;
    	
        
    	
    	//Botão para Ir para mês anterior
    	
    	
    	Image iconMesAnterior = new ImageIcon(this.getClass().getResource("/iconMesAnterior.png")).getImage();
    	Image scaledImage = iconMesAnterior.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // Redimensionar a imagem
    	BotaoRedondo mesAnterior = new BotaoRedondo(new ImageIcon(scaledImage));
    	mesAnterior.setBounds(30, 20, 30, 30);
    	getContentPane().add(mesAnterior);
    	
    	mesAnterior.addActionListener(this::acaoBotaoMesAnterior);
    	
    	
    	
    	//Botão para Ir para mês seguinte
    	
    	Image iconMesSeguinte = new ImageIcon(this.getClass().getResource("/iconMesSeguinte.png")).getImage();
    	Image IconMesSeguinteScalada = iconMesSeguinte.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // Redimensionar a imagem
    	BotaoRedondo mesSeguinte = new BotaoRedondo(new ImageIcon(IconMesSeguinteScalada));
    	mesSeguinte.setBounds(730, 20, 30, 30);
    	getContentPane().add(mesSeguinte);
    	
        mesSeguinte.addActionListener(this::acaoBotaoMesSeguinte);
    	
        
        
    	
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajusta o tamanho da janela aos componentes
        setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null); //Janela aparece no meio da tela.
        
        
    }

    
    public void fecharJanelaAnoMes(MouseEvent e) {
        if (janelaAnoMes != null && janelaAnoMes.isVisible()) {
            if (e == null || !janelaAnoMes.getBounds().contains(SwingUtilities.convertPoint(painelDaJanela, e.getPoint(), janelaAnoMes))) {
                janelaAnoMes.dispose();
                atualizarLayoutDias(data);
            }
        }
    }
    	
    	
    	
    
    
    public void abrirJanelaAnoMes(ActionEvent e) {
        if (janelaAnoMes == null || !janelaAnoMes.isVisible()) {
            janelaAnoMes = new JanelaAnoMes(this, data);
            add(janelaAnoMes);
            janelaAnoMes.setVisible(true);
            
        }
    }
	
	
	public void acaoBotaoMesAnterior(ActionEvent e) {
		app.this.atualizarCalendario(1);
		
	}
	
	
	public void acaoBotaoMesSeguinte(ActionEvent e) {
		app.this.atualizarCalendario(2);
	}
    
    public void atualizarCalendario(int op) {
    	int mes;
    	Calendario dataNova;
    	if(op == 1 && data.mesNumero > 1) {
    		mes = data.mesNumero-1;
    		
    		
    	}else if(op == 1 && data.mesNumero <= 1){
    		data.anoNumero = data.anoNumero-1;
    		mes = 12;
    		
    	}else if(op == 2 && data.mesNumero < 12){
    		mes = data.mesNumero+1;
    		
    	}else {
    		data.anoNumero = data.anoNumero+1;
    		mes = 1;
    		
    	}
    	dataNova = new Calendario(data.diaAtual, mes, data.anoNumero);
    	this.data  = dataNova;
    	
    	atualizarLayoutDias(data);
    }
    
    public void layoutDias(Calendario data) {
    	int startX = 60;  // posição X inicial
        int startY = 150;  // posição Y inicial
        int dayWidth = 50; // largura de cada "dia"
        int dayHeight = 30; // altura de cada "dia"
        int margin = 55; // margem entre os dias
        label = new JLabel[data.qtdDias];
        for (int i = data.primeirodiaDaSemana, dia = 1, nlabel = 0; dia <= data.qtdDias; i++, dia++, nlabel++) {
    		
    		
    		label[nlabel] = new JLabel(String.format("%d", dia), SwingConstants.CENTER);
    		if(nlabel+1 == dataAtual.diaAtual && dataAtual.mesNumero == data.mesNumero && data.anoNumero == dataAtual.anoNumero) {
    			label[nlabel].setBackground(Color.red);
    			label[nlabel].setForeground(Color.white);
    		}
    		else label[nlabel].setBackground(Color.LIGHT_GRAY);
    		label[nlabel].setOpaque(true);
            getContentPane().add(label[nlabel]);
            
            // Cálculo da posição de cada label
            int x = startX + (i % 7) * (dayWidth + margin); // nova coluna a cada 7 dias
            int y = startY + (i / 7) * (dayHeight + margin-25); // nova linha a cada semana
            label[nlabel].setBounds(x, y, dayWidth, dayHeight);
            label[nlabel].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // borda ao redor dos dias do mês
            //dia++;
        }
    }
    
    public void atualizarLayoutDias(Calendario data) {
    	for(int i = 0; i < label.length; i++){
    		remove(label[i]);
    	}
    	revalidate();
		repaint();
		botaoTitulo.setText(String.format("%s %d" , data.nomeMes, data.anoNumero));
		layoutDias(data);
		
    }
    
    

}


