package unidade3;

public class TestarAcessoBD {

	public static void main(String[] args) {
		/*
		try {			
			AcessoBD.mostrarMetaInfoBD();
			AcessoBD.consultarCliente();						
		}catch(Exception e) {
			System.out.println(e);
		}*/
		
		try {			
			ClienteApp clienteApp = new ClienteApp();
			clienteApp.consultarTodos();
			System.out.println("Consultar Por cpf: 393938");
			clienteApp.consultar(393938);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
