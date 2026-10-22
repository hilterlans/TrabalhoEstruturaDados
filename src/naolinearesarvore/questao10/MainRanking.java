package naolinearesarvore.questao10;

public class MainRanking {
    public static void main(String[] args) {
        RankingJogos ranking = new RankingJogos();

        String[] nicks = {
            "ProPlayerBR", "NoobMaster", "EmanuelDev", "ShadowFox", "NightWolf", "BlazeKing", "IceQueen", "ThunderBR",
            "PixelNinja", "LunaStar", "DragonSlayer", "CyberWolf", "MegaByte", "QuantumX", "RapidFire", "SilentStorm",
            "GhostRider", "IronFist", "VortexGG", "AlphaPrime", "NovaKnight", "TurboMax", "ZenMaster", "RedPhoenix",
            "CosmicRay", "StormBreaker", "ByteHunter", "FrostBite", "HyperNova", "LastBoss"
        };
        int[] pontos = {
            1500, 800, 2500, 1850, 1320, 2100, 1740, 960, 1210, 2230, 1675, 1405, 890, 1990, 1130,
            1560, 2380, 1020, 1295, 1870, 1440, 760, 1620, 1980, 1180, 1850, 1340, 920, 1710, 1260
        };

        System.out.println("===== QUESTÃO 10 – RANKING DE JOGADORES (AVL) =====\n");
        for (int i = 0; i < nicks.length; i++) {
            ranking.cadastrar(nicks[i], pontos[i]);
        }
        System.out.println("Jogadores cadastrados: " + ranking.getTotalJogadores()
                + " | Altura da AVL: " + ranking.getAltura() + " | Balanceada? " + ranking.estaBalanceada());

        System.out.println("\n--- Ranking inicial ---");
        ranking.mostrarTopK(8);
        System.out.println("(7º e 8º lugares empatados em 1850 pts: o empate é desfeito pelo nickname)");

        for (int rodada = 1; rodada <= 3; rodada++) {
            int atualizados = 0;
            for (int i = 0; i < nicks.length; i++) {
                if ((i + rodada) % 3 == 0) {
                    int ganho = (i * 37 + rodada * 53) % 300 + 20;
                    ranking.adicionarPontos(nicks[i], ganho);
                    atualizados++;
                }
            }
            System.out.println("\n--- Rodada " + rodada + ": " + atualizados + " jogadores ganharam pontos ---");
            ranking.mostrarTopK(5);
            System.out.println("   Altura da AVL: " + ranking.getAltura() + " | Balanceada? " + ranking.estaBalanceada()
                    + " | Jogadores: " + ranking.getTotalJogadores());
        }

        System.out.println("\n--- Atualização direta de pontuação ---");
        ranking.atualizarPontuacao("NoobMaster", 3000);
        System.out.println("NoobMaster foi para 3000 pts:");
        ranking.mostrarTopK(3);

        System.out.println("\n--- Top 10 final ---");
        ranking.mostrarTopK(10);

        System.out.println("\n--- Casos de erro ---");
        System.out.println("Atualizar jogador inexistente: " + (ranking.atualizarPontuacao("Fantasma", 100) ? "ok" : "jogador não encontrado"));
        System.out.println("Cadastrar nickname repetido: " + (ranking.cadastrar("ProPlayerBR", 10) ? "aceito" : "recusado, nickname já existe"));
        try {
            ranking.atualizarPontuacao("LastBoss", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Pontuação negativa: " + e.getMessage());
        }
        System.out.println("Total de jogadores continua em " + ranking.getTotalJogadores() + " (nenhum foi perdido).");
    }
}
