import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class app extends JFrame {

	List<String> agencias = new LinkedList<>();
	List<Gerente> gerentes = new LinkedList<>();

	private JPanel contentPane;
	private JTextField txtusersmacbookdownloadstestexlsx;
	private JTextField txtPlanilha;
	private JTextField txtPlanilha_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app frame = new app();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public app() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 600, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCaminhoDoArquivo = new JLabel("Caminho do arquivo:");
		lblCaminhoDoArquivo.setBounds(6, 40, 137, 16);
		contentPane.add(lblCaminhoDoArquivo);
		
		txtusersmacbookdownloadstestexlsx = new JTextField();
		txtusersmacbookdownloadstestexlsx.setText("/Users/macbook/Downloads/teste.xlsx");
		txtusersmacbookdownloadstestexlsx.setBounds(138, 35, 456, 26);
		contentPane.add(txtusersmacbookdownloadstestexlsx);
		txtusersmacbookdownloadstestexlsx.setColumns(10);
		
		JButton btnNewButton = new JButton("Gerar e enviar por e-mail");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(178, 153, 210, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gerar e guardar em pasta");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					geraAgenicas();
				} catch (FilloException e2) {
					JOptionPane.showMessageDialog(null, "Lista de agencias não encontrado");
					e2.printStackTrace();
				}
				
				Fillo f =new Fillo();
				Connection connection = null;
				
				
				try {
					connection = f.getConnection(txtusersmacbookdownloadstestexlsx.getText().trim().toString());
				} catch (FilloException e1) {
					JOptionPane.showMessageDialog(null, "Arquivo não econtrado");
					e1.printStackTrace();
				}
				
				if (!txtPlanilha.getText().isEmpty()) {
					for (String s: agencias)
					{
						String strQuery= "Select * from " + txtPlanilha.getText().trim().toString() + " where ag = " + s ;
						Recordset recordset = null;
				
				try {
					recordset = connection.executeQuery(strQuery);
				} catch (FilloException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				try {
					while(recordset.next()){
						
						Gerente g = new Gerente(recordset.getField("funcional"));
						g.setAg(recordset.getField("ag"));
						g.setMetaContatos(Integer.parseInt(recordset.getField("meta contatos")));
						g.setRealContatos(Integer.parseInt(recordset.getField("real contatos")));
						
						gerentes.add(g);
						
						Document document = new Document(PageSize.A4.rotate(), 0f, 0f, 0f, 0f);
						
			        		
						try {
			
							PdfWriter.getInstance(document, new FileOutputStream("/Users/macbook/Downloads/teste" + recordset.getField("ag")+ ".pdf"));
					        document.open();
					        
					        Paragraph paragraph = new Paragraph("Relatorio de Atividade Comercial da Agencia: " + s);
					        paragraph.setSpacingAfter(50f);
					        document.add(paragraph);

					        PdfPTable table = new PdfPTable(12);
					       
			        		table.setWidthPercentage(100);
					        
					        table.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table.setWidths(new float[] { 2, 2, 2, 1, 1, 1, 1,1,1,1,1,1 });			                
			  
			        		table.addCell(celula("Nome"));
			        		table.addCell(celula("Funcional"));
			        		table.addCell(celula("Pade"));
			        		table.addCell(celula(""));
			        		table.addCell(header("Contatos"));
			        		table.addCell(header("Revisão Financeira"));
			        		table.addCell(header("Giro de Carteira"));
			        		table.addCell(header("Giro de Carteira"));
			        		
			                document.add(table);

			        		PdfPTable table3 = new PdfPTable(12);
						       
			        		table3.setWidthPercentage(100);
					        
					        table3.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table3.setWidths(new float[] { 2, 2, 2, 1, 1, 1, 1,1,1,1,1,1 });			                
			  
			        		table3.addCell(celula(""));
			        		table3.addCell(celula(""));
			        		table3.addCell(celula(""));
			        		table3.addCell(celula(""));
			        		table3.addCell(celula("Meta"));
			        		table3.addCell(celula("Real"));
			        		table3.addCell(celula("Meta"));
			        		table3.addCell(celula("Real"));
			        		table3.addCell(celula("Meta"));
			        		table3.addCell(celula("Real"));
			        		table3.addCell(celula("Meta"));
			        		table3.addCell(celula("Real"));
			                document.add(table3);
			        		
			                table.setSpacingAfter(30);
			                
					        for (Gerente ger: gerentes) {
					        	
					        	if (ger.getAg().equals(s)) {
					        		
					        		PdfPTable table2 = new PdfPTable(12);
					        		table2.setWidthPercentage(100);
					        		table2.setHorizontalAlignment(Element.ALIGN_LEFT);
					 
					        		table2.setWidths(new float[] { 2, 2, 2,1, 1, 1, 1,1,1,1,1,1 });
					                table2.addCell(celula(""));
					                table2.addCell(celula(ger.getFuncional().toString()));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.addCell(celula(ger.getMetaContatos().toString()));
					                table2.addCell(celula(ger.getRealContatos().toString()));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.addCell(celula(""));
					                table2.setSpacingAfter(5);
					                document.add(table2);
					                
					                
					        	}
					        }
					        
						    document.close();

						}
					 catch (DocumentException e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				} catch (FilloException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				}
			}
			    agencias.clear();

			}

		});
		btnNewButton_1.setBounds(178, 195, 210, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel label = new JLabel("Nome da planilha(Aba)");
		label.setBounds(6, 68, 153, 16);
		contentPane.add(label);
		
		txtPlanilha = new JTextField();
		txtPlanilha.setText("Planilha1");
		txtPlanilha.setColumns(10);
		txtPlanilha.setBounds(148, 63, 186, 26);
		contentPane.add(txtPlanilha);
		
		txtPlanilha_1 = new JTextField();
		txtPlanilha_1.setText("Planilha2");
		txtPlanilha_1.setColumns(10);
		txtPlanilha_1.setBounds(148, 96, 186, 26);
		contentPane.add(txtPlanilha_1);
		
		JLabel label_1 = new JLabel("Nome da aba agencias");
		label_1.setBounds(6, 101, 153, 16);
		contentPane.add(label_1);
	}
	
	public void geraAgenicas() throws FilloException {
		
		Fillo f =new Fillo();
		Connection connection = null;
		
		
		try {
			connection = f.getConnection(txtusersmacbookdownloadstestexlsx.getText().trim().toString().toLowerCase());
		} catch (FilloException e1) {
			JOptionPane.showMessageDialog(null, "Arquivo não econtrado");
			e1.printStackTrace();
		}
		String strQuery= "Select * from " + txtPlanilha_1.getText().trim().toString();
		
		Recordset recordset = null;
		
		try {
			recordset = connection.executeQuery(strQuery);
			
			
		} catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(recordset.next()){
			String ag = new String(recordset.getField("Ag"));
			agencias.add(ag);
		}
	}
	
	public PdfPCell celula(String nome) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase(nome));
		cell.setBorderColor(BaseColor.WHITE);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		return cell;
	};

	public PdfPCell header(String nome) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Phrase(nome));
	    cell.setColspan(2);
		cell.setBorderColor(BaseColor.WHITE);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		return cell;
	};

}
