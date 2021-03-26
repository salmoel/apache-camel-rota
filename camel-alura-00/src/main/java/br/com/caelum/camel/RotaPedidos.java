package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				// configurando a rota Camel
				from("file:pedidos?delay=5s&noop=true").
					log("${id}").
					// method responsável por transformar o xml em json
					marshal().xmljson().
					log("${body}").
					//setHeader("CamelFileName", constant("${file:name}.json")).
					setHeader("CamelFileName", simple("${file:name.noext}.json")).
				to("file:saida");
				
			}
			
		});
		
		// inicializar o Camel
		context.start();
		Thread.sleep(20000);
		
		// finaliza o Camel
		context.stop();
	}	
}
