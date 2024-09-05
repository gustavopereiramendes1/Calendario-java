package Calendar;


import java.time.LocalDate;

public class Principal {
	public static void main(String[] args) {
	LocalDate atual = LocalDate.now();
	Calendario calendario = new Calendario(atual.getDayOfMonth(), atual.getMonthValue(), atual.getYear());
	
    app frame = new app(calendario);
    frame.setVisible(true);
        
	}
	
}