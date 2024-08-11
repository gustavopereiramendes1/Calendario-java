package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class app extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Data dataAtual;
	Data data;
	JLabel labelTitulo;
	JLabel labelSemana;
	JLabel label[];
    public app(Data data) {
    	super("Calendário");
    	this.data = data;
    	this.dataAtual = data;
    	
    	
    	JPanel painelDaJanela = new JPanel() {
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
    	
    	setLayout(null);
    	
    	labelTitulo = new JLabel(String.format("%s %d" , data.mes.nomeMes, data.ano), SwingConstants.CENTER); //Label com Titulo contendo Mês e Ano.
    	labelTitulo.setBackground(Color.LIGHT_GRAY);
    	labelTitulo.setOpaque(true);
    	//label[nlabel].setBounds(x, y, dayWidth, dayHeight);
        labelTitulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelTitulo.setBounds(350, 20, 100, 30);
    	add(labelTitulo);
    	
    	//Tabela de Dias da Semana
    	
    	int startX = 50;  // posição X inicial
        int startY = 75;  // posição Y inicial
        int dayWidth = 75; // largura de cada "dia"
        int dayHeight = 30; // altura de cada "dia"
        int margin = 30; // margem entre os dias
    	
        for (int i = 0; i < 7; i++) {
        	labelSemana = new JLabel(String.format("%s", data.mes.diasSemana[i]), SwingConstants.CENTER);
        	labelSemana.setBackground(Color.LIGHT_GRAY);
        	labelSemana.setOpaque(true);
            add(labelSemana);

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
    	//mesAnterior.setIcon();
    	add(mesAnterior);
    	mesAnterior.setBounds(30, 20, 30, 30);
    	
    	mesAnterior.addActionListener(this::acaoBotaoMesAnterior);
    	
    	
    	
    	//Botão para Ir para mês seguinte
    	
    	Image iconMesSeguinte = new ImageIcon(this.getClass().getResource("/iconMesSeguinte.png")).getImage();
    	Image IconMesSeguinteScalada = iconMesSeguinte.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // Redimensionar a imagem
    	BotaoRedondo mesSeguinte = new BotaoRedondo(new ImageIcon(IconMesSeguinteScalada));
    	add(mesSeguinte);
    	mesSeguinte.setBounds(730, 20, 30, 30);
    	
        mesSeguinte.addActionListener(this::acaoBotaoMesSeguinte);
    	
    	
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajusta o tamanho da janela aos componentes
        setVisible(true);
        setSize(800, 500);
        setResizable(false);  //Limita o tamanho da janela, não permitindo maximização.
        setLocationRelativeTo(null); //Janela aparece no meio da tela.
        
        
    }

	
	public void acaoBotaoMesAnterior(ActionEvent e) {
		app.this.atualizarCalendario(1);
		
	}
	
	
	public void acaoBotaoMesSeguinte(ActionEvent e) {
		app.this.atualizarCalendario(2);
	}
    
    public void atualizarCalendario(int op) {
    	Mes mes;
    	Data dataNova;
    	if(op == 1 && data.mes.mes > 1) {
    		mes = new Mes(data.mes.mes-1, data.ano);
    		dataNova = new Data(data.dia, mes, data.ano);
    		
    		
    		
    		
    	}else if(op == 1 && data.mes.mes <= 1){
    		data.ano = data.ano-1;
    		mes = new Mes(12, data.ano);
    		dataNova = new Data(data.dia, mes, data.ano);
    	}else if(op == 2 && data.mes.mes < 12){
    		mes = new Mes(data.mes.mes+1, data.ano);
    		dataNova = new Data(data.dia, mes, data.ano);
    	}else {
    		data.ano = data.ano+1;
    		mes = new Mes(1, data.ano);
    		dataNova = new Data(data.dia, mes, data.ano);
    	}
    
    	this.data  = dataNova;
    	labelTitulo.setText(String.format("%s %d" , data.mes.nomeMes, data.ano));
    	atualizarLayoutDias(data);
    }
    
    public void layoutDias(Data data) {
    	int startX = 60;  // posição X inicial
        int startY = 150;  // posição Y inicial
        int dayWidth = 50; // largura de cada "dia"
        int dayHeight = 30; // altura de cada "dia"
        int margin = 55; // margem entre os dias
        label = new JLabel[data.mes.qtdDias];
        for (int i = data.mes.primeirodiaDaSemana, dia = 1, nlabel = 0; dia <= data.mes.qtdDias; i++, dia++, nlabel++) {
    		
    		
    		label[nlabel] = new JLabel(String.format("%d", dia), SwingConstants.CENTER);
    		if(nlabel+1 == dataAtual.dia && data.mes.mes == dataAtual.mes.mes && data.ano == dataAtual.ano) {
    			label[nlabel].setBackground(Color.red);
    			label[nlabel].setForeground(Color.white);
    		}
    		else label[nlabel].setBackground(Color.LIGHT_GRAY);
    		label[nlabel].setOpaque(true);
            add(label[nlabel]);
            
            // Cálculo da posição de cada label
            int x = startX + (i % 7) * (dayWidth + margin); // nova coluna a cada 7 dias
            int y = startY + (i / 7) * (dayHeight + margin-25); // nova linha a cada semana
            label[nlabel].setBounds(x, y, dayWidth, dayHeight);
            label[nlabel].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // borda ao redor dos dias do mês
            //dia++;
        }
    }
    
    public void atualizarLayoutDias(Data data) {
    	for(int i = 0; i < label.length; i++){
    		remove(label[i]);
    	}
    	revalidate();
		repaint();
		layoutDias(data);
		
    }
    
    

}


