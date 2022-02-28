package app.debit.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AppConfig {
    public static final String DEFAULT_ACCOUNT_PROPERTIES = "application.external.comments";

    private String url;
    private String path;
}
