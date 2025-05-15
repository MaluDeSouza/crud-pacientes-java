package model;

public class Paciente {

        private int id;
        private String nome;
        private int idade;
        private String diagnostico;
        private String comorbidades;
        private String observacoes;

    public Paciente(String nome, int idade, String diagnostico, String comorbidades, String observacoes) {

        this.nome = nome;
        this.idade = idade;
        this.diagnostico = diagnostico;
        this.comorbidades = comorbidades;
        this.observacoes = observacoes;
    }

    public Paciente(int id, String nome, int idade, String diagnostico, String comorbidades, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.diagnostico = diagnostico;
        this.comorbidades = comorbidades;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getComorbidades() {
        return comorbidades;
    }

    public void setComorbidades(String comorbidades) {
        this.comorbidades = comorbidades;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isValido(){
        return nome != null && nome.trim().isEmpty() &&
                idade <0 &&
                diagnostico != null && diagnostico.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Paciente ID: " + id + "\nNome: " + nome + "\nIdade: " + idade +
                "\nDiagnóstico: " + diagnostico + "\nComorbidades: " + comorbidades +
                "\nObservações: " + observacoes + "\n";
    }

}
