package linearesFila.Questao1;

public class Pacientes {
    private String nome;
    private String horarioChegada;
    private String tipoAtendimento;

    public Pacientes(String nome, String horarioChegada, String tipoAtendimento) {
        this.nome = nome;
        this.horarioChegada = horarioChegada;
        this.tipoAtendimento = tipoAtendimento;
    }

    // Converte "08:05" para minutos (8*60 + 5 = 485)
    public int getChegadaEmMinutos() {
        String[] partes = horarioChegada.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }

    public String getNome(){
        return nome;
    }

    public String getHorarioChegada(){
        return horarioChegada;
    }
    public String getTipoAtendimento(){
        return  tipoAtendimento;
    }

}
