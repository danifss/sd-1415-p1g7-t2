### Sistemas Distribuídos (Distributed Systems)
> Ano lectivo de 2014 / 2015

# Artesanato de Aveiro 
### (cliente-servidor)

Construa uma simulação das actividades na empresa de artesanato baseada no modelo cliente-servidor, com replicação de servidor, em que a dona da empresa, os artesãos e os clientes são os clientes e as regiões de interacção que tenha estabelecido representam os serviços que lhes são prestados pelos servidores.
A solução deve ser implementada em Java, ser passível de execução em Linux sobre sockets TCP, poder ser executada de uma forma concentrada (numa única máquina), ou de uma forma distribuída (até oito máquinas diferentes), e terminar (deve contemplar a possibilidade de shutdown do serviço).
As operações que correspondiam, na versão concorrente já implementada, a actividades realizadas sobre a(s) estrutura(s) de dados partilhada(s), devem agora corresponder a solicitações independentes requeridas ao(s) servidor(es) através de mensagens. Em cada caso, deve ser estabelecida a ligação, ser feito o pedido, aguardar a resposta e fechar a ligação.
