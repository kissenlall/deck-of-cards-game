package com.homework.game.card.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.homework.game.card.model.playingcard.Card;

import java.io.IOException;

public class PlayingCardSerializer extends StdSerializer<Card> {

    public PlayingCardSerializer() {
        super(Card.class);
    }

    public PlayingCardSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Card card, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("card");
        jsonGenerator.writeString(String.format("%s%s",card.getRank().getDescription(), card.getSuit().getValue()));
        jsonGenerator.writeEndObject();
    }
}
