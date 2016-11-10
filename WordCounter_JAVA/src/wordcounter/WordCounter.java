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
            if (args.length > 0)
                k = Integer.parseInt(args[0]);

            WordCounter wc = new WordCounter("test.txt");
            LinkedHashMap<String, Integer> wmap = wc.sortMap(wc.getTfs());

            //wc.showTerms(wmap);

            /*

            Added show terms by frequency

            */
            wc.showTopKTerms(k, wmap);

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
