package org.ahocorasick.util;

import java.util.HashMap;
import java.util.Map;

public class AccentUtils {
    private static final String PLAIN_ASCII =
            "AaEeIiOoUu"    // grave
                    + "AaEeIiOoUuYy"  // acute
                    + "AaEeIiOoUuYy"  // circumflex
                    + "AaOoNn"        // tilde
                    + "AaEeIiOoUuYy"  // umlaut
                    + "Aa"            // ring
                    + "Cc"            // cedilla
                    + "OoUu"          // double acute
            ;

    private static final String UNICODE =
            "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
                    + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
                    + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
                    + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
                    + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF"
                    + "\u00C5\u00E5"
                    + "\u00C7\u00E7"
                    + "\u0150\u0151\u0170\u0171";

    private static final Map<Character, Character> convertUNICODEmap = new HashMap<Character, Character>();

    //Building of the map to be faster than indexOf
    static {
        for (int i = 0; i < UNICODE.length(); i++) {
            convertUNICODEmap.put(UNICODE.charAt(i), PLAIN_ASCII.charAt(i));
        }
    }

    /**
     * Unaccent a text
     * @param text to convert
     * @return text without accent
     */
    public static String unaccent(String text) {
        if (text == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(unaccentChar(text.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * Unaccent a character
     * @param c character to convert
     * @return a character
     */
    public static Character unaccentChar(Character c) {
        //use getOrDefault in Java 1.8 when
        Character res = convertUNICODEmap.get(c);
        if (res == null) {
            return c;
        } else {
            return res;
        }
    }
}