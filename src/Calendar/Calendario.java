package Calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Locale;


public class Calendario{
	final String diasSemana[] = {"Domingo", "Segunda", "Terça",  "Quarta",  "Quinta", "Sexta", "Sabado"};
	Month mes;
	YearMonth mesAno;
	String nomeMes;
	int mesNumero;
	int diaAtual;
	int qtdDias;
	int anoNumero;
	int primeirodiaDaSemana;
	boolean bissexto;
	

	@SuppressWarnings("deprecation")
	public Calendario(int dia, int mes, int ano) {
		this.mesAno = YearMonth.of(ano, mes);
		this.mes = this.mesAno.getMonth();
		this.nomeMes = capitalize(this.mes.getDisplayName(java.time.format.TextStyle.FULL, new Locale("pt", "BR")));
		this.bissexto = this.mesAno.isLeapYear();
		this.anoNumero = ano;
		this.mesNumero = mes;
		this.diaAtual = dia;
		this.qtdDias = this.mesAno.lengthOfMonth();
		LocalDate primeiroDia = LocalDate.of(ano, mes, 1);
		DayOfWeek diaDaSemana = primeiroDia.getDayOfWeek();
		this.primeirodiaDaSemana = diaDaSemana.getValue();
		if(primeirodiaDaSemana == 7) primeirodiaDaSemana = 0;
        
	}
	
	

	public String capitalize(String str) {
		if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
}
