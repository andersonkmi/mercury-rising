package org.codecraftlabs.mercury.util;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.min;

public class StringUtil {
    public static Collection<String> splitStringChunks(String str, int size) {
        ArrayList<String> split = new ArrayList<>();
        for (int i = 0; i <= str.length() / size; i++) {
            split.add(str.substring(i * size, min((i + 1) * size, str.length())));
        }
        return split;
    }
}
