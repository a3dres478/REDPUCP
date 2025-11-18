package pe.edu.pucp.progra03.redpucp.ws;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.Instant;

public class DateDeserializerUtil {

    public static ObjectMapper getObjectMapperWithDateHandling() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        SimpleModule dateFixModule = new SimpleModule("DateFixModule");
        
        // DESERIALIZER: Para recibir datos (de C# y otros)
        dateFixModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
                // Si es número (timestamp de C#)
                if (p.getCurrentToken().isNumeric()) {
                    long timestamp = p.getLongValue();
                    return new Date(timestamp);
                }
                
                // Si es string
                String raw = p.getValueAsString();
                if (raw == null || raw.isBlank()) return null;

                String cleaned = raw.trim();
                
                try {
                    // CASO 0: Timestamp numérico como string (desde C#)
                    try {
                        long timestamp = Long.parseLong(cleaned);
                        return new Date(timestamp);
                    } catch (NumberFormatException e) {
                        // No es un número, continuar
                    }
                    
                    // Resto de tus casos existentes...
                    // CASO 1: Fecha con zona horaria entre corchetes
                    if (cleaned.contains("[") && cleaned.contains("]")) {
                        String withoutBrackets = cleaned.replaceAll("\\[[^\\]]+\\]", "");
                        OffsetDateTime odt = OffsetDateTime.parse(withoutBrackets, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        return Date.from(odt.toInstant());
                    }
                    
                    // CASO 2: Solo fecha con Z
                    else if (cleaned.matches("\\d{4}-\\d{2}-\\d{2}Z")) {
                        String dateOnly = cleaned.substring(0, 10);
                        LocalDate ld = LocalDate.parse(dateOnly);
                        return Date.from(ld.atStartOfDay(ZoneOffset.UTC).toInstant());
                    }
                    
                    // CASO 3: Fecha/hora con Z
                    else if (cleaned.endsWith("Z") && cleaned.contains("T")) {
                        OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        return Date.from(odt.toInstant());
                    }
                    
                    // CASO 4: Fecha/hora sin Z
                    else if (cleaned.contains("T")) {
                        try {
                            OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                            return Date.from(odt.toInstant());
                        } catch (DateTimeParseException e) {
                            LocalDateTime ldt = LocalDateTime.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                            return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
                        }
                    }
                    
                    // CASO 5: Solo fecha
                    else if (cleaned.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        LocalDate ld = LocalDate.parse(cleaned);
                        return Date.from(ld.atStartOfDay(ZoneOffset.UTC).toInstant());
                    }
                    
                    else {
                        // Intentar parsing genérico
                        try {
                            OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                            return Date.from(odt.toInstant());
                        } catch (DateTimeParseException e1) {
                            try {
                                LocalDateTime ldt = LocalDateTime.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                                return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
                            } catch (DateTimeParseException e2) {
                                try {
                                    LocalDate ld = LocalDate.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE);
                                    return Date.from(ld.atStartOfDay(ZoneOffset.UTC).toInstant());
                                } catch (DateTimeParseException e3) {
                                    throw new IOException("Formato de fecha no soportado: " + raw, e3);
                                }
                            }
                        }
                    }
                    
                } catch (Exception e) {
                    throw new IOException("Error procesando fecha: " + raw, e);
                }
            }
        });

        // SERIALIZER: Para enviar datos al REST (siempre en formato ISO)
        dateFixModule.addSerializer(Date.class, new JsonSerializer<Date>() {
            @Override
            public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    gen.writeNull();
                } else {
                    // Siempre enviar en formato ISO al REST
                    Instant instant = value.toInstant();
                    OffsetDateTime odt = instant.atOffset(ZoneOffset.UTC);
                    String formatted = odt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    gen.writeString(formatted);
                }
            }
        });

        mapper.registerModule(dateFixModule);
        return mapper;
    }
}