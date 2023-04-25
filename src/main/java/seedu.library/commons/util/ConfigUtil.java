package seedu.library.commons.util;

import seedu.library.commons.core.Config;
import seedu.library.commons.exceptions.DataConversionException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
