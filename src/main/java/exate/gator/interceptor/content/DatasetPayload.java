package exate.gator.interceptor.content;

import java.util.Objects;

/** Record for serializing the payload for sending DATASET requests to API-Gator. */
public record DatasetPayload(
    String manifestName,
    JobType jobType,
    ThirdPartyIdentiferPayload thirdPartyIdentifer,
    String snapshotDate,
    String dataOwningCountryCode,
    String countryCode,
    Integer dataUsageId,
    Boolean protectNullValues,
    String restrictedText,
    String dataSet,
    Boolean preserveStringLength,
    SqlType sqlType,
    String classificationModel

) {

    public record ThirdPartyIdentiferPayload (String thirdPartyName, Integer thirdPartyId){
        public ThirdPartyIdentiferPayload {
            if (Objects.isNull(thirdPartyName) && Objects.isNull(thirdPartyId)) {
                throw new IllegalArgumentException("one of thirdPartyName or thirdPartyId must not be null");
            }
        }
    }

    public enum JobType {
        DataMasking, Pseudonymise, Reconstruct, Restrict, Encrypt, Decrypt, Default;
    }

    public enum SqlType {
        Oracle, MySQL, PostgreSQL, SqlServer, H2, Dremio;
    }

    public enum DatasetType {
        JSON, XML, SQL;
    }

    /** Verifying properties marked as mandatory in API-Gator documentation. */
    public DatasetPayload {
        Objects.requireNonNull(manifestName);
        Objects.requireNonNull(jobType);
        Objects.requireNonNull(countryCode);
        Objects.requireNonNull(protectNullValues);
        Objects.requireNonNull(dataSet);
        Objects.requireNonNull(preserveStringLength);
    }
}