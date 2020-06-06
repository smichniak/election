import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    private Scanner scanner;
    private Party[] parties;
    private Map<String, Integer> partiesNames;
    private List<District> districts;
    private List<Campaign> campaigns;

    public Parser(String directory) throws FileNotFoundException {
        File inputFile = new File(directory);
        scanner = new Scanner(inputFile);
    }

    public Party[] getParties() {
        return parties;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    private void generateParties(int numberOfParties) {
        parties = new Party[numberOfParties];
        partiesNames = new HashMap<>();
        String[] names = new String[numberOfParties];
        int[] budgets = new int[numberOfParties];
        String[] strategies = new String[numberOfParties];

        for (int i = 0; i < numberOfParties; ++i) {
            names[i] = scanner.next();
        }
        for (int i = 0; i < numberOfParties; ++i) {
            budgets[i] = scanner.nextInt();
        }
        for (int i = 0; i < numberOfParties; ++i) {
            strategies[i] = scanner.next();
        }

        for (int i = 0; i < numberOfParties; ++i) {
            if (strategies[i].equals("R")) {
                parties[i] = new ExpensiveParty(names[i], budgets[i]);
            } else if (strategies[i].equals("S")) {
                parties[i] = new CheapParty(names[i], budgets[i]);
            } else if (strategies[i].equals("W")) {
                parties[i] = new RandomParty(names[i], budgets[i]);
            } else {
                parties[i] = new GreedyParty(names[i], budgets[i]);
            }
            partiesNames.put(parties[i].toString(), i);
        }
    }

    private void generateDistricts(int numberOfDistricts) {
        districts = new ArrayList<>();
        for (int districtNumber = 1; districtNumber <= numberOfDistricts; ++districtNumber) {
            District newDistrict = new District(districtNumber, scanner.nextInt());
            for (Party party : parties) {
                newDistrict.addParty(party);
            }
            districts.add(newDistrict);
        }

    }

    private int[] getTraitsWeights(int numberOfTraits) {
        int[] traits = new int[numberOfTraits];
        for (int i = 0; i < numberOfTraits; ++i) {
            traits[i] = scanner.nextInt();
        }
        return traits;
    }

    private void generateCandidates(int numberOfTraits) {
        for (District district : districts) {
            for (Party party : parties) {
                for (int position = 1; position <= district.getNumberOfVoters() / 10; ++position) {
                    String name = scanner.next();
                    String surname = scanner.next();
                    scanner.nextInt(); // District number, useless
                    scanner.next(); // Party name, useless
                    scanner.nextInt(); // Position, useless
                    int[] traits = getTraitsWeights(numberOfTraits);
                    Candidate candidate = new Candidate(name, surname, position, traits);
                    district.addCandidate(party, candidate);
                }
            }
        }
    }

    private void generateVoters(int numberOfTraits) {
        for (District district : districts) {
            for (int i = 0; i < district.getNumberOfVoters(); ++i) {
                Voter voter;
                Party party = null;
                String name = scanner.next();
                String surname = scanner.next();
                scanner.nextInt(); // District number, useless
                int type = scanner.nextInt();

                if (type == 1) {
                    party = parties[partiesNames.get(scanner.next())];
                    voter = new Voter(name, surname, district, party);
                } else if (type == 2) {
                    party = parties[partiesNames.get(scanner.next())];
                    int position = scanner.nextInt();
                    voter = new OneCandidate(name, surname, district, party, position);
                } else if (type == 3) {
                    int trait = scanner.nextInt();
                    voter = new MinVoter(name, surname, district, party, trait);
                } else if (type == 4) {
                    int trait = scanner.nextInt();
                    voter = new MaxVoter(name, surname, district, party, trait);
                } else if (type == 5) {
                    int[] weights = getTraitsWeights(numberOfTraits);
                    voter = new WeightedVoter(name, surname, district, party, weights);
                } else if (type == 6) {
                    int trait = scanner.nextInt();
                    party = parties[partiesNames.get(scanner.next())];
                    voter = new MinVoter(name, surname, district, party, trait);
                } else if (type == 7) {
                    int trait = scanner.nextInt();
                    party = parties[partiesNames.get(scanner.next())];
                    voter = new MaxVoter(name, surname, district, party, trait);
                } else {
                    int[] weights = getTraitsWeights(numberOfTraits);
                    party = parties[partiesNames.get(scanner.next())];
                    voter = new WeightedVoter(name, surname, district, party, weights);
                }
                district.addVoter(voter);
            }
        }
    }

    private void generateCampaigns(int numberOfCampaigns, int numberOfTraits) {
        campaigns = new ArrayList<>();
        for (int i = 0; i < numberOfCampaigns; ++i) {
            int[] traitChange = new int[numberOfTraits];
            for (int trait = 0; trait < numberOfTraits; ++trait) {
                traitChange[trait] = scanner.nextInt();
            }
            for (District district : districts) {
                campaigns.add(new Campaign(traitChange, district));
            }
        }
        Collections.sort(campaigns);
    }

    private void mergeDistricts(int numberOfPairs, String toMerge) {
        toMerge = toMerge.replace(',', ' ').replace('(', ' ').replace(')', ' ');
        List<Integer> toRemove = new ArrayList<>();
        Scanner stringScanner = new Scanner(toMerge);
        for (int i = 0; i < numberOfPairs; ++i) {
            int first = stringScanner.nextInt();
            int second = stringScanner.nextInt();
            districts.get(first-1).merge(districts.get(second-1));
            toRemove.add(second);
        }
        Collections.reverse(toRemove);
        for (int district : toRemove) {
            districts.remove(district-1);
        }

    }


    public void parseInput() {
        int numberOfDistricts = scanner.nextInt();
        int numberOfParties = scanner.nextInt();
        int numberOfCampaigns = scanner.nextInt();
        int numberOfTraits = scanner.nextInt();
        int numberOfPairs = scanner.nextInt();

        String toMerge = scanner.nextLine();

        generateParties(numberOfParties);
        generateDistricts(numberOfDistricts);
        generateCandidates(numberOfTraits);
        generateVoters(numberOfTraits);
        generateCampaigns(numberOfCampaigns, numberOfTraits);
        mergeDistricts(numberOfPairs, toMerge);


    }
}
