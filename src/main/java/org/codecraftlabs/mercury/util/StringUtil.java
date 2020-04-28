package org.codecraftlabs.mercury.util;

import java.util.ArrayList;
import java.util.Collection;

public class StringUtil {
    public static Collection<String> splitStringChunks(String str, int size) {
        ArrayList<String> split = new ArrayList<>();
        for (int i = 0; i <= str.length() / size; i++) {
            split.add(str.substring(i * size, Math.min((i + 1) * size, str.length())));
        }
        return split;
    }
}
