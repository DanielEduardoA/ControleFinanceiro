package br.edu.univas.financas.view;

import javax.swing.text.MaskFormatter;

public class MascaraFormatter {
	
	public MaskFormatter mascara(String Mascara){
        MaskFormatter mascara = new MaskFormatter();
        try{
            mascara.setMask(Mascara); 
            mascara.setPlaceholderCharacter(' '); 
        }
        catch (Exception excecao) {
        	excecao.printStackTrace();
        } 
        return mascara;
	}

}
