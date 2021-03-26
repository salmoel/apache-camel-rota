### Apache Camel Rota - Estudos 🐪

Repositório destinado para o estudo sobre Apache Camel e construção de rotas.
</br>

1. Sobre o Apache Camel
<p align="justify">Essencialmente, o Apache Camel é um roteador (routing engine), ou seja o <b>Camel roteia os dados entre dois endpoints</b>. Cabe ao programador definir a <b>origem dos dados (from)</b>, o <b>seu destino (to)</b>, e <b>a forma com a qual ele será processado (mediation engine)</b>.</p>

#### Exemplo de FROM(Origem) e TO(Destino)

 ```java   				
    from("file:pedidos"). // a origem de onde o Apache Camel irá buscar os dados
    to("file:saida"); // o destino o qual após ele ser processado ele irá nos dar a saída, neste caso, estou fazendo uma transferência de arquivo. 									                      
```

<p align="justify">Um dos princípios fundamentais do Camel é que ele não determina o tipo de dado que precisa ser transacionado. Este alto nível de abstração facilita a interação com vários sistemas porque aproveita-se a mesma API, independentemente do protocolo ou tipo de dado utilizado.</p>

#### Um resumão do que é o Camel é:

> O Camel é uma máquina de roteamento, onde você fala de onde vêm os dados e para onde vão e ele faz alguma coisa no meio. Camel é uma biblioteca em que você pode utilizar standalone, ou seja, separado, ou integrar com algum projeto existente.
</br>
    2. Utilizando a Camel DSL
<p align="justify">A domain-specific language (DSL) do <b>Camel suporta</b> linguagens como <b>Java, Scala, Groovy</b> e também <b>permite que regras de roteamento sejam especificadas em XML.</b> O objetivo da <b>DSL é permitir que o desenvolvedor se concentre no negócio e não na ferramenta de integração</b>.</p>

    public class RotaPedidos {
    
    	public static void main(String[] args) throws Exception {
    
    		CamelContext context = new DefaultCamelContext();
    		context.addRoutes(new RouteBuilder() {
    
    			@Override
    			public void configure() throws Exception {
    				
    				from("file:pedidos").
    				to("file:saida");
    				
    			}
                
    		context.start();
    		Thread.sleep(20000);
    		context.stop();
    		});
    	}	
    }
