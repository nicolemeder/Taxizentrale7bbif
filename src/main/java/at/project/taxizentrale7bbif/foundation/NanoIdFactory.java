package at.project.taxizentrale7bbif.foundation;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class NanoIdFactory {
    public String randomNanoId(int size) {
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, size);
    }
}
