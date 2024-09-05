package Calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.Month;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;

//private app mainApp;
public class JanelaAnoMes extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	Calendario data;
	boolean janela_ativa;
	JButton botaoMeses[];
	JLabel tituloAno;
	private app mainApp;
	
	public JanelaAnoMes(app mainApp, Calendario data) {
		//super("Mes - ano");
		this.data = data;
		this.mainApp = mainApp;
		putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    setBorder(null);
		this.setBackground(new Color(255, 154, 154));
        pack();
        show();
        setLayout(null);
		setSize(400, 300);
		setBounds(200,100,400,300);
		setResizable(false);  //Limita o tamanho da janela, não permitindo maximização.
        janela_ativa = true;
        layoutMes(data);
        
        tituloAno = new JLabel(String.format("%d", data.anoNumero), SwingConstants.CENTER);
        tituloAno.setForeground(Color.WHITE);
        tituloAno.setFont(new Font("Arial", Font.BOLD, 20));
        getContentPane().add(tituloAno);
  
        tituloAno.setBounds(12, 15, 60, 25);
        
        
        
        
        
	}
	
	
	//Método que defini o layout dos meses dentro da JanelaAnoMes
	
	@SuppressWarnings("deprecation")
	public void layoutMes(Calendario data) {
		int posicaoInicialX = 24;
        int posicaoInicialY = 62;
        int marginX = 24;
        int marginY = 20;
        int tamanhoMesX = 70;
        int tamanhoMesY = 50;
        botaoMeses = new JButton[12];
        for(int qtd = 1, i = 0; i < 12; qtd++, i++) {
        	botaoMeses[i] = new JButton(String.format("%s", data.capitalize(Month.of(qtd).getDisplayName(java.time.format.TextStyle.FULL, new Locale("pt", "BR")))));
        	
        	
        	int x = posicaoInicialX + (i % 4) * (marginX + tamanhoMesX);
        	int y = posicaoInicialY + (i / 4) * (marginY + tamanhoMesY);
        	botaoMeses[i].setBounds(x, y, tamanhoMesX, tamanhoMesY);
        	
        	//Defini a cor do botão, [Vermelho = Mês atual]; [Branco = Mês selecionado]; [Vermelho claro = Outros]
        	if(mainApp.dataAtual.mesNumero == i+1 && mainApp.dataAtual.anoNumero == data.anoNumero){
        		botaoMeses[i].setBackground(Color.RED);
        		botaoMeses[i].setForeground(Color.WHITE);
        	}else if(mainApp.data.mesNumero == i+1){
        		botaoMeses[i].setBackground(Color.WHITE);
        		botaoMeses[i].setForeground(Color.BLACK);
        	}else {
        		botaoMeses[i].setBackground(new Color(255, 99, 99));
        		botaoMeses[i].setForeground(Color.WHITE);
        	}
        	getContentPane().add(botaoMeses[i]);
        	botaoMeses[i].addActionListener(this::cliqueNoMes);
        }
	}
	public void cliqueNoMes(ActionEvent e) {
		//Month mesSelecionado;
		Calendario novaData;
		int index = 0;
		//Quando o clicar no botão do mês, fecha a janela e altera o mês indicado na janela.
		JButton botãoPressionado = (JButton) e.getSource();
		for (int i = 0; i < botaoMeses.length; i++) {
	        if (botaoMeses[i] == botãoPressionado) {
	            index = i;
	            break;
	        }
		}
		//mesSelecionado = Month.of(index+1);
		novaData = new Calendario(data.diaAtual, index+1, data.anoNumero);
		this.data = novaData;
		
		//quero chamar a função fecharJanelaAnoMes que ta no frame que criou essa internalFrame
		mainApp.data = this.data;
		mainApp.fecharJanelaAnoMes(null);
	}
	
}
