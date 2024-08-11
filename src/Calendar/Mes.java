package Calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class Mes{
	final String diasSemana[] = {"Domingo", "Segunda", "Terça",  "Quarta",  "Quinta", "Sexta", "Sabado"};
	int qtdDias;
	int mes;
	String nomeMes;
	int ano;
	boolean bissexto;
	int primeirodiaDaSemana;
	public Mes(int mes, int ano) {
		this.mes = mes;
		this.nomeMes = nomeMes(mes);
		this.bissexto = bissexto(ano);
		this.ano = ano;
		this.qtdDias = quatidadesDias(mes);
		LocalDate primeiroDia = LocalDate.of(ano, mes, 1);
		DayOfWeek diaDaSemana = primeiroDia.getDayOfWeek();
		this.primeirodiaDaSemana = diaDaSemana.getValue();
		if(primeirodiaDaSemana == 7) primeirodiaDaSemana = 0;
        
	}
	
	
	public boolean bissexto(int ano) {
		if (ano % 400 == 0) return true;
		if (ano % 100 == 0) return false;
		if (ano % 4 == 0) return true;
		return false;
	}
	public String nomeMes(int mes) {
		if(mes >= 1 && mes <= 12) {
			if(mes == 1) return "Janeiro";
			if(mes == 2) return "Fevereiro";
			if(mes == 3) return "Março";
			if(mes == 4) return "Abril";
			if(mes == 5) return "Maio";
			if(mes == 6) return "Junho";
			if(mes == 7) return "Julho";
			if(mes == 8) return "Agosto";
			if(mes == 9) return "Setembro";
			if(mes == 10) return "Outubro";
			if(mes == 11) return "Novembro";
			if(mes == 12) return "Dezembro";
		}
		return "Mês Invalido";
	}
	
	public int quatidadesDias(int mes) {
		if(mes >= 1 && mes <=7) {
			if(mes == 2) {
				if(bissexto(ano)) return 29;
				return 28;
			}
			if(mes%2 == 0) return 30;
			return 31;
		}
		if(mes%2 == 0) return 31;
		return 30;
		
	}
	
	
}
