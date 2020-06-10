package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Scanner;
import voters.*;
import parties.*;

public class Parser {
    private Scanner scanner;
    private Party[] parties;
    private Map<String, Integer> partiesNames;
    private List<District> districts;
    private List<Campaign> campaigns;

    public Parser(String directory) {
        try {
            File inputFile = new File(directory);
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku \"" + directory + "\"");
            System.exit(1);
        }
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

    // Zwraca tablicę wag lub wartości chech, jej rozmiar zależny jest od liczby tych parametrów
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
                    scanner.nextInt(); // Znamy już okręg kandydata, nie musimy go zczytywać
                    scanner.next(); // Znamy już partię kandydata
                    scanner.nextInt(); // Znamy już numer na liście kandydata
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
                Party party = null; // Partia `null` oznacza, że wyborca nie ma preferencji partyjnych
                String name = scanner.next();
                String surname = scanner.next();
                scanner.nextInt(); // Znamy już okręg wyborcy
                int type = scanner.nextInt();

                if (type == 1) {
                    party = parties[partiesNames.get(scanner.next())];
                    voter = new Voter(name, surname, district, party);
                } else if (type == 2) {
                    party = parties[partiesNames.get(scanner.next())];
                    int position = scanner.nextInt();
                    voter = new OneCandidate(name, surname, district, party, position);
                } else if (type == 3 || type == 6) {
                    int trait = scanner.nextInt();
                    if (type == 6) {
                        party = parties[partiesNames.get(scanner.next())];
                    }
                    voter = new MinVoter(name, surname, district, party, trait);
                } else if (type == 4 || type == 7) {
                    int trait = scanner.nextInt();
                    if (type == 7) {
                        party = parties[partiesNames.get(scanner.next())];
                    }
                    voter = new MaxVoter(name, surname, district, party, trait);
                } else {
                    int[] weights = getTraitsWeights(numberOfTraits);
                    if (type == 8) {
                        party = parties[partiesNames.get(scanner.next())];
                    }
                    voter = new WeightedVoter(name, surname, district, party, weights);
                }
                district.addVoter(voter);
            }
        }
    }

    private void generateCampaigns(int numberOfCampaigns, int numberOfTraits) {
        campaigns = new ArrayList<>();
        for (int i = 0; i < numberOfCampaigns; ++i) {
            int[] traitChange = getTraitsWeights(numberOfTraits);
            for (District district : districts) {
                campaigns.add(new Campaign(traitChange, district));
            }
        }
        Collections.sort(campaigns); // Sortujemy działania niemalejąco po koszcie
    }

    private void mergeDistricts(int numberOfPairs, String toMerge) {
        toMerge = toMerge.replace(',', ' ').replace('(', ' ').replace(')', ' ');
        List<Integer> toRemove = new ArrayList<>();
        Scanner stringScanner = new Scanner(toMerge);
        for (int i = 0; i < numberOfPairs; ++i) {
            int first = stringScanner.nextInt();
            int second = stringScanner.nextInt();
            districts.get(first - 1).merge(districts.get(second - 1));
            toRemove.add(second); // Checmy usunąć okręgi o większym numerze
        }
        // Usuwamy okręgi od największych indeksów, by te o mniejszym nie zmieniły miejsca na liście
        Collections.reverse(toRemove);
        for (int district : toRemove) {
            districts.remove(district - 1);
        }
    }

    public void parseInput() {
        int numberOfDistricts = scanner.nextInt();
        int numberOfParties = scanner.nextInt();
        int numberOfCampaigns = scanner.nextInt();
        int numberOfTraits = scanner.nextInt();
        int numberOfPairs = scanner.nextInt();

        String toMerge = scanner.nextLine(); // Linia wejścia opsiująca okręgi do połączenia

        generateParties(numberOfParties);
        generateDistricts(numberOfDistricts);
        generateCandidates(numberOfTraits);
        generateVoters(numberOfTraits);
        generateCampaigns(numberOfCampaigns, numberOfTraits);
        mergeDistricts(numberOfPairs, toMerge);
    }

    public void printResults(MandateDistribution method) {
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
                System.out.println(party.toString() + " " + district.getMandates(party));
            }
        }
        System.out.println("W sumie: ");
        for (Party party : parties) {
            System.out.println(party.toString() + " " + party.getMandates());
        }
    }

}
