package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import utils.UtilsTexto;

@Named (value="TextoBean")
@RequestScoped
public class TextoBean implements Serializable {
	private static final long serialVersionUID = 4269566307260319608L;
	private String resultado;
	private String texto;
	private String labelResultado = "El resultado es";
	
	public TextoBean() {
		this.texto="";
		this.resultado="";
	}
	public void solucion() {
		// Se declaran las variables
		String textoIngresado=this.texto;
		String error = "ERROR ---> Información incompleta ";
		StringBuilder resultadoFinal = new StringBuilder();
		int numeroIngresado=0;
		if(textoIngresado.isBlank() || textoIngresado.isEmpty()) {
			setResultado("Valor inválido\n\r");
			return; // Se hace return para que no siga el codigo a pesar que es void
		}
		String[] lista=textoIngresado.split("\n");
		for(int i=0;i<lista.length;i++) {
			String valorActual = lista[i];
			// Verificamos que contenga el \
			if(valorActual.contains("\\")) {
				int indiceBackslash = valorActual.indexOf("\\");
				if(indiceBackslash > 0) {
					String numeroObtenido = valorActual.substring(0,indiceBackslash);
					try {
						numeroIngresado=Integer.parseInt(numeroObtenido);
					}catch (NumberFormatException e) {
						this.resultado +=error+"(Número inválido)\n";
						continue; // Que continue las iteraciones
					}
					// Reemplazamos lo que no sea letra o espacio
					String soloLetras=UtilsTexto.soloLetras(valorActual.substring(indiceBackslash+1));
					int verificacion=soloLetras.split(" ").length;
					boolean cantidadPalabras=verificacion==numeroIngresado;
					resultadoFinal.append(soloLetras+"\\"+cantidadPalabras+"\n"); 
				}else {
					resultadoFinal.append(error+"(No tiene número antes del \\)\n");
				}
			}else {
				resultadoFinal.append(error+"(No contiene \\ \n)");
			}
		}
		setResultado(resultadoFinal.toString());
	} 
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getLabelResultado() {
		return labelResultado;
	}
	public void setLabelResultado(String labelResultado) {
		this.labelResultado = labelResultado;
	}
	
	
}