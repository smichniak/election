import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public Main() throws FileNotFoundException {
    }

    private static void printResults(MandateDistribution method, Party[] parties, List<District> districts) {
        System.out.println(method);
        for (District district : districts) {
            System.out.println(district.getDistrictNumber());
            for (Voter voter : district.getVoters()) {
                System.out.println(voter.toString() + " " + voter.getVotedFor().toString());
            }
            for (Party party : parties) {
                for (Candidate candidate : district.getPartyList(party)) {
                    System.out.println(candidate.toString() + " " + party.toString() + " " + candidate.getPosition() + " " + candidate.getVotes());
                }
            }
            for (Party party : parties) {
                System.out.printf(party.toString() + " %d ", district.getMandates(party));
            }
            System.out.print("\n");
        }
        for (Party party : parties) {
            System.out.printf(party.toString() + " %d ", party.getMandates());
        }
        System.out.print("\n");

    }

    public static void main(String[] args) throws FileNotFoundException {
//        District district1 = new District(1, 10);
//        District district2 = new District(2, 15);
//        int[] vector1 = {1, 2, -3};
//        int[] vector2 = {-4, 5, -3};
//
//        Campaign campaign1 = new Campaign(vector1, district1);
//        Campaign campaign2 = new Campaign(vector2, district2);
//
//
//        List<Campaign> campaigns = new ArrayList<>(List.of(campaign1, campaign2));
//        Collections.sort(campaigns);
//        for (Campaign campaign : campaigns) {
//            System.out.println(campaign.getCost());
//        }
//
//
//        Party partyA = new CheapParty("A", 100);
//        Party partyB = new CheapParty("B", 100);
//        Party partyC = new CheapParty("C", 100);
//
//
//        Map<Party, Integer> votes = new HashMap<>(Map.of(partyA, 720, partyB, 300, partyC, 480));
//
//        MandateDistribution mandate2 = new SainteLague();
//        MandateDistribution mandate3 = new HareNiemeyer();
//        System.out.println(mandate1.mandates(votes, 8));
//        System.out.println(mandate2.mandates(votes, 8));
//        System.out.println(mandate3.mandates(votes, 8));
        MandateDistribution method1 = new DHondt();
        MandateDistribution method2 = new SainteLague();
        MandateDistribution method3 = new HareNiemeyer();
        List<MandateDistribution> methods = new ArrayList<>(List.of(method1, method2, method3));
        Collections.shuffle(methods);

        try {
            Parser parser = new Parser(args[0]);
            parser.parseInput();
            Party[] parties = parser.getParties();
            List<District> districts = parser.getDistricts();
            List<Campaign> campaigns = parser.getCampaigns();

            for (Party party : parties) {
                while (party.runCampaign(campaigns));
            }

            for (District district : districts) {
                for (Voter voter : district.getVoters()) {
                    voter.chooseCandidate();
                }
                district.countVotes();
            }

            for (MandateDistribution method : methods) {
                for (District district : districts) {
                    district.distributeMandates(method);
                }
                printResults(method, parties, districts);
                for (Party party : parties) {
                    party.addMandates(-party.getMandates());
                }
            }


        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


    }
}

