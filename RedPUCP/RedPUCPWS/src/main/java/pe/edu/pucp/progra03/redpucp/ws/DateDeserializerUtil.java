package pe.edu.pucp.progra03.redpucp.ws;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
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

public class DateDeserializerUtil {

    public static ObjectMapper getObjectMapperWithDateHandling() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        SimpleModule dateFixModule = new SimpleModule("DateFixModule");
        dateFixModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
                String raw = p.getValueAsString();
                if (raw == null || raw.isBlank()) return null;

                String cleaned = raw.trim();
                
                try {
                    // CASO 1: Fecha con zona horaria entre corchetes: "2025-02-11T16:40:00Z[UTC]"
                    if (cleaned.contains("[") && cleaned.contains("]")) {
                        // Extraer la parte antes del corchete
                        String withoutBrackets = cleaned.replaceAll("\\[[^\\]]+\\]", "");
                        OffsetDateTime odt = OffsetDateTime.parse(withoutBrackets, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        return Date.from(odt.toInstant());
                    }
                    
                    // CASO 2: Solo fecha con Z: "2025-04-13Z"
                    else if (cleaned.matches("\\d{4}-\\d{2}-\\d{2}Z")) {
                        String dateOnly = cleaned.substring(0, 10);
                        LocalDate ld = LocalDate.parse(dateOnly);
                        return Date.from(ld.atStartOfDay(ZoneOffset.UTC).toInstant());
                    }
                    
                    // CASO 3: Fecha/hora con Z pero sin corchetes: "2025-02-11T16:40:00Z"
                    else if (cleaned.endsWith("Z") && cleaned.contains("T")) {
                        OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        return Date.from(odt.toInstant());
                    }
                    
                    // CASO 4: Fecha/hora sin Z (formato local)
                    else if (cleaned.contains("T")) {
                        // Intentar como OffsetDateTime primero
                        try {
                            OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                            return Date.from(odt.toInstant());
                        } catch (DateTimeParseException e) {
                            // Si falla, intentar como LocalDateTime
                            LocalDateTime ldt = LocalDateTime.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                            return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
                        }
                    }
                    
                    // CASO 5: Solo fecha sin Z: "2025-04-13"
                    else if (cleaned.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        LocalDate ld = LocalDate.parse(cleaned);
                        return Date.from(ld.atStartOfDay(ZoneOffset.UTC).toInstant());
                    }
                    
                    // CASO 6: Intentar parsing genérico como último recurso
                    else {
                        // Primero intentar OffsetDateTime
                        try {
                            OffsetDateTime odt = OffsetDateTime.parse(cleaned, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                            return Date.from(odt.toInstant());
                        } catch (DateTimeParseException e1) {
                            // Luego LocalDateTime
                            try {
                                LocalDateTime ldt = LocalDateTime.parse(cleaned, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                                return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
                            } catch (DateTimeParseException e2) {
                                // Finalmente LocalDate
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

        mapper.registerModule(dateFixModule);
        return mapper;
    }
}