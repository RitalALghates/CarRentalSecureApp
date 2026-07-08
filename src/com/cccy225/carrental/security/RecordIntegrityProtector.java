package com.cccy225.carrental.security;

public final class RecordIntegrityProtector {
    private RecordIntegrityProtector() {
    }

    public static String createChecksum(String... values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                builder.append("|");
            }
            builder.append(values[i] == null ? "" : values[i]);
        }
        return PasswordHasher.hash(builder.toString());
    }

    public static boolean matches(String checksum, String... values) {
        return createChecksum(values).equals(checksum);
    }
}
