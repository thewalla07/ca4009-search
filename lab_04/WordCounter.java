/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Debasis
 */
public class WordCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

            /*

            Added K specifiable as input parameter

            */
            int k = 30;
            String filename = "test.txt";
            if (args.length == 2) {
                k = Integer.parseInt(args[0]);
                filename = args[1];
            }
            WordCounter wc = new WordCounter(filename);
            LinkedHashMap<String, Integer> wmap = wc.sortMap(wc.getTfs());

            //wc.showTerms(wmap);

            /*

            Added show terms by frequency

            */
            if (k < 0) {
                wc.showTermsNoVal(wmap);
            } else {
                wc.showTopKTermsNoVal(k, wmap);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    String fileName;

    static final String DELIMS = " \t,;.?!'\"";

    WordCounter(String fileName) {
        this.fileName = fileName;
    }

    List<String> getTerms() throws Exception {
        List<String> terms = new ArrayList<>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;

        while ((line = br.readLine())!= null) {
            StringTokenizer st = new StringTokenizer(line, DELIMS);
            while (st.hasMoreTokens()) {
                /*

                Added lower casing of tokens

                */
                terms.add(st.nextToken().toLowerCase());
            }
        }
        return terms;
    }

    HashMap<String, Integer> getTfs() throws Exception {
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        List<String> terms = getTerms();
        for (String term: terms) {
            Integer tf = wordCountMap.get(term);
            if (tf == null) {
                tf = new Integer(0);
            }
            tf++;
            wordCountMap.put(term, tf);
        }
        return wordCountMap;
    }

    void showTerms(Map<String, Integer> wordMap) {
        for (Map.Entry<String, Integer> e : wordMap.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
    void showTermsNoVal(Map<String, Integer> wordMap) {
        for (Map.Entry<String, Integer> e : wordMap.entrySet()) {
            try {
                int test = Integer.parseInt(e.getKey());
            } catch (Exception ex) {
                System.out.println(e.getKey());
            }
        }
    }

    /*

    Added option to view top K terms

    */

    void showTopKTerms(int k, Map<String, Integer> wordMap) {
        for (Map.Entry<String, Integer> e : wordMap.entrySet()) {
            if (k == 0) return;
            System.out.println(e.getKey() + ": " + e.getValue());
            k--;
        }
    }

    void showTopKTermsNoVal(int k, Map<String, Integer> wordMap) {
        for (Map.Entry<String, Integer> e : wordMap.entrySet()) {
            if (k == 0) return;
            System.out.println(e.getKey());
            k--;
        }
    }

    /*

    Added sorting of terms

    */

    LinkedHashMap<String, Integer> sortMap(HashMap<String, Integer> map) {
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> option_a, Entry<String, Integer> option_b) {
                return option_b.getValue().compareTo(option_a.getValue());
            }
        });

        LinkedHashMap<String, Integer> newMap = new LinkedHashMap<String, Integer>();

        for (Entry<String, Integer> value : list)
            newMap.put(value.getKey(), value.getValue());

        return newMap;
    }
}
