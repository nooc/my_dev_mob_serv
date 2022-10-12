package airline;

import javax.annotation.Nonnull;

/**
 * Airport record with its code, name and connecting airports.
 * @param code Three letter airport code
 * @param name Airport name
 */
public record Airport(@Nonnull String code, @Nonnull String name) { }
