package app;

import model.Paciente;
import repository.PacienteRepository;
import repository.TabelaUtil;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*TabelaUtil.excluirTabelaPacientes();
        System.out.println("Banco reiniciado com sucesso");*/
       // TabelaUtil.criarTabelaPacientes();
        System.out.println("Bem-Vindo(a)");
        boolean sair = true;
        while (sair){
            System.out.println("01 - CADASTRAR\n02 - LISTAR\n03 - EDITAR\n04 - EXCLUIR\n05 - SAIR");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("CADASTRO DE NOVO PACIENTE ");
                    System.out.println("NOME: ");
                    String nome = scanner.nextLine().toUpperCase();
                    boolean validName = false;
                    while (validName == false) {
                        if (nome.trim().isEmpty()) {

                            System.out.println("O campo nome é obrigatorio!");
                            System.out.println("NOME: ");
                            nome = scanner.nextLine().toUpperCase();
                        } else {
                            validName = true;
                        }
                    }

                    System.out.println("IDADE: ");
                    String inputIdade = scanner.nextLine();
                    int idade = 0;
                    try {
                        idade = Integer.parseInt(inputIdade);
                        if (idade <= 0) {
                            System.out.println("Idade inválida. Deve ser maior que 0.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Idade inválida. Digite um número inteiro.");
                        return;
                    }

                    System.out.println("DIAGNOSTICO: ");
                    String diagnostico = scanner.nextLine().toUpperCase();
                    boolean validDiagnostic = false;
                    while (validDiagnostic == false) {
                        if (diagnostico.trim().isEmpty()) {
                            System.out.println("O campo diagnostico é obrigatorio!");
                            System.out.println("Diagnostico: ");
                            diagnostico = scanner.nextLine().toUpperCase();
                        } else {
                            validDiagnostic = true;
                        }
                    }
                    System.out.println("COMORBIDADES: ");
                    String comorbidades = scanner.nextLine().toUpperCase();
                    System.out.println("OBSERVACOES: ");
                    String observacoes = scanner.nextLine().toUpperCase();
                    Paciente novoPaciente = new Paciente(nome,idade,diagnostico,comorbidades,observacoes);
                    PacienteRepository pacienteRepo = new PacienteRepository();
                    pacienteRepo.createPaciente(novoPaciente);
                    break;
                case 2:
                    System.out.println("Lista de todos os pacientes: ");
                    PacienteRepository paciente = new PacienteRepository();
                    paciente.readPaciente();
                    break;
                case 3:
                    PacienteRepository paciente1 = new PacienteRepository();
                    paciente1.readPaciente();
                    System.out.println("Digite o id do paciente que deseja editar ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Paciente pacienteEncontrado = paciente1.findById(idBusca);
                    if(pacienteEncontrado == null){
                        System.out.println("Nao foi possivel encontrar o paciente com o id: " + idBusca);
                        break;
                    }
                    System.out.println("Digite o novo nome (ou deixe em branco para manter)");
                    String novoNome = scanner.nextLine();
                    if(!novoNome.trim().isEmpty()){
                        pacienteEncontrado.setNome(novoNome.toUpperCase());
                    }
                    System.out.println("Digite a nova idade (ou 0 para manter)");
                    String novaIdadeStr = scanner.nextLine();
                    try {
                    Integer novaIdade = Integer.parseInt(novaIdadeStr);
                    if(novaIdade > 0 ){
                        pacienteEncontrado.setIdade(novaIdade);
                    }
                    }catch (NumberFormatException e){
                        System.out.println("Idade Invalida, mantendo a atual");
                    }
                    System.out.println("Digite o novo diagnostico (ou deixe em branco para manter o atual)");
                    String novoDiagnostico = scanner.nextLine();
                    if(!novoDiagnostico.trim().isEmpty()){
                        pacienteEncontrado.setDiagnostico(novoDiagnostico.toUpperCase());
                    }
                    System.out.println("Novas comorbidades (deixe em branco para manter): ");
                    String novasComorbidades = scanner.nextLine();
                    if (!novasComorbidades.trim().isEmpty()) {
                        pacienteEncontrado.setComorbidades(novasComorbidades.toUpperCase());
                    }

                    System.out.println("Novas observações (deixe em branco para manter): ");
                    String novasObs = scanner.nextLine();
                    if (!novasObs.trim().isEmpty()) {
                        pacienteEncontrado.setObservacoes(novasObs.toUpperCase());
                    }
                    paciente1.updatePaciente(pacienteEncontrado);
                    System.out.println("Dados do paciente " + pacienteEncontrado.getId() + "atualizado");
                    break;
                case 4:
                    PacienteRepository pacienteDelete = new PacienteRepository();
                    pacienteDelete.readPaciente();
                    System.out.println("Digite o id do paciente que deseja editar ");
                    int idBuscaDelete = scanner.nextInt();
                    scanner.nextLine();
                    Paciente pacienteDeleteEncontrado = pacienteDelete.findById(idBuscaDelete);
                    if(pacienteDeleteEncontrado == null){
                        System.out.println("Paciente nao encontrado");
                        break;
                    }
                    pacienteDelete.deletePaciente(pacienteDeleteEncontrado.getId());
                    System.out.println("Paciente deletado com sucesso");
                    break;
                case 5:
                    sair = false;
                    System.out.println("Saindo...");
                    break;
            }
        }
    }
}
