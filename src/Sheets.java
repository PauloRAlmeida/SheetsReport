import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

	
public class Sheets {
	
	static List<Gerente> gerentes = new LinkedList<>();	
	
	public static void main(String[] args) throws FilloException, FileNotFoundException {
		
		Fillo f =new Fillo();
		Connection connection= f.getConnection("/Users/macbook/Downloads/teste.xlsx");
		String strQuery= "Select * from Planilha2 ";
		
		Recordset recordset=connection.executeQuery(strQuery);
		

		while(recordset.next()){
			Gerente g = new Gerente(recordset.getField("funcional"));
			gerentes.add(g);
			
		}
		
		
		geraRelatorio();
		
	}
	
	public static void listaAgencias() throws FilloException {


	}
	
	public static void geraRelatorio() throws FilloException, FileNotFoundException {
	
	List<Gerente> relatorio = new CopyOnWriteArrayList<>();	
	relatorio = gerentes;

	Fillo fillo=new Fillo();
	Connection connection= fillo.getConnection("/Users/macbook/Downloads/teste.xlsx");
			
	for (Gerente g: gerentes) {
		String strQuery= "Select * from Planilha1 where funcional =" + g.getFuncional().toString();
		
	Recordset recordset=connection.executeQuery(strQuery);
	
	while(recordset.next()){
		
	Gerente ger = new Gerente(recordset.getField("funcional").toString());
	
	
	for (Gerente gir: relatorio) {
		if (gir.getFuncional().equals(ger.getFuncional())) relatorio.remove(ger);	
	}
	

	
	for (Gerente a: relatorio) {
		if (a.getFuncional().equals(ger.getFuncional())) {
			a.setMetaContatos(a.getMetaContatos()+Integer.parseInt(recordset.getField( "meta contatos").toString()));
			a.setRealContatos(a.getRealContatos()+Integer.parseInt(recordset.getField( "real contatos").toString()));
		}
	}
	
	//Document document = new Document();
    
	/*try {
		
        
        PdfWriter.getInstance(document, new FileOutputStream("/Users/macbook/Downloads/teste" + recordset.getField("funcional")+ ".pdf"));
        document.open();
        
        // adicionando um parágrafo no documento
        document.add(new Paragraph("Gerando PDF - Java"));
        document.add(new Paragraph("Codigo funcional: " + recordset.getField("funcional")));
        document.add(new Paragraph("Cargo: " + recordset.getField( "cargo")));
        document.add(new Paragraph("Meta de Contatos: " + recordset.getField( "meta contatos")));
        document.add(new Paragraph("Real de Contatos: " + recordset.getField( "real contatos")));

	}
    catch(DocumentException de) {
        System.err.println(de.getMessage());
    
    document.close();
*/
	
	}
}
	for (Gerente a: relatorio) {
		System.out.println("xxxxxxxxxxxxxx");
		System.out.println("Funcionario: "+ a.getFuncional());
		System.out.println("Meta de Contatos: "+ a.getMetaContatos());
		System.out.println("Real de Contatos: "+ a.getRealContatos());
		System.out.println("xxxxxxxxxxxxxx");


}
	}
}