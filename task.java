import java.util.HashMap;
import java.util.Random;




public class Main{

    public static void main(String args[]) {
        URLShortener u = new URLShortener(6, "www.tinyurl.com/");
        String urls[] = { "www.netlix.com/hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd", "www.razorpay.com/gggdgdggdg/hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd",
                "http://www.narvar.com/hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd", "www.reddit.com//hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd", "www.amazon.com/hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd",
                "www.leetcode.com/page1.php", "www.codingninjas.com/page2.php/hdhhdhhdhdjsjsjhshshsh/ggdgdgdggd"};

        for (int i = 0; i < urls.length; i++) {
            System.out.println("URL:" + urls[i] + "\tTiny: "
                    + u.shrinkURL(urls[i]) + "\tExpanded: "
                    + u.expandURL(u.shrinkURL(urls[i])));
        }
    }
}

 class URLShortener {
    private HashMap<String, String> keyMap;
    private HashMap<String, String> valueMap;
    private String domain;
    private char myChars[];
    private Random myRand;
    private int keyLength;

    //constructor
    URLShortener() {
        keyMap = new HashMap<String, String>();
        valueMap = new HashMap<String, String>();
        myRand = new Random();
        keyLength = 8;
        myChars = new char[62];
        domain = "http://abc.in";
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {
                j = i + 48;
            } else if (i > 9 && i <= 35) {
                j = i + 55;
            } else {
                j = i + 61;
            }
            myChars[i] = (char) j;
        }
    }

    URLShortener(int length, String newDomain) {
        this();
        this.keyLength = length;
        if (!newDomain.isEmpty()) {
            domain = newDomain;
        }
    }

    public String shrinkURL(String longURL) {
        String shortURL = "";
        if (validate_URL(longURL)) {
            if (valueMap.containsKey(longURL)) {
                shortURL = domain + "/" + valueMap.get(longURL);
            } else {
                shortURL = domain + "/" + getKey(longURL);
            }
        }
        return shortURL;
    }

    public String expandURL(String shortURL) {
        String longURL = "";
        String key = shortURL.substring(domain.length() + 1);
        longURL = keyMap.get(key);
        return longURL;
    }

    boolean validate_URL(String url) {
        return true;
    }
    private String getKey(String longURL) {
        String key;
        key = generateKey();
        keyMap.put(key, longURL);
        valueMap.put(longURL, key);
        return key;
    }

    private String generateKey() {
        String key = "";
        boolean flag = true;
        while (flag) {
            key = "";
            for (int i = 0; i <= keyLength; i++) {
                key += myChars[myRand.nextInt(62)];
            }
            if (!keyMap.containsKey(key)) {
                flag = false;
            }
        }
        return key;
    }

}
