import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task5 {

    private static final Logger logger =
            LoggerFactory.getLogger(Task5.class);

    public ValidationResult validate(Document doc) {

        try {

            // FIX: Validation failure should use IllegalArgumentException
            // instead of generic RuntimeException
            if (doc == null) {
                throw new IllegalArgumentException("Document is null");
            }

            String content = doc.extractContent();

            // FIX: Prevent NullPointerException if content is null
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("Empty content");
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // FIX: Use proper logging instead of printStackTrace
            // Expected validation failures logged as warning
            logger.warn("Validation failed: {}", e.getMessage());

            // FIX: Never return null to avoid downstream NullPointerException
            return ValidationResult.invalid(e.getMessage());

        } catch (Exception e) {

            // FIX: Unexpected exceptions logged properly with stack trace
            logger.error("Unexpected error during validation", e);

            return ValidationResult.invalid("Unexpected validation error");
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {

            try {

                ValidationResult r = validate(doc);

                // FIX: Prevent NullPointerException before calling isValid()
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {

                // FIX: Do not silently swallow exceptions
                logger.error("Batch validation failed for document", e);
            }
        }
    }

    private ValidationResult runValidationRules(String content) {
        return null;
    }

    private void saveResult(ValidationResult r) {
    }
}