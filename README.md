### Apache Camel Rota - Estudos 🐪

Repositório destinado para o estudo sobre Apache Camel e construção de rotas.
</br>

1. Sobre o Apache Camel
<p align="justify">Essencialmente, o Apache Camel é um roteador (routing engine), ou seja o **Camel roteia os dados entre dois endpoints**. Cabe ao programador definir a <b>origem dos dados (from)</b>, o <b>seu destino (to)</b>, e <b>a forma com a qual ele será processado (mediation engine)</b>.</p>

#### Exemplo de FROM(Origem) e TO(Destino)


    public class RotaPedidos {
    
    	public static void main(String[] args) throws Exception {
    
    		CamelContext context = new DefaultCamelContext();
    		context.addRoutes(new RouteBuilder() {
    
    			@Override
    			public void configure() throws Exception {
    				
    				// configurando a rota Camel
    				from("file:pedidos?delay=5s&noop=true"). // a origem de onde o Apache Camel irá buscar os dados
    				to("file:saida"); // o destino o qual após ele ser processado ele irá nos dar a saída, neste caso, estou fazendo uma 									transferência de arquivo.
    				
    			}
    		// inicializar o Camel
    		context.start();
    		Thread.sleep(20000); // aqui eu do um descanso para o Camel e depois de 20s ele retorna ao trabalho.
    		
    		// finaliza o Camel
    		context.stop();
    		});
    	}	
    }

<p align="justify">Um dos princípios fundamentais do Camel é que ele não determina o tipo de dado que precisa ser transacionado. Este alto nível de abstração facilita a interação com vários sistemas porque aproveita-se a mesma API, independentemente do protocolo ou tipo de dado utilizado.</p>

