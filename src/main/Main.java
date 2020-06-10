package main;

import java.util.List;
import java.util.ArrayList;

import parties.*;
import voters.Voter;

public class Main {
    public static void main(String[] args) {
        MandateDistribution dHondt = new DHondt();
        MandateDistribution sainteLague = new SainteLague();
        MandateDistribution hareNiemeyer = new HareNiemeyer();
        List<MandateDistribution> methods = new ArrayList<>(List.of(dHondt, sainteLague, hareNiemeyer));

        try {
            Parser parser = new Parser(args[0]);

            parser.parseInput();
            Party[] parties = parser.getParties();
            List<District> districts = parser.getDistricts();
            List<Campaign> campaigns = parser.getCampaigns();

            for (Party party : parties) {
                while (party.runCampaign(campaigns)) ;
            }

            for (District district : districts) {
                for (Voter voter : district.getVoters()) {
                    Candidate votedFor = voter.chooseCandidate();
                    votedFor.vote();
                }
                district.countVotes();
            }

            for (MandateDistribution method : methods) {
                for (District district : districts) {
                    district.distributeMandates(method);
                }
                parser.printResults(method);
                for (Party party : parties) {
                    // Po wypisaniu wyników dla danej metody ustawiamy mandaty każdej partii na 0
                    party.addMandates(-party.getMandates());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Podaj plik wejściowy");
            System.exit(1);
        }

    }
}

