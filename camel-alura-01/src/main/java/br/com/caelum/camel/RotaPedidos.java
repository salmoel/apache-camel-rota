package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
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
				// o metódo split ele vai ser responsável por dividir meus itens no xml e meio
				// que transformar em sub-itens(blocos)
				split().xpath("/pedido/itens/item").

						filter().
				// o filtro funciona atráves desta expressão
				xpath("item/formato[text()='EBOOK']").

				// metódo responsável por transformar o xml em json
				marshal().xmljson(). // de xml para json
				log("${id} - ${body}").

				// setHeader("CamelFileName", simple("${id}.json")). // ignora o nome do arquivo e usa a id 
				setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json")). // usando o nome do arquivo como base e concatenar com o SplitIndex

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
