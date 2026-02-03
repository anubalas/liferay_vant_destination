import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Class responsible for formatting and preprocessing incoming IoT data.
 */
public class DataPreprocessor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Validates and formats the incoming data.
     * @param data The incoming data as a JSON string.
     * @return A formatted JsonNode if valid, null otherwise.
     */
    public JsonNode preprocessData(String data) {
        if (isValidJson(data)) {
            // Here you can add any formatting logic if needed
            try {
                return objectMapper.readTree(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Validates if the incoming payload is a valid JSON format.
     * @param data The data to validate.
     * @return true if valid JSON, false otherwise.
     */
    private boolean isValidJson(String data) {
        try {
            objectMapper.readTree(data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}