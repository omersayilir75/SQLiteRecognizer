import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TokenTypePairSerializer extends StdSerializer<TokenTypePair> {

    public TokenTypePairSerializer() {
        this(null);
    }

    public TokenTypePairSerializer(Class<TokenTypePair> t) {
        super(t);
    }

    @Override
    public void serialize(
            TokenTypePair value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("first", value.first);
        jgen.writeNumberField("second", value.second);
        jgen.writeEndObject();
    }
}
