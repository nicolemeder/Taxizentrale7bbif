/*package at.project.taxizentrale7bbif.service;
import at.project.taxizentrale7bbif.foundation.NanoIdFactory;
import at.project.taxizentrale7bbif.foundation.TokenSourceProvider;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenService {

    @Value("${hashid.salt:cheeseburger}")
    private String salt;

    private Hashids hashids;
    private final NanoIdFactory nanoIdFactory;
    public final int DEFAULT_NANO_ID_LENGTH = 8;
    private Collector<CharSequence, ?, String> joining = Collectors.joining("|", "[", "]");


    @PostConstruct
    private void initHashids() {hashids = new Hashids(salt);}

    public String createTokenFor(LocalDateTime timeStamp, TokenSourceProvider provider) {
        return createTokenFor(timeStamp, provider.getTokenRelevantFields()
                .stream()
                .map(Supplier::get)
                .collect(joining));
    }

    public String createTokenFor(LocalDateTime timeStamp, String... strings) {
        return createTokenFor(timeStamp, Arrays.stream(strings)
                .filter(s -> s != null)
                .collect(joining));
    }

    public String createTokenFor(LocalDateTime timeStamp, String string) {
        Long tokenSource = timeStamp.toEpochSecond(ZoneOffset.UTC) + string.hashCode();
        String token = hashids.encode(tokenSource);
        return token;
    }

    public String createNanoToken() {
        return createNanoToken(DEFAULT_NANO_ID_LENGTH);
    }

    public String createNanoToken(int size) { return nanoIdFactory.randomNanoId(size);}















}


*/






