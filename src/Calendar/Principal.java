package Calendar;


import java.time.LocalDate;

public class Principal {
	public static void main(String[] args) {
	LocalDate atual = LocalDate.now();
	Mes mes = new Mes(atual.getMonthValue(), atual.getYear());
	Data data = new Data(atual.getDayOfMonth(), mes, atual.getYear());
	
	
    app frame = new app(data);
    frame.setVisible(true);
        
	}
	
}