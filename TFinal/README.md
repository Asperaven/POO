    Operações do Sistema
1. Realizar login de médicos e pacientes.
2. Permitir que apenas o próprio usuário altere seus dados.
3. Agendar uma consulta.
4. Cancelar um agendamento.
5. Realizar uma consulta.
6. Avaliar uma consulta.
7. Visualizar prontuário/histórico do paciente.
8. Visualizar estatísticas do sistema.
        Regras do Sistema
    Pesquisa de médicos
O paciente pode pesquisar médicos por:
• nome;
• especialidade.
O sistema deverá mostrar:
• nome do médico;
• especialidade;
• média de estrelas;
• últimas avaliações recebidas.
O sistema mostra apenas médicos que atendem ao plano de saúde do paciente. Caso o
paciente não possua plano, todos os médicos deverão ser exibidos.
    Agendamento de consultas
Para agendar uma consulta:
• o paciente seleciona o médico;
• escolhe a data desejada.
Regras:
• um médico atende no máximo 3 pacientes por dia;
• caso o dia esteja lotado, o paciente pode entrar em uma lista de espera.
Lista de espera
Caso uma consulta seja cancelada:
• o primeiro paciente da lista de espera assume automaticamente a vaga disponível;
• o sistema deve notificar o paciente promovido da lista de espera.
A lista de espera deve respeitar a ordem de entrada dos pacientes.
    Realização da consulta
Durante a consulta, o médico deve informar:
• sintomas;
• diagnóstico;
• tratamento sugerido;
• medicamentos;
• exames solicitados;
• observações gerais.
Essas informações devem ser armazenadas no histórico/prontuário do paciente.
Caso o paciente tenha plano de saúde, ele não paga pela consulta.
Caso não tenha, o sistema gera uma conta para pagamento.
Especialidades médicas
Cada especialidade médica pode possuir regras diferentes.
Exemplos:
• Cardiologistas possuem consulta mais cara;
• Pediatras atendem no máximo 2 pacientes por dia;
• Dermatologistas podem possuir planos específicos.
Utilize herança e polimorfismo para representar especialidades médicas e seus
comportamentos específicos.
    Avaliação da consulta
Após a consulta, o paciente pode:
• escrever uma avaliação textual;
• atribuir de 1 a 5 estrelas ao médico.
As avaliações devem impactar a nota média do médico.
    Prontuário do paciente
O sistema deve manter um histórico de consultas do paciente contendo:
• data da consulta;
• médico responsável;
• sintomas;
• diagnóstico;
• medicamentos;
• exames solicitados;
• valor da consulta.
Durante a consulta, o médico pode visualizar o prontuário.
    Estatísticas do sistema
O sistema deverá apresentar algumas estatísticas, como:
• médico mais bem avaliado;
• especialidade mais procurada;
• quantidade total de consultas realizadas;
• média geral de avaliações;
• quantidade de consultas por médico.
As estatísticas podem ser exibidas em telas, gráficos simples ou arquivos de relatório.
    Tratamento de Exceções
Utilize exceções para tratar situações como:
• login inválido;
• data inválida;
• consulta inexistente;
• agenda lotada;
• paciente duplicado;
• operações não permitidas.
Crie exceções próprias sempre que necessário.
Obs: Não precisa fazer exceções para tudo!
    Requisitos Obrigatórios de Programação Orientada a Objetos
O trabalho deve utilizar obrigatoriamente:
• classes e objetos;
• encapsulamento;
• herança;
• polimorfismo;
• classes abstratas;
• coleções (ex: ArrayList);
• tratamento de exceções;
• informações armazenadas em arquivos .csv ou .txt
