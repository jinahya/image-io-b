package com.github.jinahya.imageio;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class JsonbTests {

    // -----------------------------------------------------------------------------------------------------------------
    static <R> R applyJsonb(final Function<? super Jsonb, ? extends R> function) {
        try {
            try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(Boolean.TRUE))) {
                return function.apply(jsonb);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <U, R> R applyJsonb(final BiFunction<? super Jsonb, ? super U, ? extends R> function,
                               final Supplier<? extends U> supplier) {
        return applyJsonb(v -> function.apply(v, supplier.get()));
    }

    static void acceptJsonb(final Consumer<? super Jsonb> consumer) {
        applyJsonb(v -> {
            consumer.accept(v);
            return null;
        });
    }

    static <U> void acceptJsonb(final BiConsumer<? super Jsonb, ? super U> consumer,
                                final Supplier<? extends U> supplier) {
        acceptJsonb(v -> consumer.accept(v, supplier.get()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JsonbTests() {
        super();
    }
}
