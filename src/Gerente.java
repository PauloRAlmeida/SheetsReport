
public class Gerente {

	private String ag;
	private String funcional;
	private String nome;
	private Integer metaContatos;
	private Integer realContatos;
	
	
	
	public Gerente(String funcional) {
		super();
		this.funcional = funcional;
		
	}
	
	
	public String getAg() {
		return ag;
	}


	public void setAg(String ag) {
		this.ag = ag;
	}


	public String getFuncional() {
		return funcional;
	}
	public void setFuncional(String funcional) {
		this.funcional = funcional;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getMetaContatos() {
		return metaContatos;
	}
	public void setMetaContatos(int i) {
		this.metaContatos = i;
	}
	public Integer getRealContatos() {
		return realContatos;
	}
	public void setRealContatos(int realContatos) {
		this.realContatos = realContatos;
	}
	
}
