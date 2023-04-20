<img src="./img/course-apresentation.png" alt="gRPC"/>


## Stack
- Spring Boot;
- [gRPC-Spring-Boot-Starter](https://yidongnan.github.io/grpc-spring-boot-starter/en/)
- Protocol Buffers;
- Java 17;


<img src="./img/project.png" alt="Estrutura do projeto"/>



## RPC

O que é chamada de procedimento remoto (RPC)?

Remote Procedure Call é um protocolo de comunicação de software que um programa pode usar para solicitar um serviço de um programa localizado em outro computador em uma rede sem ter que entender os detalhes da rede. O RPC é usado para chamar outros processos nos sistemas remotos, como um sistema local. Às vezes, uma chamada de procedimento também é conhecida como chamada de função ou chamada de sub-rotina.

O RPC usa o modelo cliente-servidor. O programa solicitante é um cliente e o programa provedor de serviço é o servidor. Como uma chamada de procedimento local, um RPC é uma operação síncrona que exige que o programa solicitante seja suspenso até que os resultados do procedimento remoto sejam retornados. No entanto, o uso de processos ou encadeamentos leves que compartilham o mesmo espaço de endereço permite que vários RPCs sejam executados simultaneamente.

A linguagem de definição de interface (IDL) -- a linguagem de especificação usada para descrever a interface de programação de aplicativo (API) de um componente de software -- é comumente usada no software Remote Procedure Call. Nesse caso, o IDL fornece uma ponte entre as máquinas em cada extremidade do link que podem estar usando diferentes sistemas operacionais (SOs) e linguagens de computador.


## O que o RPC faz?

Quando as instruções do programa que usam a estrutura RPC são compiladas em um programa executável, um stub é incluído no código compilado que atua como representante do código do procedimento remoto. Quando o programa é executado e a chamada de procedimento é emitida, o stub recebe a solicitação e a encaminha para um programa de tempo de execução do cliente no computador local. Na primeira vez que o stub do cliente é invocado, ele contata um servidor de nomes para determinar o endereço de transporte onde o servidor reside.

O programa de tempo de execução do cliente tem o conhecimento de como endereçar o computador remoto e o aplicativo do servidor e envia a mensagem pela rede que solicita o procedimento remoto. Da mesma forma, o servidor inclui um programa de tempo de execução e stub essa interface com o próprio procedimento remoto. Os protocolos de solicitação de resposta são retornados da mesma maneira.


## Como funciona o RPC?

Quando uma chamada de procedimento remoto é invocada, o ambiente de chamada é suspenso, os parâmetros do procedimento são transferidos pela rede para o ambiente onde o procedimento deve ser executado e o procedimento é então executado nesse ambiente.

Quando o procedimento termina, os resultados são transferidos de volta para o ambiente de chamada, onde a execução é retomada como se retornasse de uma chamada de procedimento normal.

Durante um RPC, as seguintes etapas ocorrem:

1     O cliente chama o stub do cliente. A chamada é uma chamada de procedimento local com parâmetros inseridos na pilha da maneira normal.
2     O stub do cliente empacota os parâmetros do procedimento em uma mensagem e faz uma chamada de sistema para enviar a mensagem. O empacotamento dos parâmetros do procedimento é chamado de empacotamento.
3     O sistema operacional local do cliente envia a mensagem da máquina cliente para a máquina servidora remota.
4     O sistema operacional do servidor passa os pacotes de entrada para o stub do servidor.
5     O stub do servidor descompacta os parâmetros -- chamados unmarshalling -- da mensagem.
6     Quando o procedimento do servidor é concluído, ele retorna ao stub do servidor, que organiza os valores de retorno em uma mensagem. O stub do servidor então entrega a mensagem para a camada de transporte.
7     A camada de transporte envia a mensagem resultante de volta para a camada de transporte do cliente, que devolve a mensagem ao stub do cliente.
8     O stub do cliente desempacota os parâmetros de retorno e a execução retorna ao chamador.


## Tipos de RPC

Existem vários modelos de RPC e implementações de computação distribuída. Um modelo e uma implementação populares são o Distributed Computing Environment (DCE) da Open Software Foundation (OSF). O Instituto de Engenheiros Elétricos e Eletrônicos (IEEE) define RPC em sua Especificação de Chamada de Procedimento Remoto ISO, ISO/IEC CD 11578 N6561, ISO/IEC, novembro de 1991.

Exemplos de configurações de RPC incluem o seguinte:

     O método normal de operação em que o cliente faz uma chamada e não continua até que o servidor retorne a resposta.
     O cliente faz uma chamada e continua com seu próprio processamento. O servidor não responde.
     Um recurso para enviar várias chamadas sem bloqueio de cliente em um lote.
     Os clientes RPC possuem um recurso de broadcast, ou seja, podem enviar mensagens para vários servidores e receber todas as respostas resultantes.
     O cliente faz uma chamada cliente/servidor sem bloqueio; o servidor sinaliza que a chamada foi concluída chamando um procedimento associado ao cliente.

O RPC abrange a camada de transporte e a camada de aplicativo no modelo OSI (Open Systems Interconnection) de comunicação de rede. O RPC facilita o desenvolvimento de um aplicativo que inclui vários programas distribuídos em uma rede. Métodos alternativos para comunicação cliente-servidor incluem o enfileiramento de mensagens e o Advanced Program-to-Program Communication (APPC) da IBM.


